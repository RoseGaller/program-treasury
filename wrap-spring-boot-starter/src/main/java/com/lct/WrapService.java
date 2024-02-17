package com.lct;


public class WrapService {

    private String before;

    private String after;

    public WrapService(String before,String after){
        this.before = before;
        this.after = after;
    }


    public String wrap(String word){
        return before + word + after;
    }
}
