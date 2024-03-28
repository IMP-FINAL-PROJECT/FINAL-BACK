package com.imp.fluffy_mood.dto;

import com.imp.fluffy_mood.entity.User;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {

    private String id;
    private String password;
    private Date birth;
    private String name;
    private Character gender;
    private int point;

    public User toEntity() {
        return User.builder()
                .id(id)
                .birth(birth)
                .name(name)
                .gender(gender)
                .build();
    }

}
