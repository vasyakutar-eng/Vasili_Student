package weapons;

/** Конкретный класс: Меч. */
public class Sword extends MeleeWeapon {

    private int sharpness; // острота 0..100
    private String style;  // стиль (например, "клинковый", "тяжёлый")

    private static int createdCount = 0; // статический счётчик только мечей

    public Sword() {
        this("Меч", 3.0, 100, 12, 80.0, 80, "клинковый");
    }

    public Sword(String name, double weight, int durability, int baseDamage,
                 double bladeLength, int sharpness, String style) {
        super(name, weight, durability, baseDamage, bladeLength);
        this.sharpness = clamp(sharpness, 0, 100);
        this.style = style;
        createdCount++;
    }

    public int getSharpness() { return sharpness; }
    public void setSharpness(int sharpness) { this.sharpness = clamp(sharpness, 0, 100); }

    public String getStyle() { return style; }
    public void setStyle(String style) { this.style = style; }

    public static int getCreatedCount() { return createdCount; }

    @Override
    public int attack() {
        // Урон = базовый + бонус от остроты. Тратим 1 ед. прочности.
        int dmg = getBaseDamage() + (int)Math.round(getBaseDamage() * (getSharpness() / 200.0));
        consumeDurability(1);
        return dmg;
    }

    @Override
    public String description() {
        return super.description() + String.format(", острота=%d, стиль=%s, клинок=%.1fсм",
                sharpness, style, getBladeLength());
    }
}
