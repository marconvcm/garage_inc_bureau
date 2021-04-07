package com.garage_inc.bureau.presenter;

import com.garage_inc.bureau.interactor.ViewScheduleBookInteractor;
import com.garage_inc.bureau.model.Order;
import com.garage_inc.bureau.model.ScheduleBook;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class ViewScheduleBookPresenterImpl implements ViewScheduleBookPresenter {

    @Override
    public ScheduleBook present(ViewScheduleBookInteractor.Output output) {
        Map<DayOfWeek, List<Order>> result = new HashMap<>();
        for (int i = 0; i < output.getDaysOfWeek().length; i++) {
            LocalDate date = output.getDaysOfWeek()[i];
            result.put(date.getDayOfWeek(), filterOrders(date, output.getOrders()));
        }
        return new ScheduleBook(output.getDaysOfWeek()[0], output.getDaysOfWeek()[6], result);
    }

    private List<Order> filterOrders(LocalDate date, List<Order> orders) {
        return orders.stream().filter(order -> order.getScheduledDateTime().toLocalDate().isEqual(date)).collect(Collectors.toList());
    }
}
