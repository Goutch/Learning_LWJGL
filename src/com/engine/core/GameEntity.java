package com.engine.core;

import com.engine.math.Vector3f;

public class GameEntity {
    protected Vector3f rotation=new Vector3f(0,0,0);
    protected Vector3f position=new Vector3f(0,0,0);
    GameEntity(Vector3f position,Vector3f rotation)
    {
        this.position=position;
        this.rotation=rotation;
    }
}
