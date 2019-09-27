package com.engine.rendering;


import com.engine.entities.Entity;
import com.engine.geometry.Mesh;
import com.engine.rendering.shader.BaseShader;
import com.engine.rendering.shader.ShaderProgram;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;


public class Renderer {

    public static void init(){


    };
    public static void render(Entity entity)
    {
        Mesh mesh=entity.getMesh();
        ShaderProgram shader=mesh.getShader();
        shader.start(entity);
        mesh.bindTexture();
        GL30.glBindVertexArray(mesh.getVaoID());
        for (int i=0;i<shader.getAtributeCount();i++) { GL20.glEnableVertexAttribArray(i); }
        GL11.glDrawElements(GL11.GL_TRIANGLES,mesh.getVertexCount(),GL11.GL_UNSIGNED_INT,0);
        for (int i=0;i<shader.getAtributeCount();i++) { GL20.glDisableVertexAttribArray(i); }
        GL30.glBindVertexArray(0);
        mesh.unBindTexture();
        shader.stop();
    }


    public static void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

}
