package com.garage_inc.bureau.interactor;

import com.garage_inc.bureau.model.Order;
import com.garage_inc.bureau.model.WorkState;
import com.garage_inc.bureau.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.naming.OperationNotSupportedException;
import java.util.Optional;

@Component
public class ConfirmScheduleInteractor implements Interactor<ConfirmScheduleInteractor.Input, ConfirmScheduleInteractor.Output>{

    final OrderRepository repository;

    public ConfirmScheduleInteractor(@Autowired OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public Output run(Input input) throws Exception {
        Optional<Order> optionalOrder = repository.findById(input.getId());
        if (optionalOrder.isEmpty() || !optionalOrder.get().getOtp().equalsIgnoreCase(input.getOtp())) {
            throw new InvalidOtpBusinessException();
        }
        Order order = optionalOrder.get();
        order.setWorkState(WorkState.OPEN);
        return new Output(repository.save(order));
    }

    public static class Input {
        Integer id;
        String otp;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }
    }

    public static class Output {
        Order order;

        public Output() {

        }

        public Output(Order order) {
            this.order = order;
        }

        public Order getOrder() {
            return order;
        }

        public void setOrder(Order order) {
            this.order = order;
        }
    }

    public static class BusinessException extends OperationNotSupportedException { }

    public static class InvalidOtpBusinessException extends BusinessException { }
}
