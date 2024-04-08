package com.imp.fluffy_mood.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.imp.fluffy_mood.dto.UserDto;
import com.imp.fluffy_mood.entity.converter.ListOneStringConverter;
import jakarta.persistence.*;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Date;
import java.util.List;

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

    @Column(name = "address", nullable = false)
    @Convert(converter = ListOneStringConverter.class)
    private List<? extends Number> address;

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
        if(userDto.getAddress() != null) {
            this.address = userDto.getAddress();
        }

    }

    public UserDto toDto() {
        return UserDto.builder()
                .id(id)
                .password(password)
                .birth(birth)
                .name(name)
                .gender(gender)
                .address(address)
                .build();
    }

}
