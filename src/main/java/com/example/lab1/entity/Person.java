package com.example.lab1.entity;

import com.example.lab1.entity.auth.User;
import com.example.lab1.entity.enums.Color;
import com.example.lab1.entity.enums.Country;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.io.Serializable;
@Entity
@Data
@Table(name = "person")
public class Person implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    @Column(unique = true, nullable = false)
    @Size(min = 1, max = 42, message = "Размер строки должен быть от 1 до 42 символов")
    @Pattern(regexp = "^[1234567890a-zA-Zа-яА-Я]+$", message = "Строка не должна быть пустой или состоять только из пробелов")
    private String passportID;

    @NotNull(message = "Имя не может быть null")
    @NotEmpty(message = "Имя не должно быть пустым")
    @Pattern(regexp = "^[a-zA-Zа-яА-Я]+(?:\\s[a-zA-Zа-яА-Я]+)*$", message = "Имя должно содержать только буквы и пробелы")
    private String name;

    @Enumerated(EnumType.STRING)
    private Color eyeColor; // Может быть null

    @NotNull(message = "Цвет волос не может быть null")
    @Enumerated(EnumType.STRING)
    private Color hairColor;

    @Valid
    @NotNull(message = "Локация не может быть null")
    @OneToOne(cascade = CascadeType.ALL)
    private Location location;

    @NotNull(message = "Национальность не может быть null")
    @Enumerated(EnumType.STRING)
    private Country nationality;

    @Valid
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy; 

    @NotNull
    private boolean canBeChanged = true; 
}
