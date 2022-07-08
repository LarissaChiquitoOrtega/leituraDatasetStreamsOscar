package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class PessoaOscar {
    private int index;
    private int year;
    private int age;
    private String name;
    private String movie;
}
