package com.engine.rendering;


import com.engine.geometry.Geometry;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;


public class Renderer {

    public static void init(){


    };
    public static void render(Geometry model)
    {
        GL30.glBindVertexArray(model.getVoaID());
        GL20.glEnableVertexAttribArray(0);
        GL11.glDrawElements(GL11.GL_TRIANGLES,model.getVertexCount(),GL11.GL_UNSIGNED_INT,0);
        GL20.glDisableVertexAttribArray(0);
        GL30.glBindVertexArray(0);
    }
    public static void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

}
