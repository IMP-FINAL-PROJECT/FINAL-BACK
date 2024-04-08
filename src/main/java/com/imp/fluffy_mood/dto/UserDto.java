package com.imp.fluffy_mood.dto;

import com.imp.fluffy_mood.entity.User;
import lombok.*;

import java.util.Date;
import java.util.List;

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
    private List<? extends Number> address;

    public User toEntity() {
        return User.builder()
                .id(id)
                .password(password)
                .birth(birth)
                .name(name)
                .gender(gender)
                .address(address)
                .build();
    }

}
