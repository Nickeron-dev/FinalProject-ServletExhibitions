package ua.project.containers;

public interface SQLStatements {
    String CREATE_EXHIBITION = "INSERT INTO exhibitions (topic, startDate, endDate, startTime, endTime," +
            " rooms, price, state) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String FIND_EXHIBITION_BY_ID = "SELECT * FROM exhibitions WHERE id = ?";
    String FIND_ALL_EXHIBITIONS = "SELECT * FROM exhibitions";
    String CANCEL_BY_ID = "UPDATE exhibitions SET state = 'CANCELED' WHERE (id = ?)";
    String PLAN_BY_ID = "UPDATE exhibitions SET state = 'PLANNED' WHERE (id = ?)";
    String FIND_BY_PAGE = "SELECT * FROM exhibitions LIMIT ?,?";
    String PAGES_AVAILABLE = "SELECT COUNT(*) FROM exhibitions";
    String FILTER_BY_DATE = "SELECT * FROM exhibitions WHERE endDate >= ?";

    String CREATE_TICKET = "INSERT INTO tickets (exhibitionTopic, exhibitionId, userEmail, userId) VALUES (?, ?, ?, ?); ";
    String COUNT_BY_EXHIBITION_ID = "SELECT COUNT(*) FROM tickets WHERE exhibitionId = ?";

    String CREATE_USER = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?); ";
    String FIND_USER_BY_NAME = "SELECT * FROM users WHERE username = ?";

    String FIND_ALL_TICKETS = "SELECT * FROM tickets";
    String FIND_TICKET_BY_ID = "SELECT * FROM tickets WHERE id = ?";

    String FIND_ALL_USERS = "SELECT * FROM users";
    String FIND_USER_BY_ID = "SELECT * FROM users WHERE id = ?";
}
