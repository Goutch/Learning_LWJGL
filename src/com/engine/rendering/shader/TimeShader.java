package com.engine.rendering.shader;

import com.engine.core.GameLoop;
import com.engine.entities.Entity;
import org.joml.Matrix4f;

public class TimeShader extends BaseShader {
    private static final String VERTEX_FILE="src/res/shaders/TimeVertex.glsl";
    private static final String FRAMGMENT_FILE="src/res/shaders/TimeFragment.glsl";
    protected int timeLocation;
    public TimeShader() {
        super(VERTEX_FILE, FRAMGMENT_FILE);
    }

    @Override
    protected void getAllUniformLocations() {
        super.getAllUniformLocations();
        timeLocation=getUniformLocation("time");
    }
    @Override
    public void start(Entity entity)
    {
        super.start(entity);
        loadTime();
    }
    private void loadTime(){
        loadFloatUniform(timeLocation, GameLoop.getTimeSinceStart());
    }
}