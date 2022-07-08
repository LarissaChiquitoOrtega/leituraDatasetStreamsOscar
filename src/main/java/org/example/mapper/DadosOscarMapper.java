package org.example.mapper;

import org.example.model.PessoaOscar;
import java.util.function.Function;
import static java.lang.Integer.parseInt;

public class DadosOscarMapper implements Function<String, PessoaOscar> {

    @Override
    public PessoaOscar apply(String s) {
        var dadosLinha = s.split(";");
        var index = parseInt(dadosLinha[0].replace(" ", ""));
        var year = parseInt(dadosLinha[1].replace(" ", ""));
        var age = parseInt(dadosLinha[2].replace(" ", ""));
        var name = (dadosLinha[3]);
        var movie = (dadosLinha[4]);

        return new PessoaOscar(index, year, age, name, movie);
    }
}
