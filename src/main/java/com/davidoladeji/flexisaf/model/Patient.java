package com.davidoladeji.flexisaf.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "patient")
public class Patient implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_sequence_name")
    private long Id;


    @NotEmpty(message = "The name is required.")
    @Column(name = "name", unique = true)
    @JsonProperty("name")
    private String name;

    @NotEmpty(message = "The age is required.")
    @Column(name = "age")
    @JsonProperty("age")
    private int age;

    @Column(name = "last_visit_date")
    @JsonProperty("last_visit_date")
    private Date last_visit_date;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private Timestamp created_at;

    @UpdateTimestamp
    private Timestamp updated_at;





}