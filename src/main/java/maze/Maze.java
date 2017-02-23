package maze;

public interface Maze {

    int getWidth();
    int getHeight();

    void setCell(int i, int j, CellType cell) throws IndexOutOfBoundsException;
    CellType getCell(int i, int j) throws IndexOutOfBoundsException;

    Point2d<Integer> getStartCell();
    Point2d<Integer> getFinishCell();

    void setStartCell(Point2d<Integer> cell);
    void setFinishCell(Point2d<Integer> cell);

}
