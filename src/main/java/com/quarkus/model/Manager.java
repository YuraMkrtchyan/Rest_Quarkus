package com.quarkus.model;

import com.quarkus.Status;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "manager")
public class Manager extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "salary")
    private Double salary;

    @OneToMany(mappedBy = "manager", cascade = CascadeType.ALL)
    private List<Company> companies = new ArrayList<>();

    public Manager(String name, LocalDate birth, Status status) {
        super(name, birth, status);
    }

}
