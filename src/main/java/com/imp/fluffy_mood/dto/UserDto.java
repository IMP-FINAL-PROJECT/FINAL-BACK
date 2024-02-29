package com.imp.fluffy_mood.dto;

import com.imp.fluffy_mood.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String id;
    private String password;

    public User toEntity() {
        return User.builder()
                .id(id)
                .password(password)
                .build();
    }

}
