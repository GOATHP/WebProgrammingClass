package kr.co.tlf.ex.dto;

import java.util.Date;

public class MBDto {
    private int id;
    private String title;
    private String content;
    private String writer;
    private Date writeDate;
    private int count;
    private int groups;
    private int step;
    private int indent;

    public MBDto() {
    }

    public MBDto(int id, String title, String content, String writer, Date writeDate, int count, int groups, int step, int indent) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.writeDate = writeDate;
        this.count = count;
        this.groups = groups;
        this.step = step;
        this.indent = indent;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Date getWriteDate() {
        return writeDate;
    }

    public void setWriteDate(Date writeDate) {
        this.writeDate = writeDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getGroups() {
        return groups;
    }

    public void setGroups(int groups) {
        this.groups = groups;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getIndent() {
        return indent;
    }

    public void setIndent(int indent) {
        this.indent = indent;
    }
}
