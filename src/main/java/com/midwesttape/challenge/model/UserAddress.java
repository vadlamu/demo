package com.midwesttape.challenge.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Entity
@Table(name = "users_address")
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class UserAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "street address cannot be blamk")
    private String streetAddress;
    @JsonIgnore
    private String apartmentNo;
    @NotBlank(message = "city cannot be blank")
    private String city;
    @NotBlank(message = "state cannot be blank")
    private String state;
    @NotBlank(message = "zipcode cannot me blank")
    private String postalCode;

}
