package preti.spark.stock;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.codehaus.jackson.map.ObjectMapper;
import org.joda.time.DateTime;

import preti.spark.stock.reporting.BalanceReport;
import preti.spark.stock.reporting.FileReport;
import preti.spark.stock.reporting.OperationsReport;
import preti.spark.stock.reporting.StockReport;
import preti.spark.stock.run.DonchianStrategyOptimizer;
import preti.spark.stock.run.StocksRepository;
import preti.spark.stock.system.TradeSystemExecution;
import preti.stock.analysismodel.donchian.DonchianModel;
import preti.stock.coremodel.Stock;
import preti.stock.system.TradingStrategy;
import preti.stock.system.TradingStrategyImpl;

public class StockAnalysis {
	private static final Log log = LogFactory.getLog(StockAnalysis.class);
	private static JavaSparkContext sc;

	public static void main(String[] args) throws IOException, ParseException {
		if (args.length != 1) {
			System.out.println("Must specify config file");
			System.exit(-1);
		}

		ConfigContext configContext = new ObjectMapper().readValue(new File(args[0]), ConfigContext.class);

		SparkConf conf = new SparkConf();
		sc = new JavaSparkContext(conf);

		log.info("Loading stock data ...");
		StocksRepository stocksRepository = new StocksRepository(sc);
		List<Stock> stocks = stocksRepository.loadStocks(configContext.getStockHistoryFile(),
				configContext.getStockCodesToAnalyze());

		final double accountInitialPosition = configContext.getAccountInitialValue();
		final int trainingSize = configContext.getTrainingSizeInMonths();
		final int windowSize = configContext.getWindowSizeInMonths();

		Date initialDate = configContext.getInitialDate();
		Date finalDate = configContext.getFinalDate();
		TradeSystemExecution system = new TradeSystemExecution(stocks, accountInitialPosition, accountInitialPosition,
				null);

		DateTime currentInitialDate = new DateTime(initialDate.getTime()).plusMonths(trainingSize);
		DateTime currentFinalDate = currentInitialDate.plusMonths(windowSize);
		DonchianStrategyOptimizer optimizer = new DonchianStrategyOptimizer(sc);
		Map<Long, TradingStrategy> optimzedStrategies = new HashMap<>();
		while (currentFinalDate.isBefore(finalDate.getTime() + 1)) {
			Map<Long, TradingStrategy> newStrategies = new HashMap<>();
			for (Stock s : stocks) {
				DonchianModel optimizationResult = optimizer.optimizeParameters(s, accountInitialPosition,
						currentInitialDate.minusMonths(trainingSize).toDate(), currentInitialDate.minusDays(1).toDate(),
						configContext.getMinDochianEntryValue(), configContext.getMaxDonchianEntryValue(),
						configContext.getMinDonchianExitValue(), configContext.getMaxDonchianExitValue(),
						configContext.getRiskRate());
				if (optimizationResult != null) {
					newStrategies.put(s.getId(),
							new TradingStrategyImpl(s, 0, optimizationResult.getEntryDonchianSize(),
									optimizationResult.getExitDonchianSize(), optimizationResult.getRiskRate()));
				}
			}
			log.error("Analyzing from " + currentInitialDate + " to " + currentFinalDate + " with training data from "
					+ currentInitialDate.minusMonths(trainingSize) + " to " + currentInitialDate.minusDays(1));
			optimzedStrategies = mergeStrategies(optimzedStrategies, newStrategies);

			system.setTradingStrategies(optimzedStrategies);
			system.analyzeStocks(currentInitialDate.toDate(), currentFinalDate.toDate());
			log.info("Analyze finished.");

			currentInitialDate = currentInitialDate.plusMonths(windowSize);
			currentFinalDate = currentFinalDate.plusMonths(windowSize);
		}

		System.out.println("Final balance before closing open trades: " + system.getAccountBalance());
		system.closeAllOpenTrades(finalDate);
		System.out.println("Final balance after closing open trades: " + system.getAccountBalance());

		log.info("Generating reports ...");
		new StockReport(system, "localhost", 5001, "stock").generate();
		new OperationsReport(system, "localhost", 5002, "operations").generate();
		new BalanceReport(system, "localhost", 5003, "balance").generate();
		new FileReport(system, "/tmp", configContext).generate();
		log.info("Reports generated");

	}

	private static Map<Long, TradingStrategy> mergeStrategies(Map<Long, TradingStrategy> oldStrategies,
			Map<Long, TradingStrategy> newStrategies) {
		Map<Long, TradingStrategy> mergedStrategies = new HashMap<>(newStrategies);

		for (Long stockId : oldStrategies.keySet()) {
			if (!newStrategies.containsKey(stockId)) {
				TradingStrategyImpl oldStrategy = (TradingStrategyImpl) oldStrategies.get(stockId);
				mergedStrategies.put(stockId, new TradingStrategyImpl(oldStrategy.getStock(), oldStrategy.getModelId(),
						0, oldStrategy.getExitDonchianSize(), oldStrategy.getRiskRate()));
			}
		}

		return mergedStrategies;
	}
}
