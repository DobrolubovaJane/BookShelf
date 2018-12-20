package com.bookshelf.date;


import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateTimeImpl implements DateTime {
    @Override
    public Date now() {
        return new Date();
    }

}
