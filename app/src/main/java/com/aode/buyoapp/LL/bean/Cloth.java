package com.aode.buyoapp.LL.bean;

import java.io.Serializable;
import java.util.List;

public class Cloth implements Serializable{
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_cloth.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_cloth.size
     *
     * @mbggenerated
     */

    private String title ;
    private String size;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_cloth.color
     *
     * @mbggenerated
     */
    private String color;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_cloth.pattern
     *
     * @mbggenerated
     */
    private String pattern;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_cloth.width
     *
     * @mbggenerated
     */
    private String width;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_cloth.stock
     *
     * @mbggenerated
     */
    private Long stock;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_cloth.b_id
     *
     * @mbggenerated
     */
    private String bId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_cloth.picture
     *
     * @mbggenerated
     */

    private Double price ;
    private String picture;



    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_cloth
     *
     * @mbggenerated
     */
    private Business business ;
    private List<User> users ;

    public Cloth(Long id, String title, String size, String color, String pattern, String width, Long stock, String bId, Double price, String picture, Business business, List<User> users) {
        this.id = id;
        this.title = title;
        this.size = size;
        this.color = color;
        this.pattern = pattern;
        this.width = width;
        this.stock = stock;
        this.bId = bId;
        this.price = price;
        this.picture = picture;
        this.business = business;
        this.users = users;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_cloth
     *
     * @mbggenerated
     */
    public Cloth() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_cloth.id
     *
     * @return the value of tb_cloth.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_cloth.id
     *
     * @param id the value for tb_cloth.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_cloth.size
     *
     * @return the value of tb_cloth.size
     *
     * @mbggenerated
     */
    public String getSize() {
        return size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_cloth.size
     *
     * @param size the value for tb_cloth.size
     *
     * @mbggenerated
     */
    public void setSize(String size) {
        this.size = size;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_cloth.color
     *
     * @return the value of tb_cloth.color
     *
     * @mbggenerated
     */
    public String getColor() {
        return color;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_cloth.color
     *
     * @param color the value for tb_cloth.color
     *
     * @mbggenerated
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_cloth.pattern
     *
     * @return the value of tb_cloth.pattern
     *
     * @mbggenerated
     */
    public String getPattern() {
        return pattern;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_cloth.pattern
     *
     * @param pattern the value for tb_cloth.pattern
     *
     * @mbggenerated
     */
    public void setPattern(String pattern) {
        this.pattern = pattern;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_cloth.width
     *
     * @return the value of tb_cloth.width
     *
     * @mbggenerated
     */
    public String getWidth() {
        return width;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_cloth.width
     *
     * @param width the value for tb_cloth.width
     *
     * @mbggenerated
     */
    public void setWidth(String width) {
        this.width = width;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_cloth.stock
     *
     * @return the value of tb_cloth.stock
     *
     * @mbggenerated
     */
    public Long getStock() {
        return stock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_cloth.stock
     *
     * @param stock the value for tb_cloth.stock
     *
     * @mbggenerated
     */
    public void setStock(Long stock) {
        this.stock = stock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_cloth.b_id
     *
     * @return the value of tb_cloth.b_id
     *
     * @mbggenerated
     */
    public String getbId() {
        return bId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_cloth.b_id
     *
     * @param bId the value for tb_cloth.b_id
     *
     * @mbggenerated
     */
    public void setbId(String bId) {
        this.bId = bId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_cloth.picture
     *
     * @return the value of tb_cloth.picture
     *
     * @mbggenerated
     */
    public String getPicture() {
        return picture;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_cloth.picture
     *
     * @param picture the value for tb_cloth.picture
     *
     * @mbggenerated
     */
    public void setPicture(String picture) {
        this.picture = picture;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Cloth{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", size='" + size + '\'' +
                ", color='" + color + '\'' +
                ", pattern='" + pattern + '\'' +
                ", width='" + width + '\'' +
                ", stock=" + stock +
                ", bId='" + bId + '\'' +
                ", price=" + price +
                ", picture='" + picture + '\'' +
                ", business=" + business +
                ", users=" + users +
                '}';
    }
}