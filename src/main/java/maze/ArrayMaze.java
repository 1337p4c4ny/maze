package maze;

import maze.math.Vec2i;

public class ArrayMaze implements Maze {

    private int width;
    private int height;
    private Vec2i startCell;
    private Vec2i finishCell;

    private CellType[][] maze;

    ArrayMaze(int w, int h) {
        width = w;
        height = h;
        maze = new CellType[width][height];
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public void setCell(int i, int j, CellType wall) throws IndexOutOfBoundsException {
        maze[i][j] = wall;
    }

    @Override
    public CellType getCell(int i, int j) throws IndexOutOfBoundsException {
        return maze[i][j];
    }

    @Override
    public Vec2i getStartCell() {
        return startCell;
    }

    @Override
    public void setStartCell(Vec2i startCell) {
        this.startCell = startCell;
    }

    @Override
    public Vec2i getFinishCell() {
        return finishCell;
    }

    @Override
    public void setFinishCell(Vec2i finishCell) {
        this.finishCell = finishCell;
    }
}
