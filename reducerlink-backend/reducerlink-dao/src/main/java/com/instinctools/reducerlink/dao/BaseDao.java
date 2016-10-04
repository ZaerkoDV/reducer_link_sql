package com.instinctools.reducerlink.dao;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

public interface BaseDao<T, ID extends Serializable> {
    public void delete(ID id);
    public void delete(T entity);
    public void delete(Iterable<? extends T> entities);
    public void deleteInBatch(Iterable<T> entities);
    public void deleteAll();
    public void deleteAllInBatch();
    public T findOne(ID id);
    public T getOne(ID id);
    public boolean exists(ID id);
    public List<T> findAll();
    public List<T> findAll(Iterable<ID> ids);
    public List<T> findAll(Sort sort);
    public Page<T> findAll(Pageable pageable);
    public List<T> findAll(Specification<T> spec);
    public Page<T> findAll(Specification<T> spec, Pageable pageable);
    public List<T> findAll(Specification<T> spec, Sort sort);
    public long count();
    public long count(Specification<T> spec);
    public <S extends T> S save(S entity);
    public <S extends T> S saveAndFlush(S entity);
    public <S extends T> List<S> save(Iterable<S> entities);
    public void flush();
    public void refresh(T entity);
    public boolean contains(T entity);
    public void detach(T entity);
    public Criteria createCriteria();
}