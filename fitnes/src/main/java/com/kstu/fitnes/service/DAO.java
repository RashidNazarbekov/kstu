package com.kstu.fitnes.service;

import java.util.List;

public interface DAO<Entity, key> {
    List<Entity> getAll();
    Entity getById(key id);
    void add(Entity entity);
    void update(Entity entity);
    void delete(key id);
}
