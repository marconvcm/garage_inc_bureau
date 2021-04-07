package com.garage_inc.bureau.component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public interface DateTimeManager {

    LocalDate[] getNextWeekDays();

    LocalDate[] getCurrentWeekDays();

    LocalDate[] getDaysOfWeek(LocalDate refDate);
}
