package preti.stock.coremodel;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@SuppressWarnings("serial")
@JsonIgnoreProperties(value = { "profitable" }, ignoreUnknown = true)
public class Trade implements Serializable {
    private long id;
    private long stockId;
    private long accountId;
    private double size;
    private double stopPos;
    private long buyOrderId;
    private long sellOrderId;
    private Date buyDate;
    private Date sellDate;
    private double buyValue;
    private double sellValue;

    private Stock stock;

    public Trade() {

    }

    public Trade(Stock stock, long accountId, double size, double stopPos, long buyOrderId, Date buyDate,
            double buyValue) {
        this(stock.getId(), accountId, size, stopPos, buyOrderId, buyDate, buyValue);
        applyStock(stock);
    }

    public Trade(long stockId, long accountId, double size, double stopPos, long buyOrderId, Date buyDate,
            double buyValue) {
        this(0, stockId, accountId, size, stopPos, buyOrderId, 0, buyDate, null, buyValue, 0);
    }

    public Trade(long id, long stockId, long accountId, double size, double stopPos, long buyOrderId, Date buyDate,
            double buyValue) {
        this(id, stockId, accountId, size, stopPos, buyOrderId, 0, buyDate, null, buyValue, 0);
    }

    public Trade(long id, long stockId, long accountId, double size, double stopPos, long buyOrderId, long sellOrderId,
            Date buyDate, Date sellDate, double buyValue, double sellValue) {
        super();
        this.id = id;
        this.stockId = stockId;
        this.accountId = accountId;
        this.size = size;
        this.stopPos = stopPos;
        this.buyOrderId = buyOrderId;
        this.sellOrderId = sellOrderId;
        this.buyDate = buyDate;
        this.sellDate = sellDate;
        this.buyValue = buyValue;
        this.sellValue = sellValue;
    }

    public void applyStock(Stock s) {
        this.stock = s;
    }

    @JsonIgnore
    public Stock getStock() {
        return this.stock;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getStockId() {
        return stockId;
    }

    public void setStockId(long stockId) {
        this.stockId = stockId;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public void setStopPos(double stopPos) {
        this.stopPos = stopPos;
    }

    public void setBuyOrderId(long buyOrderId) {
        this.buyOrderId = buyOrderId;
    }

    public void setSellOrderId(long sellOrderId) {
        this.sellOrderId = sellOrderId;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }

    public void setSellDate(Date sellDate) {
        this.sellDate = sellDate;
    }

    public double getSize() {
        return size;
    }

    public double getStopPos() {
        return stopPos;
    }

    public long getBuyOrderId() {
        return buyOrderId;
    }

    public long getSellOrderId() {
        return sellOrderId;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public Date getSellDate() {
        return sellDate;
    }

    public void setBuyValue(double buyValue) {
        this.buyValue = buyValue;
    }

    public void setSellValue(double sellValue) {
        this.sellValue = sellValue;
    }

    public boolean isOpen() {
        return sellDate == null;
    }

    public boolean isOpen(Date d) {
        return (buyDate != null && sellDate != null && d.compareTo(buyDate) >= 0 && d.compareTo(sellDate) < 0);
    }

    public void close(long orderId, Date d, double sellValue) {
        this.sellOrderId = orderId;
        this.sellDate = d;
        this.sellValue = sellValue;
    }

    public double getProfit(Date d) {
        if (buyDate == null) {
            throw new IllegalArgumentException("Trade invalid: buyDate is empty.");
        }

        if (d == null && isOpen()) {
            throw new IllegalArgumentException("Trade not closed and no date informed to calculate profits.");
        }

        if (d != null) {
            return size * (stock.getCloseValueAtDate(d) - buyValue);
        } else {
            return size * (sellValue - buyValue);
        }
    }

    @JsonIgnore
    public double getProfit() {
        return getProfit(null);
    }

    @JsonIgnore
    public boolean isProfitable(Date d) {
        return getProfit(d) > 0;
    }

    @JsonIgnore
    public boolean isProfitable() {
        return getProfit(null) > 0;
    }

    public double getBuyValue() {
        return this.buyValue;
    }

    public double getSellValue() {
        return this.sellValue;
    }

    public boolean hasReachedStopPosition(Date d) {
        if (!isOpen())
            throw new IllegalArgumentException("Trade is not opened.");

        return stock.getCloseValueAtDate(d) <= stopPos;
    }

    public double getTotalValue(Date d) {
        return stock.getCloseValueAtDate(d) * size;
    }

    public String toString() {
        if (isOpen()) {
            return String.format("stockId=%s size=%s buyValue=%s stopPos=%s buyOrderId=%s buy=Date%s", stockId, size,
                    getBuyValue(), stopPos, buyOrderId, buyDate);
        } else {
            return String.format(
                    "stockId=%s size=%s buyValue=%s stopPos=%s buyOrderId=%s sellOrderId=%s buy=Date%s sellDate=%s sellValue=%s",
                    stockId, size, getBuyValue(), stopPos, buyOrderId, sellOrderId, buyDate, sellDate, sellValue);
        }

    }

}
