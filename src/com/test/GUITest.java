package com.test;

import com.engine.core.GameLogic;
import com.engine.entities.PanelRenderer;
import com.engine.gui.Panel;
import org.joml.Vector2i;
import org.joml.Vector3f;

public class GUITest implements GameLogic {
    PanelRenderer panel;
    @Override
    public void init() {

        panel=new PanelRenderer(new Panel(new Vector2i(1,1)),new Vector3f(0,0,0));

    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        panel.render();
    }

    @Override
    public void dispose() {

    }
}
