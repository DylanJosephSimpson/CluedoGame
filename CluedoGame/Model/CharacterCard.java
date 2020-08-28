package Model;

import Model.Card;

import javax.swing.*;
import java.awt.*;

/**
 * Class which is necessary for interactions. Describes the character *in question.
 * Model.CharacterCard subclass:
 *
 * The Model.CharacterCard subclass is a class which extends the Model.Card Superclass. CharacterCards are used to carry out
 * interaction between players during the suggestions faze of the game.
 */

public class CharacterCard extends Card {

    private String characterName;

    /**Constructor for a single Model.CharacterCard
     * @param aCharacterName - the name of the card. For CharacterCards this is the character names.
     */
    public CharacterCard(String aCharacterName, JLabel cardIcon, Image cardImage) {

        characterName = aCharacterName;
        super.cardIcon = cardIcon;
        super.cardImage = cardImage;
    }
    /**
     * getCharacterName method :
     *
     * getter method used to get the name of the characterCard
     *
     * @return - The name of the characterCard as a String
     */
    String getCharacterName() {
        return characterName;
    }

    /**
     * toString method:
     *
     * basic toString method for a Model.CharacterCard
     *
     * @return - returns the name of the character as a String
     */

    public String toString() {
        return characterName;
    }
}
