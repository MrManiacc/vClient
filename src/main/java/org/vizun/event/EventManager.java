package org.vizun.event;

import com.google.common.eventbus.EventBus;

import org.vizun.Vizun;

/**
 * Client Side event manager for publish & subscribe events
 * Utilizes the Guava EventBus 
 */
public class EventManager {
  
  private final Vizun _instance;
  private final EventBus eventBus = new EventBus();

  public EventManager(Vizun instance) {
    _instance = instance;
  }
  
  public EventBus getEventBus() {
    return eventBus;
  }
  
  
}
