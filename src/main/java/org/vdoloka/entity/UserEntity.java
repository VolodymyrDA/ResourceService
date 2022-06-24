package org.vdoloka.entity;




import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

public class UserEntity {
    @NotEmpty(message = "Usermame may not be empty")
    private final String username;
    @Digits(message = "Phone length must have max 13 digits", integer = 0, fraction = 0)
    private final String phone;
    @Size(min = 10, max = 500,message = "Discription size must 10-500 symbols")
    private final String description;
    private final int locationId;

    @Size(min = 10, max = 20,message = "Password size must 10-500 symbols")
    private String password;
    private LocalDateTime date;
    private Boolean active;
    private  String role;
    private int Id;

    public UserEntity(String userName, String password, String phone, String description, int locationId) {
        this.username = userName;
        this.password = password;
        this.phone = phone;
        this.description = description;
        this.locationId = locationId;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public String getPhone() {
        return phone;
    }

    public String getDescription() {
        return description;
    }

    public int getLocationId() {
        return locationId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Boolean getActive() {
        return active;
    }

    public String getRole() {
        return role;
    }

    public int getId() {
        return Id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public void setId(int id) {
        this.Id = id;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                ", phone='" + phone + '\'' +
                ", description='" + description + '\'' +
                ", locationId=" + locationId +
                ", password='" + password + '\'' +
                ", date=" + date +
                ", active=" + active +
                ", role='" + role + '\'' +
                ", Id=" + Id +
                '}';
    }
}