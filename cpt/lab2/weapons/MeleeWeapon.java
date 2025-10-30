package weapons;

/** Промежуточный абстрактный класс: Рукопашное оружие (2-й уровень наследования). */
public abstract class MeleeWeapon extends Weapon {

    private double bladeLength; // длина клинка/стержня, см

    public MeleeWeapon() {
        super();
        this.bladeLength = 50;
    }

    public MeleeWeapon(String name, double weight, int durability, int baseDamage, double bladeLength) {
        super(name, weight, durability, baseDamage);
        this.bladeLength = Math.max(1, bladeLength);
    }

    public double getBladeLength() { return bladeLength; }
    public void setBladeLength(double bladeLength) { this.bladeLength = Math.max(1, bladeLength); }

    @Override
    public String type() { return "Рукопашное"; }
}
