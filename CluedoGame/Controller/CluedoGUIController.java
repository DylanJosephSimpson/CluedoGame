package Controller;

import Model.Board;
import Model.CluedoGUIModel;
import Model.Player;
import Model.Tile;
import View.AccusationSetup;
import View.CluedoGUI;
import View.SuggestionSetup;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CluedoGUIController {

    public CluedoGUIController() {

        CluedoGUI.getExitOption().addActionListener(e -> CluedoGUIModel.ExitOptionModel());

        CluedoGUI.getRestartGame().addActionListener(e -> {  CluedoGUIModel.RestartOptionModel();  });

        CluedoGUI.getEndTurn().addActionListener(e -> { CluedoGUIModel.EndTurnModel(); CluedoGUI.getGameControlPanel().requestFocus();});

        CluedoGUI.getRollDice().addActionListener(e -> {
            if (!CluedoGUI.isHasRolled()) {
                CluedoGUIModel.RollDiceValidModel();
            }
            else {
                CluedoGUIModel.RollDiceInvalidModel();
            }
            CluedoGUI.getGameControlPanel().requestFocus();
        });

        CluedoGUI.getMakeAccusation().addActionListener(e -> {
            if (Board.getCurrentPlayer().canMakeActions() && Board.getCurrentPlayer().isInARoom()) {
                CluedoGUIModel.MakeAccusationValidModel();
            }
            else {
                CluedoGUIModel.MakeAccusationInvalidModel();

            }
            CluedoGUI.getGameControlPanel().requestFocus();
        });

        CluedoGUI.getMakeSuggestion().addActionListener(e -> {
            if (Board.getCurrentPlayer().canMakeActions() && Board.getCurrentPlayer().isInARoom()) {
                CluedoGUIModel.MakeSuggestionValidModel();
            }
            else {
                CluedoGUIModel.MakeSuggestionInvalidModel();
            }
            CluedoGUI.getGameControlPanel().requestFocus();
        });

        // Add A KeyListener to the GameControlPanel
        CluedoGUI.getGameControlPanel().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                //function keys
                if (e.getKeyChar() == '1') {
                    CluedoGUI.getEndTurn().doClick();
                }
                else if (e.getKeyChar() == '2') {
                        CluedoGUI.getRollDice().doClick();
                }
                else if (e.getKeyChar() == '3') {
                    CluedoGUI.getMakeAccusation().doClick();
                }
                else if (e.getKeyChar() == '4') {
                    CluedoGUI.getMakeSuggestion().doClick();
                }
                if (CluedoGUI.isHasRolled()) {
                    Board.setCurrentPlayer(Player.getPlayerList().get(CluedoGUI.getCurrentPlayerPos()));
                    //if the current player has no moves left, prompt the player that their turn has ended and return the settings to their defult
                    //convert pixel pos to tile pos
                    int tileX = Board.getCurrentPlayer().getAssignedCharacter().currentTile.getX() / 30;
                    int tileY = Board.getCurrentPlayer().getAssignedCharacter().currentTile.getRow() / 30;
                    CluedoGUI.getPreviouslyTraversedTiles().add(new Tile(Board.getBoardLayoutArray()[tileY][tileX],tileX*30,tileY*30));
//                    Pattern pattern = Pattern.compile("(Scarlett|Mustard|Green|White|Plum|Peacock|Wall)",Pattern.CASE_INSENSITIVE); //todo update board each time player is moved and then uncomment this(Caleb)
                    Pattern pattern = Pattern.compile("(Wall)", Pattern.CASE_INSENSITIVE);
                    //ensures the player can move into the position that they want to, if they are not able to then do not decrese their moves left
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        if (Board.getCurrentPlayer().getAssignedCharacter().currentTile.getRow() > 0 && Player.validMove(CluedoGUI.getBoard()[tileY - 1][tileX])) {
                            Matcher matcher = pattern.matcher(CluedoGUI.getBoard()[tileY - 1][tileX].getTileType());
                            if (!matcher.find()) {
                                Board.getCurrentPlayer().move("NORTH");
                                Board.getCurrentPlayer().setRemainingMoves(Board.getCurrentPlayer().getRemainingMoves() - 1);
                                CluedoGUI.getCluedoGame().repaint();
                                //previouslyTraversedTiles.add(new int[]{tileX, tileY});
                            }
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                        if (Board.getCurrentPlayer().getAssignedCharacter().currentTile.getRow() < 720 && Player.validMove(CluedoGUI.getBoard()[tileY + 1][tileX])) {
                            Matcher matcher = pattern.matcher(CluedoGUI.getBoard()[tileY + 1][tileX].getTileType());
                            if (!matcher.find()) {
                                //previouslyTraversedTiles.add(new int[]{tileX, tileY});
                                Board.getCurrentPlayer().move("SOUTH");
                                Board.getCurrentPlayer().setRemainingMoves(Board.getCurrentPlayer().getRemainingMoves() - 1);
                                CluedoGUI.getCluedoGame().repaint();
                            }
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                        if (Board.getCurrentPlayer().getAssignedCharacter().currentTile.getX() > 0 && Player.validMove(CluedoGUI.getBoard()[tileY][tileX - 1])) {
                            Matcher matcher = pattern.matcher(CluedoGUI.getBoard()[tileY][tileX - 1].getTileType());
                            if (!matcher.find()) {
                                // previouslyTraversedTiles.add(new int[]{tileX, tileY});
                                Board.getCurrentPlayer().move("WEST");
                                Board.getCurrentPlayer().setRemainingMoves(Board.getCurrentPlayer().getRemainingMoves() - 1);
                                CluedoGUI.getCluedoGame().repaint();
                            }
                        }
                    }
                    if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                        if (Board.getCurrentPlayer().getAssignedCharacter().currentTile.getX() < 690 && Player.validMove(CluedoGUI.getBoard()[tileY][tileX + 1])) {
                            Matcher matcher = pattern.matcher(CluedoGUI.getBoard()[tileY][tileX + 1].getTileType());
                            if (!matcher.find()) {
                                //previouslyTraversedTiles.add(new int[]{tileX, tileY});
                                Board.getCurrentPlayer().move("EAST");
                                Board.getCurrentPlayer().setRemainingMoves(Board.getCurrentPlayer().getRemainingMoves() - 1);
                                CluedoGUI.getCluedoGame().repaint();
                            }
                        }
                    }
                    //redraw the frame
                    //CluedoGame.repaint();
                    if (Board.getCurrentPlayer().getRemainingMoves() <= 0) {
                        CluedoGUI.getPreviouslyTraversedTiles().clear();
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "You now have no more moves", "No more moves", JOptionPane.PLAIN_MESSAGE);
                    }
                }
                else {
                    //prompts the player to roll if they have not already
                    JFrame frame = new JFrame();
                    JOptionPane.showMessageDialog(frame, "You need to roll the dice before you can move", "You have not rolled", JOptionPane.WARNING_MESSAGE);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });


    }
}
