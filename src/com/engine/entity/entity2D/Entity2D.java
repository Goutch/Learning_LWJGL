package com.engine.entity.entity2D;
import com.engine.entity.entity2D.RectTranform.PivotPoint;
import com.engine.entity.Entity;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Entity2D extends Entity {
    public RectTranform transform;
    public Entity2D(Vector3f position, float rotation, Vector2f size, PivotPoint pivotPoint){
        super();
        transform =new RectTranform(position,rotation,size,pivotPoint);
    }
    @Override
    public void render() {
        super.render();
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
    }
}
