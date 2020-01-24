package io.github.h2kb.entity;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "phone_book")
public class PhoneBook {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToOne()
    @JoinColumn(name = "owner_id", nullable = false, foreignKey = @ForeignKey(name = "owner_id"))
    private User owner;

    @OneToMany(mappedBy = "phoneBook", fetch = FetchType.EAGER)
    private Collection<Entry> entries;

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

    public Collection<Entry> getEntries() {
        return entries;
    }
}
