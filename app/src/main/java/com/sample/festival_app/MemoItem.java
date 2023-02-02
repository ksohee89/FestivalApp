package com.sample.festival_app;

public class MemoItem {
    private String name;
    private int id;
    private String title;
    private String memo;
    private String writeDate;
    private String duration;

    public MemoItem() {
    }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(String writeDate) {
        this.writeDate = writeDate;
    }
}
