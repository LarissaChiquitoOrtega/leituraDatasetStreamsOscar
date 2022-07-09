package org.example.service;

import org.example.model.PersonOscar;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class OscarService {

    private final List<PersonOscar> personOscar;

    public OscarService(List<PersonOscar> personOscar) {
        this.personOscar = personOscar;
    }

    public void youngestActorOrActress() {

        System.out.println("----Ator(es) ou atriz(es) mais jovem(ens) premiado(s)---");
        var minAge = personOscar.stream()
                .min(Comparator.comparing(c -> c.getAge()))
                .get();

        personOscar.stream()
                .filter(p -> p.getAge() == minAge.getAge())
                .collect(Collectors.groupingBy(p -> p.getName()))
                .forEach((key, value) -> System.out.printf("%s aos %d %n",
                        value.get(0).getName() , value.get(0).getAge()));
        System.out.println();

    }

    public void mostAwardedActorOrActress() {

        System.out.println("----O ator ou atriz mais jovem a ser premiado é(são):---");

        Map<String, Long> countPerName = personOscar.stream()
                .map(PersonOscar::getName)
                .collect(Collectors.groupingBy(nome -> nome, Collectors.counting()));

        long maxValue = countPerName.entrySet().stream()
                .max(Comparator.comparingLong(Map.Entry::getValue)).get().getValue();

        countPerName.entrySet().stream()
                .filter(a -> a.getValue().equals(maxValue))
                .distinct()
                .forEach(c -> System.out.println(c + "prêmios"));

        System.out.println();
    }
    public void mostAwardedYoung(){

        System.out.println("----Jovem(ens) mais premiado(s) (entre 18 e 24 anos) foi(foram):---");

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

        System.out.printf("%s. %n", name);
    }

}
