package com.instinctools.reducerlink.dao.impl;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

public class BaseDaoImpl<T, ID extends Serializable> {
    protected Class<T> domainClass;
    protected EntityManager em;
    protected SimpleJpaRepository<T, ID> repository;

    public BaseDaoImpl(Class<T> domainClass) {
        this.domainClass = domainClass;
    }

    @PersistenceContext
    private void setEntityManager(EntityManager em) {
        this.em = em;
        this.repository = new SimpleJpaRepository<T, ID>(domainClass, em);
    }

    public void delete(ID id) {
        repository.delete(id);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public void delete(Iterable<? extends T> entities) {
        repository.delete(entities);
    }

    public void deleteInBatch(Iterable<T> entities) {
        repository.deleteInBatch(entities);
    }

    public void deleteAll() {
        repository.deleteAll();
    }

    public void deleteAllInBatch() {
        repository.deleteAllInBatch();
    }

    public T findOne(ID id) {
        return repository.findOne(id);
    }

    public T getOne(ID id) {
        return repository.getOne(id);
    }

    public boolean exists(ID id) {
        return repository.exists(id);
    }

    public List<T> findAll() {
        return repository.findAll();
    }

    public List<T> findAll(Iterable<ID> ids) {
        return repository.findAll(ids);
    }

    public List<T> findAll(Sort sort) {
        return repository.findAll(sort);
    }

    public Page<T> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public T findOne(Specification<T> spec) {
        return repository.findOne(spec);
    }

    public List<T> findAll(Specification<T> spec) {
        return repository.findAll(spec);
    }

    public Page<T> findAll(Specification<T> spec, Pageable pageable) {
        return repository.findAll(spec, pageable);
    }

    public List<T> findAll(Specification<T> spec, Sort sort) {
        return repository.findAll(spec, sort);
    }

    public long count() {
        return repository.count();
    }

    public long count(Specification<T> spec) {
        return repository.count(spec);
    }

    public <S extends T> S save(S entity) {
        return repository.save(entity);
    }

    public <S extends T> S saveAndFlush(S entity) {
        return repository.saveAndFlush(entity);
    }

    public <S extends T> List<S> save(Iterable<S> entities) {
        return repository.save(entities);
    }

    public void flush() {
        repository.flush();
    }

    public void refresh(T entity) {
        em.refresh(entity);
    }

    public boolean contains(T entity) {
        return em.contains(entity);
    }

    public void detach(T entity) {
        em.detach(entity);
    }

    public Criteria createCriteria() {
        return em.unwrap(Session.class).createCriteria(domainClass);
    }
}