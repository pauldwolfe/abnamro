package abn.marklogic;

import abn.domain.Market;
import abn.store.MarketStore;
import com.marklogic.client.pojo.PojoRepository;

import java.time.LocalDateTime;
import java.util.List;

public class MarkLogicMarketStore implements MarketStore {

    private final PojoRepository<Market, LocalDateTime> repository;

    public MarkLogicMarketStore(PojoRepository<Market, LocalDateTime> repository) {
        this.repository = repository;
    }

    public void store(List<Market> market) {
        market.forEach(repository::write);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
