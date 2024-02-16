package com.thiagodd.stackoverclone.domain.repository;

import com.thiagodd.stackoverclone.domain.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITagRepository extends ICrudRepository<Tag> {

    Optional<Tag> findByName(String name);
}
