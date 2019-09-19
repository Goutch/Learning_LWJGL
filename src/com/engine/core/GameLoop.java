package com.engine.core;

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
        Thread thread = new Thread() {
            public void run() {

                init();
                while (!glfwWindowShouldClose(display.getWindow())) {
                    getInputs();
                    update(0f);
                    render();
                    if (PRINT_FPS) {
                        printFPS();
                    }
                }
                dispose();
            }
        };
        thread.setName("GameLoop");
        thread.start();
    }

    private static void init() {
        display = new Display(600, 400, "title");
        Input input=new Input(display.getWindow());
    }
    private static void getInputs(){
        display.getInputs();
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_ESCAPE))
        {
            display.close();
        }
    }
    private static void update(float delta) {
        display.update();
    }
    private static void printFPS()
    {
        frames++;
        if (lastFramePrint < System.currentTimeMillis() - 1000) {
            lastFramePrint = System.currentTimeMillis();
            System.out.println(frames);
            frames = 0;
        }
    }
    private static void render() {
        display.render();
    }
    private static void dispose(){
        GLFW.glfwDestroyWindow(display.getWindow());
        //free callbacks
        GLFW.glfwTerminate();

    }


}
