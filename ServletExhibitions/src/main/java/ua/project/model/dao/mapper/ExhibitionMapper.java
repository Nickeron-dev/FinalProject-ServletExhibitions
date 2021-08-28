package ua.project.model.dao.mapper;

import ua.project.containers.ISubstringIndexesForDatesAndTimes;
import ua.project.model.entity.Exhibition;
import ua.project.model.entity.ExhibitionState;
import ua.project.model.entity.Role;
import ua.project.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Map;

public class ExhibitionMapper implements ObjectMapper<Exhibition> {
    @Override
    public Exhibition extractFromResultSet(ResultSet resultSet) throws SQLException {
        Exhibition exhibition = new Exhibition();
        exhibition.setId(resultSet.getInt("id"));
        exhibition.setTopic(resultSet.getString("topic"));
        exhibition.setStartDate(LocalDate.of(Integer.parseInt(resultSet.getString("startDate").substring(ISubstringIndexesForDatesAndTimes.YEAR_BEGIN_INDEX,
                ISubstringIndexesForDatesAndTimes.YEAR_END_INDEX)),
                Integer.parseInt(resultSet.getString("startDate")
                        .substring(ISubstringIndexesForDatesAndTimes.MONTH_BEGIN_INDEX,
                                ISubstringIndexesForDatesAndTimes.MONTH_END_INDEX)),
                Integer.parseInt(resultSet.getString("startDate")
                        .substring(ISubstringIndexesForDatesAndTimes.DAY_BEGIN_INDEX))));
        exhibition.setEndDate(LocalDate.of(Integer.parseInt(resultSet.getString("endDate").substring(ISubstringIndexesForDatesAndTimes.YEAR_BEGIN_INDEX,
                ISubstringIndexesForDatesAndTimes.YEAR_END_INDEX)),
                Integer.parseInt(resultSet.getString("endDate")
                        .substring(ISubstringIndexesForDatesAndTimes.MONTH_BEGIN_INDEX,
                                ISubstringIndexesForDatesAndTimes.MONTH_END_INDEX)),
                Integer.parseInt(resultSet.getString("endDate")
                        .substring(ISubstringIndexesForDatesAndTimes.DAY_BEGIN_INDEX))));
        exhibition.setStartTimeEveryDay(LocalTime.of(Integer.parseInt(resultSet.getString("startTime")
                        .substring(ISubstringIndexesForDatesAndTimes.HOUR_BEGIN_INDEX,
                                ISubstringIndexesForDatesAndTimes.HOUR_END_INDEX)),
                Integer.parseInt(resultSet.getString("startTime")
                        .substring(ISubstringIndexesForDatesAndTimes.MINUTE_BEGIN_INDEX,
                                ISubstringIndexesForDatesAndTimes.MINUTE_END_INDEX))));
        exhibition.setEndTimeEveryDay(LocalTime.of(Integer.parseInt(resultSet.getString("endTime")
                        .substring(ISubstringIndexesForDatesAndTimes.HOUR_BEGIN_INDEX,
                                ISubstringIndexesForDatesAndTimes.HOUR_END_INDEX)),
                Integer.parseInt(resultSet.getString("endTime")
                        .substring(ISubstringIndexesForDatesAndTimes.MINUTE_BEGIN_INDEX,
                                ISubstringIndexesForDatesAndTimes.MINUTE_END_INDEX))));
        exhibition.setRooms(resultSet.getInt("rooms"));
        exhibition.setPrice(resultSet.getInt("price"));
        if (resultSet.getString("state").equals("PLANNED")) {
            exhibition.setState(ExhibitionState.PLANNED);
        }
        if (resultSet.getString("state").equals("CANCELED")) {
            exhibition.setState(ExhibitionState.CANCELED);
        }
        return exhibition;
    }

    @Override
    public Exhibition makeUnique(Map<Integer, Exhibition> cache, Exhibition exhibition) {
        cache.putIfAbsent(exhibition.getId(), exhibition);
        return cache.get(exhibition.getId());
    }
}
