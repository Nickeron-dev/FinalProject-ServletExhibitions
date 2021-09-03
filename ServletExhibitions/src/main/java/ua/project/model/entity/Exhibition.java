package ua.project.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * @author Illia Koshkin
 */
public class Exhibition {
    private Integer id;
    private String topic;
    private Integer rooms;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalTime startTimeEveryDay;
    private LocalTime endTimeEveryDay;
    private Integer price;
    private ExhibitionState state;

    public Exhibition() {

    }

    public Exhibition(Integer id, String topic, Integer rooms, LocalDate startDate, LocalDate endDate,
                      LocalTime startTimeEveryDay, LocalTime endTimeEveryDay,
                      Integer price, ExhibitionState state) {
        this.id = id;
        this.topic = topic;
        this.rooms = rooms;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTimeEveryDay = startTimeEveryDay;
        this.endTimeEveryDay = endTimeEveryDay;
        this.price = price;
        this.state = state;
    }

    public Exhibition(String topic, Integer rooms, LocalDate startDate, LocalDate endDate,
                      LocalTime startTimeEveryDay, LocalTime endTimeEveryDay,
                      Integer price, ExhibitionState state) {
        this.topic = topic;
        this.rooms = rooms;
        this.startDate = startDate;
        this.endDate = endDate;
        this.startTimeEveryDay = startTimeEveryDay;
        this.endTimeEveryDay = endTimeEveryDay;
        this.price = price;
        this.state = state;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Integer getRooms() {
        return rooms;
    }

    public void setRooms(Integer rooms) {
        this.rooms = rooms;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalTime getStartTimeEveryDay() {
        return startTimeEveryDay;
    }

    public void setStartTimeEveryDay(LocalTime startTimeEveryDay) {
        this.startTimeEveryDay = startTimeEveryDay;
    }

    public LocalTime getEndTimeEveryDay() {
        return endTimeEveryDay;
    }

    public void setEndTimeEveryDay(LocalTime endTimeEveryDay) {
        this.endTimeEveryDay = endTimeEveryDay;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public ExhibitionState getState() {
        return state;
    }

    public void setState(ExhibitionState state) {
        this.state = state;
    }

    /**
     * Overridden toString() method
     * @return String of all object's values
     */
    @Override
    public String toString() {
        return "Exhibition{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", rooms=" + rooms +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTimeEveryDay=" + startTimeEveryDay +
                ", endTimeEveryDay=" + endTimeEveryDay +
                ", price=" + price +
                ", state=" + state +
                '}';
    }
}
