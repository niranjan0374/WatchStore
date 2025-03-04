package com.itvedant.Watch.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itvedant.Watch.Entity.Address;


@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}
