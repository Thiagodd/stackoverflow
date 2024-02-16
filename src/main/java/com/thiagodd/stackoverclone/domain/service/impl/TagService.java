package com.thiagodd.stackoverclone.domain.service.impl;

import com.thiagodd.stackoverclone.domain.dto.TagDTO;
import com.thiagodd.stackoverclone.domain.mapper.IGenericMapper;
import com.thiagodd.stackoverclone.domain.model.Tag;
import com.thiagodd.stackoverclone.domain.repository.ICrudRepository;
import com.thiagodd.stackoverclone.domain.service.ITagService;
import org.springframework.stereotype.Service;

@Service
public class TagService extends CrudServiceImpl<Tag, TagDTO> implements ITagService {

    public TagService(ICrudRepository<Tag> repository, IGenericMapper<Tag, TagDTO> mapper) {
        super(repository, mapper);
    }

    public void updateLastActivityDate(Long id){
        var tagDTO = super.findById(id);
        var tag = mapper.toEntity(tagDTO);

        tag.updateLastActivityDate();

        super.repository.save(tag);
    }
}
