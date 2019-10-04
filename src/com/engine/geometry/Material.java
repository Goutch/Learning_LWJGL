package com.engine.geometry;

import com.engine.entities.Entity;
import com.engine.rendering.shaders.BaseShader;
import com.engine.rendering.shaders.ShaderProgram;
import com.engine.rendering.shaders.Shaders;
import com.engine.util.Color;
import com.engine.util.Texture;

public class Material {
    public static Material DEFAULT=new Material();
    private BaseShader shader= Shaders.BASE_SHADER;
    private float shineFactor =0;
    private float dampFactor =0;
    private Texture texture=null;
    private Color color=Color.WHITE;

    public Material(){

    }
    public void bind()
    {
        shader.start();
        shader.loadPreRenderGeneralUniforms();
        shader.loadPreRenderMaterialUniforms(this);
        if(texture!=null)
        {
            texture.bind();
        }
    }
    public void unBind()
    {
        if(texture!=null)
        {
            texture.unbind();
        }
        shader.stop();
    }
    public void bindEntity(Entity entity)
    {
        shader.loadPreRenderEntityUniforms(entity);
    }

    public Material texture(Texture texture)
    {
        this.texture=texture;
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
        this.color=color;
        return this;
    }

    public float getShineFactor() {
        return shineFactor;
    }

    public float getDampFactor() {
        return dampFactor;
    }

    public Color getColor() { return color; }


}
