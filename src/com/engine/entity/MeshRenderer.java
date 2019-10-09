package com.engine.entity;

import com.engine.geometry.Material;
import com.engine.geometry.Mesh;
import com.engine.rendering.Renderer;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public class MeshRenderer extends Entity{

    private Mesh mesh;
    private Material material;

    /**
     *
     * @param position
     * @param rotation
     * @param scale
     * @param mesh
     * @param material
     */
    public MeshRenderer(Vector3f position, Quaternionf rotation, float scale, Mesh mesh, Material material) {
        super(position, rotation, scale);
        this.mesh = mesh;
        this.material = material;
    }
    public MeshRenderer(Vector3f position, Quaternionf rotation, float scale, Mesh mesh) {
        super(position, rotation, scale);
        this.mesh = mesh;
        this.material = Material.DEFAULT;
    }


    public Mesh getMesh() {
        return mesh;
    }
    public Material getMaterial(){return material;}

    @Override
    public void render() {
        super.render();
        Renderer.addToRenderQueue(this);
    }
}
