package com.amigoscode.examples;


import com.amigoscode.beans.Car;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GroupingData {

    @Test
    public void simpleGrouping() throws Exception {
        Map<String, List<Car>> map = MockData.getCars()
                .stream()
                .collect(Collectors.groupingBy(Car::getMake));
        map.forEach((s, cars) -> {
            System.out.println("Make " + s);
            cars.forEach(System.out::println);
            System.out.println("---------------------");
        });

    }


    @Test
    public void groupingAndCounting_String() throws Exception {
        List<String> names = List.of(
                "John",
                "John",
                "Mariam",
                "Alex",
                "Mohammado",
                "Mohammado",
                "Vincent",
                "Alex",
                "Alex"
        );

        Map<String, Long> map = names.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        Collectors.counting())
                );

        System.out.println(map);

    }

    @Test
    public void GroupingAndCounting_Cars() throws Exception {
        Map<String, Long> map = MockData.getCars()
                .stream()
                .collect(Collectors.groupingBy(Car::getMake, Collectors.counting()));

        map.forEach((key, count) -> {
            System.out.println("Make " + key + " | count: "+ count);
        });

    }

    @Test
    public void groupingAndPriceAverageByMake_Cars() throws IOException {
        Map<String, Double> priceAverageByMaker = MockData.getCars()
                .stream()
                .collect(Collectors.groupingBy(Car::getMake, Collectors.averagingDouble(Car::getPrice)));

        for(Map.Entry<String, Double> entry : priceAverageByMaker.entrySet()){
            System.out.println("Maker: "+ entry.getKey() + ", priceAverage: "+ entry.getValue());
        }

    }

}