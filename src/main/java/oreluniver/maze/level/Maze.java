package oreluniver.maze.level;

import java.util.List;

public class Maze {

    /**
     * first dimension is row,
     * second - is column
     */
    public final int [][] bitmap;
    private List<MazePos> entrances;

    public Maze (int [][] bitmap, List<MazePos> entrances) {
        this.bitmap    = bitmap;
        this.entrances = entrances;
    }

    public MazePos getEntrance() {
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
        for (MazePos e : this.entrances) {
            res = x == e.row && y == e.col;
            if (res) {
                break;
            }
        }
        return res;
    }

    public List<MazePos> getEntrances() {
        return this.entrances;
    }
}
