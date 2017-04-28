package oreluniver.maze.game;

import oreluniver.maze.engine.GameItem;
import oreluniver.maze.engine.IGameLogic;
import oreluniver.maze.engine.MouseInput;
import oreluniver.maze.engine.Window;
import oreluniver.maze.engine.gfx.*;
import oreluniver.maze.level.Maze;
import oreluniver.maze.level.MazeBuilder;
import oreluniver.maze.level.MazePos;
import org.joml.Vector2f;
import org.joml.Vector3f;

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

    private Maze maze;

    private Vector3f ambientLight;
    private PointLight pointLight;
    private DirectionalLight directionalLight;

    public MazeGame() {
        renderer = new Renderer();
    }

    @Override
    public void init(Window window) throws Exception {
        renderer.init(window);

        maze = MazeBuilder.build(50, 50, 2);


//        maze = new Maze(makeMockBitmap(), new ArrayList<>());
        gameItems = createLevel(maze);


//        GameItem bunny = loadBunny();
//        bunny.setPosition(maze.getWidth() / 2, 2, maze.getHeight() / 2);
//        gameItems.add(bunny);

        camera = new Camera();

        MazePos pos = Utils.getSpawnPosition(this.maze.bitmap);

        camera.setPosition(pos.col, 1, pos.row);
//        camera.setPosition(1, 0, 0);
//        camera.setRotation(0, 180, 0);
        cameraInc = new Vector3f();
        setUpLight();
    }


    private int [][] makeMockBitmap() {
        return new int [][] {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };
    }

    private List<GameItem> loadFloor() throws Exception {
        List<GameItem> items = new ArrayList<>();
        Texture texture = new Texture("/textures/grassblock_32.png");
        Mesh mesh = OBJLoader.loadMesh("/models/floor.obj");
        mesh.setMaterial(new Material(texture));
        GameItem floor = new GameItem(mesh);
        floor.setPosition(0, 0, -2);
        items.add(floor);
        return items;
    }


    private GameItem loadBunny() throws Exception {
        Mesh mesh = OBJLoader.loadMesh("/models/bunny.obj");
        GameItem gameItem = new GameItem(mesh);
//        gameItem.setScale(0.5f);
        return gameItem;
    }

    private List<GameItem> createLevel(Maze maze) throws Exception {
        Texture cubeTexture = new Texture("/textures/grassblock_32.png");

        Mesh wallMesh = OBJLoader.loadMesh("/models/cube.obj");


        wallMesh.setMaterial(new Material(cubeTexture));


        List<GameItem> items = new ArrayList<>();
        GameItem gameItem;
        for (int i = 0; i < maze.getHeight(); ++i) {
            for (int j = 0; j < maze.getWidth(); ++j) {
                if (maze.bitmap[i][j] == 0) {

                    // bottom brick
                    gameItem = new GameItem(wallMesh);
                    gameItem.setScale(0.5f);
                    gameItem.setPosition(j, 0, i);
                    items.add(gameItem);

                    //top brick
                    gameItem = new GameItem(wallMesh);
                    gameItem.setScale(0.5f);
                    gameItem.setPosition(j, 1, i);
                    items.add(gameItem);
                }

            }
        }
        items.add(createFloor(maze));
        items.add(createSkyBox());
        return items;
    }

    private GameItem createSkyBox() throws Exception {
        Texture texture = new Texture("/textures/skybox.png");
        Mesh mesh = OBJLoader.loadMesh("/models/skybox.obj");
        mesh.setMaterial(new Material(texture));

        GameItem skyBox = new GameItem(mesh);
        skyBox.setScale(500);
        return skyBox;
    }

    private GameItem createFloor(Maze maze) throws Exception {
        Texture floorTexture = new Texture("/textures/sand_1.png");
        Mesh floorMesh = OBJLoader.loadMesh("/models/floor.obj");
        floorMesh.setMaterial(new Material(floorTexture));
        GameItem floorItem;
        floorItem = new GameItem(floorMesh);
        float scale = Math.max(maze.getWidth(), maze.getHeight()) / 2 + 1;
        floorItem.setScale(scale);
        floorItem.setPosition(maze.getWidth() / 2, -0.5f, maze.getHeight() / 2);
        return floorItem;
    }

    private void setUpLight() {
        ambientLight = new Vector3f(0.3f, 0.3f, 0.3f);

        Vector3f lightColour = new Vector3f(1, 1, 1);
        Vector3f lightPosition = new Vector3f(0, 5, 0);
        float lightIntensity = 1.0f;

        pointLight = new PointLight(lightColour, lightPosition, lightIntensity);
        PointLight.Attenuation att = new PointLight.Attenuation(0.0f, 0.0f, 1.0f);
        pointLight.setAttenuation(att);

        lightPosition = new Vector3f(1, 5, 0);
        lightColour = new Vector3f(1, 1, 1);
        directionalLight = new DirectionalLight(lightColour, lightPosition, lightIntensity);
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
            glPolygonMode(GL_FRONT_AND_BACK, GL_LINE );
        }

        if (window.isKeyPressed(GLFW_KEY_F)) {
            glPolygonMode(GL_FRONT_AND_BACK, GL_FILL);
        }
    }

    @Override
    public void update(float interval, MouseInput mouseInput) {
        Vector3f v = new Vector3f(cameraInc.x * CAMERA_POS_STEP,
                                  cameraInc.y * CAMERA_POS_STEP,
                                  cameraInc.z * CAMERA_POS_STEP);

        Vector3f oldPos = camera.getPosition();
        camera.movePosition(v, this.maze.bitmap);
        Vector3f pos = camera.getPosition();
        Utils.checkCollision(oldPos, pos, this.maze.bitmap);

        if (!mouseInput.isRightButtonPressed()) {
            Vector2f rotVec = mouseInput.getDisplVec();
            camera.moveRotation(rotVec.x * MOUSE_SENSITIVITY,
                    rotVec.y * MOUSE_SENSITIVITY, 0);
        }
    }

    @Override
    public void render(Window window) {
        renderer.render(window, camera, gameItems, ambientLight, pointLight, directionalLight);
    }

    @Override
    public void cleanup () {
        renderer.cleanup();
        for (GameItem gameItem : gameItems) {
            gameItem.getMesh().cleanUp();
        }
    }


}
