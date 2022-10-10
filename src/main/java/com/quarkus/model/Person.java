package com.quarkus.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.quarkus.Status;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "birth")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status status;

    public Person(String name, LocalDate birth, Status status) {
        this.name = name;
        this.birth = birth;
        this.status = status;
    }

}