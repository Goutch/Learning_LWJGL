package com.engine.core;

import com.engine.events.EventManager;
import com.engine.geometry.MeshLoader;
import com.engine.inputs.Input;
import com.engine.rendering.Display;
import com.engine.rendering.Renderer;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.CallbackI;


import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class GameLoop {
    private static Thread gameLoopThread;
    private static boolean running = false;
    private static Display display;
    private final static boolean PRINT_FPS = true;
    private static long lastFramePrint = 0;
    private static GameLogic gameLogic;
    private static int frames = 0;
    private static long startTime;

    public static void start(GameLogic gameLogicListener) {
        gameLogic = gameLogicListener;
        gameLoopThread = new Thread() {
            public void run() {

                if (running)
                    return;

                running = true;

                init();

                long current = System.currentTimeMillis();
                long previous = System.currentTimeMillis();
                while (!glfwWindowShouldClose(display.getWindow())) {
                    previous = current;
                    current = System.currentTimeMillis();
                    getInputs();
                    if (GameOptions.PRINT_FPS) {
                        printFPS();
                    }
                    update((float) (current - previous) / 1000);
                    render();

                }
                dispose();
            }
        };
        gameLoopThread.setName("GameLoop");
        gameLoopThread.start();
    }

    private static void init() {
        startTime = System.currentTimeMillis();
        display = new Display(GameOptions.WINDOW_START_WIDTH, GameOptions.WINDOW_START_HEIGHT, GameOptions.TITLE);
        Input.init(display.getWindow());
        Renderer.init();
        gameLogic.init();
        EventManager.onInit();
    }

    public static float getTimeSinceStart() {
        return (float)(System.currentTimeMillis() - startTime)*0.001f;
    }

    private static void getInputs() {
        display.getInputs();
        if (Input.IsKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
            display.close();
        }
    }

    private static void update(float delta) {
        gameLogic.update(delta);
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
        gameLogic.render();
        EventManager.onRender();
        display.swapBuffers();
    }

    private static void dispose() {
        gameLogic.dispose();
        EventManager.onDispose();
        Input.dispose();
        MeshLoader.dispose();

    }
}
