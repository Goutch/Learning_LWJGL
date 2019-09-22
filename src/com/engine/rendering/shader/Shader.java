package com.engine.rendering.shader;



import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20C.glCompileShader;
import static org.lwjgl.opengl.GL20C.glCreateShader;
import static org.lwjgl.opengl.GL30.glBindFragDataLocation;

public class Shader {

    public static final String VERTEX_SOURCE =
            "#version 150 core\n" +
            "in vec3 position;\n" +
            "in vec3 color;\n" +
            "out vec3 vertexColor;\n" +
            "uniform mat4 model;\n" +
            "uniform mat4 view;\n" +
            "uniform mat4 projection;\n" +
            "void main() {\n" +
            "    vertexColor = color;\n" +
            "    mat4 mvp = projection * view * model;\n" +
            "    gl_Position = mvp * vec4(position, 1.0);\n" +
            "}";
    public static final String FRAGMENT_SOURCE =
            "#version 150 core\n" +
            "in vec3 vertexColor;\n" +
            "out vec4 fragColor;\n" +
            "void main() {\n" +
            "    fragColor = vec4(vertexColor, 1.0);\n" +
            "}";

    private static int vertexShader;
    private static int fragmentShader;
    private static int shaderProgram;


    public static void init()
    {
        int status;

        //VERTEX_SHADER
        int vertexShader = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexShader, VERTEX_SOURCE);
        glCompileShader(vertexShader);
        status = glGetShaderi(vertexShader, GL_COMPILE_STATUS);
        if (status != GL_TRUE) {
            throw new RuntimeException(glGetShaderInfoLog(vertexShader));
        }

        //FRAGMENT_SHADER
        int fragmentShader = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentShader, FRAGMENT_SOURCE);
        glCompileShader(fragmentShader);
        status = glGetShaderi(fragmentShader, GL_COMPILE_STATUS);
        if (status != GL_TRUE) {
            throw new RuntimeException(glGetShaderInfoLog(fragmentShader));
        }

        //SHADER_PROGRAM
        shaderProgram = glCreateProgram();
        glAttachShader(shaderProgram, vertexShader);
        glAttachShader(shaderProgram, fragmentShader);
        glBindFragDataLocation(shaderProgram, 0, "fragColor");
        glLinkProgram(shaderProgram);

        status = glGetProgrami(shaderProgram, GL_LINK_STATUS);
        if (status != GL_TRUE) {
            throw new RuntimeException(glGetProgramInfoLog(shaderProgram));
        }

        glUseProgram(shaderProgram);
    }

}
