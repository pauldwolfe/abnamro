package abn.domain;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MarketGenerator {

    public static List<Market> generate(Instant start, long intervalMillis, long count) {
        return Stream.iterate(start, d -> d.minus(intervalMillis, ChronoUnit.MILLIS))
                .limit(count)
                .map(dateTime -> new Market(dateTime, "EUR/USD", new Quote(1.2, 100), new Quote(1.3, 100)))
                .collect(Collectors.toList());
    }
}
