import javax.swing.*;

/**
 * Class which is necessary for interactions. Describes the weapon in *queston.
 * WeaponCard subclass:
 *
 * The Weapon subclass is a class which extends the Card Superclass. WeaponCards are used to carry out
 * interaction between players during the suggestions faze of the game.
 *
 */

public class WeaponCard extends Card {

    private String weaponName;

    /**
     * Constructor for a single WeaponCard
     * @param aWeaponName - the name of the card. For WeaponCard this is the name of the associated weapon.
     */
    WeaponCard(String aWeaponName, JLabel cardIcon) {
        super.cardIcon = cardIcon;
        weaponName = aWeaponName;
    }

    /**
     * getWeaponName method:
     *
     * getter method for a WeaponCard
     *
     * @return - returns the name of the weapon as a String
     */

    String getWeaponName() {
        return weaponName;
    }

    /**
     * * toString method:
     *
     * basic toString method for a WeaponCard
     *
     * @return - returns the name of the weapon as a String
     */
    public String toString() {
        return weaponName;
    }
}
