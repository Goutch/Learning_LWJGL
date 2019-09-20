package com.engine.events;


import java.util.LinkedList;

public class EventManager {
    private static LinkedList<RenderListener> renderListeners = new LinkedList<RenderListener>();
    private static LinkedList<UpdateListener> updateListeners = new LinkedList<UpdateListener>();
    private static LinkedList<WindowResizeListener> windowResizeListeners=new LinkedList<WindowResizeListener>();
    private static LinkedList<DisposeListener> disposeListeners=new LinkedList<DisposeListener>();

    public static void subscribeUpdate(UpdateListener listener) {
        updateListeners.add(listener);
    }
    public static void subscribeRender(RenderListener listener) {
        renderListeners.add(listener);
    }
    public static void subcribeWindowResize(WindowResizeListener listener) { windowResizeListeners.add(listener);}
    public static void subscribeDispose(DisposeListener listener){}

    public static void unSubscribeRender(RenderListener listener) {
        renderListeners.remove(listener);
    }
    public static void unSubscribeUpdate(UpdateListener listener) {
        updateListeners.remove(listener);
    }
    public static void unSubscribeWindowResize(WindowResizeListener listener){windowResizeListeners.remove(listener);}
    public static void unSubscribeDispose(DisposeListener listener){disposeListeners.remove(listener);}

    public static void onRender() { for (RenderListener l : renderListeners) { l.render(); } }
    public static void onUpdate(float deltaTime) { for (UpdateListener l : updateListeners) { l.update(deltaTime); }}
    public static void onWindowResize(int width,int height) { for (WindowResizeListener l : windowResizeListeners) { l.onWindowResize(width,height); }}
    public static void onDispose(){for (DisposeListener l : disposeListeners) { l.onDispose(); }}
    public static void reset()
    {
        renderListeners.clear();
        updateListeners.clear();
        disposeListeners.clear();
        windowResizeListeners.clear();
    }

}
