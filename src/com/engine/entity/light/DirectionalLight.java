package com.engine.entity.light;

import com.engine.entity.Transform;
import com.engine.util.Color;
import org.joml.Quaternionf;
import org.joml.Vector3f;

import java.util.LinkedList;

public class DirectionalLight extends Light {
    public static LinkedList<DirectionalLight> Lights=new LinkedList<DirectionalLight>();
    public DirectionalLight(Vector3f position, Quaternionf rotation, Color color) {
        super(position, rotation, color);
    }
}
