package com.engine.entity.entity2D;

import com.engine.entity.entity3D.Transform;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class RectTranform extends Transform {
    public enum PivotPoint {
        NONE,

        TOP_RIGHT,
        TOP_CENTER,
        TOP_LEFT,

        BOTTOM_RIGHT,
        BOTTOM_CENTER,
        BOTTOM_LEFT,

        CENTER_RIGHT,
        CENTER,
        CENTER_LEFT,
    }
    public PivotPoint pivot;
    public Vector2f size;
    public RectTranform(Vector3f position, float rotation, Vector2f size,  PivotPoint pivot) {
        super(position, new Vector3f(0,0,rotation), 1);
        this.pivot=pivot;
        this.size=size;
        updatePivot();
    }
    private void updatePivot()
    {
        switch (pivot)
        {
            case TOP_RIGHT:
                position.y-=size.y;
                position.x-=size.x;
                break;
            case TOP_CENTER:
                position.x-=size.x*0.5;
                position.y-=size.y;
                break;
            case TOP_LEFT:
                position.x-=size.x;
                position.y-=size.y;
                break;
            case BOTTOM_RIGHT:
                position.x-=size.x;
                break;
            case BOTTOM_CENTER:
                position.x-=size.x*0.5f;
                break;
            case BOTTOM_LEFT:
                break;
            case CENTER_RIGHT:
                position.x-=size.x;
                position.y-=size.y*.5;
                break;
            case CENTER:
                position.x-=size.x*.5;
                position.y-=size.y*.5;
                break;
            case CENTER_LEFT:
                position.y-=size.y*.5;
                break;
        }
    }
}
