import static org.lwjgl.glfw.GLFW.glfwPollEvents;
import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class LogicThread extends Thread {

    private long window;

    LogicThread(long window) {
        this.window = window;
    }

    @Override
    public void run() {
        Logger.info("LogicThread started");

        try {
            mainLoop();
        } catch (InterruptedException e) {
            Logger.info("Logic loop was interrupted " + e.getMessage());
        }
    }

    public void mainLoop() throws InterruptedException {
        while ( !glfwWindowShouldClose(window) ) {

            sleep(100);
            glfwPollEvents();
        }
    }
}
