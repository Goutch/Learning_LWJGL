package com.engine.core;

import com.engine.events.EventManager;
import com.engine.geometry.Triangle;
import com.engine.inputs.Input;
import com.engine.math.Vector2f;
import com.engine.math.Vector3f;
import com.engine.rendering.Display;
import com.engine.rendering.Renderer;
import com.engine.util.Color;
import org.lwjgl.glfw.GLFW;


import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class GameLoop {
    private static Thread gameLoopThread;
    private static boolean running = false;
    private static Display display;
    private final static boolean PRINT_FPS = true;
    private static long lastFramePrint = 0;

    private static int frames = 0;

    public static void start() {
       // gameLoopThread = new Thread() {
            //public void run() {
                if (running)
                    return;

                running = true;

                init();

                long current=System.currentTimeMillis();
                long previous=System.currentTimeMillis();
                while (!glfwWindowShouldClose(display.getWindow())) {
                    previous=current;
                    current=System.currentTimeMillis();
                    getInputs();
                    if (GameOptions.PRINT_FPS) {
                        printFPS();
                    }
                    update((float)(current-previous)/1000);
                    render();

                }
                dispose();
            //}
       // };
        //gameLoopThread.setName("GameLoop");
       // gameLoopThread.start();
    }

    private static void init() {
        display = new Display(GameOptions.WINDOW_START_WIDTH, GameOptions.WINDOW_START_HEIGHT, GameOptions.TITLE);
        new Input(display.getWindow());
        Renderer.init();
        Triangle t =new Triangle( new Vector3f(),0, new Vector2f(1,1), Color.RED);
    }

    private static void getInputs() {
        display.getInputs();
        if (Input.IsKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
            display.close();
        }
    }

    private static void update(float delta) {
        EventManager.onUpdate(delta);
    }

    private static void printFPS() {
        frames++;
        if (lastFramePrint < System.currentTimeMillis() - 1000) {
            lastFramePrint = System.currentTimeMillis();
            System.out.println(frames);
            frames = 0;
        }
    }

    private static void render() {
        Renderer.clear();

        EventManager.onRender();

        display.swapBuffers();

    }

    private static void dispose() {
        EventManager.onDispose();
    }
}
