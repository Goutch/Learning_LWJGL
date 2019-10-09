package com.engine.gui;

import com.engine.entity.gui.Panel;
import com.engine.rendering.shaders.gui.GUIShader;
import com.engine.rendering.shaders.Shaders;
import com.engine.util.Color;
import com.engine.util.Texture;
import com.sun.deploy.security.SelectableSecurityManager;


public class GUIMaterial {
    public static GUIMaterial DEFAULT=new GUIMaterial();
    protected GUIShader shader = Shaders.GUI_SHADER;
    protected Texture texture;
    protected boolean hasTexture=false;
    protected Color color = new Color(Color.WHITE);
    private float borderWidth =0f;
    private Color borderColor=new Color(Color.WHITE);
    public GUIMaterial borderWidth(float width)
    {
        this.borderWidth=width;
        return this;
    }
    public float borderWidth()
    {
        return borderWidth;
    }
    public GUIMaterial borderColor(Color color)
    {
        this.borderColor=color;
        return this;
    }
    public Color borderColor()
    {
        return  borderColor;
    }
    public GUIMaterial color(Color color)
    {
        this.color.set(color);
        return this;
    }
    public Color color()
    {
        return color;
    }
    public GUIMaterial texture(Texture texture)
    {
        this.texture=texture;
        hasTexture= texture != null;
        return this;
    }
    public Texture texture()
    {
        return texture;
    }
    public boolean hasTexture()
    {
        return hasTexture;
    }

    public void bind()
    {
        shader.start();
        shader.loadPreRenderGeneralUniforms();
        shader.loadPreRenderMaterialUniforms(this);
        if(hasTexture)
        {
            texture.bind();
        }
    }
    public void unBind()
    {
        if(hasTexture)
        {
            texture.unBind();
        }
        shader.stop();
    }

    public void bindPanel(Panel panel)
    {
        shader.loadPreRenderPanelUniforms(panel);
    }

}
