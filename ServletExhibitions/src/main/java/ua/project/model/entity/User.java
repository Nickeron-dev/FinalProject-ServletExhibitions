package ua.project.model.entity;

/**
 * Author : Pavel Ravvich.
 * Created : 29/10/2017.
 * <p>
 * DTO obj determines user's state.
 */
public class User {

    private int id;

    private String login;

    private String password;

    private Role role;

    public User() {
    }

    public User(int id, String login, String password, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "name: " + getLogin() + " id: " + getId() + " password: " + getPassword() + " role: " + getRole();
    }
}
