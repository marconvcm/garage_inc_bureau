package com.garage_inc.bureau.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @JsonIgnore(false)
    @GeneratedValue()
    private Integer id;

    @Column(length = 100)
    private String customerName;

    @Column(length = 100)
    private String drivingLicenseNumber;

    @Column(length = 20)
    private String carLicensePlate;

    @Column(length = 500)
    private String address;

    @Column(length = 30)
    private String phone;

    @Column(length = 300)
    private String email;

    @Column(length = 4000)
    private String detailing;

    @Column
    private LocalDateTime scheduledDateTime;

    @Column
    private ScheduledType scheduledType;

    @Column
    private WorkState workState;

    @Column(length = 6)
    private String otp;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getDrivingLicenseNumber() {
        return drivingLicenseNumber;
    }

    public void setDrivingLicenseNumber(String drivingLicenseNumber) {
        this.drivingLicenseNumber = drivingLicenseNumber;
    }

    public String getCarLicensePlate() {
        return carLicensePlate;
    }

    public void setCarLicensePlate(String carLicensePlate) {
        this.carLicensePlate = carLicensePlate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDetailing() {
        return detailing;
    }

    public void setDetailing(String detailing) {
        this.detailing = detailing;
    }

    public LocalDateTime getScheduledDateTime() {
        return scheduledDateTime;
    }

    public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
        this.scheduledDateTime = scheduledDateTime;
    }

    public ScheduledType getScheduledType() {
        return scheduledType;
    }

    public void setScheduledType(ScheduledType scheduledType) {
        this.scheduledType = scheduledType;
    }

    public WorkState getWorkState() {
        return workState;
    }

    public void setWorkState(WorkState workState) {
        this.workState = workState;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    @JsonIgnore
    public String getOtp() {
        return otp;
    }
}
