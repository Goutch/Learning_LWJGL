package com.engine.rendering;

import com.engine.entity.gui.Panel;
import com.engine.geometry.Mesh;
import org.lwjgl.opengl.GL11;

import java.util.LinkedList;
import java.util.List;

public class GUIRenderer {
    private static List<Panel> renderQueue=new LinkedList<Panel>();
    public static void init()
    {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA,GL11.GL_ONE_MINUS_SRC_ALPHA);
    }
    public static void addToRenderQueue(Panel panel)
    {
        renderQueue.add(panel);
    }
    public static void render()
    {
        GL11.glEnable(GL11.GL_BLEND);
        for (Panel panel:renderQueue)
        {
            Mesh mesh=panel.getMesh();
            panel.bindShader();
            mesh.bind();
            GL11.glDrawElements(GL11.GL_TRIANGLES,mesh.getVertexCount(), GL11.GL_UNSIGNED_INT,0);
            mesh.unBind();
            panel.unBindShader();
        }
        GL11.glDisable(GL11.GL_BLEND);
        renderQueue.clear();
    }
}
