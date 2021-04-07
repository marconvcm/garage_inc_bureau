package com.garage_inc.bureau.component;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class DefaultDateTimeManagerTest {

    DateTimeManager target = new DefaultDateTimeManager();

    @Test
    void getNextWeekDays() {
        LocalDate[] nextWeekDays1 = target.getNextWeekDays();
        LocalDate[] nextWeekDays2 = target.getDaysOfWeek(LocalDate.now().plusWeeks(1));
        for (int i = 0; i < 7; i++) {
            assertEquals(nextWeekDays1[i].getDayOfMonth(), nextWeekDays2[i].getDayOfMonth());
        }
    }

    @Test
    void getCurrentWeekDays() {
        LocalDate[] currentWeekDays = target.getCurrentWeekDays();
        LocalDate[] nowWeekDays = target.getDaysOfWeek(LocalDate.now());
        for (int i = 0; i < 7; i++) {
            assertEquals(currentWeekDays[i].getDayOfMonth(), nowWeekDays[i].getDayOfMonth());
        }
    }

    @Test
    void getDaysOfWeek() {
        LocalDate asOf = LocalDate.of(2021, 4, 14);
        LocalDate[] daysOfWeek = target.getDaysOfWeek(asOf);
        assertEquals(daysOfWeek[0].getDayOfMonth(), 11);
        assertEquals(daysOfWeek[6].getDayOfMonth(), 17);
    }
}