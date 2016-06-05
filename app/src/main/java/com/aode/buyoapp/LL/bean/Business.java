package com.aode.buyoapp.LL.bean;

import java.io.Serializable;
import java.util.List;

public class Business implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_business.id
     *
     * @mbggenerated
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_business.login_name
     *
     * @mbggenerated
     */
    private String loginName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_business.name
     *
     * @mbggenerated
     */
    private String name;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_business.password
     *
     * @mbggenerated
     */
    private String password;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_business.license
     *
     * @mbggenerated
     */

    private String phoneNumber;

    private String license;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_business.address
     *
     * @mbggenerated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_business.sales
     *
     * @mbggenerated
     */
    private Long sales;

    private String description;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_business
     *
     * @mbggenerated
     */

    private String contacts;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_business. identities
     *
     * @mbggenerated
     */
    private String identities;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column tb_business.payment
     *
     * @mbggenerated
     */
    private String payment;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_business
     */
    private List<Cloth> cloths;

    private List<Orders> orderses;

    public Business(String id, String loginName, String name, String password, String phoneNumber, String license, String address, Long sales, String description, String contacts, String identities, String payment, List<Cloth> cloths, List<Orders> orderses) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.license = license;
        this.address = address;
        this.sales = sales;
        this.description = description;
        this.contacts = contacts;
        this.identities = identities;
        this.payment = payment;
        this.cloths = cloths;
        this.orderses = orderses;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table tb_business
     *
     * @mbggenerated
     */
    public Business() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_business.id
     *
     * @return the value of tb_business.id
     * @mbggenerated
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_business.id
     *
     * @param id the value for tb_business.id
     * @mbggenerated
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_business.login_name
     *
     * @return the value of tb_business.login_name
     * @mbggenerated
     */
    public String getLoginName() {
        return loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_business.login_name
     *
     * @param loginName the value for tb_business.login_name
     * @mbggenerated
     */
    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_business.name
     *
     * @return the value of tb_business.name
     * @mbggenerated
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_business.name
     *
     * @param name the value for tb_business.name
     * @mbggenerated
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_business.password
     *
     * @return the value of tb_business.password
     * @mbggenerated
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_business.password
     *
     * @param password the value for tb_business.password
     * @mbggenerated
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_business.license
     *
     * @return the value of tb_business.license
     * @mbggenerated
     */
    public String getLicense() {
        return license;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_business.license
     *
     * @param license the value for tb_business.license
     * @mbggenerated
     */
    public void setLicense(String license) {
        this.license = license;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_business.address
     *
     * @return the value of tb_business.address
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_business.address
     *
     * @param address the value for tb_business.address
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column tb_business.sales
     *
     * @return the value of tb_business.sales
     * @mbggenerated
     */
    public Long getSales() {
        return sales;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column tb_business.sales
     *
     * @param sales the value for tb_business.sales
     * @mbggenerated
     */
    public void setSales(Long sales) {
        this.sales = sales;
    }

    public List<Cloth> getCloths() {
        return cloths;
    }

    public void setCloths(List<Cloth> cloths) {
        this.cloths = cloths;
    }

    public List<Orders> getOrderses() {
        return orderses;
    }

    public void setOrderses(List<Orders> orderses) {
        this.orderses = orderses;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getIdentities() {
        return identities;
    }

    public void setIdentities(String identities) {
        this.identities = identities;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    @Override
    public String toString() {
        return "Business{" +
                "id='" + id + '\'' +
                ", loginName='" + loginName + '\'' +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", license='" + license + '\'' +
                ", address='" + address + '\'' +
                ", sales=" + sales +
                ", description='" + description + '\'' +
                ", contacts='" + contacts + '\'' +
                ", identities='" + identities + '\'' +
                ", payment='" + payment + '\'' +
                ", cloths=" + cloths +
                ", orderses=" + orderses +
                '}';
    }
}