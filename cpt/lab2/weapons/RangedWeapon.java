package weapons;

/** Промежуточный абстрактный класс: Дальнобойное оружие. */
public abstract class RangedWeapon extends Weapon {

    private int maxRange; // максимальная дальность, м

    public RangedWeapon() {
        super();
        this.maxRange = 50;
    }

    public RangedWeapon(String name, double weight, int durability, int baseDamage, int maxRange) {
        super(name, weight, durability, baseDamage);
        this.maxRange = Math.max(1, maxRange);
    }

    public int getMaxRange() { return maxRange; }
    public void setMaxRange(int maxRange) { this.maxRange = Math.max(1, maxRange); }

    @Override
    public String type() { return "Дальнобойное"; }
}
