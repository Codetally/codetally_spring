package com.codetally;

import java.util.List;

public interface EventAdapter {
    public Configuration getConfiguration();
    public List<Event> getEvents(String body);
}
