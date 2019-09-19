package com.engine.rendering;

import com.engine.util.Color;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetWindowPos;
import static org.lwjgl.opengl.GL11.glViewport;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Display{
    private long window;
    private int width;
    private int height;
    private boolean PRINT_FPS=false;
    public Display(int width, int height,String title) {
        this.width=width;
        this.height=height;
        createWindow(title);
    }
    private void createWindow(String title)
    {
        if(!GLFW.glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }
        glfwMakeContextCurrent(window);
        GL.createCapabilities();
        //GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());

        // Center the window
        //glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);



        GLFW.glfwSetWindowSizeCallback(window,(window,w,h)->{
            width=w;
            height=h;
            glViewport(0,0,width,height);
        });

        Color c=Color.GREEN;
        GL11.glClearColor(c.r,c.b,c.g,c.a);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);


        glfwSwapInterval(1);
        glfwShowWindow(window);
    }
    public void getInputs()
    {
        //getInputs
        glfwPollEvents();
    }
    public void update(){

    }
    public void render(){
        glfwSwapBuffers(window);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
    }

    public void close()
    {
        glfwSetWindowShouldClose(window, true);
    }
    public long getWindow()
    {
        return window;
    }
    public void setFullScreen(boolean fullScreen)
    {
        glfwSetWindowMonitor(window,fullScreen?GLFW.glfwGetPrimaryMonitor():0,0,0,width,height,0);
    }

}
