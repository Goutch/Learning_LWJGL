package com.test;

import com.engine.core.GameLogic;
import com.engine.entity.Entity;
import com.engine.entity.gui.Panel;
import com.engine.materials.GUIMaterial;
import com.engine.util.Color;
import com.engine.util.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.LinkedList;

public class GuiTest implements GameLogic {

    LinkedList<Panel> GUIEntities = new LinkedList<Panel>();

    @Override
    public void init() {
        //GUI
        GUIEntities.add(new Panel(
                new Vector3f(0,0,0),
                new Vector2f(0.5f,1f),
                Panel.PivotPoint.CENTER,
                new GUIMaterial()
                        .borderColor(Color.GRAY)
                        .borderWidth(0.015f)
                        .texture(
                                new Texture("res/textures/Untitled.png")
                        )
                )
        );
        GUIEntities.get(0).fitTexture();
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render() {
        for (Entity e:
             GUIEntities) {
            e.render();
        }
    }

    @Override
    public void dispose() {

    }
}
