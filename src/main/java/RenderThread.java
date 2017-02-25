import org.lwjgl.opengl.GL21.*;

import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwSwapBuffers;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;
import static org.lwjgl.opengl.GL11.*;

public class RenderThread extends Thread {

    private long window;

    RenderThread(long window) {
        this.window = window;
    }

    @Override
    public void run() {
        Logger.info("RenderThread started");
        try {
            renderLoop();
        } catch (InterruptedException e) {
            Logger.info("Render loop was interrupted " + e.getMessage());
        }
    }

    public void renderLoop() throws InterruptedException {
    }
}
