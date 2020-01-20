package io.github.h2kb.entity;

import io.github.h2kb.entity.enums.EntryType;

import javax.persistence.*;

@Entity
@Table(name = "entry")
public class Entry {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "phone_book_id", nullable = false, foreignKey = @ForeignKey(name = "phone_book_id"))
    private PhoneBook phoneBook;

    @Enumerated(EnumType.ORDINAL)
    private EntryType entryType;

    public Entry() {
    }

    public Entry(PhoneBook phoneBook, String name, EntryType entryType) {
        this.phoneBook = phoneBook;
        this.name = name;
        this.entryType = entryType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public String getPhoneNote() {
        return name;
    }
}
