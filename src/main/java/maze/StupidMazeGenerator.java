package maze;

import javafx.scene.control.Cell;

import java.util.Random;

public class StupidMazeGenerator implements MazeGenerator {
    @Override
    public Maze generate(int w, int h) {
        Maze arrayMaze = new ArrayMaze(w, h);

        int minimal_dx = (int)(Math.sqrt(w*w - h*h)*0.75);

        Random random = new Random();

        int si, sj, fi, fj;
        do {
            if (Math.random() > 0.5) {
                si = random.nextInt(w);
                sj = 0;
                fi = w-1;
                fj = random.nextInt(h);
            } else {
                si = 0;
                sj = random.nextInt(h);
                fi = random.nextInt(w);
                fj = h-1;
            }
        } while( Math.sqrt((si-sj)*(si-sj) - (fi-fj)*(fi-fj)) >= minimal_dx);

        for (int i = 0; i < w; ++i) {
            for (int j = 0; j < h; ++j) {
                arrayMaze.setCell(i, j, CellType.WALL);
            }
        }

        arrayMaze.setStartCell(new Point2d<>(si, sj));
        arrayMaze.setFinishCell(new Point2d<>(fi, fj));

        arrayMaze.setCell(si, sj, CellType.START);
        arrayMaze.setCell(fi, fj, CellType.FINISH);

        for (int i = si+1; i < fi; ++i) {
            arrayMaze.setCell(i, sj, CellType.EMPTY);
        }
        for (int i = sj; i < fj; ++i) {
            arrayMaze.setCell(fi, i, CellType.EMPTY);
        }

        return arrayMaze;
    }
}
