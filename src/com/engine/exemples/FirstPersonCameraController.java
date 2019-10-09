package com.engine.exemples;

import com.engine.entity.Entity;
import com.engine.entity.MeshRenderer;
import com.engine.events.EventManager;
import com.engine.events.UpdateListener;
import com.engine.geometry.ModelImporter;
import com.engine.inputs.Input;
import com.engine.entity.Camera;
import com.engine.rendering.Window;
import org.joml.Quaternionf;
import org.joml.Vector2f;
import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;


public class FirstPersonCameraController extends Entity implements UpdateListener {
    float speed=2;
    Vector2f lastFrame;
    private Camera camera;
    private final float boderWidth=20;//pixels
    private final float borderHeight=20;//pixels
    public FirstPersonCameraController(Vector3f position, Vector3f rotation, Camera camera, float speed) {
        super(position, rotation,1f);
        this.speed=speed;
        this.camera=camera;
        camera.transform.setParent(this.transform);
        lastFrame =new Vector2f(Window.getWidth()/2, Window.getHeight()/2);
        MeshRenderer cube=new MeshRenderer(new Vector3f(0,0,0),new Vector3f(0,0,0),1,ModelImporter.ImportModel("res/models/cube.obj"));
        EventManager.subscribeRender(cube);
        cube.transform.setParent(camera.transform);
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
            dir.x+=1;
        }
        if(Input.IsKeyPressed(GLFW.GLFW_KEY_D))
        {
            dir.x-=1;
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
        Vector2f change=new Vector2f((lastFrame.x-mousePos.x)/ Window.getWidth(),(lastFrame.y-mousePos.y)/ Window.getHeight());//% of screen the mouse travelled
        if (mousePos.x<0+boderWidth)
        {
            change.x=1f*deltaTime;
        }
        else if (mousePos.x> Window.getWidth()-boderWidth)
        {
            change.x=-1f*deltaTime;
        }

        change.y*=(Camera.main.getFov()* Window.getAspectRatio());
        change.x*=Camera.main.getFov();//degrees the mouse travelled
        camera.transform.rotate(new Vector3f(change.y,0,0));
        transform.rotate(new Vector3f(0,change.x,0));


        //movement
        Vector3f translation=new Vector3f();
        Vector3f rotation=new Vector3f();
        rotation=this.transform.getLocalRotation();
        if(dir.x!=0)
        {
            translation.x+=dir.x*speed*deltaTime*(float)Math.sin(Math.toRadians(rotation.y-90));
            translation.z+=dir.x*speed*deltaTime*(float)Math.cos(Math.toRadians(rotation.y-90));
        }
        if(dir.z!=0)
        {
            translation.x+=dir.z*speed*deltaTime*(float)Math.sin(Math.toRadians(rotation.y));
            translation.z+=dir.z*speed*deltaTime*(float)Math.cos(Math.toRadians(rotation.y));
        }
        if(dir.y!=0)
        {
            translation.y+=dir.y*speed*deltaTime;
        }
        transform.translate(translation);
        lastFrame =mousePos;
    }
}
