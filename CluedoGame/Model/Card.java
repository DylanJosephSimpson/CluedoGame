package Model;

import javax.swing.*;
import java.awt.*;

/**
 * Superclass for all Card Classes. Cards are used for interactions between players.
 * The card Superclass is a class extended by all Card classes. Cards are used to carry out
 * interaction between players during the suggestions phase of the game.
 */

public class Card {
    protected ImageIcon cardIcon;
    protected Image cardImage;

    /**
     * ToString Method:
     * <p>
     * ToString Method to return the toString value of the specific card types.
     *
     * @return toString value of the specific card types.
     */

    public String toString() {
        return super.toString();
    }

    /**
     * Gets the image icon from the card
     *
     * @return the card's image Icon
     */
    public ImageIcon getCardIcon() {
        return cardIcon;
    }

    /**
     * Gets the card Image from the card
     *
     * @return the card's image
     */
    public Image getCardImage() {
        return cardImage;
    }
}
