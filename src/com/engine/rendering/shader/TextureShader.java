package com.engine.rendering.shader;


public class TextureShader extends BaseShader {

    private static final String VERTEX_FILE="src/res/shaders/TextureVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/TextureFragment.glsl";

    public TextureShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void bindAttributes() {
        super.bindAttributes();
        bindAttribute(getAttributeCount(),"textureCoord");
    }
}
