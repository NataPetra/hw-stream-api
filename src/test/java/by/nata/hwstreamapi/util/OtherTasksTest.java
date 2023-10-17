package by.nata.hwstreamapi.util;

import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

class OtherTasksTest {

    //Прочтите содержимое текстового файла и сделайте из него частотный словарик. (слово -> и какое кол-во раз это слово встречается в нём)
    @Test
    void testWordFrequencyCounter(){
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
}
