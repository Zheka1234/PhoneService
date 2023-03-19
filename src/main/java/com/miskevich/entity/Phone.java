package com.miskevich.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@RequiredArgsConstructor
@Entity
@Table(name = "phone", schema = "phoneservice")
@ToString
@Getter
@Setter
public class Phone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "phone_case_model_id")
    @JsonManagedReference
    private PhoneCaseModel phoneCaseModels;

    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    @Column(name = "vin_code")
    private String vinCode;

    @Column(name = "color")
    private String color;
}
