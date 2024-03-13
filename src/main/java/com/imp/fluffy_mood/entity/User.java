package com.imp.fluffy_mood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.imp.fluffy_mood.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;

@Table(name = "user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class User {

    @Id
    @Column(name = "id", length = 60, nullable = false)
    @NotBlank
    private String id;

    @Column(name = "password", length = 60, nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "birth", columnDefinition = "DATE", nullable = false)
    private Date birth;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    private Character gender;

    // 회원 정보 변경을 위한 update Method
    public void update(UserDto userDto) {

        if(userDto.getBirth() != null) {
            this.birth = userDto.getBirth();
        }
        if(userDto.getName() != null) {
            this.name = userDto.getName();
        }
        if(userDto.getGender() != null) {
            this.gender = userDto.getGender();
        }

    }

    public UserDto toDto() {
        return UserDto.builder()
                .id(id)
                .password(password)
                .birth(birth)
                .name(name)
                .gender(gender)
                .build();
    }

}
