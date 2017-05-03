package com.felix.zhiban.bean.filmusbox;

import java.util.List;

/**
 * Created by XiaGF on 2017/4/28.
 */

public class FilmUsBox {

    private String date;

    private List<Subjects>subjects;

    private String title;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<Subjects> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<Subjects> subjects) {
        this.subjects = subjects;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
