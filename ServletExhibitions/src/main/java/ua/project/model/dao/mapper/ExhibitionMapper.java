package ua.project.model.dao.mapper;

import ua.project.configurer.Configurer;
import ua.project.model.entity.Exhibition;
import ua.project.model.entity.ExhibitionState;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author Illia Koshkin
 */
public class ExhibitionMapper implements ObjectMapper<Exhibition> {
    /**
     * This method takes Exhibition from a ResultSet
     * @param resultSet ResultSet of an SQL statement
     * @return Exhibition object
     * @throws SQLException if nothing was found
     */
    @Override
    public Exhibition extractFromResultSet(ResultSet resultSet) throws SQLException {
        Exhibition exhibition = new Exhibition();
        exhibition.setId(resultSet.getInt("id"));
        exhibition.setTopic(resultSet.getString("topic"));
        exhibition.setStartDate(Configurer.getDateFromString(resultSet.getString("startDate")));
        exhibition.setEndDate(Configurer.getDateFromString(resultSet.getString("endDate")));
        exhibition.setStartTimeEveryDay(Configurer.getTimeFromString(resultSet.getString("startTime")));
        exhibition.setEndTimeEveryDay(Configurer.getTimeFromString(resultSet.getString("endTime")));
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

    /**
     * Removes all repeated values
     * @param cache Map with exhibitions
     * @param exhibition Object of an exhibition that will be put if absent
     * @return Exhibition from Map with the same id
     */
    @Override
    public Exhibition makeUnique(Map<Integer, Exhibition> cache, Exhibition exhibition) {
        cache.putIfAbsent(exhibition.getId(), exhibition);
        return cache.get(exhibition.getId());
    }
}
