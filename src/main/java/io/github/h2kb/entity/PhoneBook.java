package io.github.h2kb.entity;

import javax.persistence.*;

@Entity
@Table(name = "phone_book")
public class PhoneBook {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id", nullable = false, foreignKey = @ForeignKey(name = "owner_id"))
    private User owner;

    public PhoneBook() {
    }

    public PhoneBook(User owner) {
        this.owner = owner;
    }

    public Integer getId() {
        return id;
    }

    public Integer getOwner() {
        return owner.getId();
    }
}
