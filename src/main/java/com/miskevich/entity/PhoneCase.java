package com.miskevich.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;


@RequiredArgsConstructor
@Entity
@Table(name = "phone_case", schema = "phoneservice")
@ToString(exclude = "phoneCaseModel")
@Getter
@Setter
public class PhoneCase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "phoneCase")
    @JsonBackReference
    private Set<PhoneCaseModel> phoneCaseModels;
}
