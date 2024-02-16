package com.thiagodd.stackoverclone.domain.service.impl;

import com.thiagodd.stackoverclone.domain.dto.BaseDTO;
import com.thiagodd.stackoverclone.domain.mapper.IGenericMapper;
import com.thiagodd.stackoverclone.domain.model.BaseEntity;
import com.thiagodd.stackoverclone.domain.repository.ICrudRepository;
import com.thiagodd.stackoverclone.domain.service.ICrudService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public class CrudServiceImpl<T extends BaseEntity, E extends BaseDTO> implements ICrudService<T, E> {

    protected final ICrudRepository<T> repository;
    protected final IGenericMapper<T, E> mapper;

    public CrudServiceImpl(ICrudRepository<T> repository, IGenericMapper<T, E> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public E create(E requestDTO) {
        var entity = mapper.toEntity(requestDTO);
        var persistedEntity = repository.save(entity);

        return mapper.toDto(persistedEntity);
    }

    @Override
    public E update(E requestDTO) {
        var entityFound = repository.findById(requestDTO.getId())
                .orElseThrow(EntityNotFoundException::new);

        mapper.partialUpdate(requestDTO, entityFound);
        var updatedEntity = repository.save(entityFound);

        return mapper.toDto(updatedEntity);
    }

    @Override
    public E findById(Long id) {
        var entityFound = repository.findById(id)
                .orElseThrow(EntityNotFoundException::new);

        return mapper.toDto(entityFound);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Page<E> findAll(Pageable pageable) {
       Page<T> page = repository.findAll(pageable);
       return page.map(mapper::toDto);
    }
}
