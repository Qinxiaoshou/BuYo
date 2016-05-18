package com.aode.buyoapp.LL.bean;

/**
 * Created by 黎垒 on 2016/5/17.Go。
 */

public class Event {
    /**
     * 列表加载事件
     */
    public static class ClothTypeListEvent {
        private ClothCategory clothCategory;

        public ClothTypeListEvent(ClothCategory clothCategory) {
            this.clothCategory = clothCategory;
        }

        public ClothCategory getClothCategory() {
            return clothCategory;

        }
    }

}