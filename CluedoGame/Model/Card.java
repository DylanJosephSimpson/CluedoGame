package Model;

import javax.swing.*;
import java.awt.*;

/**
 * todo look into drawing the cards using a super method
 * Superclass for all Model.Card Classes. Cards are used for interactions *between players.
 * Model.Card Superclass:
 * <p>
 * The card Superclass is a class extended by all xCard classes. Cards are used to carry out
 * interaction between players during the suggestions faze of the game.
 */

public class Card {
    protected JLabel cardIcon;
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

    public JLabel getCardIcon() {
        return cardIcon;
    }
    public Image getCardImage() {
        return cardImage;
    }

}
