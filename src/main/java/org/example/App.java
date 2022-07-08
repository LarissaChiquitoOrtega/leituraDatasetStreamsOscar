package org.example;

import org.example.mapper.DadosOscarMapper;
import org.example.model.PessoaOscar;
import org.example.service.OscarService;
import org.example.util.FileUtil;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class App {

    public static void main(String[] args) throws IOException {

        var oscar_age_female = new FileUtil<PessoaOscar>("oscar_age_female_teste.csv");
        var oscar_age_male = new FileUtil<PessoaOscar>("oscar_age_male.csv");

        var dadosMale = oscar_age_male.readFile(new DadosOscarMapper());
        var dadosFemale = oscar_age_female.readFile(new DadosOscarMapper());

        var dadosOscar = Stream.concat(dadosFemale.stream(), dadosMale.stream())
                .collect(Collectors.toList());

        OscarService oscarService = new OscarService(dadosOscar);

        //Quem é o ator ou atriz mais jovem a ser premiado?
        oscarService.youngestActorOrActress();
        //Quem foi o ator ou atriz mais premiado?
        oscarService.mostAwardedActorOrActress();
        //Quem foi o jovem mais premiado (entre 18 e 24 anos)?
        oscarService.mostAwardedYoung();
    }
}