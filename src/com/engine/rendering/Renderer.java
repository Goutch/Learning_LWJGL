package com.engine.rendering;


import com.engine.rendering.shader.Shader;
import com.engine.rendering.shader.ShaderProgram;

import org.lwjgl.opengl.GL11;



public class Renderer {
    private static ShaderProgram shaderProgram;
    public static void init(){
        try
        {
            shaderProgram = new ShaderProgram();
            shaderProgram.createVertexShader(Shader.VERTEX_SOURCE);
            shaderProgram.createFragmentShader(Shader.FRAGMENT_SOURCE);
            shaderProgram.link();
        }
        catch (Exception e)
        {

        }

    };

    public static void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }


    public static void bindShader()
    {
        shaderProgram.bind();
    }
    public static void unBindShader()
    {
        shaderProgram.unbind();
    }
}
