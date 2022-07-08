package org.example.service;

import org.example.model.PersonOscar;

import java.util.*;
import java.util.stream.Collectors;


public class OscarService {

    private final List<PersonOscar> personOscar;

    public OscarService(List<PersonOscar> personOscar) {
        this.personOscar = personOscar;
    }

    public void youngestActorOrActress() {

        System.out.println("----O ator ou atriz mais jovem a ser premiado é(são):---");
        var minAge = personOscar.stream()
                .min(Comparator.comparing(c -> c.getAge()))
                .get();

        personOscar.stream()
                .filter(p -> p.getAge() == minAge.getAge())
                .collect(Collectors.groupingBy(p -> p.getName()))
                .forEach((key, value) -> System.out.printf("%s aos %d %n",
                        value.get(0).getName() , value.get(0).getAge()));

    }

    public void mostAwardedActorOrActress() {
        long count = 0;
        String name = null;

        for (PersonOscar c : personOscar) {
            long qtVictories = personOscar.stream()
                    .filter(person -> (person.getName().equals(c.getName())))
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

        for (PersonOscar c : personOscar) {
            long qtVictories = personOscar.stream()
                    .filter(person -> (person.getName().equals(c.getName()) &&
                            18 < person.getAge() && person.getAge() < 24))
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
