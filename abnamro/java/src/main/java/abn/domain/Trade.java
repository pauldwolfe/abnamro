package abn.domain;

import com.marklogic.client.pojo.annotation.Id;

import java.io.Serializable;
import java.util.UUID;

public class Trade implements Serializable {

    private double price;
    private double size;
    private String contract;
    private String id;

    public Trade() {
    }

    public Trade(double price, double size, String contract, String id) {
        this.price = price;
        this.size = size;
        this.contract = contract;
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }

    public double getPrice() {
        return price;
    }

    public double getSize() {
        return size;
    }

    public String getContract() {
        return contract;
    }

    public static Trade of(double price, double size, String contract) {
        return new Trade(price, size, contract, UUID.randomUUID().toString());
    }
}
