package io.github.h2kb.entities;

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

//    @OneToOne(optional = false, cascade = CascadeType.ALL)
//    @JoinColumn(name = "entry_id", nullable = false, foreignKey = @ForeignKey(name = "entry_id"))
//    private Entry entry;

//    public void setOwner(Integer owner) {
//        this.owner = owner;
//    }

//    public void setEntry(Integer entry) {
//        this.entry = entry;
//    }

//    public Integer getId() {
//        return id;
//    }

//    public Integer getOwner() {
//        return owner;
//    }

//    public Integer getEntry() {
//        return entry;
//    }
}
