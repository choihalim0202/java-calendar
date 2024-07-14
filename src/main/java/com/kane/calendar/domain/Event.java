package com.kane.calendar.domain;

public interface Event {
    void print();

    boolean support(EventType type);
}
