package com.instinctools.reducerlink.model;

import java.io.Serializable;
import javax.persistence.Transient;
import org.hibernate.proxy.HibernateProxy;

public abstract class BaseEntity<ID extends Serializable> {
    public abstract ID getId();

    @Override
    @SuppressWarnings({ "unchecked", "rawtypes" })
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (!(o instanceof BaseEntity)) {
            return false;
        }

        BaseEntity lhs = (BaseEntity)o;

        if (!getEntityClass().equals(lhs.getEntityClass())) {
            return false;
        }

        return (
            (getId() == null && lhs.getId() == null)
            || (getId() != null && getId().equals(lhs.getId()))
        );
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + getEntityClass().hashCode();
        result = 31 * result + (getId() == null ? 0 : getId().hashCode());
        return result;
    }

    @Transient
    public Class<?> getEntityClass() {
        if (this instanceof HibernateProxy) {
            return ((HibernateProxy)this).getHibernateLazyInitializer().getPersistentClass();
        }

        return this.getClass();
    }
}
