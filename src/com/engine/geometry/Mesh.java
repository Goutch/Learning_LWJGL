package com.engine.geometry;

import com.engine.math.Vector3f;
import com.engine.util.Color;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class Mesh extends Geometry {
    private ArrayList<Vector3f> vertices;
    Mesh(Vector3f position, float rotation, Color color) {
        super(position, rotation, color);
        vertices=new ArrayList<Vector3f>();
    }
    private void addTriangle(Vector3f v1,Vector3f v2,Vector3f v3){
        vertices.add(v1);
        vertices.add(v2);
        vertices.add(v3);
    }
    @Override
    public void render() {
        glTranslatef(position.x,position.y,position.z);
        glBegin(GL_TRIANGLES);
        glColor4f(color.r, color.g, color.b, color.a);
        for (Vector3f v:
             vertices) {
            glVertex3f(v.x, v.y,v.z);
        }
        glEnd();
        glFlush();
        glTranslatef(-position.x,-position.y,position.z);
    }
}
