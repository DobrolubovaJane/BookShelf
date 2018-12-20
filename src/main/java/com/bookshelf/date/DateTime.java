package com.bookshelf.date;

import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public interface DateTime {
    Date now();
}
