package oreluniver.maze.game;

import oreluniver.maze.level.MazePos;
import org.joml.Vector3f;

public class Utils {
    private static final float BOUNDING_BOX = 0.2f;
    private static final float HALF_WALL_WIDTH = 0.5f;


    /**
     * checks wall collisions of the new player's position with the
     * nearest wall blocks and fixes the player's position if there are
     * some intersections
     * @param oldPos previous position of the player
     * @param newPos position to be checked and fixed if needed
     * @param m bitmap of the level where m[i][j] == 0 means the presence of wall
     */
    public static void checkCollision(Vector3f oldPos, Vector3f newPos, int [][] m) {
        int j = Math.round(oldPos.x);
        int i = Math.round(oldPos.z);


        if (i < 0 || i >= m.length || j < 0 || j >= m[0].length) {
            return;
        }


        // check north west
        if (i > 0 && j > 0 && hitWall(newPos, i - 1, j - 1, m)) {
            newPos.x = j - 1 + HALF_WALL_WIDTH + BOUNDING_BOX;
            newPos.z = i - 1 + HALF_WALL_WIDTH + BOUNDING_BOX;
        }

        // check north
        if (i > 0 && hitWall(newPos, i - 1, j, m)) {
            newPos.z  = i - 1 + HALF_WALL_WIDTH + BOUNDING_BOX;
        }

        // check north east
        if (i > 0 && j < m[0].length - 1 && hitWall(newPos, i - 1, j + 1, m)) {
            newPos.x = j + 1 - HALF_WALL_WIDTH - BOUNDING_BOX;
            newPos.z = i - 1 + HALF_WALL_WIDTH + BOUNDING_BOX;
        }

        // check east
        if (j < m[0].length - 1 && hitWall(newPos, i, j + 1, m)) {
            newPos.x = j + 1 - HALF_WALL_WIDTH - BOUNDING_BOX;
        }

        // check south east
        if (i < m.length - 1 && j < m[0].length - 1 && hitWall(newPos, i + 1, j + 1, m)) {
            newPos.x = j + 1 - HALF_WALL_WIDTH - BOUNDING_BOX;
            newPos.z = i + 1 - HALF_WALL_WIDTH - BOUNDING_BOX;
        }

        // check south
        if (i < m.length - 1 && hitWall(newPos, i + 1, j, m)) {
            newPos.z = i + 1 - HALF_WALL_WIDTH - BOUNDING_BOX;
        }

        // check south west
        if (i < m.length - 1 && j > 0 && hitWall(newPos, i + 1, j - 1, m)) {
            newPos.x = j - 1 + HALF_WALL_WIDTH + BOUNDING_BOX;
            newPos.z = i + 1 - HALF_WALL_WIDTH - BOUNDING_BOX;
        }

        // check west
        if (j > 0 && hitWall(newPos, i, j - 1, m)) {
            newPos.x = j - 1 + HALF_WALL_WIDTH + BOUNDING_BOX;
        }

    }


    /**
     * returns a free wall position in the level where player can start his game
     * @param m bitmap of level where m[i][j] == 0 means the presence of the wall
     * @return start position for player
     */
    public static MazePos getSpawnPosition (int [][] m) {
        MazePos pos = new MazePos(m.length / 2, m[0].length / 2);
        while (m[pos.row][pos.col] == 0) {
            pos.col++;
        }
        if (m[pos.col][pos.row] == 0) {
            pos.col = m[0].length / 2;
            while (m[pos.row][pos.col] == 0) {
                pos.col--;
            }
        } else
            return pos;

        if (m[pos.row][pos.col] == 0) {
            pos.col = m[0].length / 2;
            pos.row = m.length / 2;
            while (m[pos.row][pos.col] == 0) {
                pos.col++;
            }
        } else
            return pos;

        if (m[pos.row][pos.col] == 0) {
            pos.col = m[0].length / 2;
            while (m[pos.row][pos.col] == 0) {
                pos.col--;
            }
        }
        return pos;
    }


    /**
     * Checks whether player in the given position intersects with the wall block or not
     * @param pos position of the player
     * @param i row of the wall block to check
     * @param j column of the wall block to check
     * @param m bitmap of the level where m[i][j] == 0 means the presence of wall block
     * @return true if player's bounding box intersects with the wall block
     */
    private static boolean hitWall(Vector3f pos, int i, int j, int [][] m) {
        return m[i][j] == 0
            &&
               rangeIntersect(pos.x - BOUNDING_BOX,
                              pos.x + BOUNDING_BOX,
                              j     - HALF_WALL_WIDTH,
                              j     + HALF_WALL_WIDTH)
            &&
               rangeIntersect(pos.z - BOUNDING_BOX,
                              pos.z + BOUNDING_BOX,
                              i     - HALF_WALL_WIDTH,
                              i     + HALF_WALL_WIDTH);
    }

    private static boolean rangeIntersect(float l1, float r1, float l2, float r2) {
        return r1 >= l2 && l1 <= r2;
    }
}
