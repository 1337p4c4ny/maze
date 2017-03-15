package oreluniver.maze.level;

import java.util.List;

public class Maze {
    public int [][] bitmap;
    private List<Pair<Integer, Integer>> entrances;

    public Maze (int [][] bitmap, List<Pair<Integer, Integer>> entrances) {
        this.bitmap    = bitmap;
        this.entrances = entrances;
    }

    public Pair<Integer, Integer> getEntrance() {
        if (!entrances.isEmpty()) {
            return entrances.get(0);
        }
        return null;
    }

    public int getWidth() {
        return bitmap[0].length;
    }

    public int getHeight() {
        return bitmap.length;
    }

    public boolean isEntrance(int x, int y) {
        boolean res = false;
        for (Pair<Integer, Integer> e : this.entrances) {
            res = x == e.first && y == e.second;
            if (res) {
                break;
            }
        }
        return res;
    }

    public List<Pair<Integer, Integer>> getEntrances() {
        return this.entrances;
    }
}
