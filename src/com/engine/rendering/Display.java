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

import static org.lwjgl.system.MemoryUtil.NULL;

/**
 * Represent an GLFW display.
 */
public class Display{


    private static GLFWErrorCallback errorCallback = GLFWErrorCallback.createPrint(System.err);
    private static GLFWWindowSizeCallback windowResizeCallback;
    private static  long window;

    public static int getWidth() {
        return width;
    }

    public static int getHeight() {
        return height;
    }

    private static int width;
    private static int height;
    private static float aspectRatio;

    public static void createDisplay(int windowWidth, int windowHeight,String title)
    {
        width=windowWidth;
        height=windowHeight;
        aspectRatio=(float) height/width;
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
            aspectRatio=(float) height/width;
            glViewport(0,0,width,height);
            EventManager.onWindowResize(w,h);
        });


        if(GameOptions.VSYNC)
        {
            glfwSwapInterval(1);
        }

        glfwShowWindow(window);
    }

    private static void centerWindow(){
        GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(window, (vidmode.width() - width) / 2, (vidmode.height() - height) / 2);
    }

    public static void getInputs()
    {
        glfwPollEvents();
    }


    public static void swapBuffers(){
        glfwSwapBuffers(window);
    }


    public static void close()
    {
        glfwSetWindowShouldClose(window, true);
    }


    public static long getWindow()
    {
        return window;
    }


    public static void setFullScreen(boolean fullScreen)
    {
        glfwSetWindowMonitor(window,fullScreen?GLFW.glfwGetPrimaryMonitor():0,0,0,width,height,0);

    }
    public static float getAspectRatio()
    {
        return aspectRatio;
    }
    public static void dispose() {

        errorCallback.free();
        windowResizeCallback.free();
        GLFW.glfwDestroyWindow(window);
        GLFW.glfwTerminate();
    }
}
