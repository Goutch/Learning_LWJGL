package com.engine.examples;

import com.engine.entity.Entity;
import com.engine.events.UpdateListener;
import com.engine.inputs.Input;
import com.engine.entity.Camera;
import com.engine.rendering.Window;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;


public class FirstPersonCameraController extends Entity implements UpdateListener {
    float speed=2;
    private Camera camera;
    public FirstPersonCameraController(Vector3f position, Quaternionf rotation, Camera camera, float speed) {
        super(position, rotation,1f);
        this.speed=speed;
        this.camera=camera;
        camera.transform.setParent(this.transform);
        Input.showCursor(false);
    }

    @Override
    public void update(float deltaTime) {
        Vector3f dir=new Vector3f(0,0,0);
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_W))
        {
            dir.z-=1;
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_S))
        {
            dir.z+=1;
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_A))
        {
            dir.x-=1;
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_D))
        {
            dir.x+=1;
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_SPACE))
        {
           dir.y+=1;
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_LEFT_CONTROL))
        {
            dir.y-=1;
        }

        //rotation
        Vector2f mousePos=new Vector2f((float) Input.getMouseX(),(float) Input.getMouseY());
        Vector2f change=new Vector2f((((float)Window.getWidth()/2)-mousePos.x)/ Window.getWidth(),(((float) Window.getHeight()/2)-mousePos.y)/ Window.getHeight());//% of screen the mouse travelled

        change.y*=(Camera.main.getFov()* Window.getAspectRatio());
        change.x*=Camera.main.getFov();//degrees the mouse travelled
        this.transform.rotate((float)Math.toRadians(change.x),new Vector3f(0,1,0));
        Vector3f currentRot=camera.transform.getLocalRotation().getEulerAnglesXYZ(new Vector3f());
        if(currentRot.x+Math.toRadians(change.y)<Math.PI/2&&currentRot.x+Math.toRadians(change.y)>-Math.PI/2)
            camera.transform.rotate((float)Math.toRadians(change.y),new Vector3f(1,0,0));
        //movement
        Vector3f translation=new Vector3f();
        Vector3f forward=transform.forward();
        Vector3f left=camera.transform.left();
        if(dir.x!=0)
        {
            translation.x+=dir.x*speed*deltaTime*left.x;
            translation.z+=dir.x*speed*deltaTime*left.z;
        }
        if(dir.z!=0)
        {
            translation.x+=dir.z*speed*deltaTime*forward.x;
            translation.z+=dir.z*speed*deltaTime*forward.z;
        }
        if(dir.y!=0)
        {
            translation.y+=dir.y*speed*deltaTime;
        }
        transform.translate(translation);
        Window.centerMouse();
    }
}
