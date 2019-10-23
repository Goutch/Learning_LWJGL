package com.engine.materials;


import com.engine.entity.Entity;
import com.engine.rendering.shaders.BaseShader;

import com.engine.rendering.shaders.Shaders;
import com.engine.util.Color;
import com.engine.util.Texture;

public class Material {
    public static Material DEFAULT=new Material();
    private BaseShader shader= Shaders.DIFFUSE_LIGHT_SHADER;
    private float shineFactor =0;
    private float dampFactor =0;
    private boolean hasTexture=false;
    private Texture texture=null;
    private Color color=new Color(Color.WHITE);

    public Material(){ }

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

    public void bindEntity(Entity entity)
    {
        shader.loadPreRenderEntityUniforms(entity);
    }

    public Color color() {
        return color;
    }

    public Material texture(Texture texture)
    {
        this.texture=texture;
        hasTexture= texture != null;
        return this;
    }

    public Material shader(BaseShader shader)
    {
        this.shader=shader;
        return this;
    }
    public Material shineFactor(float shine)
    {
        this.shineFactor =shine;
        return this;
    }
    public Material dampFactor(float damp)
    {
        this.dampFactor =damp;
        return this;
    }
    public Material color(Color color)
    {
        this.color.set(color);
        return this;
    }
    public boolean hasTexture()
    {
        return hasTexture;
    }
    public float getShineFactor() {
        return shineFactor;
    }

    public float getDampFactor() {
        return dampFactor;
    }

    public Color getColor() { return color; }


}
