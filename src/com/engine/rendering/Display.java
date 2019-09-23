package com.engine.rendering;

import com.engine.core.GameOptions;
import com.engine.events.DisposeListener;
import com.engine.events.EventManager;
import com.engine.util.Color;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;


import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Display implements DisposeListener {
    private GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.err);
    private GLFWWindowSizeCallback windowResizeCallback;
    private boolean PRINT_FPS=false;
    private final Color BACKGROUND_COLOR=Color.WHITE;
    private long window;
    private int width;
    private int height;


    public Display(int width, int height,String title) {
        EventManager.subscribeDispose(this);
        this.width=width;
        this.height=height;
        createWindow(title);
    }
    private void createWindow(String title)
    {
        if(!GLFW.glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        glfwDefaultWindowHints();

        window = glfwCreateWindow(width, height, title, NULL, NULL);
        if (window == NULL) {
            glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }
        glfwSetErrorCallback(errorCallback);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        GLFW.glfwSetWindowSizeCallback(window,(window,w,h)->{
            width=w;
            height=h;
            glViewport(0,0,width,height);
            EventManager.onWindowResize(w,h);
        });

        GL11.glClearColor(GameOptions.CLEAR_COLOR.r,GameOptions.CLEAR_COLOR.b,GameOptions.CLEAR_COLOR.g,GameOptions.CLEAR_COLOR.a);
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

        if(GameOptions.VSYNC)
        {
            glfwSwapInterval(1);
        }

        glfwShowWindow(window);
    }
    private void centerWindow(){
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
    }
    public void getInputs()
    {
        //getInputs
        glfwPollEvents();
    }

    public void swapBuffers(){
        glfwSwapBuffers(window);
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

    @Override
    public void onDispose() {

        errorCallback.free();
        windowResizeCallback.free();
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
