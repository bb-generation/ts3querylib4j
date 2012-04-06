package com.google.code.ts3query.model;

public abstract class ManagedEntity<Manager> extends Entity {

  protected transient Manager manager;

  /**
   * Sets up an entity to be managed by a manager.
   * 
   * @param <E>
   *          the type of the entity
   * @param <M>
   *          the type of the manager
   * @param entity
   *          the entity instance
   * @param manager
   *          the manager instance
   * @return the managed entity
   */
  public static <E extends ManagedEntity<M>, M> E manage(E entity, M manager) {
    entity.manager = manager;
    return entity;
  }
}
