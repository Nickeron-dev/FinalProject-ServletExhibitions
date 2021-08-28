package ua.project.configurer;

import ua.project.containers.ISubstringIndexesForDatesAndTimes;

import java.time.LocalDate;
import java.time.LocalTime;

public class Configurer {
    public static LocalDate getDateFromString(String stringDate) {
         return LocalDate.of(Integer.parseInt(stringDate.substring(ISubstringIndexesForDatesAndTimes.YEAR_BEGIN_INDEX,
                ISubstringIndexesForDatesAndTimes.YEAR_END_INDEX)),
        Integer.parseInt(stringDate.substring(ISubstringIndexesForDatesAndTimes.MONTH_BEGIN_INDEX,
                        ISubstringIndexesForDatesAndTimes.MONTH_END_INDEX)),
                Integer.parseInt(stringDate.substring(ISubstringIndexesForDatesAndTimes.DAY_BEGIN_INDEX)));
    }

    public static LocalTime getTimeFromString(String stringTime) {
        return LocalTime.of(Integer.parseInt(stringTime.substring(ISubstringIndexesForDatesAndTimes.HOUR_BEGIN_INDEX,
                ISubstringIndexesForDatesAndTimes.HOUR_END_INDEX)),
        Integer.parseInt(stringTime.substring(ISubstringIndexesForDatesAndTimes.MINUTE_BEGIN_INDEX,
                        ISubstringIndexesForDatesAndTimes.MINUTE_END_INDEX)));
    }
}
