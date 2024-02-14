package com.thiagodd.stackoverclone.api.controller;

import com.thiagodd.stackoverclone.domain.dto.BaseDTO;
import com.thiagodd.stackoverclone.domain.model.BaseEntity;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

public interface ICrudGenericController<T extends BaseEntity, E extends BaseDTO> {

    ResponseEntity<Object> save(@RequestBody @Valid E entity);

    ResponseEntity<E> findAll();

    ResponseEntity<String> delete(@PathVariable Long id);
}
