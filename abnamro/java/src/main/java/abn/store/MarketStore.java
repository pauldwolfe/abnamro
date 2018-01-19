package abn.store;

import abn.domain.Market;

import java.util.List;

public interface MarketStore {

    void store(List<Market> market);

    void deleteAll();
}
