package com.test;

import com.engine.events.EventManager;
import com.engine.events.UpdateListener;
import com.engine.inputs.Input;
import com.engine.entities.Camera;
import com.engine.rendering.Display;
import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;


public class FirstPersonCam extends Camera implements UpdateListener {
    float speed=2;
    Vector2f lastFrame;
    private final float boderWidth=20;//pixels
    private final float borderHeight=20;//pixels
    public FirstPersonCam(Vector3f position, Vector3f rotation, float fov, float viewDistance, float speed) {
        super(position, rotation, fov, viewDistance);
        EventManager.subscribeUpdate(this);
        this.speed=speed;
        lastFrame =new Vector2f(Display.getWidth()/2,Display.getHeight()/2);
    }

    @Override
    public void update(float deltaTime) {

        Vector3f dir=new Vector3f(0,0,0);
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_W))
        {
            dir.z+=1;
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_S))
        {
            dir.z-=1;
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
        Vector2f change=new Vector2f((mousePos.x- lastFrame.x)/Display.getWidth(),(mousePos.y-lastFrame.y)/Display.getHeight());//% of screen the mouse travelled
        if (mousePos.x<0+boderWidth)
        {
            change.x=-1f*deltaTime;
        }
        else if (mousePos.x>Display.getWidth()-boderWidth)
        {
            change.x=1f*deltaTime;
        }
        if(mousePos.y<0+borderHeight)
        {
            change.y=-1f*deltaTime;
        }
        else if(mousePos.y>Display.getHeight()-borderHeight)
        {
            change.y=1f*deltaTime;
        }
        change.y*=(Camera.main.getFov()*Display.getAspectRatio());
        change.x*=Camera.main.getFov();//degrees the mouse travelled

        this.transform.rotation.y+=change.x;
        //this.transform.rotation.x+=change.y*(float)Math.abs(Math.cos(Math.toRadians(transform.rotation.y)))*((transform.rotation.y%180)/180);
        //this.transform.rotation.z+=change.y*(float)Math.abs(Math.cos(Math.toRadians(transform.rotation.y)))*(((transform.rotation.y+90)%180)/180);

        //movement
        if(dir.x!=0)
        {
            this.transform.position.x+=dir.x*speed*deltaTime*(float)Math.cos(Math.toRadians(transform.rotation.y));
            this.transform.position.z+=dir.x*speed*deltaTime*(float)Math.sin(Math.toRadians(transform.rotation.y));
        }
        if(dir.z!=0)
        {
            this.transform.position.x+=dir.z*speed*deltaTime*(float)Math.cos(Math.toRadians(transform.rotation.y-90));
            this.transform.position.z+=dir.z*speed*deltaTime*(float)Math.sin(Math.toRadians(transform.rotation.y-90));
        }
        if(dir.y!=0)
        {
            this.transform.position.y+=dir.y*speed*deltaTime;
        }

        lastFrame =mousePos;
    }
}
