package com.engine.events;

import com.engine.events.RenderListener;

import java.util.LinkedList;

public class EventManager {
    public static LinkedList<RenderListener> renderListeners = new LinkedList<RenderListener>();
    public static LinkedList<UpdateListener> updateListeners = new LinkedList<UpdateListener>();
    public static void subscribeUpdate(UpdateListener listener) {
        updateListeners.add(listener);
    }

    public static void unSubscribeUpdate(UpdateListener listener) {
        updateListeners.remove(listener);
    }

    public static void onUpdate(float deltaTime) {
        for (UpdateListener x :
                updateListeners) {
            x.update(deltaTime);
        }
    }

    public static void subscribeRender(RenderListener listener) {
        renderListeners.add(listener);
    }

    public static void unSubscribeRender(RenderListener listener) {
        renderListeners.remove(listener);
    }

    public static void onRender() {
        for (RenderListener l :
                renderListeners) {
            //if is within window range
          // if (!(l.getPosition().x-l.getSize().x*.5 > Camera.getX()+Renderer.UNITS_PER_WINDOW_WIDTH*.5 ||
          //         l.getPosition().x+l.getSize().x*.5 < ViewPort.getX()-Renderer.UNITS_PER_WINDOW_WIDTH*.5 ||
          //         l.getPosition().y-l.getSize().y*.5 > ViewPort.getY()+(Renderer.UNITS_PER_WINDOW_WIDTH*Renderer.Instance().getWidthToHeightRatio())*.5 ||
          //         l.getPosition().y+l.getSize().y*.5 < ViewPort.getY()-(Renderer.UNITS_PER_WINDOW_WIDTH*Renderer.Instance().getWidthToHeightRatio())*.5))
          // {
                l.render();
            //}

        }
    }

    public static void reset() {
        renderListeners.clear();
        updateListeners.clear();
    }
}
