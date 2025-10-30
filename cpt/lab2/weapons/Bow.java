package weapons;

/** Конкретный класс: Лук. */
public class Bow extends RangedWeapon {

    private int drawWeight;     // сила натяжения
    private int arrowsInQuiver; // кол-во стрел

    private static int createdCount = 0;

    public Bow() {
        this("Лук", 1.5, 100, 10, 80, 25, 10);
    }

    public Bow(String name, double weight, int durability, int baseDamage,
               int maxRange, int drawWeight, int arrowsInQuiver) {
        super(name, weight, durability, baseDamage, maxRange);
        this.drawWeight = Math.max(5, drawWeight);
        this.arrowsInQuiver = Math.max(0, arrowsInQuiver);
        createdCount++;
    }

    public int getDrawWeight() { return drawWeight; }
    public void setDrawWeight(int drawWeight) { this.drawWeight = Math.max(5, drawWeight); }

    public int getArrowsInQuiver() { return arrowsInQuiver; }
    public void setArrowsInQuiver(int arrowsInQuiver) { this.arrowsInQuiver = Math.max(0, arrowsInQuiver); }

    public void reload(int arrows) { this.arrowsInQuiver += Math.max(0, arrows); }

    public static int getCreatedCount() { return createdCount; }

    @Override
    public int attack() {
        if (arrowsInQuiver <= 0) {
            return 0; // нет стрел
        }
        arrowsInQuiver--;
        consumeDurability(1);
        int bonus = (int)Math.round(getBaseDamage() * (getDrawWeight() / 100.0));
        return getBaseDamage() + bonus;
    }

    @Override
    public String description() {
        return super.description() + String.format(", сила=%d, дальность=%dм, стрел=%d",
                drawWeight, getMaxRange(), arrowsInQuiver);
    }
}
