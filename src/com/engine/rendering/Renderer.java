package com.engine.rendering;


import com.engine.core.GameOptions;
import com.engine.entities.ColoredMeshRenderer;
import com.engine.entities.Entity;
import com.engine.entities.MeshRenderer;
import com.engine.entities.TexturedMeshRenderer;
import com.engine.geometry.ColoredMesh;
import com.engine.geometry.Mesh;
import com.engine.rendering.shader.ShaderProgram;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import static org.lwjgl.opengl.GL11.GL_LINE_LOOP;


public class Renderer {
    public static void init()
    {
       // GL11.glEnable(GL11.GL_CULL_FACE);
       // GL11.glCullFace(GL11.GL_BACK);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(GameOptions.CLEAR_COLOR.r,GameOptions.CLEAR_COLOR.b,GameOptions.CLEAR_COLOR.g,GameOptions.CLEAR_COLOR.a);
        clear();

    }
    /**
     * Render the mesh of an entity on the screen according to the entity transform.
     * @param meshRenderer
     */
    public static void render(MeshRenderer meshRenderer)
    {
        ShaderProgram shader=meshRenderer.getShader();
        Mesh mesh=meshRenderer.getMesh();
        shader.start();
        shader.loadUniforms(meshRenderer);
        GL30.glBindVertexArray(mesh.getVaoID());
        for (int i = 0; i<shader.getAttributeCount(); i++) { GL20.glEnableVertexAttribArray(i); }

        GL11.glDrawElements(GL11.GL_TRIANGLES,mesh.getVertexCount(),GL11.GL_UNSIGNED_INT,0);
        for (int i = 0; i<shader.getAttributeCount(); i++) { GL20.glDisableVertexAttribArray(i); }
        GL30.glBindVertexArray(0);
        shader.stop();
    }
    public static void render(TexturedMeshRenderer meshRenderer)
    {
        ShaderProgram shader=meshRenderer.getShader();
        Mesh mesh=meshRenderer.getMesh();
        shader.start();
        shader.loadUniforms(meshRenderer);
        meshRenderer.bindTexture();
        GL30.glBindVertexArray(mesh.getVaoID());
        for (int i = 0; i<shader.getAttributeCount(); i++) { GL20.glEnableVertexAttribArray(i); }
        GL11.glDrawElements(GL11.GL_TRIANGLES,mesh.getVertexCount(),GL11.GL_UNSIGNED_INT,0);
        for (int i = 0; i<shader.getAttributeCount(); i++) { GL20.glDisableVertexAttribArray(i); }
        GL30.glBindVertexArray(0);
        meshRenderer.unBindTexture();
        shader.stop();
    }
    public static void render(ColoredMeshRenderer meshRenderer)
    {
        ShaderProgram shader=meshRenderer.getShader();
        Mesh mesh=meshRenderer.getMesh();
        shader.start();
        shader.loadUniforms(meshRenderer);
        GL30.glBindVertexArray(mesh.getVaoID());
        for (int i = 0; i<shader.getAttributeCount(); i++) { GL20.glEnableVertexAttribArray(i); }
        GL11.glDrawElements(GL11.GL_TRIANGLES,mesh.getVertexCount(),GL11.GL_UNSIGNED_INT,0);

        for (int i = 0; i<shader.getAttributeCount(); i++) { GL20.glDisableVertexAttribArray(i); }
        GL30.glBindVertexArray(0);
        shader.stop();
    }
    public static void preRender()
    {

        clear();
    }
    public static void setWireframe(boolean wireframe)
    {
        if (!wireframe)
        {
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK,GL11. GL_FILL);
        }
        else
        {
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK,GL11. GL_LINE);
        }
    }
    /**
     * Clear the screen with the clear color.
     */
    public static void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
    }

}
