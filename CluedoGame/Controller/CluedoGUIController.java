package Controller;

import Model.Board;
import Model.Player;
import View.AccusationSetup;
import View.CluedoGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CluedoGUIController {

    public CluedoGUIController() {
        // Action Listner's for the CluedoGUI's menuOption's
        CluedoGUI.getExitOption().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(1);
            }
        });
        CluedoGUI.getRestartGame().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });

        CluedoGUI.getEndTurn().addActionListener(e -> {
            CluedoGUI.getGameControlPanel().requestFocus();
            Board.getCurrentPlayer().setRemainingMoves(0);
        });
        CluedoGUI.getRollDice().addActionListener(e -> {
            if (!CluedoGUI.isHasRolled()) {
                CluedoGUI.GenerateRandomDice();
            }
            CluedoGUI.getGameControlPanel().requestFocus();
        });
        CluedoGUI.getMakeAccusation().addActionListener(e -> {
            CluedoGUI.getGameControlPanel().requestFocus();
            if (Board.getCurrentPlayer().canMakeActions()) {
                new AccusationSetup(Board.getCurrentPlayer());
            } else {
                JOptionPane.showMessageDialog(null,
                        "Cannot make an accusation as the player has made a false accusation.",
                        "Model.Player Error",
                        JOptionPane.ERROR_MESSAGE);
            }
            CluedoGUI.endTurn();
        });
        CluedoGUI.getMakeSuggestion().addActionListener(e -> {
            CluedoGUI.getGameControlPanel().requestFocus();
            System.out.println("Add");
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
                    CluedoGUI.getGameControlPanel().requestFocus();
                }
                if (e.getKeyChar() == '2') {
                    if (!CluedoGUI.isHasRolled()) {
                        CluedoGUI.getRollDice().doClick();
                        CluedoGUI.getGameControlPanel().requestFocus();
                    }
                }
                if (e.getKeyChar() == '3') {
                    CluedoGUI.getMakeAccusation().doClick();
                    new AccusationSetup(Board.getCurrentPlayer());
                    CluedoGUI.getGameControlPanel().requestFocus();
                }
                if (CluedoGUI.isHasRolled()) {
                    Board.setCurrentPlayer(Player.getPlayerList().get(CluedoGUI.getCurrentPlayerPos()));
                    //if the current player has no moves left, prompt the player that their turn has ended and return the settings to their defult
                    //convert pixel pos to tile pos
                    int tileX = Board.getCurrentPlayer().getAssignedCharacter().getX() / 30;
                    int tileY = Board.getCurrentPlayer().getAssignedCharacter().getY() / 30;
                    CluedoGUI.getPreviouslyTraversedTiles().add(new int[]{tileX, tileY});
                    //Pattern pattern = Pattern.compile("(Scarlett|Mustard|Green|White|Plum|Peacock|Wall)",Pattern.CASE_INSENSITIVE); //todo update board each time player is moved and then uncomment this(Caleb)
                    Pattern pattern = Pattern.compile("(Wall)", Pattern.CASE_INSENSITIVE);
                    //ensures the player can move into the position that they want to, if they are not able to then do not decrese their moves left
                    if (e.getKeyCode() == KeyEvent.VK_UP) {
                        if (Board.getCurrentPlayer().getAssignedCharacter().getY() > 0 && Player.validMove(CluedoGUI.getBoard()[tileY - 1][tileX])) {
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
                        if (Board.getCurrentPlayer().getAssignedCharacter().getY() < 720 && Player.validMove(CluedoGUI.getBoard()[tileY + 1][tileX])) {
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
                        if (Board.getCurrentPlayer().getAssignedCharacter().getX() > 0 && Player.validMove(CluedoGUI.getBoard()[tileY][tileX - 1])) {
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
                        if (Board.getCurrentPlayer().getAssignedCharacter().getX() < 690 && Player.validMove(CluedoGUI.getBoard()[tileY][tileX + 1])) {
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
                        JFrame frame = new JFrame();
                        JOptionPane.showMessageDialog(frame, "You now have no more moves", "No more moves", JOptionPane.PLAIN_MESSAGE);
                        CluedoGUI.endTurn();
                    }
                } else {
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

