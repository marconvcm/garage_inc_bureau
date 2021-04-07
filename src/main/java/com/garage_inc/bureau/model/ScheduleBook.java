package com.garage_inc.bureau.model;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ScheduleBook {

    final LocalDate from;

    final LocalDate to;


    final Map<DayOfWeek, List<Order>> orders;

    public ScheduleBook(LocalDate from, LocalDate to, Map<DayOfWeek, List<Order>> orders) {
        this.from = from;
        this.to = to;
        this.orders = orders;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public Map<DayOfWeek, List<Order>> getOrders() {
        return orders;
    }
}
