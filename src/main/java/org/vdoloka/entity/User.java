package org.vdoloka.entity;

import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
public class User {
    @NotEmpty(message = "Usermame may not be empty")
    private final String username;
    @Digits(message = "Phone length must have max 13 digits", integer = 0, fraction = 0)
    @NonNull
    private String password;
    private final String phone;
    @Size(min = 10, max = 500, message = "Discription size must 10-500 symbols")
    private final String description;
    private final int locationId;

    @Size(min = 10, max = 20, message = "Password size must 10-500 symbols")
    private LocalDateTime date;
    private Boolean active;
    private String role;
    private int Id;
}