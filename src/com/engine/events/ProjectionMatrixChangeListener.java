package com.engine.events;

import org.joml.Matrix4f;

public interface ProjectionMatrixChangeListener {
    void onProjectionMatrixChanged(Matrix4f projectionMatrix);

}
