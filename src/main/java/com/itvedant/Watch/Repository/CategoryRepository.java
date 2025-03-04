package com.itvedant.Watch.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.itvedant.Watch.Entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
