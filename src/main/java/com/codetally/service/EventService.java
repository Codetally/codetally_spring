package com.codetally.service;

import com.codetally.model.Event;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by greg on 29/06/17.
 */
@Service
public class EventService {
    private static final Logger logger = LoggerFactory.getLogger(LogService.class);

    @Autowired
    private LogRepository logRepository;

    @Autowired
    private EventRepository eventRepository;

    public boolean save(Event event) {
        //One of three things needs to happen here.
        //Either this method needs to ensure the charges are calculated
        //Or an interceptor needs to validate/calculate
        //Or this needs to raise an event that a calculate method catches.
        eventRepository.add(event);
        return true;
    }
}
