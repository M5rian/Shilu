package com.github.m5rian.shilu.client.events;

import java.lang.reflect.Method;

public class EventData {

    public final Object source;
    public final Method target;
    public final EventPriority priority;

    public EventData(Object source, Method target, EventPriority priority) {
        this.source = source;
        this.target = target;
        this.priority = priority;
    }
}
