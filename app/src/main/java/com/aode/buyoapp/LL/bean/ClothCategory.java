package com.aode.buyoapp.LL.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黄柏樟 on 2016/5/16.
 * @Explain: 作为搜索条件的布匹属性，作为json传给安卓用
 */
public class ClothCategory {
    private List<String> size = new ArrayList<String>();
    private List<String> color = new ArrayList<String>();
    private List<String> pattern = new ArrayList<String>();
    private List<String> width = new ArrayList<String>();

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public List<String> getColor() {
        return color;
    }

    public void setColor(List<String> color) {
        this.color = color;
    }

    public List<String> getPattern() {
        return pattern;
    }

    public void setPattern(List<String> pattern) {
        this.pattern = pattern;
    }

    public List<String> getWidth() {
        return width;
    }

    public void setWidth(List<String> width) {
        this.width = width;
    }
}
