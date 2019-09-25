package com.engine.rendering;


import com.engine.geometry.Geometry;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;


public class Renderer {

    public static void init(){


    };
    public static void render(Geometry model,int atribsNumber)
    {
        GL30.glBindVertexArray(model.getVaoID());
        for (int i=0;i<atribsNumber;i++)
        {
            GL20.glEnableVertexAttribArray(i);
        }
        GL11.glDrawElements(GL11.GL_TRIANGLES,model.getVertexCount(),GL11.GL_UNSIGNED_INT,0);
        for (int i=0;i<atribsNumber;i++)
        {
            GL20.glDisableVertexAttribArray(i);
        }
        GL30.glBindVertexArray(0);
    }


    public static void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

}
