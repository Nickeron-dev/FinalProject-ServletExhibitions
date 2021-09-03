package ua.project.model.entity;

/**
 * @author Illia Koshkin
 */
public class Ticket {

    private Integer id;

    private String userEmail;

    private Integer userId;

    private String exhibitionTopic;

    private Integer exhibitionId;

    public Ticket() {

    }

    public Ticket(Integer id, String userEmail, Integer userId, String exhibitionTopic, Integer exhibitionId) {
        this.id = id;
        this.userEmail = userEmail;
        this.userId = userId;
        this.exhibitionTopic = exhibitionTopic;
        this.exhibitionId = exhibitionId;
    }

    public Ticket(String userEmail, Integer userId, String exhibitionTopic, Integer exhibitionId) {
        this.userEmail = userEmail;
        this.userId = userId;
        this.exhibitionTopic = exhibitionTopic;
        this.exhibitionId = exhibitionId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getExhibitionTopic() {
        return exhibitionTopic;
    }

    public void setExhibitionTopic(String exhibitionTopic) {
        this.exhibitionTopic = exhibitionTopic;
    }

    public Integer getExhibitionId() {
        return exhibitionId;
    }

    public void setExhibitionId(Integer exhibitionId) {
        this.exhibitionId = exhibitionId;
    }
}
