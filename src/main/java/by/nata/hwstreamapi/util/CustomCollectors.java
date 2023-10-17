package by.nata.hwstreamapi.util;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;

class CustomCollectors {

    private CustomCollectors() {
        throw new IllegalStateException("Utility class");
    }

    public static Collector<LocalDate, List<LocalDate>, Long> daysBetweenFirstAndLastDate() {
        return Collector.of(
                ArrayList::new,                // Create a container for intermediate results (an empty ArrayList) Supplier<A> supplier
                List::add,                     // Accumulator - a function for adding elements to the container BiConsumer<R, T> accumulator
                (list1, list2) -> {            // Combinator - a function for merging two containers BiFunction<T, U, R> combiner
                    list1.addAll(list2);
                    return list1;
                },
                list -> {                      // Function for final processing of results Function<A, R> finisher
                    if (list.isEmpty()) {
                        return 0L;
                    }
                    LocalDate firstDate = list.stream()
                            .min(LocalDate::compareTo)
                            .orElse(null);
                    LocalDate lastDate = list.stream()
                            .max(LocalDate::compareTo)
                            .orElse(null);
                    return firstDate.datesUntil(lastDate.plusDays(1))
                            .count();
                }
        );
    }
}