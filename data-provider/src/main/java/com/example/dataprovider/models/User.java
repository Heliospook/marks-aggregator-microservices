package com.example.dataprovider.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "_user")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    private String rollNum;

    @ManyToOne
    @JoinColumn(name = "batch_id")
    private Batch batch;

    private String email;

    private String name;

    private String password;
}
