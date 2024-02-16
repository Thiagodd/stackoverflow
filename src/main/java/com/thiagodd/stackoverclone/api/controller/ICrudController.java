package com.thiagodd.stackoverclone.api.controller;

import com.thiagodd.stackoverclone.domain.dto.BaseDTO;
import com.thiagodd.stackoverclone.domain.model.BaseEntity;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;



public interface ICrudController<T extends BaseEntity, E extends BaseDTO> {

    ResponseEntity<E> create(@Valid @RequestBody E requestDTO);

    ResponseEntity<E> update(@PathVariable Long id, @Valid @RequestBody E requestDTO);

    ResponseEntity<Page<E>> findAll(Pageable pageable);

    ResponseEntity<E> findById(@PathVariable Long id);

    ResponseEntity<Void> delete(@PathVariable Long id);
}
