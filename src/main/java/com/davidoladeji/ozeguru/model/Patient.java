package com.davidoladeji.ozeguru.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "patient")
public class Patient implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_patient_sequence_name")
    private long Id;


    @NotEmpty(message = "The name is required.")
    @Column(name = "name", unique = true)
    @JsonProperty("name")
    private String name;

    @NotNull(message = "The age is required.")
    @Column(name = "age")
    @JsonProperty("age")
    private int age;

    @Column(name = "last_visit_date")
    @JsonProperty("last_visit_date")
    private Date last_visit_date;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @UpdateTimestamp
    private Timestamp updatedAt;





}