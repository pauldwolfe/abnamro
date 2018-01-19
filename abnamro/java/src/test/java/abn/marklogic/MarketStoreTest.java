package abn.marklogic;

import abn.cassandra.CassandraStore;
import abn.domain.Market;
import abn.domain.MarketGenerator;
import abn.domain.Trade;
import abn.influxdb.InfluxDBStore;
import abn.store.MarketStore;
import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.DataType;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.TupleType;
import com.datastax.driver.extras.codecs.jdk8.InstantCodec;
import com.datastax.driver.extras.codecs.jdk8.ZonedDateTimeCodec;
import com.google.common.base.Stopwatch;
import com.marklogic.client.DatabaseClient;
import com.marklogic.client.DatabaseClientFactory;
import com.marklogic.client.pojo.PojoRepository;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.junit.Test;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;

public class MarketStoreTest {

    @Test
    public void marklogic() {
        DatabaseClient client =
                DatabaseClientFactory.newClient("localhost", 8000, "atlas", new DatabaseClientFactory
                        .DigestAuthContext("admin", "admin"));
        PojoRepository<Market, LocalDateTime> repository = client.newPojoRepository(Market.class, LocalDateTime.class);
        MarketStore victim = new MarkLogicMarketStore(repository);
        victim.deleteAll();
        test(victim);
    }


    @Test
    public void influxdb() {
        InfluxDB connect = InfluxDBFactory.connect("http://localhost:8086", "root", "root");
        connect.deleteDatabase(InfluxDBStore.DATABASE);
        MarketStore victim = new InfluxDBStore(connect);
        test(victim);
    }

    @Test
    public void cassandra() {
        Cluster cluster = Cluster.builder().addContactPoint("127.0.0.1").build();
        Session session = cluster.newSession();
        cluster.getConfiguration().getCodecRegistry()
                .register(InstantCodec.instance);
        session.execute("CREATE KEYSPACE IF NOT EXISTS atlas WITH replication = {"
                + " 'class': 'SimpleStrategy', "
                + " 'replication_factor': '1' "
                + "};");
        session.execute("DROP TABLE IF EXISTS atlas.market");
        session.execute("CREATE TYPE IF NOT EXISTS atlas.quote (price double, volume double)");
        session.execute("CREATE TABLE atlas.market (timestamp timestamp PRIMARY KEY, contract text," +
                " bestbid quote, bestask quote);");
        MarketStore victim = new CassandraStore(session);
        test(victim);
    }

    private void test(MarketStore victim) {
        List<Market> markets = MarketGenerator.generate(Instant.now(), 250, 1000);
        Stopwatch stopwatch = Stopwatch.createStarted();
        victim.store(markets);
        stopwatch.stop();
        System.out.println(String.format("Storing 50000 markets took %s", stopwatch.elapsed(TimeUnit.MILLISECONDS)));
    }
}