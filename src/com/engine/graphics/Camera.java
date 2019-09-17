package com.engine.graphics;

import com.engine.util.Vector2f;

public class Camera {
    private static Vector2f position=new Vector2f(0,0);

    public static void setPosition(float x,float y)
    {
        position.x=x;
        position.y=y;
    }
    public static float getX()
    {
        return position.x;
    }
    public static float getY()
    {
        return position.y;
    }
}
