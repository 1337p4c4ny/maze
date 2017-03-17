package oreluniver.maze.game;

import oreluniver.maze.engine.GameItem;
import oreluniver.maze.engine.IGameLogic;
import oreluniver.maze.engine.MouseInput;
import oreluniver.maze.engine.Window;
import oreluniver.maze.engine.gfx.Camera;
import oreluniver.maze.engine.gfx.Mesh;
import oreluniver.maze.engine.gfx.OBJLoader;
import oreluniver.maze.engine.gfx.Texture;
import oreluniver.maze.level.Maze;
import oreluniver.maze.level.MazeBuilder;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class MazeGame implements IGameLogic {


    private static final float CAMERA_POS_STEP = 0.1f;
    private static final float MOUSE_SENSITIVITY = 0.2f;

    private Vector3f cameraInc;

    private Camera camera;

    private final Renderer renderer;

    private List<GameItem> gameItems;

    public MazeGame() {
        renderer = new Renderer();
    }

    @Override
    public void init() throws Exception {
        renderer.init();

        Maze maze = MazeBuilder.build(50, 50, 2);
        gameItems = createWalls(maze);

//        gameItems = loadBunny();

        camera = new Camera();
        cameraInc = new Vector3f();
    }


    private List<GameItem> loadBunny() throws Exception {
        Mesh mesh = OBJLoader.loadMesh("/models/bunny.obj");
        List<GameItem> items = new ArrayList<>();
        GameItem gameItem = new GameItem(mesh);
//        gameItem.setScale(0.5f);
        gameItem.setPosition(0, 0, -2);
        items.add(gameItem);
        return items;
    }

    private List<GameItem> createWalls(Maze maze) throws Exception {
        Texture texture = new Texture("/textures/grassblock_32.png");
        Mesh mesh = OBJLoader.loadMesh("/models/cube.obj");
        mesh.setTexture(texture);
        List<GameItem> items = new ArrayList<>();
        GameItem gameItem;
        for (int i = 0; i < maze.getHeight(); ++i) {
            for (int j = 0; j < maze.getWidth(); ++j) {
                if (maze.bitmap[i][j] == 0) {
                    gameItem = new GameItem(mesh);
                    gameItem.setScale(0.5f);
                    gameItem.setPosition(j, 0, i);
                    items.add(gameItem);
                }
            }
        }
        return items;
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
        } else if (window.isKeyPressed(GLFW_KEY_X)){
            cameraInc.y = 1;
        }

        if (window.isKeyPressed(GLFW_KEY_L)) {
            // just to load bunny =)
            glPolygonMode(GL_FRONT_AND_BACK, GL_LINE );
        }

        if (window.isKeyPressed(GLFW_KEY_F)) {
            glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        camera.movePosition(cameraInc.x * CAMERA_POS_STEP,
                cameraInc.y * CAMERA_POS_STEP,
                cameraInc.z * CAMERA_POS_STEP);

        if (!mouseInput.isRightButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY,
                    rotVec.y * MOUSE_SENSITIVITY, 0);
        }
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
