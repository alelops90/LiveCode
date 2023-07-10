package br.com.eai.recruiting.livecode.repository;

import br.com.eai.recruiting.livecode.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query(value = "SELECT * FROM ADDRESS " +
            "WHERE ZIP_CODE = :zipCode", nativeQuery = true)
    List<Address> findByZipCode(String zipCode);
}
