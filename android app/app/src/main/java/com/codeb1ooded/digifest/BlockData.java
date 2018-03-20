package com.codeb1ooded.digifest;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by megha on 03/12/17.
 */

public class BlockData implements Serializable {
    String title;
    String name;
    int age;
    String sex;
    String others;
    String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getImage() {
        return image;
    }

    public void setImages(String image) {
        this.image = image;
    }
}
