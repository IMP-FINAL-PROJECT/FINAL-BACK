package com.imp.fluffy_mood.entity;

import com.imp.fluffy_mood.dto.SensorDto;
import com.imp.fluffy_mood.dto.UserDto;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

import java.time.LocalDateTime;

@Document(collection = "sensor")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class Sensor {

    @Id
    @Field(name = "_id", targetType = FieldType.OBJECT_ID)
    private ObjectId id;

    @Field(name = "user")
    private String user; // 사용자 ID

    @Field(name = "illuminance")
    private int illuminance; // 조도 센서

    @Field(name = "screenState")
    private Boolean screenState; // 화면 on/off

    @Field(name = "pedometer")
    private int pedometer; // 만보기

    @Field(name = "latitude")
    private Double latitude; // 위도

    @Field(name = "longitude")
    private Double longitude; // 경도

    @Field(name = "timestamp")
    private LocalDateTime timestamp; // 시간

    private SensorDto toDto() {
        return SensorDto.builder()
                .id(id)
                .user(user)
                .illuminance(illuminance)
                .screenState(screenState)
                .pedometer(pedometer)
                .latitude(latitude)
                .longitude(longitude)
                .timestamp(timestamp)
                .build();
    }

}
