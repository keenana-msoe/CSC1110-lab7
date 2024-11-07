/*
 * Course: CSC 1110 131
 * Term: Fall 2023
 * Assignment: Lab 7
 * Name: Andrew Keenan
 * Created: 10-11-23
 */
package keenana;

/**
 * This is the class for the object Warrior
 */
public class Warrior {
    private int hitPoints;
    private final int numSides20 = 20;
    private final int numSides10 = 10;
    private final int numSides8 = 8;
    private final int numSides4 = 4;
    private Die d20 = new Die(numSides20);
    private Die d10 = new Die(numSides10);
    private Die d8 = new Die(numSides8);
    private Die d4 = new Die(numSides4);

    /**
     * contructs the basic Warrior object
     */
    public Warrior(){
        setInitialHitPoints();
    }
    public int getHitPoints(){
        return hitPoints;
    }

    /**
     * calulates the hit points after the damage is dealt
     * @param damage the amount of damage dealt to the Warrior
     */
    public void takeDamage(int damage){
        hitPoints = hitPoints - damage;
    }

    /**
     * calculates the attacks done by the Warrior
     * @param type which attack is used
     * @return the amount of damage dealt
     */
    public int attack(int type){
        int attackDmg = 0;
        if (type == 1){
            d20.roll();
            final int threshold = 12;
            if (d20.getCurrentValue() <= threshold){
                for (int i = 0; i<2; i++){
                    d8.roll();
                    attackDmg = attackDmg + d8.getCurrentValue();
                }
            }
        } else if (type == 2){
            d20.roll();
            final int threshold = 6;
            if (d20.getCurrentValue() >= threshold){
                d4.roll();
                attackDmg = attackDmg + d4.getCurrentValue();
            }
        }
        return attackDmg;
    }
    private void setInitialHitPoints(){
        final int rolls = 4;
        for (int i = 0; i < rolls; i++) {
            d10.roll();
            hitPoints = hitPoints + d10.getCurrentValue();
        }
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
