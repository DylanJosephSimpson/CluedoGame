package Model;

import Model.Card;

import javax.swing.*;
import java.awt.*;

/**
 * Class which is necessary for interactions. Describes the weapon in *queston.
 * Model.WeaponCard subclass:
 *
 * The Model.Weapon subclass is a class which extends the Model.Card Superclass. WeaponCards are used to carry out
 * interaction between players during the suggestions faze of the game.
 *
 */

public class WeaponCard extends Card {

    private String weaponName;

    /**
     * Constructor for a single Model.WeaponCard
     * @param aWeaponName - the name of the card. For Model.WeaponCard this is the name of the associated weapon.
     */
    public WeaponCard(String aWeaponName, ImageIcon cardIcon, Image cardImage) {
        super.cardIcon = cardIcon;
        weaponName = aWeaponName;
        super.cardImage = cardImage;
    }

    /**
     * getWeaponName method:
     *
     * getter method for a Model.WeaponCard
     *
     * @return - returns the name of the weapon as a String
     */

    String getWeaponName() {
        return weaponName;
    }

    /**
     * * toString method:
     *
     * basic toString method for a Model.WeaponCard
     *
     * @return - returns the name of the weapon as a String
     */
    public String toString() {
        return weaponName;
    }
}
