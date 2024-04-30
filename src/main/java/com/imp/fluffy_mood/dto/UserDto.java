package com.imp.fluffy_mood.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.imp.fluffy_mood.entity.User;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String id;
    private String password;
    private LocalDate birth;
    private String name;
    private Character gender;
    private List<? extends Number> address;
    @JsonProperty("last_login")
    private LocalDateTime lastLogin;

    public User toEntity() {
        return User.builder()
                .id(id)
                .password(password)
                .birth(birth)
                .name(name)
                .gender(gender)
                .address(address)
                .lastLogin(lastLogin)
                .build();
    }

}
