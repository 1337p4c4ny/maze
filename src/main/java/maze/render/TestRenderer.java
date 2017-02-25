package maze.render;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class TestRenderer implements RendererInterface {

    double x = 0;
    double y = 0;
    double z = 0;
    double angle = 0;

    @Override
    public void render(int width, int height) {
    }
}
