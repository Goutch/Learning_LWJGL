package com.engine.inputs;

import org.lwjgl.glfw.*;

/**
 * GLFW window inputs handler of mouse and keyboard
 */
public class Input {
    private static boolean[] isKeyPressed = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] isMouseButtonPressed = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX;
    private static double mouseY;
    private static double scroll;
    private static GLFWCursorPosCallback cursorCallback;
    private static GLFWKeyCallback keyCallback;
    private static GLFWMouseButtonCallback mouseButtonCallback;
    private static GLFWScrollCallback mouseScrollCallback;

    public static void init(long window) {

        keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                isKeyPressed[key] = (action != GLFW.GLFW_RELEASE);
            }
        };
        cursorCallback = new GLFWCursorPosCallback() {
            @Override
            public void invoke(long window, double xpos, double ypos) {
                mouseX = xpos;
                mouseY = ypos;
            }
        };
        mouseButtonCallback = new GLFWMouseButtonCallback() {
            @Override
            public void invoke(long window, int button, int action, int mods) {
                isMouseButtonPressed[button] = (action != GLFW.GLFW_RELEASE);
            }
        };
        mouseScrollCallback = new GLFWScrollCallback() {
            @Override
            public void invoke(long window, double xoffset, double yoffset) {
                scroll += yoffset;
            }
        };
        GLFW.glfwSetScrollCallback(window, mouseScrollCallback);
        GLFW.glfwSetKeyCallback(window, keyCallback);
        GLFW.glfwSetMouseButtonCallback(window, mouseButtonCallback);
        GLFW.glfwSetCursorPosCallback(window, cursorCallback);
    }

    /**
     * @param key GLFW key code
     * @return True if key is pressed.
     */
    public static boolean IsKeyPressed(int key) {
        return isKeyPressed[key];
    }

    public static boolean[] IsMouseButtonPressed() {
        return isMouseButtonPressed;
    }


    public static double getMouseX() {
        return mouseX;
    }

    public static double getMouseY() {
        return mouseY;
    }

    public static double getScroll() {
        return scroll;
    }


    public static void dispose() {
        cursorCallback.free();
        keyCallback.free();
        mouseButtonCallback.free();
        mouseScrollCallback.free();
    }
}
