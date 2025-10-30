package orders;

import hashtable.HashTable;

public class OrderTable {
    private final HashTable<String, Order> table = new HashTable<>();

    public void put(Order order) { table.put(order.getOrderNumber(), order); }
    public Order get(String orderNumber) { return table.get(orderNumber); }
    public Order remove(String orderNumber) { return table.remove(orderNumber); }
    public boolean changeStatus(String orderNumber, OrderStatus newStatus) {
        Order o = table.get(orderNumber);
        if (o == null) return false;
        o.changeStatus(newStatus);
        return true;
    }
    public int size() { return table.size(); }
    public boolean isEmpty() { return table.isEmpty(); }
    public void forEach(java.util.function.Consumer<Order> consumer) { table.forEach((k,v)->consumer.accept(v)); }
}
