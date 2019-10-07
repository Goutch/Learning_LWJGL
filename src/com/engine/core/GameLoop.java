package com.engine.core;

import com.engine.events.EventManager;
import com.engine.inputs.Input;
import com.engine.entity.Camera;
import com.engine.rendering.GUIRenderer;
import com.engine.rendering.Window;
import com.engine.rendering.Renderer;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;


import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class GameLoop {
    private static Thread gameLoopThread;
    private static boolean running = false;
    private static Window window;
    private final static boolean PRINT_FPS = true;
    private static long lastFramePrint = 0;
    private static GameLogic gameLogic;
    private static int frames = 0;
    private static long startTime;

    /**
     * Start the gameloop and send events to the game logic listener.
     * @param gameLogicListener
     */
    public static void start(GameLogic gameLogicListener) {
        gameLogic = gameLogicListener;
        if (running)
            return;
        running = true;
        gameLoopThread = new Thread() {
            public void run() {

                init();

                long current = System.currentTimeMillis();
                long previous = System.currentTimeMillis();
                while (!glfwWindowShouldClose(window.getWindow())) {
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

        Window.createDisplay(GameOptions.WINDOW_START_WIDTH, GameOptions.WINDOW_START_HEIGHT, GameOptions.TITLE);
        Renderer.init();
        GUIRenderer.init();
        Input.init(Window.getWindow());
        Camera.setMainCamera(new Camera(new Vector3f(0,0,0),new Vector3f(0,0,0),90,1000));
        gameLogic.init();
        EventManager.onInit();
    }

    public static float getTimeSinceStart() {
        return (float)(System.currentTimeMillis() - startTime)*0.001f;
    }

    private static void getInputs() {
        window.getInputs();
        if (Input.IsKeyPressed(GLFW.GLFW_KEY_ESCAPE)) {
            window.close();
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
        Camera.main.calculateViewMatrix();
        Renderer.preRender();
        gameLogic.render();
        EventManager.onRender();
        Renderer.render();
        GUIRenderer.render();
        window.swapBuffers();
    }

    private static void dispose() {
        gameLogic.dispose();
        EventManager.onDispose();
        Input.dispose();
    }
}
