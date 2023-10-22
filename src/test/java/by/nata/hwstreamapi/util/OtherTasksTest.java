package by.nata.hwstreamapi.util;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.stream.Collectors;

class OtherTasksTest {

    //Прочтите содержимое текстового файла и сделайте из него частотный словарик. (слово -> и какое кол-во раз это слово встречается в нём)
    @Test
    void testWordFrequencyCounter() {
        String filePath = "file.txt";
        Path path = Paths.get(filePath);

        try (BufferedReader reader = new BufferedReader(new FileReader(path.toFile()))) {
            Map<String, Long> wordFrequencyMap = reader.lines()
                    .flatMap(line -> Arrays.stream(line.split("\\s+")))
                    .map(word -> word.toLowerCase().replaceAll("[^a-zA-Z'`\\u002D]", ""))
                    .filter(word -> !word.isEmpty())
                    .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

            wordFrequencyMap.forEach((word, frequency) -> {
                System.out.println(word + ": " + frequency);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Получите список дат и найдите количество дней между первой и последней датой.
    @Test
    void testDaysBetweenFirstAndLastDate() {
        List<LocalDate> dates = new ArrayList<>();
        dates.add(LocalDate.of(2023, 1, 1));
        dates.add(LocalDate.of(2023, 12, 10));
        dates.add(LocalDate.of(2023, 10, 20));

        long daysBetween = dates.stream()
                .collect(CustomCollectors.daysBetweenFirstAndLastDate());

        System.out.println("Number of days between first and last date: " + daysBetween);
    }

    //Получите список строк, преобразуйте их в числа, и посчитайте среднее значение (не забудьте отфильтровать не валидные строки)
    @Test
    void testCalculateAverage() {
        List<String> str = Arrays.asList("10.5", "15.2", "20.0", "invalid", "5.3", "12.8");
        double value = str.stream()
                .filter(s -> s.matches("\\d+\\.\\d+"))
                .mapToDouble(Double::parseDouble)
                .average()
                .orElse(0.0);
//                .map(s -> {
//                    try {
//                        return Optional.of(Double.parseDouble(s));
//                    } catch (NumberFormatException e) {
//                        return Optional.empty();
//                    }
//                })
//                .filter(Optional::isPresent)
//                .mapToDouble(o -> (double) o.get())
//                .average()
//                .orElse(0.0);

        System.out.println("Average value: " + value);
    }

    //Сгенерируйте миллион рандомных чисел и посчитайте их сумму используя parallelStream с двумя потоками.
    @Test
    void testParallelStream() {
        long startTime = System.currentTimeMillis();

        ForkJoinPool customThreadPool = new ForkJoinPool(2);

        long sum = customThreadPool.submit(() ->
                ThreadLocalRandom.current()
                        .longs(1_000_000)
                        .parallel()
                        .sum()
        ).join();

        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;

        System.out.println("Sum: " + sum);
        System.out.println("Execution time (milliseconds): " + totalTime);
    }

}
