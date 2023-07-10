package br.com.eai.recruiting.livecode.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ADDRESS")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "STREET", nullable = false)
    private String street;

    @Column(name = "NUMBER", nullable = false)
    private String number;

    @Column(name = "NEIGHBORHOOD", nullable = false)
    private String neighborhood;

    @Column(name = "CITY", nullable = false)
    private String city;

    @Column(name = "STATE", nullable = false)
    private String state;

    @Column(name = "ZIP_CODE", nullable = false)
    private String zipCode;


}
