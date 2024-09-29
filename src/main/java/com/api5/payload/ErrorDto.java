package com.api5.payload;

import java.util.Date;

public class ErrorDto {
private String name;
private Date date;
private String uri;

    public ErrorDto(String name, Date date, String uri) {
        this.name = name;
        this.date = date;
        this.uri = uri;
    }

    public String getName() {
        return name;
    }

    public Date getDate() {
        return date;
    }

    public String getUri() {
        return uri;
    }
}
