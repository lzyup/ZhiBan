package com.felix.zhiban.bean.filmlive;

import com.felix.zhiban.bean.filmusbox.Subject;

import java.util.List;

/**
 * Created by XiaGF on 2017/4/28.
 */

public class FilmLive {

    private int count;

    private int start;

    private int total;

    private List<Subject>subjects;

    private String title;

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

    public List<Subject> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
