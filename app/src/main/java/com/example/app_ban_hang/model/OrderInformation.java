package com.example.app_ban_hang.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Entity
public class OrderInformation implements Serializable {

    @SerializedName("purchaseId")
    @ColumnInfo(name="purchaseId")
    @PrimaryKey(autoGenerate = true)
    private int purchaseId;

    @SerializedName("orderId")
    @ColumnInfo(name="orderId")
    private int orderId;

    @SerializedName("userId")
    @ColumnInfo(name="userId")
    private int userId;

    @SerializedName("username")
    @ColumnInfo(name="username")
    private String username;

    @SerializedName("email")
    @ColumnInfo(name="email")
    private String email;

    @SerializedName("phone")
    @ColumnInfo(name="phone")
    private String phone;

    @SerializedName("address")
    @ColumnInfo(name="address")
    private String address;

    @SerializedName("totalPayment")
    @ColumnInfo(name="totalPayment")
    private double totalPayment;

    @SerializedName("paymentMethod")
    @ColumnInfo(name="paymentMethod")
    private String paymentMethod;

    @SerializedName("purchaseTime")
    @ColumnInfo(name="purchaseTime")
    private String purchaseTime;


    public int getPurchaseId() {
        return purchaseId;
    }
    public void setPurchaseId(int purchaseId) {
        this.purchaseId = purchaseId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(double totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPurchaseTime() {
        Date currentDateTime = Calendar.getInstance().getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String purchaseTime = dateFormat.format(currentDateTime);
        return purchaseTime;
    }

    public void setPurchaseTime(String purchaseTime) {
        this.purchaseTime = purchaseTime;
    }



    public OrderInformation(int purchaseId, int orderId, int userId, String username, String email, String phone, String address, double totalPayment, String paymentMethod, String purchaseTime) {
        this.purchaseId = purchaseId;
        this.orderId = orderId;
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.totalPayment = totalPayment;
        this.paymentMethod = paymentMethod;
        this.purchaseTime = purchaseTime;
    }

    public OrderInformation() {
    }
}
