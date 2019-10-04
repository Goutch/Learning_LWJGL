package com.engine.gui;

import com.engine.geometry.Mesh;
import org.joml.Vector2i;
import org.joml.Vector3i;

public class Panel extends Mesh {

    Vector2i size;

    public Panel(Vector2i size)
    {
        this.size=size;

        updateMesh();
    }
    private void updateMesh()
    {
        int sizeX=1;
        int sizeY=1;
        vertices = new float[(sizeY+1)*(sizeX+1) * 3];
        indices = new int[(sizeY+1)*(sizeX+1)* 6];
        for (int x = 0; x < (sizeX+1); x++) {
            for (int y = 0; y < (sizeY+1); y++) {
                int index = (x * (sizeY+1) + y);
                vertices[index * 3] = x*size.x ;
                vertices[index * 3 + 1] = y*size.y;
                vertices[index * 3 + 2] = 0;
            }
        }
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                int index = (x * sizeY + y);
                indices[index * 6 ] = index + x + sizeY + 1;
                indices[index * 6 + 1] = index + x + 1;
                indices[index * 6 + 2] = index + x;
                indices[index * 6 + 3] = index + x + sizeY + 2;
                indices[index * 6 + 4] = index + x + 1;
                indices[index * 6 + 5] = index + x + sizeY + 1;
            }
        }
        build();
    }

}
