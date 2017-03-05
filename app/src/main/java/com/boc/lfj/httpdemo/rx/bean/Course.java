package com.boc.lfj.httpdemo.rx.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class Course {
    private String name;

    private List<Book> books;
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
