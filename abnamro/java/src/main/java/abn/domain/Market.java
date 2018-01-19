package abn.domain;

import com.datastax.driver.mapping.annotations.PartitionKey;
import com.datastax.driver.mapping.annotations.Table;
import com.marklogic.client.pojo.annotation.Id;

import java.time.Instant;
import java.time.LocalDateTime;

@Table(keyspace = "atlas", name = "market",
        readConsistency = "QUORUM",
        writeConsistency = "QUORUM")
public class Market {

    @PartitionKey
    private Instant timestamp;
    private String contract;
    private Quote bestBid;
    private Quote bestAsk;

    public Market(Instant timestamp, String contract, Quote bestBid, Quote bestAsk) {
        this.timestamp = timestamp;
        this.contract = contract;
        this.bestBid = bestBid;
        this.bestAsk = bestAsk;
    }

    public Market() {
    }

    public Quote getBestBid() {
        return bestBid;
    }

    public Quote getBestAsk() {
        return bestAsk;
    }

    @Id
    public Instant getTimestamp() {
        return timestamp;
    }

    public String getContract() {
        return contract;
    }
}
