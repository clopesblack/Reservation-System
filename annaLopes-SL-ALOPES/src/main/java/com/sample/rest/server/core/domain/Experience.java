package com.sample.rest.server.core.domain;

import java.beans.ConstructorProperties;

public class Experience {

    private final Long code;

    @ConstructorProperties("id")
    public Experience(final Long code) {
        this.code = code;
    }

    public Long getCode() {
        return code;
    }
}
