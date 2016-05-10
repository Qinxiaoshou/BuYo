package com.aode.buyoapp.LL.bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 黎垒 on 2016/3/22.Go。
 */
public class Item {
    private String id;
    private String content;
    public static List<Item> ITEMS = new ArrayList<Item>();

    static {
        addItem(new Item("1", "花型"));
        addItem(new Item("2", "卡通人物"));
        addItem(new Item("3", "大自然"));
        addItem(new Item("4", "几何字母"));
        addItem(new Item("5", "动植物"));
        addItem(new Item("6", "抽象生活"));
        addItem(new Item("7", "底部"));
        addItem(new Item("8", "针织"));
        addItem(new Item("9", "蕾丝"));
        addItem(new Item("10", "更多"));

    }

    private static void addItem(Item item) {
        ITEMS.add(item);
    }

    public Item(String id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}
