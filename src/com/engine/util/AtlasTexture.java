package com.engine.util;

import org.joml.Vector2f;
import org.joml.Vector2i;

public class AtlasTexture extends Texture {
    int numRows, numColumn;
    Vector2i cellSize;
    public AtlasTexture(String path,Vector2i cellSize,int numRows, int numColumn) {
        super(path);
        this.numRows=numRows;
        this.numColumn=numColumn;
    }
    public Vector2f getUVS(int index)
    {
        int col=index%numRows;
        int row=index-col/numRows;
        return new Vector2f((float) col*cellSize.x/width(),(float) row*cellSize.y/height());
    }
}
