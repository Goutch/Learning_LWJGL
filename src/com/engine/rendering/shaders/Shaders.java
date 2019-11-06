package com.engine.rendering.shaders;


import com.engine.rendering.shaders.gui.GUIShader;
import com.engine.rendering.shaders.projection.DiffuseLightShader;
import com.engine.rendering.shaders.projection.VertexColorShader;
import com.engine.examples.voxelEngine.voxelShader.VoxelShader;

public class Shaders {
    public static final BaseShader BASE_SHADER =new BaseShader();
    public static final DiffuseLightShader DIFFUSE_LIGHT_SHADER =new DiffuseLightShader();
    public static final VertexColorShader VERTEX_COLOR_SHADER=new VertexColorShader();
    public static final GUIShader GUI_SHADER=new GUIShader();
    public static final VoxelShader VOXEL_SHADER=new VoxelShader();
}
