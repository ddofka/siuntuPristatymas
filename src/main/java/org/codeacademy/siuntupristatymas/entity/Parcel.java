package org.codeacademy.siuntupristatymas.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.codeacademy.siuntupristatymas.enums.Status;

@Entity
@Getter
@Setter
public class Parcel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackingNumber;
    private Integer weightKg;
    private Status status;
    @ManyToOne
    private Courier courier;

}
