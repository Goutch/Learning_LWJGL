package com.engine.entity;

import com.engine.events.RenderListener;
import com.engine.events.UpdateListener;
import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Entity implements RenderListener, UpdateListener {
    private static long current_entities_id = 0;
    public final long id = current_entities_id;
    public String name;
    public boolean enabled = true;
    private Transform transform;
    private Entity parent;

    public Entity(Vector3f position, Vector3f rotation, float scale) {
        current_entities_id++;
        transform = new Transform(position, rotation, scale);
    }

    @Override
    public void render() {
        if (!enabled) {
            return;
        }
    }

    @Override
    public void update(float deltaTime) {
        if (!enabled) {
            return;
        }
    }

    public void translate(Vector3f translation) {
        transform.position.add(translation);
    }

    public void setPosition(Vector3f position) {
        transform.position.set(position);
    }

    public void setParent(Entity parent) {
        this.parent = parent;
    }

    public Vector3f getLocalPosition() {
        return new Vector3f().set(transform.position);
    }
    public Vector3f getPosition() { return parent==null? new Vector3f().set(transform.position):new Vector3f().set(transform.position).add(parent.getPosition()); }
    public float px() {
        return parent == null ? transform.position.x : transform.position.x + parent.px();
    }
    public float lpx() { return transform.position.x; }
    public void px(float x) {transform.position.x = x;}
    public void lpx(float x) {transform.position.x=parent == null ? x : x - parent.px();}

    public float py() {
        return parent == null ? transform.position.y : transform.position.y + parent.py();
    }
    public float lpy() { return transform.position.y; }
    public void py(float y) {transform.position.y = y;}
    public void lpy(float y) {transform.position.y=parent == null ? y : y - parent.py();}

    public float pz() {
        return parent == null ? transform.position.z : transform.position.z + parent.pz();
    }
    public float lpz() { return transform.position.z; }
    public void pz(float z) {transform.position.z = z;}
    public void lpz(float z) {transform.position.z=parent == null ? z : z - parent.px();}

    public void rotate(Vector3f rotation) {
        transform.rotation.add(rotation);
    }

    public Vector3f getLocalRotation() {
        return new Vector3f().set(transform.rotation);
    }
    public Vector3f getRotation() { return parent==null? new Vector3f().set(transform.rotation):new Vector3f().set(transform.rotation).add(parent.getRotation()); }
    public float rx() {
        return parent == null ? transform.rotation.x : transform.rotation.x + parent.rx();
    }
    public float lrx() { return transform.rotation.x; }
    public void rx(float x) {transform.rotation.x = x;}
    public void lrx(float x) {transform.rotation.x=parent == null ? x : x - parent.rx();}

    public float ry() {
        return parent == null ? transform.rotation.y : transform.rotation.y + parent.ry();
    }
    public float lry() { return transform.rotation.y; }
    public void ry(float y) {transform.rotation.x = y;}
    public void lry(float y) {transform.rotation.x=parent == null ? y: y - parent.ry();}

    public float rz() {
        return parent == null ? transform.rotation.z : transform.rotation.z + parent.rz();
    }
    public float lrz() { return transform.rotation.z; }
    public void rz(float z) {transform.rotation.z = z;}
    public void lrz(float z) {transform.rotation.z=parent == null ?z : z - parent.rz();}

    public void s(float s) { transform.scale = s; }
    public float s() { return parent == null ? transform.scale : transform.scale * parent.s(); }

    public Matrix4f getTransformMatrix() {

        if (parent == null) {
            return transform.toTranformMatrix();
        } else {
            return parent.getTransformMatrix().mul(transform.toTranformMatrix());
        }
    }
}
