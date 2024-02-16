package com.thiagodd.stackoverclone.domain.mapper;

import com.thiagodd.stackoverclone.domain.dto.TagDTO;
import com.thiagodd.stackoverclone.domain.model.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface TagMapper extends IGenericMapper<Tag, TagDTO>{
}
