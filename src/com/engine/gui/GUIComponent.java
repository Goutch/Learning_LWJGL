package com.engine.gui;

import com.engine.geometry.Mesh;
import com.engine.util.Color;

public interface GUIComponent {
    public Mesh getMesh();
    public Color getColor();
}
