package com.engine.gui;

import org.joml.Vector2i;

public class Panel {
    enum anchor{
        topCenter,
        topRight,
        topLeft,
        bottomCenter,
        bottomLeft,
        bottomRight,
        centerRight,
        centerLeft,
        center,
    }
    Vector2i size;
    Vector2i position;

    public Panel()
    {

    }
}
