package org.codeacademy.siuntupristatymas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codeacademy.siuntupristatymas.enums.Status;

@Entity
@Getter
@Setter
@ToString
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackingNumber;
    private Double weightKg;
    @Enumerated(EnumType.STRING)
    private Status status;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Courier courier;

}
