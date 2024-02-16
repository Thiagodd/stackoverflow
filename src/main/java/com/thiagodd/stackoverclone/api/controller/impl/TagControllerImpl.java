package com.thiagodd.stackoverclone.api.controller.impl;


import com.thiagodd.stackoverclone.api.controller.ITagController;
import com.thiagodd.stackoverclone.domain.dto.TagDTO;
import com.thiagodd.stackoverclone.domain.model.Tag;
import com.thiagodd.stackoverclone.domain.service.ICrudService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/tag")
public class TagControllerImpl extends CrudController<Tag, TagDTO> implements ITagController {

    public TagControllerImpl(ICrudService<Tag, TagDTO> iCrudService) {
        super(iCrudService);
    }

}
