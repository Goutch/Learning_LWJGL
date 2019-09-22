package com.engine.geometry;

import com.engine.util.Color;

import com.engine.math.Vector2f;
import com.engine.math.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glFlush;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Quad extends Geometry {

    Vector2f size;
    public Quad(Vector3f position,float rotation, Vector2f size, Color color)
    {
        super(position,rotation,color);
        this.size=size;
    }
    public void render()
    {
        glTranslatef(position.x,position.y,0);
        glRotatef(rotation,0,0,1);
        glColor4f(color.r, color.g, color.b, color.a);
        glBegin(GL_QUADS);
        glVertex2f(-(size.x / 2), - (size.y / 2));
        glVertex2f(size.x/ 2,  - (size.y / 2));
        glVertex2f(size.x / 2,  (size.y / 2));
        glVertex2f(-(size.x / 2), (size.y / 2));
        glEnd();
        glFlush();
        glRotatef(-rotation,0,0,1);
        glTranslatef(-position.x,-position.y,0);
    }
}
