package com.quarkus.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quarkus.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "developer")
public class Developer extends Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dev_language")
    private String devLanguage;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "developers")
    @JsonIgnore
    private List<Project> projects = new ArrayList<>();

    public Developer(String name, LocalDate birth, Status status) {
        super(name, birth, status);
    }
}
