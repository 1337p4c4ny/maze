package oreluniver.maze.game;

import oreluniver.maze.engine.GameItem;
import oreluniver.maze.engine.IGameLogic;
import oreluniver.maze.engine.MouseInput;
import oreluniver.maze.engine.Window;
import oreluniver.maze.engine.gfx.Camera;
import oreluniver.maze.engine.gfx.Mesh;
import oreluniver.maze.engine.gfx.OBJLoader;
import oreluniver.maze.engine.gfx.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class MazeGame implements IGameLogic {


    private static final float CAMERA_POS_STEP = 0.1f;
    private static final float MOUSE_SENSITIVITY = 0.2f;

    private Vector3f cameraInc;

    private Camera camera;

    private final Renderer renderer;

    private GameItem[] gameItems;

    public MazeGame() {
        renderer = new Renderer();
    }

    @Override
    public void init() throws Exception {
        renderer.init();
        Texture texture = new Texture("/textures/grassblock_32.png");
        Mesh mesh = OBJLoader.loadMesh("/models/bunny.obj");
//        Mesh mesh = OBJLoader.loadMesh("/models/cube.obj");

        mesh.setTexture(texture);
        GameItem gameItem = new GameItem(mesh);
        gameItem.setScale(0.5f);
        gameItem.setPosition(0, 0, -2);
        gameItems = new GameItem[]{gameItem};

        camera = new Camera();
        cameraInc = new Vector3f();
    }

    @Override
    public void input(Window window, MouseInput mouseInput) {
        cameraInc.set(0, 0, 0);
        if (window.isKeyPressed(GLFW_KEY_W)) {
            cameraInc.z = -1;
        } else if (window.isKeyPressed(GLFW_KEY_S)) {
            cameraInc.z = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_A)) {
            cameraInc.x = -1;
        } else if (window.isKeyPressed(GLFW_KEY_D)) {
            cameraInc.x = 1;
        }
        if (window.isKeyPressed(GLFW_KEY_Z)) {
            cameraInc.y = -1;
        } else if (window.isKeyPressed(GLFW_KEY_X)) {
            cameraInc.y = 1;
        }

        else if (window.isKeyPressed(GLFW_KEY_LEFT)) {
            Vector3f rotation = gameItems[0].getRotation();
            gameItems[0].setRotation(rotation.x, rotation.y - 1, rotation.z);

        } else if (window.isKeyPressed(GLFW_KEY_RIGHT)) {
            Vector3f rotation = gameItems[0].getRotation();
            gameItems[0].setRotation(rotation.x, rotation.y + 1, rotation.z);

        } else if (window.isKeyPressed(GLFW_KEY_UP)) {
            Vector3f rotation = gameItems[0].getRotation();
            gameItems[0].setRotation(rotation.x + 1, rotation.y, rotation.z);

        } else if (window.isKeyPressed(GLFW_KEY_DOWN)) {
            Vector3f rotation = gameItems[0].getRotation();
            gameItems[0].setRotation(rotation.x - 1, rotation.y, rotation.z);
        }

//        if (! window.isKeyPressed(GLFW_KEY_LEFT) && !window.isKeyPressed(GLFW_KEY_RIGHT)) {
//            Vector3f rotation = gameItems[0].getRotation();
//            gameItems[0].setRotation(rotation.x, 0, rotation.z);
//        }
//        if (! window.isKeyPressed(GLFW_KEY_UP) && ! window.isKeyPressed(GLFW_KEY_DOWN)) {
//            Vector3f rotation = gameItems[0].getRotation();
//            gameItems[0].setRotation(0, rotation.y, rotation.z);
//        }

    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP,
                cameraInc.y * CAMERA_POS_STEP,
                cameraInc.z * CAMERA_POS_STEP);

        if (mouseInput.isRightButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY,
                    rotVec.y * MOUSE_SENSITIVITY, 0);
        }

        float rotation = gameItems[0].getRotation().x;
        if (rotation > 360) {
            rotation = 0;
        }
        gameItems[0].setRotation(rotation, 0, 0);
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, gameItems);
    }

    @Override
    public void cleanup() {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }
}
