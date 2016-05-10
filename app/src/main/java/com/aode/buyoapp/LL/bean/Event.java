package com.aode.buyoapp.LL.bean;

import java.util.List;

/**
 * Created by 黎垒 on 2016/3/22.Go。
 */

public class Event {
    /**
     * 列表加载事件
     */
    public static class ItemListEvent {
        private List<Item> items;

        public ItemListEvent(List<Item> items) {
            this.items = items;
        }

        public List<Item> getItems() {
            return items;

        }
    }

}