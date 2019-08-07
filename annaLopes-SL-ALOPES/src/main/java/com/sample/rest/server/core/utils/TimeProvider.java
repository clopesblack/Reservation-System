package com.sample.rest.server.core.utils;

import java.time.LocalDateTime;

public class TimeProvider {
    public long getTimeInMillis() {
        return System.currentTimeMillis();
    }

    public LocalDateTime getCurrentDateTime() {
        return LocalDateTime.now();
    }
}
