package org.moloshnikov.votingsystem.model;

import org.springframework.data.domain.Persistable;

public abstract class AbstractBaseEntity implements Persistable<Integer> {
    protected Integer id;

    protected AbstractBaseEntity() {
    }

    protected AbstractBaseEntity(Integer id) {
        this.id = id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public boolean isNew() {
        return this.id == null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + ":" + id;
    }

    @Override
    public int hashCode() {
        return id == null ? 0 : id;
    }
}
