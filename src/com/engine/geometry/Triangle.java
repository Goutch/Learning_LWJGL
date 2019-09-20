package com.engine.geometry;

import com.engine.util.Color;
import com.engine.util.Vector2f;
import com.engine.util.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glFlush;

public class Triangle extends Geometry {
    protected Vector2f size;
    public Triangle(Vector3f position, Vector2f size, Color color)
    {
        super(position,color);
        this.size=size;

    }

    public void render()
    {
        glBegin(GL_TRIANGLES);
        glColor4f(color.r, color.g, color.b, color.a);
        glVertex2f(-(size.x / 2), - (size.y / 2));
        glVertex2f(size.x/ 2,  - (size.y / 2));
        glVertex2f(0,  (size.y / 2));
        glEnd();
        glFlush();
    }
}
