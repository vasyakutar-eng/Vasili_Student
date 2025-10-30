package orders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private String orderNumber;
    private LocalDateTime createdAt;
    private List<OrderItem> items = new ArrayList<>();
    private OrderStatus status = OrderStatus.NEW;

    public Order() {}
    public Order(String orderNumber) { this.orderNumber = orderNumber; this.createdAt = LocalDateTime.now(); }

    public String getOrderNumber() { return orderNumber; }
    public void setOrderNumber(String orderNumber) { this.orderNumber = orderNumber; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }

    public void addItem(OrderItem item) { items.add(item); }
    public double totalAmount() { return items.stream().mapToDouble(OrderItem::lineTotal).sum(); }
    public void changeStatus(OrderStatus newStatus) { setStatus(newStatus); }

    public String toString() {
        return "Order{num='" + orderNumber + "', createdAt=" + createdAt +
                ", status=" + status + ", total=" + String.format("%.2f", totalAmount()) + "}";
    }
}
