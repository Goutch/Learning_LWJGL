package com.engine.entities;

import com.engine.events.RenderListener;
import com.engine.geometry.Mesh;
import com.engine.gui.Panel;
import com.engine.rendering.Renderer;
import com.engine.rendering.shaders.GUIShader;
import com.engine.rendering.shaders.Shaders;
import com.engine.util.Color;
import com.engine.util.Texture;
import org.joml.Vector3f;

public class PanelRenderer extends Entity implements RenderListener {

    enum RectPoint{
        NONE,

        TOP_RIGHT,
        TOP_CENTER,
        TOP_LEFT,

        BOTTOM_RIGHT,
        BOTTOM_CENTER,
        BOTTOM_LEFT,

        CENTER_RIGHT,
        CENTER,
        CENTER_LEFT,
    }
    RectPoint anchor;
    RectPoint pivot;
    private Panel panel;
    private Texture texture;
    private Color color=new Color(1,1,0,0.5f);
    private GUIShader shader= Shaders.GUI_SHADER;
    public PanelRenderer(Panel panel, Vector3f position) {
        super(position, new Vector3f(0,0,0), 1f);
        this.panel=panel;
    }
    public void bindShader()
    {
        shader.start();
        shader.loadPreRenderPanelUniforms(this);

    }
    public void unBindShader()
    {
        shader.stop();
    }
    public Mesh getMesh()
    {
        return panel;
    }
    public Color getColor()
    {
        return color;
    }
    @Override
    public void render() {
        Renderer.render(this);
    }
}
