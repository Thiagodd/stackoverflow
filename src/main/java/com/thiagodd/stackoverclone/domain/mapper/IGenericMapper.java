package com.thiagodd.stackoverclone.domain.mapper;

import org.mapstruct.*;


public interface IGenericMapper<T, E> {
    T toEntity(E dto);

    E toDto(T entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    T partialUpdate(E dto, @MappingTarget T entity);
}
