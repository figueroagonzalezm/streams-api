package com.amigoscode.examples;


import com.amigoscode.beans.Person;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Collectors;

public class HowStreamsWork {
    @Test
    public void understandingCollect() throws Exception {
        List<String> emails = MockData.getPeople()
                .stream()
                .map(Person::getEmail)
                .collect(Collectors.toList());

        emails.forEach(System.out::println);
    }

    @Test
    public void intermediateAndTerminalOperations() throws Exception {

        List<Double> result = MockData.getCars()
                .stream()
                .limit(100)
                .filter(car -> {
                    System.out.println("filter car " + car);
                    return car.getPrice() < 10_000;
                })
                .map(car -> {
                    System.out.println("mapping car " + car);
                    return car.getPrice();
                })
                .map(price -> {
                    System.out.println("mapping price " + price);
                    return price + (price * .14);
                })
                .collect(Collectors.toList());

        System.out.println(":::::"+result);
    }
}
