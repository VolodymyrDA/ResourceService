package org.vdoloka.entity;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class User {
    private String username;
    private String password;
    private String phone;
    private String description;
    private int locationId;
    private LocalDateTime date;
    private Boolean active;
    private String role;
    private String sub;
    private long id;
}