/*
 * Course: CS1011 - 131
 * Fall 2023
 * Lab 7 - Battle Simulator 3000
 * Name: Andrew Keenan
 * Created: 10-11-23
 */
package keenana;

import java.util.Scanner;

/**
 * BattleSim Driver for Battle Simulator 3000
 */
public class BattleSim {
    /**
     * Ten-sided die to be used for checking initiative by all classes
     *
     * @param args none
     */
    public static void main(String[] args) {
        // Local variables
        Warrior warrior;
        Mugwump mugwump;
        // Include any variable that will need to be accessed throughout the program here
        // sentinel value for the game loop
        boolean play = true;
        // String used to determine the winner of the epic battle
        // game loop
        do {
            String victor = "none.";
            // print the introduction and rules
            intro();
            // initialize game
            // Initialize the Warrior and Mugwump classes, set the current victor to "none"
            warrior = new Warrior();
            mugwump = new Mugwump();
            Scanner in = new Scanner(System.in);


            // while neither combatant has lost all of their hit points, report status and battle!
            while (victor.equals("none.")) {
                report(warrior, mugwump);
                victor = battle(warrior, mugwump, in);
            }
            // declare the winner
            victory(victor);

            // ask to play again
            play = playAgain(in);
        } while (play);
        // Thank the user for playing your game
        System.out.println("Thank you for playing have a nice day");
    }

    /**
     * This method displays the introduction to the game and gives a description of the rules.
     */
    private static void intro() {
        // Write a suitable introduction to your game
        System.out.println("You are a brave warrior from the town of Camelot.");
        System.out.println("You have taken an oath to protect the city of Camelot" +
                " when you were just a young child.");
        System.out.println("During your patrol one day a wild Mugwump jumps out of the trees.");
        System.out.println("You raise your Trusty Sword (deals high damage but lower hit chance)");
        System.out.println("and Shield of Light (deals low damage but high hit chance)");
        System.out.println("in preparation for a fight");
        System.out.println();
        System.out.println();
    }

    /**
     * This method handles the battle logic for the game.
     *
     * @param warrior The Warrior of Light!
     * @param mugwump The Evil Mugwump!
     * @return The name of the victor, or "none", if the battle is still raging on
     */
    private static String battle(Warrior warrior, Mugwump mugwump, Scanner in) {
        // determine who attacks first (Roll! For! Initiative!) and store the result
        int wAttack;
        int mAttack;
        // attack code
        // If the Warrior attacks first
        do {
            System.out.println();
            System.out.println();
            wAttack = warrior.initiative();
            mAttack = mugwump.initiative();
            if (wAttack > mAttack) {
                int dealt = warrior.attack(attackChoice(in));
                mugwump.takeDamage(dealt);
                if (dealt > 0) {
                    System.out.println("You hit the mugwump for " + dealt + " damage");
                } else {
                    System.out.println("You missed the mugwump");
                }
                if (mugwump.getHitPoints() > 0) {
                    dealt = mugwump.attack();
                    if (dealt > 0) {
                        warrior.takeDamage(dealt);
                        System.out.println("The mugwump hits you for " + dealt + " damage");
                    } else if (dealt == 0) {
                        System.out.println("The mugwump misses");
                    } else {
                        System.out.println("The mugwump heals itself");
                    }
                }
            }
            if (mAttack > wAttack) {
                int dealt = mugwump.attack();
                if (dealt > 0) {
                    warrior.takeDamage(dealt);
                    System.out.println("The mugwump hits you for " + dealt + " damage");
                } else if (dealt == 0) {
                    System.out.println("The mugwump misses");
                } else {
                    System.out.println("The mugwump heals itself");
                }
                if (warrior.getHitPoints() > 0) {
                    dealt = warrior.attack(attackChoice(in));
                    mugwump.takeDamage(dealt);
                    if (dealt > 0) {
                        System.out.println("You hit the mugwump for " + dealt + " damage");
                    } else {
                        System.out.println("You missed the mugwump");
                    }
                }
            }
        } while (wAttack == mAttack);
        // Warrior attacks and assigns the resulting damage to the mugwump
        if (warrior.getHitPoints() == 0) {
            System.out.println();
        }
        // Check if the Mugwump has been defeated
        // If not, Mugwump attacks!
        // Otherwise, the Warrior wins!
        if (warrior.getHitPoints() <= 0) {
            return "The Warrior has been defeated";
        }
        if (mugwump.getHitPoints() <= 0) {
            return "the Mugwump has been defeated";
        }
        // Otherwise the Mugwump is first
        // see above
        return "none.";
        // If neither combatant is defeated, the battle rages on!
    }

    /**
     * This method reports the status of the combatants
     *
     * @param warrior The Warrior of Light!
     * @param mugwump The Evil Mugwump!
     */
    private static void report(Warrior warrior, Mugwump mugwump) {
        System.out.println();
        System.out.println("Warrior: " + warrior.getHitPoints() + " hp");
        System.out.println("Mugwump: " + mugwump.getHitPoints() + " hp");
        System.out.println();
    }

    /**
     * This method asks the user what attack type they want to use and returns the result
     *
     * @return 1 for sword, 2 for shield
     */
    private static int attackChoice(Scanner in) {
        System.out.println("Which attack would you like to use?");
        System.out.println("1: for Trusty Sword");
        System.out.println("2: for Shield of Light");
        int chosenAttackType = in.nextInt();
        if (chosenAttackType == 1) {
            System.out.println("You choose to swing at the Mugwump with your trusty sword");
        } else if (chosenAttackType == 2) {
            System.out.println("You choose to strike the Mugwump with your Sword");
        }
        return chosenAttackType;
    }

    /**
     * Determines which combatant attacks first and returns the result. In the case of a tie,
     * re-roll.
     *
     * @return 1 if the warrior goes first, 2 if the mugwump goes first
     */
    private static int initiative() {
        return -1;
    }

    /**
     * This method declares the victor of the epic battle
     *
     * @param victor the name of the victor of the epic battle
     */
    private static void victory(String victor) {
        if (victor.charAt(4) == 'M') {
            System.out.println("Congratulations you have defeated the evil Mugump");
            System.out.println("The citizens of Camelot thank you for saving the city");
        } else if (victor.charAt(4) == 'W') {
            System.out.println("You failed to defend the city from the evil Mugwump");
        }
    }

    /**
     * This method asks the user if they would like to play again
     *
     * @param in Scanner
     * @return true if yes, false otherwise
     */
    private static boolean playAgain(Scanner in) {
        System.out.println("Would you like to play again? (yes/no)");
        String response = in.next();
        if (response.equalsIgnoreCase("y") || (response.equalsIgnoreCase("yes"))) {
            return true;
        } else {
            return false;
        }
    }
}