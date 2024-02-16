package com.thiagodd.stackoverclone.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "tag")
@Entity(name = "tag")
public class Tag extends BaseEntity{

    @Column(name = "last_activity_date")
    private Instant LastActivityDate;

    @Column(name = "has_synonyms")
    private boolean HasSynonyms;

    @Column(name = "is_moderator_only")
    private boolean isModeratorOnly;

    @Column(name = "is_required")
    private boolean isRequired;

    @Column(name = "count")
    private Integer count;

    @Column(name = "name")
    private String name;

    public void updateLastActivityDate(){
        this.LastActivityDate = Instant.now();
    }
}
