package org.vdoloka.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
public class UserDTO {
    @NotEmpty(message = "Username should not be empty")
    private String username;
    @Size(min = 1, max = 20, message = "Password size must 1-20 symbols")
    private String password;
    @Digits(message = "Phone length should have max 13 digits", integer = 13, fraction = 0)
    private String phone;
    @Size(min = 10, max = 500, message = "Description size must 10-500 symbols")
    private String description;
    private int locationId;
}