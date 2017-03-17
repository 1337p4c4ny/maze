package oreluniver.maze;

import oreluniver.maze.engine.GameEngine;
import oreluniver.maze.engine.IGameLogic;
import oreluniver.maze.game.MazeGame;

public class Main {

    public static void main(String[] args) {
        try {
            boolean vSync = false;
            IGameLogic gameLogic = new MazeGame();
            GameEngine gameEngine = new GameEngine("Maze", 1920, 1080, vSync, gameLogic);
            gameEngine.start();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
        }
    }

}