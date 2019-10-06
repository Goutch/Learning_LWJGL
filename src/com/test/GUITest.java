package com.test;

import com.engine.core.GameLogic;
import com.engine.entity.entity2D.RectTranform;
import com.engine.entity.entity2D.gui.Panel;
import com.engine.util.Color;
import org.joml.Vector2f;
import org.joml.Vector3f;


public class GUITest implements GameLogic {
    Panel panel;
    @Override
    public void init() {
        panel=new Panel(new Vector3f(),0, new Vector2f(1,1), RectTranform.PivotPoint.CENTER, Color.WHITE);
        panel.addPanel(new Panel(new Vector3f(),0,new Vector2f(0.5f,0.5f), RectTranform.PivotPoint.BOTTOM_CENTER,Color.ORANGE));
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
