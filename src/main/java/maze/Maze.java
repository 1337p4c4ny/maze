package maze;

import maze.math.Vec2i;

public interface Maze {

    int getWidth();
    int getHeight();

    void setCell(int i, int j, CellType cell) throws IndexOutOfBoundsException;
    CellType getCell(int i, int j) throws IndexOutOfBoundsException;

    Vec2i getStartCell();
    Vec2i getFinishCell();

    void setStartCell(Vec2i cell);
    void setFinishCell(Vec2i cell);

}
