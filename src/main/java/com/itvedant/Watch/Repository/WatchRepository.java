package com.itvedant.Watch.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itvedant.Watch.Entity.Watch;

@Repository
public interface WatchRepository extends JpaRepository<Watch, Integer> {

}
