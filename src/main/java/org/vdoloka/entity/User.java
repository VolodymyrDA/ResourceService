package org.vdoloka.entity;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    @NotEmpty(message = "Username should not be empty")
    private String username;
    @Digits(message = "Phone length should have max 13 digits", integer = 0, fraction = 0)
    @NonNull
    private String password;
    private String phone;
    @Size(min = 10, max = 500, message = "Description size must 10-500 symbols")
    private String description;
    private int locationId;
    @Size(min = 10, max = 20, message = "Password size must 10-500 symbols")
    private LocalDateTime date;
    private Boolean active;
    private String role;
    private int id;
}