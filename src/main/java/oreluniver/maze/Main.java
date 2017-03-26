package oreluniver.maze;

import oreluniver.maze.engine.GameEngine;
import oreluniver.maze.engine.IGameLogic;
import oreluniver.maze.game.MazeGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {

    public static void main(String[] args) {
        try {
            boolean vSync = false;
            IGameLogic gameLogic = new MazeGame();
            GameEngine gameEngine = new GameEngine("Maze", 1366, 768, vSync, gameLogic);
            gameEngine.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}