package com.english.entity;

public class Word {
	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWord_id() {
        return word_id;
    }

    public void setWord_id(int word_id) {
        this.word_id = word_id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody_zh() {
        return body_zh;
    }

    public void setBody_zh(String body_zh) {
        this.body_zh = body_zh;
    }

    public String getBody_en() {
        return body_en;
    }

    public void setBody_en(String body_en) {
        this.body_en = body_en;
    }

    public String getUsage_zh() {
        return usage_zh;
    }

    public void setUsage_zh(String usage_zh) {
        this.usage_zh = usage_zh;
    }

    public String getUsage_en() {
        return usage_en;
    }

    public void setUsage_en(String usage_en) {
        this.usage_en = usage_en;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getCreat_user_id() {
        return creat_user_id;
    }

    public void setCreat_user_id(int creat_user_id) {
        this.creat_user_id = creat_user_id;
    }

    public int getModify_user_id() {
        return modify_user_id;
    }

    public void setModify_user_id(int modify_user_id) {
        this.modify_user_id = modify_user_id;
    }

    public String getSoundmark() {
        return soundmark;
    }

    public void setSoundmark(String soundmark) {
        this.soundmark = soundmark;
    }

    public String getSoundmark2() {
        return soundmark2;
    }

    public void setSoundmark2(String soundmark2) {
        this.soundmark2 = soundmark2;
    }

    public String getOther_body() {
        return other_body;
    }

    public void setOther_body(String other_body) {
        this.other_body = other_body;
    }

    public String getSound_ex() {
        return sound_ex;
    }

    public void setSound_ex(String sound_ex) {
        this.sound_ex = sound_ex;
    }

    public String getCreat_time() {
        return creat_time;
    }

    public void setCreat_time(String creat_time) {
        this.creat_time = creat_time;
    }

    public String getModify_time() {
        return modify_time;
    }

    public void setModify_time(String modify_time) {
        this.modify_time = modify_time;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getUnit_number() {
        return unit_number;
    }

    public void setUnit_number(int unit_number) {
        this.unit_number = unit_number;
    }

    public int getPolyphone() {
        return polyphone;
    }

    public void setPolyphone(int polyphone) {
        this.polyphone = polyphone;
    }

    private int id;
    private int word_id;
    private String body;
    private  String body_zh;
    private  String body_en;
    private String usage_zh;
    private String usage_en;
    private String rank;
    private int creat_user_id;
    private int modify_user_id;
    private String creat_time;
    private String modify_time;
    private int book_id;
    private int unit_number;
    private int polyphone;
    private String soundmark;
    private String soundmark2;
    private String other_body;
    private String sound_ex;



}
