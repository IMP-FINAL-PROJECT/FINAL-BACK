package com.imp.fluffy_mood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.imp.fluffy_mood.dto.UserDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "user")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class User {

    @Id
    @Column(length = 20, nullable = false)
    @NotBlank
    private String id;

    @Column(length = 20, nullable = false)
    @JsonIgnore
    private String password;

    public UserDto toDto() {
        return UserDto.builder()
                .id(id)
                .password(password)
                .build();
    }

}
