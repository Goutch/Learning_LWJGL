package com.engine.inputs;

import com.engine.util.Vector2i;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWCursorPosCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWMouseButtonCallback;

public class Input {
    private static boolean[] isKeyPressed = new boolean[GLFW.GLFW_KEY_LAST];
    private static boolean[] isMouseButtonPressed = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];
    private static double mouseX;
    private static double mouseY;

    public Input(long window) {
        GLFW.glfwSetKeyCallback(window,(w, key, scancode, action, mods)-> {
            isKeyPressed[key] = (action != GLFW.GLFW_RELEASE);
        });
        GLFW.glfwSetMouseButtonCallback(window,(w, button, action, mods)-> {
            isMouseButtonPressed[button] = (action != GLFW.GLFW_RELEASE);
        });
        GLFW.glfwSetCursorPosCallback(window,(w,xpos, ypos) ->{
                mouseX = xpos;
                mouseY = ypos;
        });
    }

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
}
