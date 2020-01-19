package io.github.h2kb.entities;

import io.github.h2kb.enums.EntryType;

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

//    public void setEntryType(String entryType) {
//        this.type = entryType;
//    }

    public void setPhoneNote(String name) {
        this.name = name;
    }

//    public void setPhoneNumber(Integer phoneNumber) {
//        this.phoneNumber = phoneNumber;
//    }

    public Integer getId() {
        return id;
    }

//    public String getEntryType() {
//        return type;
//    }

    public String getPhoneNote() {
        return name;
    }

//    public Integer getPhoneNumber() {
//        return phoneNumber;
//    }
}
