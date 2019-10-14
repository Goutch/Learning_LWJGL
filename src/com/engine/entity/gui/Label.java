package com.engine.entity.gui;

import com.engine.materials.GUIMaterial;
import com.engine.util.AtlasTexture;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Label extends Panel {
    private char[] text;

    AtlasTexture font;

    public Label(String text, AtlasTexture font, Vector3f position, Vector2f size, PivotPoint pivotPoint, GUIMaterial material) {
        super(position, size, pivotPoint, material);
        this.text = text.toCharArray();
        this.font = font;

    }

    public Label(Vector3f position, Vector2f size, PivotPoint pivotPoint) {
        super(position, size, pivotPoint);
    }

    public Label(Vector3f position, Vector2f size) {
        super(position, size);
    }

    @Override
    protected void updateUVS(int row, int column) {
        float[] uvs = new float[(column + 1) * (row + 1) * 2];
        for (int x = 0; x < (row + 1); x++) {
            for (int y = 0; y < (column + 1); y++) {
                int index = (x * (column + 1) + y);
                Vector2f uv=font.getUVS((int)text[index]);
                uvs[index * 2] = uv.x;
                uvs[index * 2 + 1] = uv.y;
            }
        }
        mesh.uvs(uvs);
    }
}

