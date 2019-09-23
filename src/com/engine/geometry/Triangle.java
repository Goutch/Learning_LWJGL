package com.engine.geometry;

import com.engine.events.EventManager;
import com.engine.events.UpdateListener;
import com.engine.inputs.Input;
import com.engine.rendering.Renderer;
import com.engine.util.Color;
import com.engine.math.Vector2f;
import com.engine.math.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL20C.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL30C.glBindVertexArray;
import static org.lwjgl.system.MemoryUtil.memFree;


public class Triangle extends Geometry implements UpdateListener {
    protected Vector2f size;
    private float[] vertices;
    private FloatBuffer verticesBuffer;
    private int vaoId;
    private int vboId;

    public Triangle(Vector3f position, float rotation, Vector2f size, Color color) {
        super(position, rotation, color);
        EventManager.subscribeUpdate(this);
        this.size = size;
        vertices = new float[]{
                0.0f, size.y / 2, 0.0f,
                -size.x / 2, -size.x / 2, 0.0f,
                size.x / 2, -size.y / 2, 0.0f
        };
        verticesBuffer = MemoryUtil.memAllocFloat(vertices.length);
        verticesBuffer.put(vertices).flip();
        vaoId = glGenVertexArrays();
        vboId = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboId);
        glBufferData(GL_ARRAY_BUFFER, verticesBuffer, GL_STATIC_DRAW);
        memFree(verticesBuffer);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        // Unbind the VBO
        glBindBuffer(GL_ARRAY_BUFFER, 0);

        // Unbind the VAO
        glBindVertexArray(0);
        if (verticesBuffer != null) {
            MemoryUtil.memFree(verticesBuffer);
        }

    }

    public void render() {

        //glTranslatef(position.x, position.y, 0);
        //glBegin(GL_TRIANGLES);
        //glColor4f(color.r, color.g, color.b, color.a);
        //glVertex2f(-(size.x / 2), -(size.y / 2));
        //glVertex2f(size.x / 2, -(size.y / 2));
        //glVertex2f(0, (size.y / 2));
        //glEnd();
        //glFlush();
        //glTranslatef(-position.x, -position.y, 0);
        Renderer.bindShader();

        // Bind to the VAO
        glBindVertexArray(vaoId);
        glEnableVertexAttribArray(0);

        // Draw the vertices
        glDrawArrays(GL_TRIANGLES, 0, 3);
        System.out.println("Render trig");
        // Restore state
        glDisableVertexAttribArray(0);
        glBindVertexArray(0);
        Renderer.unBindShader();

    }

    @Override
    public void update(float deltaTime) {
        rotation++;
        if (Input.IsKeyPressed(GLFW.GLFW_KEY_W)) {
            position.x += 1 * deltaTime;
        }
    }
}
