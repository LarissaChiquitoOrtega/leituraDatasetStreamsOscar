package org.example.service;

import org.example.model.PessoaOscar;

import java.security.KeyStore;
import java.util.*;
import java.util.stream.Collectors;


public class OscarService {

    private final List<PessoaOscar> pessoaOscar;

    public OscarService(List<PessoaOscar> pessoaOscar) {
        this.pessoaOscar = pessoaOscar;
    }

    public void youngestActorOrActress() {
        pessoaOscar.stream()
                .min(Comparator.comparing(PessoaOscar::getAge))
                .ifPresent(pessoa -> System.out.printf("O ator ou atriz mais jovem a ser premiado é %s aos %s anos.%n"
                , pessoa.getName(), pessoa.getAge()));
    }

    public void mostAwardedActorOrActress() {
        long count = 0;
        String name = null;

        for (PessoaOscar c : pessoaOscar) {
            long qtVictories = pessoaOscar.stream()
                    .filter(pessoa -> (pessoa.getName().equals(c.getName())))
                    .count();

            if (qtVictories > count) {
                count = qtVictories;
                name = c.getName();
            }else if (qtVictories == count && !name.contains(c.getName())) {
                name = name + ", " + c.getName();
            }
        }

        System.out.printf("O ator ou atriz mais premiado(a) foi %s. %n", name);
    }


    public void mostAwardedYoung(){
        long count = 0;
        String name = null;

        for (PessoaOscar c : pessoaOscar) {
            long qtVictories = pessoaOscar.stream()
                    .filter(pessoa -> (pessoa.getName().equals(c.getName()) &&
                            18 < pessoa.getAge() && pessoa.getAge() < 24))
                    .count();

            if (qtVictories > count) {
                count = qtVictories;
                name = c.getName();
            } else if (qtVictories == count && !name.contains(c.getName())) {
                name = name + ", " + c.getName();
            }

        }

        System.out.printf("O(s) jovem(ens) mais premiado(s) (entre 18 e 24 anos) foi(foram): %s. %n", name);
    }

}
