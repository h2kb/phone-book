package io.github.h2kb.entity;

import io.github.h2kb.entity.enums.NumberType;

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

    public Number() {
    }

    public Number(Entry entry, String number, NumberType numberType) {
        this.entry = entry;
        this.number = number;
        this.numberType = numberType;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Integer getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public NumberType getNumberType() {
        return numberType;
    }
}
