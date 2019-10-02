package com.engine.rendering;


import com.engine.core.GameOptions;
import com.engine.entities.MeshRenderer;
import com.engine.geometry.Mesh;
import com.engine.rendering.shader.ShaderProgram;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;


public class Renderer {
    public static void init() {
        // GL11.glEnable(GL11.GL_CULL_FACE);
        // GL11.glCullFace(GL11.GL_BACK);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(GameOptions.CLEAR_COLOR.r, GameOptions.CLEAR_COLOR.b, GameOptions.CLEAR_COLOR.g, GameOptions.CLEAR_COLOR.a);
        clear();

    }

    /**
     * Render the mesh of an entity on the screen according to the entity transform.
     *
     * @param meshRenderer
     */
    public static void render(MeshRenderer meshRenderer) {
        ShaderProgram shader = meshRenderer.getShader();
        Mesh mesh = meshRenderer.getMesh();
        shader.start();
        shader.loadUniforms(meshRenderer);
        meshRenderer.bindTexture();
        GL30.glBindVertexArray(mesh.getVaoID());

        GL20.glEnableVertexAttribArray(ShaderProgram.VERTICES_ATTRIBUTE_ID);
        GL20.glEnableVertexAttribArray(ShaderProgram.NORMALS_ATTRIBUTE_ID);
        GL20.glEnableVertexAttribArray(ShaderProgram.UVS_ATTRIBUTE_ID);
        GL20.glEnableVertexAttribArray(ShaderProgram.COLORS_ATTRIBUTE_ID);

        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

        GL20.glDisableVertexAttribArray(ShaderProgram.VERTICES_ATTRIBUTE_ID);
        GL20.glDisableVertexAttribArray(ShaderProgram.NORMALS_ATTRIBUTE_ID);
        GL20.glDisableVertexAttribArray(ShaderProgram.UVS_ATTRIBUTE_ID);
        GL20.glDisableVertexAttribArray(ShaderProgram.COLORS_ATTRIBUTE_ID);

        GL30.glBindVertexArray(0);
        meshRenderer.unBindTexture();
        shader.stop();
    }

    public static void preRender() {

        clear();
    }

    public static void setWireframe(boolean wireframe) {
        if (!wireframe) {
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        } else {
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        }
    }

    /**
     * Clear the screen with the clear color.
     */
    public static void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }

}
