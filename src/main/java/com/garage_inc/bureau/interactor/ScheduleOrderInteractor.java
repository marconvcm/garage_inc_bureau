package com.garage_inc.bureau.interactor;

import com.garage_inc.bureau.component.SendMessageProvider;
import com.garage_inc.bureau.model.Order;
import com.garage_inc.bureau.model.ScheduledType;
import com.garage_inc.bureau.model.WorkState;
import com.garage_inc.bureau.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Component
public class ScheduleOrderInteractor implements Interactor<ScheduleOrderInteractor.Input, ScheduleOrderInteractor.Output> {

    final Random rnd = new Random();

    final OrderRepository repository;
    final SendMessageProvider sendMessageProvider;

    public ScheduleOrderInteractor(@Autowired OrderRepository repository,
                                   @Autowired SendMessageProvider sendMessageProvider) {
        this.repository = repository;
        this.sendMessageProvider = sendMessageProvider;
    }

    @Override
    public Output run(Input input) throws Exception {

        Optional<Order> optionalOrder = repository.findFirstByScheduledDateTime(input.getScheduledDateTime());
        if(optionalOrder.isPresent()) { throw new OrderAlreadyBookedOnSameTimeException(); }

        Order order = parse(input);
        order = repository.save(order);
        order = sendOtpMessage(order);
        return new Output(order);
    }

    private Order sendOtpMessage(Order order) {
        sendMessageProvider.send(order.getPhone(), "Your OTP is: " + order.getOtp());
        return order;
    }

    private Order parse(Input input) {
        Order order = new Order();
        order.setCustomerName(input.getCustomerName());
        order.setAddress(input.getAddress());
        order.setDrivingLicenseNumber(input.getDrivingLicenseNumber());
        order.setCarLicensePlate(input.getCarLicensePlate());
        order.setPhone(input.getPhone());
        order.setEmail(input.getEmail());
        order.setDetailing(input.getDetailing());
        order.setScheduledType(input.getScheduledType());
        order.setScheduledDateTime(input.getScheduledDateTime());
        order.setWorkState(WorkState.SCHEDULING);
        order.setOtp(getFreshOtp());
        return order;
    }

    private String getFreshOtp() {
        return String.format("%06d", rnd.nextInt(999999));
    }

    public static class BusinessException extends Exception {}

    public static class OrderAlreadyBookedOnSameTimeException extends BusinessException {}

    public static class Input {

        private String customerName;
        private String address;
        private String drivingLicenseNumber;
        private String carLicensePlate;
        private String phone;
        private String email;
        private String detailing;
        private ScheduledType scheduledType;
        private LocalDateTime scheduledDateTime;

        public String getCustomerName() {
            return customerName;
        }

        public void setCustomerName(String customerName) {
            this.customerName = customerName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
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

        public ScheduledType getScheduledType() {
            return scheduledType;
        }

        public void setScheduledType(ScheduledType scheduledType) {
            this.scheduledType = scheduledType;
        }

        public LocalDateTime getScheduledDateTime() {
            return scheduledDateTime;
        }

        public void setScheduledDateTime(LocalDateTime scheduledDateTime) {
            this.scheduledDateTime = scheduledDateTime;
        }
    }

    public static class Output {
        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }

        Order order;

        public Output() {

        }
        public Output(Order order) {
            this.order = order;
        }
    }
}
