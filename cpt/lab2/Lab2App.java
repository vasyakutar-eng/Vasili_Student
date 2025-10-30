import weapons.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Демонстрация Лабораторной №2: иерархия Оружия + принципы ООП. */
public class Lab2App {
    private static final Scanner SC = new Scanner(System.in);
    private static final List<Weapon> ARMORY = new ArrayList<>(); // полиморфная коллекция

    public static void main(String[] args) {
        System.out.println("ЛР №2 — ООП. Базовый класс: Оружие. Потомки: Меч, Лук, Волшебная палочка.");
        while (true) {
            showMenu();
            String cmd = SC.nextLine().trim();
            switch (cmd) {
                case "1" -> createSword();
                case "2" -> createBow();
                case "3" -> createWand();
                case "4" -> listWeapons();
                case "5" -> attackAll();
                case "6" -> repairByIndex();
                case "7" -> showCounters();
                case "0" -> { System.out.println("Выход."); return; }
                default -> System.out.println("Неизвестная команда.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\nМеню:");
        System.out.println("1 — Создать Меч");
        System.out.println("2 — Создать Лук");
        System.out.println("3 — Создать Волшебную палочку");
        System.out.println("4 — Список оружия");
        System.out.println("5 — Атаковать всеми (полиморфизм)");
        System.out.println("6 — Отремонтировать оружие по индексу");
        System.out.println("7 — Показать счётчики созданных объектов");
        System.out.println("0 — Выход");
        System.out.print("Выбор: ");
    }

    private static void createSword() {
        System.out.print("Название меча: ");
        String name = SC.nextLine();
        System.out.print("Вес (кг): ");
        double weight = readDouble(0.1, 100);
        System.out.print("Базовый урон: ");
        int dmg = readInt(1, 1000);
        System.out.print("Длина клинка (см): ");
        double blade = readDouble(1, 300);
        System.out.print("Острота 0..100: ");
        int sharp = readInt(0, 100);
        System.out.print("Стиль (строка): ");
        String style = SC.nextLine();
        Weapon w = new Sword(name, weight, 100, dmg, blade, sharp, style);
        ARMORY.add(w);
        System.out.println("Создано: " + w);
    }

    private static void createBow() {
        System.out.print("Название лука: ");
        String name = SC.nextLine();
        System.out.print("Вес (кг): ");
        double weight = readDouble(0.1, 50);
        System.out.print("Базовый урон: ");
        int dmg = readInt(1, 1000);
        System.out.print("Макс. дальность (м): ");
        int range = readInt(10, 1000);
        System.out.print("Сила натяжения: ");
        int draw = readInt(5, 200);
        System.out.print("Стрел при старте: ");
        int arrows = readInt(0, 1000);
        Weapon w = new Bow(name, weight, 100, dmg, range, draw, arrows);
        ARMORY.add(w);
        System.out.println("Создано: " + w);
    }

    private static void createWand() {
        System.out.print("Название палочки: ");
        String name = SC.nextLine();
        System.out.print("Вес (кг): ");
        double weight = readDouble(0.1, 10);
        System.out.print("Базовый урон: ");
        int dmg = readInt(1, 1000);
        System.out.print("Стоимость маны: ");
        int manaCost = readInt(0, 100);
        System.out.print("Тип кристалла: ");
        String crystal = SC.nextLine();
        System.out.print("Запас маны: ");
        int mana = readInt(0, 1000);
        Weapon w = new MagicWand(name, weight, 100, dmg, manaCost, crystal, mana);
        ARMORY.add(w);
        System.out.println("Создано: " + w);
    }

    private static void listWeapons() {
        if (ARMORY.isEmpty()) { System.out.println("Пока ничего нет."); return; }
        for (int i = 0; i < ARMORY.size(); i++) {
            System.out.printf("[%d] %s%n", i, ARMORY.get(i));
        }
    }

    private static void attackAll() {
        if (ARMORY.isEmpty()) { System.out.println("Пока ничего нет."); return; }
        System.out.println("Атака всеми оружиями...");
        for (Weapon w : ARMORY) {
            int dmg = w.attack(); // полиморфный вызов
            System.out.printf("%s нанёс %d урона. Ост.прочность=%d%%%n",
                    w.getName(), dmg, w.getDurability());
        }
    }

    private static void repairByIndex() {
        if (ARMORY.isEmpty()) { System.out.println("Пока ничего нет."); return; }
        listWeapons();
        System.out.print("Введите индекс для ремонта: ");
        int idx = readInt(0, ARMORY.size() - 1);
        System.out.print("На сколько восстановить прочность (1..100): ");
        int val = readInt(1, 100);
        Weapon w = ARMORY.get(idx);
        w.repair(val);
        System.out.println("После ремонта: " + w);
    }

    private static void showCounters() {
        System.out.printf("Всего создано оружия (включая наследников): %d%n", Weapon.getTotalCreated());
        System.out.printf("Мечей: %d, Луков: %d, Палочек: %d%n",
                Sword.getCreatedCount(), Bow.getCreatedCount(), MagicWand.getCreatedCount());
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
