package com.thiagodd.stackoverclone.api.controller.impl;

import com.thiagodd.stackoverclone.api.controller.ICrudController;
import com.thiagodd.stackoverclone.domain.dto.BaseDTO;
import com.thiagodd.stackoverclone.domain.model.BaseEntity;
import com.thiagodd.stackoverclone.domain.service.ICrudService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@SuppressWarnings({"unchecked", "rawtypes"})
@ResponseBody
public class CrudController<T extends BaseEntity, E extends BaseDTO> implements ICrudController<T, E> {

    private final ICrudService<T, E> crudService;

    public CrudController(ICrudService<T, E> crudService) {
        this.crudService = crudService;
    }

    @Override
    @PostMapping
    public ResponseEntity<E> create(@Valid @RequestBody E requestDTO) {
        var persistedEntity = crudService.create(requestDTO);
        var location = createURI(persistedEntity.getId());

        return ResponseEntity.created(location).body(persistedEntity);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<E> update(@PathVariable Long id, @Valid @RequestBody E requestDTO) {
        requestDTO.setId(id);
        var responseDTO = crudService.update(requestDTO);
        var location = createURI(id);

        return ResponseEntity.ok().location(location).body(responseDTO);
    }


    @Override
    @GetMapping
    public ResponseEntity<Page<E>> findAll(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        var page = crudService.findAll(pageable);

        return ResponseEntity.ok(page);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<E> findById(@PathVariable Long id) {
        var responseDTO = crudService.findById(id);

        return ResponseEntity.ok(responseDTO);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        crudService.delete(id);

        return ResponseEntity.noContent().build();
    }

    private URI createURI(Long id) {
        return ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
    }


}
