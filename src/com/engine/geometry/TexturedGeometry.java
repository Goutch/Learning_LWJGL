package com.engine.geometry;

import com.engine.rendering.shader.ShaderProgram;

public class TexturedGeometry extends Geometry {
    GeometryTexture texture;
    public TexturedGeometry(GeometryTexture texture,float[] vertices, int[] indexes, ShaderProgram shader) {
        super(vertices, indexes, shader);
        this.texture=texture;
    }
}
