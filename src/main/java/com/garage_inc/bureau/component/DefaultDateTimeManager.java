package com.garage_inc.bureau.component;

import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.WeekFields;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

@Component
public class DefaultDateTimeManager implements DateTimeManager {

    @Override
    public LocalDate[] getNextWeekDays() {
        return getDaysOfWeek(LocalDate.now().plusDays(7));
    }

    @Override
    public LocalDate[] getCurrentWeekDays() {
        return getDaysOfWeek(LocalDate.now());
    }

    @Override
    public LocalDate[] getDaysOfWeek(LocalDate refDate) {
        LocalDate[] daysOfWeek = new LocalDate[7];
        DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        LocalDate firstDateOfWeek = refDate.plusDays(-7).with(firstDayOfWeek);
        for (int i = 0; i < 7; i++) {
            daysOfWeek[i] = firstDateOfWeek.plusDays(i);
        }
        return daysOfWeek;
    }
}
