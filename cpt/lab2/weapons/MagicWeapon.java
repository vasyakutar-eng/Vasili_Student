package weapons;

/** Промежуточный абстрактный класс: Магическое оружие. */
public abstract class MagicWeapon extends Weapon {

    private int manaCost; // стоимость маны за заклинание

    public MagicWeapon() {
        super();
        this.manaCost = 5;
    }

    public MagicWeapon(String name, double weight, int durability, int baseDamage, int manaCost) {
        super(name, weight, durability, baseDamage);
        this.manaCost = Math.max(0, manaCost);
    }

    public int getManaCost() { return manaCost; }
    public void setManaCost(int manaCost) { this.manaCost = Math.max(0, manaCost); }

    @Override
    public String type() { return "Магическое"; }
}
