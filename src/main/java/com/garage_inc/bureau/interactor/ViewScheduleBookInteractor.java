package com.garage_inc.bureau.interactor;

import com.garage_inc.bureau.component.DateTimeManager;
import com.garage_inc.bureau.model.Order;
import com.garage_inc.bureau.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
public class ViewScheduleBookInteractor implements Interactor<ViewScheduleBookInteractor.Input, ViewScheduleBookInteractor.Output> {

    final DateTimeManager dateTimeManager;

    final OrderRepository repository;

    public ViewScheduleBookInteractor(@Autowired DateTimeManager dateTimeManager, @Autowired OrderRepository repository) {
        this.dateTimeManager = dateTimeManager;
        this.repository = repository;
    }

    @Override
    public Output run(Input input) throws Exception {

        LocalDate[] daysOfWeek = null;
        switch (input.getViewWhen()) {
            case TODAY:
                daysOfWeek = dateTimeManager.getCurrentWeekDays();
                break;
            case NEXT_WEEK:
                daysOfWeek = dateTimeManager.getNextWeekDays();
                break;
        }

        LocalDate firstDay = daysOfWeek[0];
        LocalDate lastDay = daysOfWeek[6];

        Iterable<Order> orders = repository.findByScheduledDateTimeAfterAndScheduledDateTimeBefore(firstDay.atTime(00, 00), lastDay.atTime(22,00));

        return new Output(daysOfWeek, StreamSupport.stream(orders.spliterator(), false).collect(Collectors.toList()));
    }

    public static enum ViewWhen {
        TODAY, NEXT_WEEK
    }

    public static class StringToViewWhenConverter implements Converter<String, ViewWhen> {
        @Override
        public ViewWhen convert(String source) {
            try {
                return ViewWhen.valueOf(source.toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }
        }
    }


    public static class Input {
        ViewWhen viewWhen;

        public Input(ViewWhen viewWhen) {
            this.viewWhen = viewWhen;
        }

        public ViewWhen getViewWhen() {
            return viewWhen;
        }

        public void setViewWhen(ViewWhen viewWhen) {
            this.viewWhen = viewWhen;
        }
    }

    public static class Output {
        final LocalDate[] daysOfWeek;

        final List<Order> orders;
        public Output(LocalDate[] daysOfWeek, List<Order> orders) {
            this.daysOfWeek = daysOfWeek;
            this.orders = orders;
        }

        public List<Order> getOrders() {
            return orders;
        }

        public LocalDate[] getDaysOfWeek() {
            return daysOfWeek;
        }
    }
}
