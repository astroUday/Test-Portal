package com.webapp.testportal.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;

import java.util.UUID;

@Entity
@Table(name="user-token")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserToken {


    @Id
    private String Uuid;

    @ManyToOne
    @JsonIgnore
    private Users userr;


}
