package com.engine.util;

import org.joml.Vector3f;
import org.joml.Vector4f;

public class Color {

    public static Color RED=new Color(1,0,0,1);
    public static Color GREEN=new Color(0,1,0,1);
    public static Color BLUE=new Color(0,0,1,1);
    public static Color WHITE=new Color(1,1,1,1);
    public static Color BLACK=new Color(0,0,0,1);

    public float r=1,g=1,b=1,a=1;
    public Color(float r,float g,float b,float a){
        this.r=Math.max(0,Math.min(1,r));
        this.g=Math.max(0,Math.min(1,g));
        this.b=Math.max(0,Math.min(1,b));
        this.a=Math.max(0,Math.min(1,a));
    }
    public Vector3f toVector3f()
    {
        return new Vector3f(r,g,b);
    }
    public Vector4f toVector4f()
    {
        return new Vector4f(r,g,b,a);
    }
}
