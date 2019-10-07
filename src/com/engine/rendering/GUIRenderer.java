package com.engine.rendering;


import com.engine.entity.gui.Panel;

import com.engine.geometry.Mesh;
import com.engine.gui.GUIMaterial;
import org.lwjgl.opengl.GL11;


import java.util.HashMap;
import java.util.LinkedList;


public class GUIRenderer {
    private static HashMap<GUIMaterial, LinkedList<Panel>> renderQueue = new HashMap<GUIMaterial, LinkedList<Panel>>();

    public static void init() {
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
    }

    public static void addToRenderQueue(Panel panel) {
        GUIMaterial mat = panel.getMaterial();
        LinkedList<Panel> materialBatch = renderQueue.get(mat);
        if (materialBatch != null) {
            materialBatch.add(panel);
        } else {
            materialBatch = new LinkedList<Panel>();
            materialBatch.add(panel);
            renderQueue.put(mat, materialBatch);
        }
    }

    public static void render() {
        GL11.glEnable(GL11.GL_BLEND);
        for (GUIMaterial mat : renderQueue.keySet()) {
            mat.bind();
            for (Panel panel : renderQueue.get(mat)) {
                Mesh mesh = panel.getMesh();
                mat.bindPanel(panel);
                mesh.bind();
                GL11.glDrawElements(GL11.GL_TRIANGLES, mesh.getVertexCount(), GL11.GL_UNSIGNED_INT, 0);
                mesh.unBind();
            }
            mat.unBind();
        }
        GL11.glDisable(GL11.GL_BLEND);
        renderQueue.clear();
    }
}
