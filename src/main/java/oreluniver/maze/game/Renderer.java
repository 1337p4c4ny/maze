package oreluniver.maze.game;

import oreluniver.maze.engine.GameItem;
import oreluniver.maze.engine.Utils;
import oreluniver.maze.engine.Window;
import oreluniver.maze.engine.gfx.Camera;
import oreluniver.maze.engine.gfx.Mesh;
import oreluniver.maze.engine.gfx.ShaderProgram;
import oreluniver.maze.engine.gfx.Transformation;
import org.joml.Matrix4f;

import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class Renderer {

    /**
     * FOV in Radians - field of view
     */
    private static final float FOV = (float) Math.toRadians(60.0f);

    private static final float Z_NEAR = 0.01f;

    private static final float Z_FAR = 1000.0f;

    private Transformation transformation;

    private ShaderProgram shaderProgram;

    public Renderer() {
        transformation = new Transformation();
    }

    public void init() throws Exception {
        shaderProgram = new ShaderProgram();
        shaderProgram.createVertexShader(Utils.loadResource("/shaders/vertex.vs.glsl"));
        shaderProgram.createFragmentShader(Utils.loadResource("/shaders/fragment.fs.glsl"));
        shaderProgram.link();

        shaderProgram.createUniform("projectionMatrix");
        shaderProgram.createUniform("modelViewMatrix");
        shaderProgram.createUniform("texture_sampler");

        // Create uniform for default colour and the flag that controls it
        shaderProgram.createUniform("colour");
        shaderProgram.createUniform("useColour");
    }

    public void clear() {
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public void render(Window window, Camera camera, List<GameItem> gameItems) {
        clear();

        if (window.isResized()) {
            glViewport(0, 0, window.getWidth(), window.getHeight());
            window.setResized(false);
        }

        shaderProgram.bind();

        shaderProgram.setUniform("texture_sampler", 0);

        Matrix4f projectionMatrix = transformation.getProjectionMatrix(FOV, window.getWidth(), window.getHeight(),
                Z_NEAR, Z_FAR);
        shaderProgram.setUniform("projectionMatrix", projectionMatrix);

        Matrix4f viewMatrix = transformation.getViewMatrix(camera);

        // Render game items
        for(GameItem gameItem : gameItems) {
            Mesh mesh = gameItem.getMesh();
            // Set model view matrix for this item
            Matrix4f modelViewMatrix = transformation.getModelViewMatrix(gameItem, viewMatrix);
            shaderProgram.setUniform("modelViewMatrix", modelViewMatrix);
            // Render the mes for this game item
            shaderProgram.setUniform("colour", mesh.getColor());
            shaderProgram.setUniform("useColour", mesh.isTextured() ? 0 : 1);
            mesh.render();
        }

        shaderProgram.unbind();
    }

    public void cleanup() {
        if (shaderProgram != null) {
            shaderProgram.cleanup();
        }
    }
}
