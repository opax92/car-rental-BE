package com.opackisebastian.carrental.domain.form;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Form {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String name;

    private FormStatus formStatus;

}
