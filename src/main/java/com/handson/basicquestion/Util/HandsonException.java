package com.handson.basicquestion.Util;

import java.util.function.Supplier;

public class HandsonException extends RuntimeException implements Supplier<HandsonException> {

    public HandsonException(String message) {
        super(message);
    }

    @Override
    public HandsonException get() {
        return this;
    }
}
