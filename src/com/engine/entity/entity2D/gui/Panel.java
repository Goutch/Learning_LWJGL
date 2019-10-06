package com.engine.entity.entity2D.gui;
import com.engine.entity.entity2D.Entity2D;
import com.engine.entity.entity2D.RectTranform.PivotPoint;
import com.engine.geometry.Mesh;
import com.engine.rendering.Renderer;
import com.engine.rendering.shaders.GUIShader;
import com.engine.rendering.shaders.Shaders;
import com.engine.util.Color;
import org.joml.Vector2f;
import org.joml.Vector3f;

import java.util.LinkedList;
import java.util.List;

public class Panel extends Entity2D{

    protected Mesh mesh;
    protected Color color=Color.WHITE;
    protected GUIShader shader= Shaders.GUI_SHADER;

    protected List<Panel> panels=new LinkedList<Panel>();
    public Panel(Vector3f position, float rotation, Vector2f size, PivotPoint pivotPoint,Color color) {
        super(position,rotation,size,pivotPoint);
        this.color=color;
        createMesh();
    }
    private void createMesh()
    {
        int sizeX=1;
        int sizeY=1;

        float[] vertices = new float[(sizeY+1)*(sizeX+1) * 3];
        for (int x = 0; x < (sizeX+1); x++) {
            for (int y = 0; y < (sizeY+1); y++) {
                int index = (x * (sizeY+1) + y);
                vertices[index * 3] = x*transform.size.x ;
                vertices[index * 3 + 1] = y*transform.size.y;
                vertices[index * 3 + 2] = 0;
            }
        }
        int[] indices = new int[sizeY*sizeX* 6];
        for (int x = 0; x < sizeX; x++) {
            for (int y = 0; y < sizeY; y++) {
                int index = (x * sizeY + y);
                indices[index * 6 ] = index + x + sizeY + 1;
                indices[index * 6 + 1] = index + x + 1;
                indices[index * 6 + 2] = index + x;
                indices[index * 6 + 3] = index + x + sizeY + 2;
                indices[index * 6 + 4] = index + x + 1;
                indices[index * 6 + 5] = index + x + sizeY + 1;
            }
        }

        mesh=new Mesh().vertices(vertices).indices(indices);
        mesh.build();
    }
    public void bindShader()
    {
        shader.start();
        shader.loadPreRenderPanelUniforms(this);

    }

    public void unBindShader()
    {
        shader.stop();
    }

    public Mesh getMesh()
    {
        return mesh;
    }
    public Color getColor()
    {
        return color;
    }
    public void addPanel(Panel panel)
    {
        panels.add(panel);
    }
    @Override
    public void render() {
        super.render();
        Renderer.render(this);
    }
}
