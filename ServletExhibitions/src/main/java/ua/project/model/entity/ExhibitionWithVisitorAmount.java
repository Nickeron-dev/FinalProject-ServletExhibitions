package ua.project.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class ExhibitionWithVisitorAmount {

    private final Integer id;
    private final String topic;
    private final Integer rooms;
    private final LocalDate startDate;
    private final LocalDate endDate;
    private final LocalTime startTimeEveryDay;
    private final LocalTime endTimeEveryDay;
    private final Integer price;
    private final ExhibitionState state;
    private final Integer visitors;

    public ExhibitionWithVisitorAmount(Exhibition exhibition,
                                       Integer visitors) {
        this.id = exhibition.getId();
        this.topic = exhibition.getTopic();
        this.rooms = exhibition.getRooms();
        this.startDate = exhibition.getStartDate();
        this.endDate = exhibition.getEndDate();
        this.startTimeEveryDay = exhibition.getStartTimeEveryDay();
        this.endTimeEveryDay = exhibition.getEndTimeEveryDay();
        this.price = exhibition.getPrice();
        this.state = exhibition.getState();
        this.visitors = visitors;
    }

    public Integer getId() {
        return id;
    }

    public String getTopic() {
        return topic;
    }

    public Integer getRooms() {
        return rooms;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalTime getStartTimeEveryDay() {
        return startTimeEveryDay;
    }

    public LocalTime getEndTimeEveryDay() {
        return endTimeEveryDay;
    }

    public Integer getPrice() {
        return price;
    }

    public ExhibitionState getState() {
        return state;
    }

    public Integer getVisitors() {
        return visitors;
    }

    @Override
    public String toString() {
        return "ExhibitionWithVisitorAmount{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", rooms=" + rooms +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", startTimeEveryDay=" + startTimeEveryDay +
                ", endTimeEveryDay=" + endTimeEveryDay +
                ", price=" + price +
                ", state=" + state +
                ", visitors=" + visitors +
                '}';
    }
}
