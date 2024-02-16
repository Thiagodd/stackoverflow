package com.thiagodd.stackoverclone.domain.service;

import com.thiagodd.stackoverclone.domain.dto.BaseDTO;
import com.thiagodd.stackoverclone.domain.model.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface ICrudService<T extends BaseEntity, E extends BaseDTO> {

    E create(E requestDTO);

    E update(E requestDTO);

    Page<E> findAll(Pageable pageable);

    E findById(Long id);

    void delete(Long id);

}
