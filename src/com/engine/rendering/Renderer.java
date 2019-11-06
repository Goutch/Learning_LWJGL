package com.engine.rendering;


import com.engine.entity.MeshRenderer;
import com.engine.materials.Material;
import com.engine.geometry.Mesh;
import com.engine.util.Color;
import org.lwjgl.opengl.GL11;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;



public class Renderer {
    private static HashMap<Mesh, HashMap<Material,List<MeshRenderer>>> renderQueue=new HashMap<Mesh, HashMap<Material,List<MeshRenderer>>>();
    public static void init() {

        GL11.glCullFace(GL11.GL_BACK);
        setClearColor(Color.BLACK);
        clear();

    }
    public static void setClearColor(Color color)
    {
        GL11.glClearColor(color.r, color.g, color.b, color.a);
    }
    public static void addToRenderQueue(MeshRenderer meshRenderer)
    {
        Mesh mesh= meshRenderer.getMesh();
        HashMap<Material,List<MeshRenderer>> materialBatch=renderQueue.get(mesh);
        if(materialBatch!=null)
        {
            Material material = meshRenderer.getMaterial();
            List<MeshRenderer> batch=materialBatch.get(material);
            if(batch!=null)
            {
                batch.add(meshRenderer);
            }
            else
            {
                List<MeshRenderer> newBatch= new ArrayList<>();
                newBatch.add(meshRenderer);
                materialBatch.put(material,newBatch);
            }
        }
        else
        {
            materialBatch=new  HashMap<Material,List<MeshRenderer>>();
            List<MeshRenderer> newBatch=new ArrayList<>();
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
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glEnable(GL11.GL_DEPTH_TEST);
        for (Mesh mesh:renderQueue.keySet()) {
            mesh.bind();
            for (Material material : renderQueue.get(mesh).keySet()) {
                material.bind();
                List<MeshRenderer> batch=renderQueue.get(mesh).get(material);

                for (MeshRenderer meshRenderer :batch)
                {
                    material.bindEntity(meshRenderer);
                    GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
                }
                material.unBind();
            }
            mesh.unBind();
        }
        renderQueue.clear();
        GL11.glDisable(GL11.GL_CULL_FACE);
        GL11.glDisable(GL11.GL_DEPTH_TEST);
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
