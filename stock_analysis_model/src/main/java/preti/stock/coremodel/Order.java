package preti.stock.coremodel;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

@SuppressWarnings("serial")
public class Order implements Serializable {
    private long orderId;
    private OrderType type;
    private long accountId;
    private long stockId;
    private long modelId;
    private double size;
    private Date creationDate;
    private double value;
    private double stopPos;

    private Stock stock;

    public Order() {

    }

    public Order(long orderId, OrderType type, long accountId, long stockId, long modelId, double size, Date date,
            double value, double stopPos) {
        this.orderId = orderId;
        this.type = type;
        this.accountId = accountId;
        this.stockId = stockId;
        this.modelId = modelId;
        this.size = size;
        this.creationDate = date;
        this.value = value;
        this.stopPos = stopPos;
    }

    private static Order createOrder(Stock stock, OrderType type, long accountId, long modelId, double size, Date date,
            double value, double stopPos) {
        Order order = new Order(0, type, accountId, stock.getId(), modelId, size, date, value, stopPos);
        order.applyStock(stock);
        return order;
    }

    public static Order createBuyOrder(long orderId, long accountId, long stockId, long modelId, double size, Date date,
            double value, double stopPos) {
        return new Order(orderId, OrderType.BUY, accountId, stockId, modelId, size, date, value, stopPos);
    }

    public static Order createBuyOrder(Stock stock, long accountId, long modelId, double size, Date date, double value,
            double stopPos) {
        return createOrder(stock, OrderType.BUY, accountId, modelId, size, date, value, stopPos);
    }

    public static Order createSellOrder(long orderId, long accountId, long stockId, long modelId, double size,
            Date date, double value) {
        return new Order(orderId, OrderType.SELL, accountId, stockId, modelId, size, date, value, 0);
    }

    public static Order createSellOrder(Stock stock, long accountId, long modelId, double size, Date date,
            double value) {
        return createOrder(stock, OrderType.SELL, accountId, modelId, size, date, value, 0);
    }

    public void applyStock(Stock s) {
        this.stock = s;
    }

    @JsonIgnore
    public Stock getStock() {
        return this.stock;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public OrderType getType() {
        return type;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    @JsonIgnore
    public boolean isBuyOrder() {
        return type == OrderType.BUY;
    }

    @JsonIgnore
    public boolean isSellOrder() {
        return type == OrderType.SELL;
    }

    public long getAccountId() {
        return accountId;
    }

    public void setAccountId(long accountId) {
        this.accountId = accountId;
    }

    public long getStockId() {
        return stockId;
    }

    public void setStockId(long stockId) {
        this.stockId = stockId;
    }

    public long getModelId() {
        return modelId;
    }

    public void setModelId(long modelId) {
        this.modelId = modelId;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public double getValue() {
        return value;
    }

    public double getStopPos() {
        return stopPos;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setStopPos(double stopPos) {
        this.stopPos = stopPos;
    }

}
