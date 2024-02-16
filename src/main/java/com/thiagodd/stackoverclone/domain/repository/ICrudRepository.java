package com.thiagodd.stackoverclone.domain.repository;

import com.thiagodd.stackoverclone.domain.model.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ICrudRepository<T extends BaseEntity> extends JpaRepository<T, Long> {
}
