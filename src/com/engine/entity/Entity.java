package com.engine.entity;


import com.engine.events.RenderListener;
import com.engine.events.UpdateListener;


/**
 * Represents the base class for all game entities
 */
public abstract class Entity implements RenderListener, UpdateListener {

    private static int current_entities_id=0;
    public final int id=current_entities_id;
    public String name;
    public boolean enabled=true;
    protected Entity()
    {
        current_entities_id++;
    }



    @Override
    public void render() {
        if(!enabled)
        {
            return;
        }
    }
    @Override
    public void update(float deltaTime) {
        if(!enabled)
        {
            return;
        }
    }
}
