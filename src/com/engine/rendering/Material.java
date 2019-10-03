package com.engine.rendering;

import com.engine.entities.Entity;
import com.engine.rendering.shader.ShaderProgram;
import com.engine.rendering.shader.Shaders;
import com.engine.util.Texture;

public class Material {
    public static Material DEFAULT=new Material().shader(Shaders.BASE_SHADER);
    private ShaderProgram shader= Shaders.BASE_SHADER;
    private Texture texture=null;
    public Material(){

    }
    public void bind(Entity entity)
    {
        shader.start();
        shader.loadUniformsBeforeRender(entity,this);
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
    public Material texture(Texture texture)
    {
        this.texture=texture;
        return this;
    }

    public Material shader(ShaderProgram shader)
    {
        this.shader=shader;
        return this;
    }


}
