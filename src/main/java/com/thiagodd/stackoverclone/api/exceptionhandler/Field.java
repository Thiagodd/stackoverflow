package com.thiagodd.stackoverclone.api.exceptionhandler;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Field {
    private String name;
    private String reason;
}
