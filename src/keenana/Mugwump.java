/*
 * Course: CS1011 - 131
 * Fall 2023
 * Lab 7 - Battle Simulator 3000
 * Name: Andrew Keenan
 * Created: 10-11-23
 */
package keenana;

/**
 * This is the class for the Mugwump character
 */
public class Mugwump {
    private final int numSides100 = 100;
    private final int numSides20 = 20;
    private final int numSides10 = 10;
    private final int numSides6 = 6;
    private Die d100 = new Die(numSides100);
    private Die d20 = new Die(numSides20);
    private Die d10 = new Die(numSides10);
    private Die d6 = new Die(numSides6);
    private int hitPoints;
    private int maxHitPoints;
    // add methods here

    /**
     * creation of the Mugwump
     */
    public Mugwump(){
        setInitialHitPoints();
    }

    public int getHitPoints() {
        return hitPoints;
    }

    /**
     * simulating taking damage
     *
     * @param damage is the amount of damage the warrior deals
     */
    public void takeDamage(int damage) {
        hitPoints = hitPoints - damage;
    }

    /**
     * This method handles the attack logic
     *
     * @return the amount of damage an attack has caused, 0 if the attack misses or
     * a negative amount of damage if the Mugwump heals itself
     */
    public int attack() {
        // get attack type from ai
        int action = ai();
        int damage = 0;
        final int claw = 85;
        final int fang = 25;
        final int range = 100;
        if (action <= fang) {
            d20.roll();
            final int threshold = 13;
            if (d20.getCurrentValue() >= threshold) {
                for (int i = 0; i < 2; i++) {
                    d6.roll();
                    damage = damage + d6.getCurrentValue();
                }
            }
        } else if (action <= claw) {
            d20.roll();
            final int threshold = 16;
            if (d20.getCurrentValue() >= threshold) {
                for (int i = 0; i < 3; i++) {
                    d6.roll();
                    damage = damage + d6.getCurrentValue();
                }
            }
        } else if(action <= range){
            d6.roll();
            if ((d6.getCurrentValue() + hitPoints) < maxHitPoints) {
                hitPoints = d6.getCurrentValue();
            } else{
                hitPoints = maxHitPoints;
            }
        }
        return damage;
    }

    private void setInitialHitPoints() {
        final int rolls = 6;
        for (int i = 0; i < rolls; i++) {
            d10.roll();
            hitPoints += d10.getCurrentValue();
        }
        maxHitPoints = hitPoints;
    }

    /**
     * This method determines what action the Mugwump performs
     *
     * @return 1 for a Claw attack, 2 for a Bite, and 3 if the Mugwump licks its wounds
     */
    private int ai() {
        d100.roll();
        return d100.getCurrentValue();
    }
    /**
     * rolls a d10 to find who attacks first
     * @return d10 value
     */
    public int initiative(){
        d10.roll();
        return d10.getCurrentValue();
    }
}