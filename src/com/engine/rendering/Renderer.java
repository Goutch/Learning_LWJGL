package com.engine.rendering;


import com.engine.entities.GameEntity;
import com.engine.geometry.Geometry;
import com.engine.rendering.shader.ShaderProgram;
import com.engine.entities.Transform;
import com.engine.rendering.shader.StaticShader;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;


public class Renderer {

    public static void init(){


    };
    public static void render(GameEntity entity)
    {
        Geometry geometry=entity.getGeometry();
        StaticShader shader=geometry.getShader();
        shader.start();
        geometry.bindTexture();
        GL30.glBindVertexArray(geometry.getVaoID());
        for (int i=0;i<shader.getAtributeCount();i++) { GL20.glEnableVertexAttribArray(i); }
        Matrix4f transformMatrix= entity.transform.toTranformMatrix();
        shader.loadTransformationMatrix(transformMatrix);
        GL11.glDrawElements(GL11.GL_TRIANGLES,geometry.getVertexCount(),GL11.GL_UNSIGNED_INT,0);
        for (int i=0;i<shader.getAtributeCount();i++) { GL20.glDisableVertexAttribArray(i); }
        GL30.glBindVertexArray(0);
        geometry.unBindTexture();
        shader.stop();
    }


    public static void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

}
