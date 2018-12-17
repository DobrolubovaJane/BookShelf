package com.bookshelf.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "reader")
public class Reader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    private String name;

    @OneToMany(mappedBy = "reader", cascade = CascadeType.REMOVE)
    private List<DeliveryDesk> deliveryDesk = new ArrayList<>();

    public Reader() {
    }

    public Reader(String name) {
        this.name = name;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeliveryDesk> getDeliveryDesk() {
        return deliveryDesk;
    }

    public void setDeliveryDesk(List<DeliveryDesk> deliveryDesk) {
        this.deliveryDesk = deliveryDesk;
    }
}
