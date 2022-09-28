package com.amigoscode.examples;

import com.amigoscode.beans.Car;
import com.amigoscode.mockdata.MockData;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ReturnMap {

    @Test
    public void getMapFromList() throws IOException {
        Map<Integer, String> map = MockData.getCars()
                .stream()
                .collect(Collectors.toMap(Car::getId, Car::getModel));
        System.out.println(map);
    }

    @Test
    public void getMaxGroupedValueFromMap() throws IOException {
        Map<String, Long> groupedMap = MockData.getCars()
                .stream()
                .collect(Collectors.groupingBy(Car::getMake, Collectors.counting()));
        System.out.println(groupedMap);

        List<Map.Entry<String, Long>> result = groupedMap.entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(1)
                .collect(Collectors.toList());

        System.out.println(result);
    }

}
