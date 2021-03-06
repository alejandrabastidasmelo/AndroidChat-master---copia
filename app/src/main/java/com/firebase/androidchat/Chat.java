package com.firebase.androidchat;

import java.util.GregorianCalendar;

/**
 * @author greg
 * @since 6/21/13
 */
public class Chat {

    private String message;
    private String author;
    private String text;
    private String name;

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Chat() {
    }

    Chat(String message, String author) {
        this.message = message;
        this.author = author;
        //this.text = text;
        //this.name = name;

    }

    public String getMessage() {
        return message;
    }

    public String getAuthor() {
        return author;
    }

    public String getText(){
        return text;
    }

    public  String getName(){
        return name;
    }

    public void setAlgo()
    {
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.getTimeInMillis();

    }
}
