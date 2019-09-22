package com.engine.core;

import com.engine.events.EventManager;
import com.engine.inputs.Input;
import com.engine.rendering.Display;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class GameLoop {
    private static boolean running = false;
    private static Display display;
    private final static boolean PRINT_FPS = true;
    private static long lastFramePrint = 0;

    private static int frames = 0;

    public static void start() {
        if (running)
            return;
        running = true;

        init();
        long lastFrameTime = System.currentTimeMillis();
        long currentTime;
        while (!glfwWindowShouldClose(display.getWindow())) {
            currentTime = System.currentTimeMillis();
            getInputs();
            update((float)(currentTime - lastFrameTime)/1000f);
            render();
            if (PRINT_FPS) {
                printFPS();
            }
            lastFrameTime = currentTime;
        }
        dispose();
    }

    private static void init() {
        display = new Display(GameOptions.WINDOW_START_WIDTH, GameOptions.WINDOW_START_HEIGHT, GameOptions.TITLE);
        Input input = new Input(display.getWindow());
    }

    private static void getInputs() {
        display.getInputs();
        if (Input.IsKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
            display.close();
        }
    }

    private static void update(float delta) {
        display.update();
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
        display.render();
        EventManager.onRender();
        display.swapBuffers();
    }

    private static void dispose() {
        EventManager.onDispose();
    }


}
