package ua.project.model.entity;

/**
 * @author Illia Koshkin
 */
public class User {

    private int id;

    private String login;

    private String password;

    private String email;

    private Role role;

    public User() {
    }

    public User(String login, String password, String email, Role role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(int id, String login, String password, String email, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Overridden toString() method
     * @return String of all object's values
     */
    @Override
    public String toString() {
        return "name: " + getLogin() + " id: " + getId() + " password: " + getPassword() + " role: " + getRole();
    }
}
