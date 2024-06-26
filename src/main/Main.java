package main;

import input.InputManager;
import model.GameObjects.Ranch;
import model.SoundPlayer;
import view.*;
import view.Threads.Redessine;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    private static final GameFrame frame = new GameFrame();

    // Affiche le menu principal
    public static void ShowMenu() {
        frame.getContentPane().removeAll();
        frame.setLayout(new FlowLayout());
        frame.add(new GameMenuPanel());
        frame.pack();
    }

    // Démarre le jeu
    public static void GameStart() {

        frame.getContentPane().removeAll();

        Ranch ranch = new Ranch();

        InputManager inputManager = new InputManager(ranch.getRancher()); // Crée un gestionnaire d'entrée pour contrôler le rancher

        GamePanel panel = new GamePanel(ranch); // Crée un panneau de jeu
        GameUIPanel uiPanel = new GameUIPanel(frame, ranch); // Crée un panneau d'interface utilisateur (magasin)

        //start the ui thread to repaint the ui panel for animation
        panel.startRedessine(); //start the thread to repaint the frame
        uiPanel.startRedessine();
        //ranch start means game start
        ranch.start();

        frame.setLayout(new FlowLayout()); // Configure la disposition du frame (1行2列)
        frame.add(panel);
        frame.add(uiPanel);
        frame.addKeyListener(inputManager);
        frame.pack();
        frame.requestFocus();


        //start background sound
        SoundPlayer.playBackGroundSound();
    }

    // Arrête le jeu et affiche l'écran de fin avec le résultat
    public static void GameStop(boolean isWin, Ranch ranch,String message) {
        ranch.stop();
        frame.getContentPane().removeAll();
        frame.setLayout(new FlowLayout());
        frame.add(new GameFinishPanel(isWin, ranch, message));
        frame.pack();

        //stop background sound
        SoundPlayer.stopBackGroundSound();

        if(isWin){
            SoundPlayer.playWinSound();
        }else{
            SoundPlayer.playGameOverSound();
        }
    }

    public static void main(String[] args) {
        ShowMenu();
         //GameStart();
//        GameStop(true, new Ranch());
    }
}