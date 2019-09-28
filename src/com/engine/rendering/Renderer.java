package com.engine.rendering;


import com.engine.entities.Entity;
import com.engine.geometry.Mesh;
import com.engine.rendering.shader.ShaderProgram;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;


public class Renderer {
    /**
     * Render the mesh of an entity on the screen according to the entity transform.
     * @param mesh
     */
    public static void render(Mesh mesh)
    {
        ShaderProgram shader=mesh.getShader();
        shader.start();
        shader.loadUniforms(mesh);
        mesh.bindTexture();
        GL30.glBindVertexArray(mesh.getVaoID());
        for (int i = 0; i<shader.getAttributeCount(); i++) { GL20.glEnableVertexAttribArray(i); }
        GL11.glDrawElements(GL11.GL_TRIANGLES,mesh.getVertexCount(),GL11.GL_UNSIGNED_INT,0);
        for (int i = 0; i<shader.getAttributeCount(); i++) { GL20.glDisableVertexAttribArray(i); }
        GL30.glBindVertexArray(0);
        mesh.unBindTexture();
        shader.stop();
    }
    public static void preRender()
    {
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        clear();
    }
    /**
     * Clear the screen with the clear color.
     */
    public static void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT|GL11.GL_DEPTH_BUFFER_BIT);
    }

}
