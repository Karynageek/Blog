package com.myexample.blog.models;

import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String text;
    private String tag;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Message() {
    }
    public Message(User author, String tag, String text) {
        this.author=author;
        this.tag=tag;
        this.text=text;
    }
    public String getAutorName(){
        return  author != null ? author.getUsername() : "<none>";
    }
}
