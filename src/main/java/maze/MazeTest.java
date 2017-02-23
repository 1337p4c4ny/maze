package maze;

public class MazeTest {

    public static void main(String[] args) {
        MazeGenerator gen = new StupidMazeGenerator();
        Maze maze = gen.generate(25, 25);

        System.out.println();
        for (int i = 0; i < maze.getWidth(); ++i) {
            for (int j = 0; j < maze.getHeight(); ++j) {
                System.out.print(maze.getCell(i, j)+" ");
            }
            System.out.println();
        }
    }
}
