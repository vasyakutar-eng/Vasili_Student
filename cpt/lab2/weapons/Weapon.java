package weapons;

import java.util.Objects;

/** Абстрактный базовый класс: Оружие (инкапсуляция, абстракция, перегрузка). */
public abstract class Weapon {

    /** Статический счётчик созданных объектов Weapon и наследников. */
    private static int totalCreated = 0;

    private String name;        // название
    private double weight;      // вес, кг
    private int durability;     // прочность 0..100
    private int baseDamage;     // базовый урон

    /** Конструктор по умолчанию. */
    public Weapon() {
        this("Безымянное оружие", 1.0, 100, 1);
    }

    /** Полный конструктор. */
    public Weapon(String name, double weight, int durability, int baseDamage) {
        this.name = name;
        this.weight = weight;
        this.durability = clamp(durability, 0, 100);
        this.baseDamage = Math.max(0, baseDamage);
        totalCreated++;
    }

    /** Абстрактный тип оружия (для описания). */
    public abstract String type();

    /** Переопределяемый метод атаки. Возвращает нанесённый урон. */
    public abstract int attack();

    /** Перегрузка: атака с коэффициентом мощности (0..200%). */
    public int attack(int powerPercent) {
        int base = this.attack(); // полиморфизм: вызовется реализация подкласса
        double k = clamp(powerPercent, 0, 200) / 100.0;
        return (int) Math.round(base * k);
    }

    /** Ремонт увеличивает прочность. */
    public void repair(int amount) {
        setDurability(clamp(durability + amount, 0, 100));
    }

    protected void consumeDurability(int amount) {
        setDurability(clamp(durability - amount, 0, 100));
    }

    protected int clamp(int v, int min, int max) {
        return Math.max(min, Math.min(max, v));
    }

    // --------- Геттеры/сеттеры ---------
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getWeight() { return weight; }
    public void setWeight(double weight) { this.weight = Math.max(0.1, weight); }

    public int getDurability() { return durability; }
    public void setDurability(int durability) { this.durability = clamp(durability, 0, 100); }

    public int getBaseDamage() { return baseDamage; }
    public void setBaseDamage(int baseDamage) { this.baseDamage = Math.max(0, baseDamage); }

    public static int getTotalCreated() { return totalCreated; }

    /** Описание. */
    public String description() {
        return String.format("%s [%s]: вес=%.2fкг, прочность=%d%%, баз.урон=%d",
                getName(), type(), getWeight(), getDurability(), getBaseDamage());
    }

    @Override
    public String toString() {
        return description();
    }
}
