package com.test;

import com.engine.core.GameLogic;
import com.engine.entity.Entity;
import com.engine.entity.gui.Panel;
import com.engine.inputs.Input;
import com.engine.materials.GUIMaterial;
import com.engine.rendering.Renderer;
import com.engine.util.Color;
import com.engine.util.Texture;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;

import java.util.LinkedList;

public class GuiTest implements GameLogic {

    LinkedList<Panel> GUIEntities = new LinkedList<Panel>();

    @Override
    public void init() {
        //GUI
        GUIEntities.add(new Panel(
                new Vector3f(-1,1,0),
                new Vector2f(0.1f,0.1f),
                Panel.PivotPoint.TOP_LEFT,
                new GUIMaterial()
                        .borderColor(Color.WHITE)
                        .borderWidth(0.01f)
                        .color(Color.RED)
        ));
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
                ).fitTexture()
        );
    }

    @Override
    public void update(float delta) {
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_RIGHT))
        {
            Vector2f add=new Vector2f(GUIEntities.get(0).getSize());
            add.x=add.x+delta;
            GUIEntities.get(0).setSize(add);
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_LEFT))
        {
            Vector2f add=new Vector2f(GUIEntities.get(0).getSize());
            add.x=add.x-delta;
            GUIEntities.get(0).setSize(add);
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_1))
        {
            Renderer.setWireframe(true);
        }
        else
        {
            Renderer.setWireframe(false);
        }

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
