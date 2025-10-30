package weapons;

/** Конкретный класс: Волшебная палочка. */
public class MagicWand extends MagicWeapon {

    private String crystalType; // тип кристалла
    private int manaPool;       // текущий запас маны

    private static int createdCount = 0;

    public MagicWand() {
        this("Палочка", 0.3, 100, 9, 5, "кварц", 20);
    }

    public MagicWand(String name, double weight, int durability, int baseDamage,
                     int manaCost, String crystalType, int manaPool) {
        super(name, weight, durability, baseDamage, manaCost);
        this.crystalType = crystalType;
        this.manaPool = Math.max(0, manaPool);
        createdCount++;
    }

    public String getCrystalType() { return crystalType; }
    public void setCrystalType(String crystalType) { this.crystalType = crystalType; }

    public int getManaPool() { return manaPool; }
    public void setManaPool(int manaPool) { this.manaPool = Math.max(0, manaPool); }

    public void restoreMana(int amount) { this.manaPool += Math.max(0, amount); }

    public static int getCreatedCount() { return createdCount; }

    @Override
    public int attack() {
        if (manaPool < getManaCost()) {
            return 0; // не хватает маны
        }
        manaPool -= getManaCost();
        consumeDurability(1);
        // Магия даёт бонус 50% к базовому урону
        return getBaseDamage() + (int)Math.round(getBaseDamage() * 0.5);
    }

    /** Перегрузка: атака с указанием заклинания — просто текстовый эффект. */
    public int attack(String spellName) {
        int dmg = attack();
        System.out.println("Заклинание: " + spellName);
        return dmg;
    }

    @Override
    public String description() {
        return super.description() + String.format(", кристалл=%s, мана=%d, стоимость=%d",
                crystalType, manaPool, getManaCost());
    }
}
