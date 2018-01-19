package abn.domain;

import com.datastax.driver.mapping.annotations.Field;
import com.datastax.driver.mapping.annotations.UDT;

@UDT(keyspace = "atlas", name = "quote")
public class Quote {

    @Field(name = "price")
    private double price;
    @Field(name = "volume")
    private double volume;

    public Quote() {
    }

    public Quote(double price, double volume) {
        this.price = price;
        this.volume = volume;
    }

    public double getPrice() {
        return price;
    }

    public double getVolume() {
        return volume;
    }
}
