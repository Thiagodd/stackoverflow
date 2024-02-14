package com.thiagodd.stackoverclone.domain.service.impl;

import com.thiagodd.stackoverclone.domain.dto.BaseDTO;
import com.thiagodd.stackoverclone.domain.mapper.IGenericMapper;
import com.thiagodd.stackoverclone.domain.model.BaseEntity;
import com.thiagodd.stackoverclone.domain.repository.ICrudGenericRepository;
import com.thiagodd.stackoverclone.domain.service.ICrudGenericService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.util.Assert;

import java.util.Collection;


public class GenericCrudServiceImpl<T extends BaseEntity, E extends BaseDTO> implements ICrudGenericService<T, E> {

    private final ICrudGenericRepository<T> repository;
    private final IGenericMapper<T, E> mapper;

    public GenericCrudServiceImpl(ICrudGenericRepository<T> repository, IGenericMapper<T, E> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public T save(E entity) {
        Assert.notNull(entity, "Entity must not be null");
        T entity1 = mapper.toEntity(entity);
        return repository.save(entity1);
    }

    @Override
    public T update(E entity) {
        Assert.notNull(entity, "Id must not be null");
        return null;
    }

    @Override
    public T findById(Long id) {
        Assert.notNull(id, "Id must not be null");
        return repository.findById(id).orElseThrow(
                EntityNotFoundException::new
        );
    }

    @Override
    public void delete(Long id) {
        Assert.notNull(id, "Id must not be null");
        repository.deleteById(id);
    }

    @Override
    public Collection<T> findAll() {
        return repository.findAll();
    }
}
