package maze;

import javafx.scene.control.Cell;

public class ArrayMaze implements Maze {

    private int width;
    private int height;
    private Point2d<Integer> startCell;
    private Point2d<Integer> finishCell;

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
    public Point2d<Integer> getStartCell() {
        return startCell;
    }

    @Override
    public void setStartCell(Point2d<Integer> startCell) {
        this.startCell = startCell;
    }

    @Override
    public Point2d<Integer> getFinishCell() {
        return finishCell;
    }

    @Override
    public void setFinishCell(Point2d<Integer> finishCell) {
        this.finishCell = finishCell;
    }
}
