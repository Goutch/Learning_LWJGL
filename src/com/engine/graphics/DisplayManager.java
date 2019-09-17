package com.engine.graphics;

import com.engine.graphics.geometry.Quad;
import com.engine.graphics.geometry.Triangle;
import com.engine.util.Vector2f;
import com.sun.prism.image.ViewPort;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.system.MemoryUtil.NULL;

public class DisplayManager {
    private long window;

    public DisplayManager(int width, int height) {
        // Initialize GLFW. Most GLFW functions will not work before doing this.
        if (!glfwInit())
            throw new IllegalStateException("Unable to initialize GLFW");

        // Create the window

        window = glfwCreateWindow(width, height, "LWJGL", NULL, NULL);
        glfwMakeContextCurrent(window);
        GL.createCapabilities();

        glfwSetWindowSizeCallback(window,(long window,int w, int h)-> {

        });

        glfwShowWindow(window);

        glClearColor(1, 1, 1, 1);

        Quad q = new Quad(new Vector2f(0, 0), new Vector2f(100, 100), Color.BLACK);
        Triangle t = new Triangle(new Vector2f(1, 0), new Vector2f(100, 100), Color.RED);

        // Run the com.rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while (!glfwWindowShouldClose(window)) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            q.render();
            t.render();

            glfwSwapBuffers(window);
            glfwPollEvents();

        }
    }
}
