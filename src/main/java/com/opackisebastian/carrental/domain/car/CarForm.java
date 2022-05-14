package com.opackisebastian.carrental.domain.car;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarForm {

    @Id
    @Column(name = "id", nullable = false)
    private String id;

    private String formId;

    @OneToMany
    private List<Car> carFormsIds;

}
