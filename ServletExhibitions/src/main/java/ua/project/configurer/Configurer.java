package ua.project.configurer;

import ua.project.containers.ISubstringIndexesForDatesAndTimes;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Illia Koshkin
 */
public class Configurer {
    /**
     * This method gets date as a String and returns it as LocalDate(Can be used with SQL dates)
     * @param stringDate Reference to a String object with date
     * @return LocalDate object that contains date
     */
    public static LocalDate getDateFromString(String stringDate) {
         return LocalDate.of(Integer.parseInt(stringDate.substring(ISubstringIndexesForDatesAndTimes.YEAR_BEGIN_INDEX,
                ISubstringIndexesForDatesAndTimes.YEAR_END_INDEX)),
        Integer.parseInt(stringDate.substring(ISubstringIndexesForDatesAndTimes.MONTH_BEGIN_INDEX,
                        ISubstringIndexesForDatesAndTimes.MONTH_END_INDEX)),
                Integer.parseInt(stringDate.substring(ISubstringIndexesForDatesAndTimes.DAY_BEGIN_INDEX)));
    }

    /**
     * This method gets time as a String and returns it as LocalTime(Can be used with SQL time)
     * @param stringTime Reference to a String object with time
     * @return LocalTime object that contains time
     */
    public static LocalTime getTimeFromString(String stringTime) {
        return LocalTime.of(Integer.parseInt(stringTime.substring(ISubstringIndexesForDatesAndTimes.HOUR_BEGIN_INDEX,
                ISubstringIndexesForDatesAndTimes.HOUR_END_INDEX)),
        Integer.parseInt(stringTime.substring(ISubstringIndexesForDatesAndTimes.MINUTE_BEGIN_INDEX,
                        ISubstringIndexesForDatesAndTimes.MINUTE_END_INDEX)));
    }
}
