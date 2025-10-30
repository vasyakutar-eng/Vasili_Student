import orders.*;
import java.util.Scanner;

/** Демонстрация ЛР №3: HashTable (цепочки) + заказы интернет‑магазина. */
public class Lab3App {
    private static final Scanner SC = new Scanner(System.in);
    private static final OrderTable ORDERS = new OrderTable();

    public static void main(String[] args) {
        System.out.println("ЛР №3 — Хэш‑таблица (цепочки). Вариант: заказы интернет‑магазина.");
        while (true) {
            showMenu();
            String cmd = SC.nextLine().trim();
            switch (cmd) {
                case "1" -> insertOrder();
                case "2" -> findOrder();
                case "3" -> deleteOrder();
                case "4" -> changeStatus();
                case "5" -> listOrders();
                case "6" -> stats();
                case "0" -> { System.out.println("Выход."); return; }
                default -> System.out.println("Неизвестная команда.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nМеню:");
        System.out.println("1 — Вставить/обновить заказ");
        System.out.println("2 — Найти заказ по номеру");
        System.out.println("3 — Удалить заказ по номеру");
        System.out.println("4 — Изменить статус заказа");
        System.out.println("5 — Список всех заказов");
        System.out.println("6 — Размер/пустота");
        System.out.println("0 — Выход");
        System.out.print("Выбор: ");
    }

    private static void insertOrder() {
        System.out.print("Номер заказа: ");
        String num = SC.nextLine().trim();
        if (num.isEmpty()) { System.out.println("Пустой номер."); return; }
        Order order = ORDERS.get(num);
        if (order == null) order = new Order(num);
        while (true) {
            System.out.println("Добавить товар? y/n");
            String yn = SC.nextLine().trim().toLowerCase();
            if (!yn.equals("y")) break;
            System.out.print("SKU: ");
            String sku = SC.nextLine().trim();
            System.out.print("Название: ");
            String title = SC.nextLine().trim();
            System.out.print("Количество: ");
            int qty = readInt(1, 10000);
            System.out.print("Цена за ед.: ");
            double price = readDouble(0, 1_000_000);
            order.addItem(new OrderItem(sku, title, qty, price));
            System.out.printf("Позиция добавлена. Итого: %.2f%n", order.totalAmount());
        }
        ORDERS.put(order);
        System.out.println("Сохранено: " + order);
    }

    private static void findOrder() {
        System.out.print("Номер заказа: ");
        String num = SC.nextLine().trim();
        Order o = ORDERS.get(num);
        if (o == null) System.out.println("Не найден.");
        else {
            System.out.println(o);
            o.getItems().forEach(i -> System.out.println("  - " + i));
        }
    }

    private static void deleteOrder() {
        System.out.print("Номер заказа: ");
        String num = SC.nextLine().trim();
        Order removed = ORDERS.remove(num);
        System.out.println(removed == null ? "Не найден." : "Удалён: " + removed);
    }

    private static void changeStatus() {
        System.out.print("Номер заказа: ");
        String num = SC.nextLine().trim();
        System.out.print("Новый статус (NEW, PAID, SHIPPED, DELIVERED, CANCELLED): ");
        String s = SC.nextLine().trim().toUpperCase();
        try {
            OrderStatus st = OrderStatus.valueOf(s);
            boolean ok = ORDERS.changeStatus(num, st);
            System.out.println(ok ? "Статус изменён." : "Заказ не найден.");
        } catch (IllegalArgumentException e) {
            System.out.println("Некорректный статус.");
        }
    }

    private static void listOrders() {
        if (ORDERS.isEmpty()) { System.out.println("Нет заказов."); return; }
        ORDERS.forEach(o -> System.out.println(o));
    }

    private static void stats() {
        System.out.printf("size=%d, isEmpty=%s%n", ORDERS.size(), ORDERS.isEmpty());
    }

    private static int readInt(int min, int max) {
        while (true) {
            try {
                int v = Integer.parseInt(SC.nextLine().trim());
                if (v < min || v > max) throw new NumberFormatException();
                return v;
            } catch (Exception e) {
                System.out.print("Введите целое "+min+".."+max+": ");
            }
        }
    }

    private static double readDouble(double min, double max) {
        while (true) {
            try {
                double v = Double.parseDouble(SC.nextLine().trim().replace(',', '.'));
                if (v < min || v > max) throw new NumberFormatException();
                return v;
            } catch (Exception e) {
                System.out.print("Введите число "+min+".."+max+": ");
            }
        }
    }
}
