
package com.example.restaurant_app.model.reservationmodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class CreatedTable {

    @SerializedName("_id")
    @Expose
    private String id;
    @SerializedName("phone")
    @Expose
    private Integer phone;
    @SerializedName("requestedtime")
    @Expose
    private String requestedtime;
    @SerializedName("waitingtime")
    @Expose
    private Object waitingtime;
    @SerializedName("checkintime")
    @Expose
    private Object checkintime;
    @SerializedName("checkouttime")
    @Expose
    private Object checkouttime;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("persons")
    @Expose
    private String persons;
    @SerializedName("table")
    @Expose
    private Object table;
    @SerializedName("Status")
    @Expose
    private String status;
    @SerializedName("__v")
    @Expose
    private Integer v;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getRequestedtime() {
        return requestedtime;
    }

    public void setRequestedtime(String requestedtime) {
        this.requestedtime = requestedtime;
    }

    public Object getWaitingtime() {
        return waitingtime;
    }

    public void setWaitingtime(Object waitingtime) {
        this.waitingtime = waitingtime;
    }

    public Object getCheckintime() {
        return checkintime;
    }

    public void setCheckintime(Object checkintime) {
        this.checkintime = checkintime;
    }

    public Object getCheckouttime() {
        return checkouttime;
    }

    public void setCheckouttime(Object checkouttime) {
        this.checkouttime = checkouttime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }

    public Object getTable() {
        return table;
    }

    public void setTable(Object table) {
        this.table = table;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getV() {
        return v;
    }

    public void setV(Integer v) {
        this.v = v;
    }

}
