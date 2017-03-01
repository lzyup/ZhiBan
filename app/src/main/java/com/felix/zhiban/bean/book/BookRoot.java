package com.felix.zhiban.bean.book;


import java.util.List;

public class BookRoot  {

    private int count;

    private int start;

    private int total;

    private List<Books> books;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Books> getBooks() {
        return books;
    }

    public void setBooks(List<Books> bookses) {
        this.books = bookses;
    }
}
