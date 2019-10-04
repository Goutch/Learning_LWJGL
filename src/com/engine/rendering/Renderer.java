package com.engine.rendering;


import com.engine.core.GameOptions;
import com.engine.entities.Entity;
import com.engine.entities.MeshRenderer;
import com.engine.geometry.Mesh;
import com.engine.rendering.shader.ShaderProgram;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Renderer {
    public static HashMap<Mesh, HashMap<Material,List<MeshRenderer>>> renderQueue=new HashMap<Mesh, HashMap<Material,List<MeshRenderer>>>();
    public static void init() {
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        GL11.glClearColor(GameOptions.CLEAR_COLOR.r, GameOptions.CLEAR_COLOR.b, GameOptions.CLEAR_COLOR.g, GameOptions.CLEAR_COLOR.a);
        clear();

    }
    public static void addToRenderQueue(MeshRenderer meshRenderer)
    {
        Mesh mesh=meshRenderer.getMesh();
        HashMap<Material,List<MeshRenderer>> materialBatch=renderQueue.get(mesh);
        if(materialBatch!=null)
        {
            Material material=meshRenderer.getMaterial();
            List<MeshRenderer> batch=materialBatch.get(material);
            if(batch!=null)
            {
                batch.add(meshRenderer);
            }
            else
            {
                List<MeshRenderer> newBatch=new ArrayList<MeshRenderer>();
                newBatch.add(meshRenderer);
                materialBatch.put(material,newBatch);
            }
        }
        else
        {
            materialBatch=new  HashMap<Material,List<MeshRenderer>>();
            List<MeshRenderer> newBatch=new ArrayList<MeshRenderer>();
            newBatch.add(meshRenderer);
            materialBatch.put(meshRenderer.getMaterial(),newBatch);
            renderQueue.put(mesh,materialBatch);
        }

    }
    public static void preRender() {
        clear();
    }

    /**
     * render mesh in render queue
     */
    public static void render() {

        for (Mesh mesh:renderQueue.keySet()) {
            mesh.bind();
            for (Material material: renderQueue.get(mesh).keySet()) {
                material.bind();
                List<MeshRenderer> batch=renderQueue.get(mesh).get(material);

                for (MeshRenderer entity:batch)
                {
                    material.bindEntity(entity);
                    GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
                }
                material.unBind();
            }
            mesh.unBind();
        }
        renderQueue.clear();
    }

    public static void setWireframe(boolean wireframe) {
        if (!wireframe) {
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);
        } else {
            GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
        }
    }

    /**
     * Clear the screen with the clear color.
     */
    public static void clear() {
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
    }
    /**
     *
     * @deprecated  use {@link #addToRenderQueue(MeshRenderer)} instead.
     */
    @Deprecated
    public static void render(MeshRenderer meshRenderer) {
        Material material = meshRenderer.getMaterial();
        Mesh mesh = meshRenderer.getMesh();
        material.bind();
        material.bindEntity(meshRenderer);
        mesh.bind();
        GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);

        mesh.unBind();
        material.unBind();
    }



}
