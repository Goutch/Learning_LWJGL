package com.engine.graphics.geometry;

import com.engine.graphics.Color;
import com.engine.events.RenderListener;
import com.engine.util.Vector2f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.glFlush;

public class Quad extends Geometry implements RenderListener {

    Vector2f size;
    public Quad(Vector2f position, Vector2f size, Color color)
    {
        super(position,color);
        this.size=size;
    }
    public void render()
    {
        glTranslatef(position.x,position.y,0);

        glBegin(GL_QUADS);
        glColor4f(color.r, color.g, color.b, color.a);
        glVertex2f(-(size.x / 2), - (size.y / 2));
        glVertex2f(size.x/ 2,  - (size.y / 2));
        glVertex2f(size.x / 2,  (size.y / 2));
        glVertex2f(-(size.x / 2), (size.y / 2));
        glEnd();
        glFlush();

        glTranslatef(-position.x,-position.y,0);
    }
}
