package com.thiagodd.stackoverclone.api.exceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import lombok.*;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
@AllArgsConstructor
public class ErrorResponse {

    private static final URI BLANK_TYPE = URI.create("about:blank");

    private Instant timestamp;

    private URI type;

    private int status;

    @Nullable
    private String title;

    @Nullable
    private String detail;

    @JsonProperty("invalid-params")
    private List<Field> fields;

}
