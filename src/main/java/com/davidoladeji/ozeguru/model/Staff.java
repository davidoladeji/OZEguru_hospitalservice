package com.davidoladeji.ozeguru.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "staff")
public class Staff implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "my_staff_sequence_name")
    private long Id;


    @NotEmpty(message = "The name is required.")
    @Column(name = "name", unique = true)
    @JsonProperty("name")
    private String name;

    @NotEmpty(message = "The username is required.")
    @Column(name = "username", unique = true)
    @JsonProperty("username")
    private String username;

    @NotEmpty(message = "The password is required.")
    @Column(name = "password")
    @JsonProperty("password")
    private String password;

    @Column(name = "uuid")
    @JsonProperty("uuid")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;

    @CreationTimestamp
    @Column(name = "registration_date", updatable = false)
    @JsonProperty("registration_date")
    private Timestamp registration_date;

    @PrePersist
    protected void onCreate() {
        // set the uid
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        setUuid(uuid);

    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public Timestamp getRegistration_date() {
        return registration_date;
    }

    public void setRegistration_date(Timestamp registration_date) {
        this.registration_date = registration_date;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o)
            return true;
        if (!(o instanceof Staff staff))
            return false;
        return Objects.equals(this.name, staff.name) &&
                Objects.equals(this.uuid, staff.uuid) &&
                Objects.equals(this.registration_date, staff.registration_date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.uuid, this.name, this.registration_date);
    }

    @Override
    public String toString() {
        return "Staff{" + "name=" + this.name + ", uuid='" + this.uuid + '\'' + ", registration_date='" + this.registration_date + '\'' + '}';
    }



}