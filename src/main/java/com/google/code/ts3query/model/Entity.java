package com.google.code.ts3query.model;

import com.google.code.ts3query.TeamspeakCommand;
import com.google.code.ts3query.TeamspeakProtocol;
import com.google.code.ts3query.TeamspeakResponse;

public abstract class Entity {

  protected transient TeamspeakProtocol server;

  /**
   * Sets up an entity to be served by a server.
   * 
   * @param <E>
   *          the type of the entity
   * @param entity
   *          the entity instance to be served
   * @param server
   *          the server instance to serve
   * @return the served entity
   */
  public <E extends Entity> E serve(E entity) {
    entity.server = server;
    return entity;
  }

  protected static TeamspeakCommand command(String keyword) {
    return new TeamspeakCommand(keyword);
  }

  protected TeamspeakResponse send(TeamspeakCommand command) {
    return server.send(command);
  }
}
