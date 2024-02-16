package com.thiagodd.stackoverclone.domain.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.Instant;

@Data
public class TagDTO extends BaseDTO {

    @JsonProperty("last_activity_date")
    private Instant LastActivityDate;

    @JsonProperty("has_synonyms")
    private boolean HasSynonyms;

    @JsonProperty("is_moderator_only")
    private boolean isModeratorOnly;

    @JsonProperty("is_required")
    private boolean isRequired;

    private Integer count;

    private String name;
}
