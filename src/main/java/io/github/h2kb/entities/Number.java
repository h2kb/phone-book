package io.github.h2kb.entities;

import io.github.h2kb.enums.NumberType;

import javax.persistence.*;

@Entity
@Table(name = "number")
public class Number {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "number", nullable = false)
    private String number;

    @ManyToOne
    @JoinColumn(name = "entry_id", nullable = false, foreignKey = @ForeignKey(name = "entry_id"))
    private Entry entry;

    @Enumerated(EnumType.ORDINAL)
    private NumberType numberType;

//    @Column(name = "entry_id")
//    private Entry entry;

//    public void setNumber(String number) {
//        this.number = number;
//    }
//
//    public void setNumberType(String numberType) {
//        this.numberType = numberType;
//    }
//
//    public Integer getId() {
//        return id;
//    }
//
//    public String getNumber() {
//        return number;
//    }
//
//    public String getNumberType() {
//        return numberType;
//    }
}
