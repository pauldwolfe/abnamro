package abn.cassandra;

import abn.domain.Market;
import abn.store.MarketStore;
import com.datastax.driver.core.Session;
import com.datastax.driver.mapping.Mapper;
import com.datastax.driver.mapping.MappingManager;

import java.util.List;

public class CassandraStore implements MarketStore {
    private final Mapper<Market> mapper;
    private final Session session;

    public CassandraStore(Session session) {
        this.mapper = new MappingManager(session).mapper(Market.class);
        this.session = session;
    }

    @Override
    public void store(List<Market> market) {
        market.forEach(mapper::save);
    }

    @Override
    public void deleteAll() {
        session.execute("drop table atlas.market");
    }
}
