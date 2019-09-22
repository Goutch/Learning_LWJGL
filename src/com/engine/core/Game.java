package com.engine.core;

import com.engine.geometry.Quad;
import com.engine.geometry.Triangle;
import com.engine.util.Color;
import com.engine.math.Vector2f;
import com.engine.math.Vector3f;

public class Game {
    public Game()
    {
        Triangle t =new Triangle(new Vector3f(-1f,0,0),0,new Vector2f(1,1), Color.RED);
    }

    public void start()
    {
        GameLoop.start();

    }

}
