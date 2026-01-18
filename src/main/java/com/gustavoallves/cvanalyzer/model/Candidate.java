package com.gustavoallves.cvanalyzer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "candidates")
public class Candidate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @Email(message = "Invalid email")
    @NotBlank(message = "Invalid email")
    private String email;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "candidate", fetch = FetchType.LAZY)
    private List<Resume> resumes;

}