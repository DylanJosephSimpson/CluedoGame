package Model;

import View.AccusationSetup;
import View.CluedoGUI;
import View.SuggestionSetup;

import javax.swing.*;
import java.io.IOException;

public class CluedoGUIModel {

    /**
     * Constructor for a CluedoGUIModel - As is, not required.
     */
    public CluedoGUIModel(){}

    /**
     * Model for the ExitOptionController.
     * Called when an action is preformed involving the ExitOption Action Listener.
     */
    public static void ExitOptionModel(){
        System.exit(1);
    }

    /**
     * Model for the RestartOptionModel.
     * Called when an action is preformed involving the RestartOption Action Listener.
     */
    public static void RestartOptionModel(){
        //TODO : SETUP RESTART OF GAME
        System.exit(1);
    }

    /**
     * Model for the  EndTurnModel.
     * Called when an action is preformed involving the EndTurn Action Listener.
     * Begins the EndOfTurn Logic
     */
    public static void EndTurnModel(){
        Board.getCurrentPlayer().setRemainingMoves(0);
        CluedoGUI.endTurn();
    }

    /**
     * Model for the  RollDiceModel.
     * Called when an action is preformed involving the EndTurn Action Listener.
     * Rolls the dice
     */
    public static void RollDiceValidModel(){
        CluedoGUI.GenerateRandomDice();
        CluedoGUI.setHasRolled(true);
    }

    /**
     * Model for the  RollDiceModel.
     * Called when an action is preformed involving the EndTurn Action Listener.
     * Rolls the dice
     */
    public static void RollDiceInvalidModel(){
        JOptionPane.showMessageDialog(null,
                Board.getCurrentPlayer().getName() + ", you have already rolled the dice for your turn\n and have " +
                        Board.getCurrentPlayer().getRemainingMoves() + " move(s) remaining"
                , "You have already rolled", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Model for the  RollDiceModel.
     * Called when an action is preformed involving the EndTurn Action Listener.
     * Rolls the dice
     */
    public static void MakeAccusationValidModel(){
        new AccusationSetup(Board.getCurrentPlayer());
    }

    /**
     * Model for the  RollDiceModel.
     * Called when an action is preformed involving the EndTurn Action Listener.
     * Rolls the dice
     */
    public static void MakeAccusationInvalidModel(){
        if (!Board.getCurrentPlayer().isInARoom()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot make an accusation as you are not in a Room",
                    "Player Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "Cannot make an accusation as the player has made a false accusation.",
                    "No Accusation!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Model for the  RollDiceModel.
     * Called when an action is preformed involving the EndTurn Action Listener.
     * Rolls the dice
     */
    public static void MakeSuggestionValidModel() throws IOException {
        new SuggestionSetup(Board.getCurrentPlayer());
    }

    /**
     * Model for the  RollDiceModel.
     * Called when an action is preformed involving the EndTurn Action Listener.
     * Rolls the dice
     */
    public static void MakeSuggestionInvalidModel(){
        if (!Board.getCurrentPlayer().isInARoom()) {
            JOptionPane.showMessageDialog(null,
                    "Cannot make an Suggestion as you are not in a Room",
                    "Player Error!",
                    JOptionPane.ERROR_MESSAGE);
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "Cannot make a Suggestion as the player has made a false Accusation.",
                    "No Suggestion!",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
