package com.thiagodd.stackoverclone.domain.service;

import com.thiagodd.stackoverclone.domain.dto.BaseDTO;
import com.thiagodd.stackoverclone.domain.model.BaseEntity;

import java.util.Collection;


public interface ICrudGenericService<T extends BaseEntity, E extends BaseDTO> {

    T save(E entity);

    T update(E entity);

    T findById(Long id);

    void delete(Long id);

    Collection<T> findAll();
}
