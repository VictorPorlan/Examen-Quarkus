package edu.pingpong.quickstart;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.*;

@Entity
@Table(name = "t_ordenes")
public class Orden extends PanacheEntityBase {
    @Id
    @Column(name="ord_id")
    public Long id;
    @OneToOne
    @JoinColumn(name="ord_user")
    public Usuaria user;
    @OneToOne
    @JoinColumn(name="ord_item")
    public Item item;

    public Orden(Long id, Usuaria user, Item item) {
        this.id = id;
        this.user = user;
        this.item = item;
    }

    public Orden() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Usuaria getUser() {
        return user;
    }

    public void setUser(Usuaria user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
