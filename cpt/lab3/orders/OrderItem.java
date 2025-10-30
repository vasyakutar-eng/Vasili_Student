package orders;

public class OrderItem {
    private String sku;
    private String title;
    private int quantity;
    private double price;

    public OrderItem() {}
    public OrderItem(String sku, String title, int quantity, double price) {
        this.sku = sku; this.title = title; setQuantity(quantity); setPrice(price);
    }

    public String getSku() { return sku; }
    public void setSku(String sku) { this.sku = sku; }
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = Math.max(1, quantity); }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = Math.max(0.0, price); }
    public double lineTotal() { return quantity * price; }
    public String toString() { return String.format("%s x%d (%.2f) = %.2f", title, quantity, price, lineTotal()); }
}
