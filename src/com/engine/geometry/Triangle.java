package com.engine.geometry;

import com.engine.events.EventManager;
import com.engine.events.UpdateListener;
import com.engine.inputs.Input;
import com.engine.util.Color;
import com.engine.math.Vector2f;
import com.engine.math.Vector3f;
import org.lwjgl.glfw.GLFW;

import static org.lwjgl.opengl.GL11.*;


public class Triangle extends Geometry implements UpdateListener {
    protected Vector2f size;

    public Triangle(Vector3f position,float rotation, Vector2f size, Color color)
    {
        super(position,rotation,color);
        this.size=size;
        EventManager.subscribeUpdate(this);
    }

    public void render()
    {
        glTranslatef(position.x,position.y,0);
        glBegin(GL_TRIANGLES);
        glColor4f(color.r, color.g, color.b, color.a);
        glVertex2f(-(size.x / 2), - (size.y / 2));
        glVertex2f(size.x/ 2,  - (size.y / 2));
        glVertex2f(0,  (size.y / 2));
        glEnd();
        glFlush();
       glTranslatef(-position.x,-position.y,0);
    }

    @Override
    public void update(float deltaTime) {
        rotation++;
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_W))
        {
            position.x+=1*deltaTime;
        }
    }
}
