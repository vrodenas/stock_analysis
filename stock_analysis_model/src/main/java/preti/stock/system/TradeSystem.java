package preti.stock.system;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import preti.stock.coremodel.Stock;
import preti.stock.coremodel.Trade;

public class TradeSystem {
	private static final Log log = LogFactory.getLog(TradeSystem.class);

	private double accountBalance;
	private Map<Long, Trade> openTrades;
	private Map<Long, TradingStrategy> tradingStrategies;
	private List<Stock> stocksToAnalyse;
	private Map<Long, Trade> closedTrades;

	public TradeSystem(Collection<Trade> trades, Collection<Stock> stocks, Map<Long, TradingStrategy> strategies,
			double balance) {
		this.accountBalance = balance;
		this.tradingStrategies = strategies;

		this.stocksToAnalyse = new ArrayList<>();
		this.stocksToAnalyse.addAll(stocks);
		this.stocksToAnalyse.sort(new Comparator<Stock>() {

			@Override
			public int compare(Stock o1, Stock o2) {
				return o1.getCode().compareTo(o2.getCode());
			}
		});

		openTrades = new HashMap<>();
		for (Trade t : trades) {
			if (!t.isOpen())
				throw new IllegalStateException();

			openTrades.put(t.getStockId(), t);
		}

		this.closedTrades = new HashMap<>();
	}

	public Map<Long, Trade> getOpenTrades() {
		return openTrades;
	}

	public Collection<Trade> getAllOpenTrades() {
		return openTrades.values();
	}

	public double getAccountBalance() {
		return accountBalance;
	}

	public Map<Long, Trade> getClosedTrades() {
		return closedTrades;
	}

	private Trade closeTrade(Trade trade, Date d, double sellValue) {
		log.info(String.format("Closing trade for stock %s at date %s", trade.getStockId(), d));

		if (!trade.isOpen())
			throw new IllegalArgumentException(
					String.format("No open trade to close for stock %s.", trade.getStockId()));

		trade.close(d, sellValue);
		this.accountBalance += trade.getSize() * trade.getSellValue();
		openTrades.remove(trade.getStockId());
		closedTrades.put(trade.getStockId(), trade);
		return trade;
	}

	private Trade openNewTrade(Stock stock, Date d) {
		log.info(String.format("Opening new trade for stock %s at date %s", stock.getCode(), d));

		double openTradesValue = calculateOpenTradesValue(d);
		TradingStrategy strategy = this.tradingStrategies.get(stock.getId());
		double size = strategy.calculatePositionSize(d, openTradesValue + accountBalance);
		if (size < 1) {
			log.info("Postion size<1: not enough balance to enter position");
			return null;
		}

		double stockValue = stock.getCloseValueAtDate(d);
		while ((size * stockValue) > this.accountBalance && size > 1) {
			size--;
		}
		if (size < 1) {
			log.warn(String.format("Not enough balance to enter position for stock %s at date %d", stock.getCode(), d));
			return null;
		}

		if (isInOpenPosition(stock)) {
			throw new IllegalArgumentException(
					String.format("Can't open a new trade for stock %s with one already opened.", stock.getCode()));
		}
		Trade newTrade = new Trade(stock, strategy.getModelId(), size, strategy.calculateStopLossPoint(d), d,
				stockValue);
		this.accountBalance -= newTrade.getSize() * newTrade.getBuyValue();
		openTrades.put(newTrade.getStockId(), newTrade);
		return newTrade;
	}

	private boolean isInOpenPosition(Stock s) {
		return openTrades.containsKey(s.getId());
	}

	public List<Trade> analyzeStocks(Date recomendationDate) {
		List<Trade> updatedTrades = new ArrayList<>();

		for (Stock stock : stocksToAnalyse) {
			log.info(String.format("Analysing stock %s at date %s", stock.getCode(), recomendationDate));
			if (!stock.hasHistoryAtDate(recomendationDate)) {
				log.info("No data to analyze");
				continue;
			}

			TradingStrategy strategy = this.tradingStrategies.get(stock.getId());
			if (strategy == null) {
				continue;
			}

			if (isInOpenPosition(stock)) {
				Trade openTrade = openTrades.get(stock.getId());
				boolean profittable = openTrade.isProfitable(recomendationDate);
				if ((profittable && strategy.exitPosition(recomendationDate))
						|| (!profittable && openTrade.hasReachedStopPosition(recomendationDate))) {
					Trade t = closeTrade(openTrade, recomendationDate, stock.getCloseValueAtDate(recomendationDate));
					if (t != null) {
						updatedTrades.add(t);
					}
				}

			} else {
				if (strategy.enterPosition(recomendationDate)) {
					Trade t = openNewTrade(stock, recomendationDate);
					if (t != null) {
						updatedTrades.add(t);
					}
				}
			}
		}

		return updatedTrades;
	}

	private double calculateOpenTradesValue(Date d) {
		double value = 0;
		for (Trade t : getAllOpenTrades()) {
			if (!t.isOpen())
				continue;

			value += t.getTotalValue(d);
		}

		return value;
	}

	public void closeAllOpenTrades(Date d) {
		Long[] stockIds = openTrades.keySet().toArray(new Long[] {});
		for (Long stockId : stockIds) {
			Trade t = openTrades.get(stockId);
			if (t.isOpen())
				closeTrade(t, d, t.getStock().getCloseValueAtDate(d));
		}
	}

}