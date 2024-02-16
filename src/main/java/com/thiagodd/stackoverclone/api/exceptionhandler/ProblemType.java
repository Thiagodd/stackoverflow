package com.thiagodd.stackoverclone.api.exceptionhandler;

import lombok.Getter;

import java.net.URI;

@Getter
public enum ProblemType {



    ENTITY_NOT_FOUND("Entity Not Found", "/entity-not-found",
                             "The requested resource does not exist. Check the URL or provided identifier."),

    ENTITY_IN_USE("Entity In Use", "/entity-in-use",
                          "The requested resource is currently in use and cannot be modified or deleted. Try again later or contact support."),

    ERROR_BUSINESS("Business Rule Violation", "/business-error",
                           "The request violates business rules. Refer to the documentation or contact support for details."),

    MESSAGE_NOT_READABLE("Message Not Readable", "/message-not-readable",
                                 "The provided message could not be processed due to invalid formatting or errors. Ensure it adheres to the expected format."),

    METHOD_NOT_SUPPORTED("Method Not Supported", "/method-not-supported",
                                 "The requested HTTP method is not supported for this resource. Consult the documentation for valid methods."),

    RESOURCE_NOT_FOUND("Resource Not Found", "/resource-not-found",
                               "The requested resource could not be found. Double-check the URL or identifier and ensure it exists."),

    PARAMETER_NOT_VALID("Invalid Request Parameters", "/parameter-not-valid",
                                "One or more request parameters are invalid or missing. Refer to the documentation for expected parameter values and formats.");

    private final String title;
    private final URI uri;
    private final String detail;

    ProblemType(String title, String uri, String detail) {
        this.title = title;
        this.uri = URI.create("Http://localhost:8080" + uri);
        this.detail = detail;
    }
}
