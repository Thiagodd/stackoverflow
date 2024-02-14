package com.thiagodd.stackoverclone.api.controller.impl;

import com.thiagodd.stackoverclone.api.controller.ICrudGenericController;
import com.thiagodd.stackoverclone.domain.dto.BaseDTO;
import com.thiagodd.stackoverclone.domain.model.BaseEntity;
import com.thiagodd.stackoverclone.domain.service.ICrudGenericService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SuppressWarnings({ "unchecked", "rawtypes" })
@ResponseBody
public class CrudGenericController<T extends BaseEntity, E extends BaseDTO> implements ICrudGenericController<T, E> {

    private final ICrudGenericService<T, E> iCrudGenericService;

    public CrudGenericController(ICrudGenericService<T, E> iCrudGenericService) {
        this.iCrudGenericService = iCrudGenericService;
    }

    @Override
    @PostMapping
    public ResponseEntity<Object> save(E entity) {
        try {
            return new ResponseEntity(iCrudGenericService.save(entity), HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Erro ao salvar!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    @GetMapping
    public ResponseEntity<E> findAll() {
        try {
            return new ResponseEntity(iCrudGenericService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Erro ao buscar todos!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        try {
            iCrudGenericService.delete(id);
            return new ResponseEntity("Sucesso ao apagar!", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity("Erro ao apagar!", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
