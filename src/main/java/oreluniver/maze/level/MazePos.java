package oreluniver.maze.level;

public class Pair<T1, T2> {
    public T1 row;
    public T2 second;

    public Pair(T1 row, T2 col) {
        this.row = row;
        this.second = col;
    }
}
