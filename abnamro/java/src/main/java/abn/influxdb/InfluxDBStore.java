package abn.influxdb;

import abn.domain.Market;
import abn.domain.Quote;
import abn.store.MarketStore;
import com.google.common.collect.Lists;
import org.influxdb.InfluxDB;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class InfluxDBStore implements MarketStore {

    public static final String DATABASE = "atlas";
    private final InfluxDB influx;

    public InfluxDBStore(InfluxDB database) {
        this.influx = database;
        if (!influx.databaseExists(DATABASE)) {
            influx.createDatabase(DATABASE);
        }
    }

    @Override
    public void store(List<Market> markets) {
        try {
            BatchPoints.Builder builder = BatchPoints.database(DATABASE);
            for (Market market : markets) {
                builder
                        .tag("contract", market.getContract());
                Quote bid = market.getBestBid();
                Quote ask = market.getBestAsk();
                Point.Builder pointBuilder = Point.measurement("market");
                pointBuilder.time(market.getTimestamp().toEpochMilli(), TimeUnit.MILLISECONDS);
                pointBuilder.addField("bid_volume", bid.getVolume());
                pointBuilder.addField("bid_price", bid.getPrice());
                pointBuilder.addField("ask_price", ask.getPrice());
                pointBuilder.addField("ask_volume", ask.getVolume());
                builder.point(pointBuilder.build());
            }
            influx.write(builder.build());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteAll() {
        influx.deleteDatabase(DATABASE);
    }
}
