package com.codetally;

import com.codetally.plugin.Configuration;
import com.codetally.plugin.Event;
import com.codetally.plugin.EventAdapter;

import java.util.List;

/**
 * Hello world!
 *
 */
public class BitbucketAdapter implements EventAdapter
{

    @Override
    public Configuration getConfiguration() {
        return null;
    }

    @Override
    public List<Event> getEvents(String body) {
        return null;
    }
}
