package com.engine.entity;

import com.engine.events.EventManager;
import com.engine.rendering.Window;
import org.joml.Matrix4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class Camera extends Entity {
    public static Camera main=null;
    private float fov = 90;
    private float nearPlane = 0.1f;
    private float farPlane = 1000;
    private Matrix4f projectionMatrix;
    private Matrix4f viewMatrix;
    public Camera(Vector3f position,Quaternionf rotation, float fov, float viewDistance) {
        super(position,rotation,1);
        createProjectionMatrix();
    }

    public Matrix4f getProjectionMatrix()
    {
        return projectionMatrix;
    }

    public static void setMainCamera(Camera camera)
    {
        main=camera;
        EventManager.onProjectionMattrixChanged(main.projectionMatrix);
    }

    public void setFov(float fov) {
        this.fov = fov;
    }

    public void setNearPlane(float nearPlane) {
        this.nearPlane = nearPlane;
    }

    public void setFarPlane(float farPlane) {
        this.farPlane = farPlane;
    }

    public float getFov()
    {
        return fov;
    }
    public Matrix4f getViewMatrix()
    {
        return viewMatrix;
    }
    public void calculateViewMatrix()
    {
        Quaternionf rotation;
        Vector3f position;
        rotation=transform.getGlobalRotation();
        position=transform.getGlobalPosition();
        viewMatrix=new Matrix4f();
        viewMatrix.identity();
        viewMatrix.rotate(rotation.invert());
        viewMatrix.translate(position.mul(-1f));
    }
    private void createProjectionMatrix() {
        float aspectRatio = (float) Window.getWidth() / (float) Window.getHeight();
        float y_scale = (float) (1f / Math.tan(Math.toRadians(fov / 2f)));
        float x_scale = y_scale / aspectRatio;
        float frustum_length = farPlane - nearPlane;

        projectionMatrix = new Matrix4f();
        projectionMatrix.m00(x_scale);
        projectionMatrix.m11(y_scale);
        projectionMatrix.m22(-((farPlane + nearPlane) / frustum_length));
        projectionMatrix.m23(-1);
        projectionMatrix.m32(-((2 * nearPlane * farPlane) / frustum_length));
        projectionMatrix.m33(0);
    }
}
