package com.aode.buyoapp.LL.bean;

import java.util.Date;
public class Orders {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_orders.id
     *
     * @mbggenerated
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_orders.address
     *
     * @mbggenerated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_orders.description
     *
     * @mbggenerated
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_orders.price
     *
     * @mbggenerated
     */
    private Double price;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_orders.length
     *
     * @mbggenerated
     */
    private Integer length;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_orders.state
     *
     * @mbggenerated
     */
    private String state;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_orders.u_id
     *
     * @mbggenerated
     */
    private String uId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_orders.date
     *
     * @mbggenerated
     */
    private Date date;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_orders.b_id
     *
     * @mbggenerated
     */
    private String bId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_orders.c_id
     *
     * @mbggenerated
     */
    private Long cId;

    private String pId ;
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_orders
     *
     * @mbggenerated
     */

    private String buyer;

    private String issuer;
    private Business business ;

    public Orders(Long id, String address, String description, Double price, Integer length, String state, String uId, Date date, String bId, Long cId, String pId, String buyer, String issuer, Business business) {
        this.id = id;
        this.address = address;
        this.description = description;
        this.price = price;
        this.length = length;
        this.state = state;
        this.uId = uId;
        this.date = date;
        this.bId = bId;
        this.cId = cId;
        this.pId = pId;
        this.buyer = buyer;
        this.issuer = issuer;
        this.business = business;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_orders
     *
     * @mbggenerated
     */
    public Orders() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_orders.id
     *
     * @return the value of tb_orders.id
     *
     * @mbggenerated
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_orders.id
     *
     * @param id the value for tb_orders.id
     *
     * @mbggenerated
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_orders.address
     *
     * @return the value of tb_orders.address
     *
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_orders.address
     *
     * @param address the value for tb_orders.address
     *
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_orders.description
     *
     * @return the value of tb_orders.description
     *
     * @mbggenerated
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_orders.description
     *
     * @param description the value for tb_orders.description
     *
     * @mbggenerated
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_orders.price
     *
     * @return the value of tb_orders.price
     *
     * @mbggenerated
     */
    public Double getPrice() {
        return price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_orders.price
     *
     * @param price the value for tb_orders.price
     *
     * @mbggenerated
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_orders.length
     *
     * @return the value of tb_orders.length
     *
     * @mbggenerated
     */
    public Integer getLength() {
        return length;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_orders.length
     *
     * @param length the value for tb_orders.length
     *
     * @mbggenerated
     */
    public void setLength(Integer length) {
        this.length = length;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_orders.state
     *
     * @return the value of tb_orders.state
     *
     * @mbggenerated
     */
    public String getState() {
        return state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_orders.state
     *
     * @param state the value for tb_orders.state
     *
     * @mbggenerated
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_orders.u_id
     *
     * @return the value of tb_orders.u_id
     *
     * @mbggenerated
     */
    public String getuId() {
        return uId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_orders.u_id
     *
     * @param uId the value for tb_orders.u_id
     *
     * @mbggenerated
     */
    public void setuId(String uId) {
        this.uId = uId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_orders.date
     *
     * @return the value of tb_orders.date
     *
     * @mbggenerated
     */
    public Date getDate() {
        return date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_orders.date
     *
     * @param date the value for tb_orders.date
     *
     * @mbggenerated
     */
    public void setDate(Date date) {
        this.date = date;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_orders.b_id
     *
     * @return the value of tb_orders.b_id
     *
     * @mbggenerated
     */
    public String getbId() {
        return bId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_orders.b_id
     *
     * @param bId the value for tb_orders.b_id
     *
     * @mbggenerated
     */
    public void setbId(String bId) {
        this.bId = bId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_orders.c_id
     *
     * @return the value of tb_orders.c_id
     *
     * @mbggenerated
     */
    public Long getcId() {
        return cId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_orders.c_id
     *
     * @param cId the value for tb_orders.c_id
     *
     * @mbggenerated
     */
    public void setcId(Long cId) {
        this.cId = cId;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public Business getBusiness() {
        return business;
    }

    public void setBusiness(Business business) {
        this.business = business;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", length=" + length +
                ", state='" + state + '\'' +
                ", uId='" + uId + '\'' +
                ", date=" + date +
                ", bId='" + bId + '\'' +
                ", cId=" + cId +
                ", pId='" + pId + '\'' +
                ", buyer='" + buyer + '\'' +
                ", issuer='" + issuer + '\'' +
                ", business=" + business +
                '}';
    }
}