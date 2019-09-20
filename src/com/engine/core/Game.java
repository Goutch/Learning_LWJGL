package com.engine.core;

import com.engine.events.WindowResizeListener;
import com.engine.geometry.Quad;
import com.engine.rendering.Display;
import com.engine.util.Color;
import com.engine.util.Vector2f;
import com.engine.util.Vector3f;

public class Game {
    public Game()
    {
        Quad q=new Quad(new Vector3f(0,0,0),new Vector2f(1,1), Color.RED);
    }
    public void start()
    {
        GameLoop.start();

    }

}
