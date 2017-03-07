package model;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Gubanov Pavel on 20.11.16.
 */
class DataHelper {

    private static LocalDateTime startTime;
    private static LocalDateTime currentTime;

    static String getFormattedStartDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("G yyy-MM-dd | HH:mm:ss");
        startTime = LocalDateTime.now().minusYears(1500);
        currentTime = LocalDateTime.now().minusYears(1500);
        return startTime.format(formatter);
    }

    static void skipTime() {
        currentTime = currentTime.plusMinutes(45);
    }

    static String getFormattedDiff() {
        Duration duration = Duration.between(startTime, currentTime);
        long days = duration.toDays();
        long hours = 0;
        long minutes = 0;

        /*
        * Условный оператор используется для правильного расчёта времени по
        * каждому разряду.
        * Пример: разность между временем = 185 минут.
        * дней: 0, часов 3, минут 185  ---> не правильно.
        * дней: 0, часов 3, минут 5    ---> правильно.
        */
        if ((duration.toMinutes() % 60) != 0)  minutes = duration.toMinutes() % 60;
        if ((duration.toHours() % 24) != 0)  hours = duration.toHours() % 24;

        return days + " дней, " + hours + " часов, " + minutes + " минут.";
    }
}