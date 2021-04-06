package com.takeaway.demo.dao;


import com.takeaway.demo.entity.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin("http://localhost:4200")
//@Repository
public interface DishRepository extends JpaRepository<Dish, Long>{

    Page<Dish> findByCategoryId(@RequestParam("id") Long id, Pageable pageable);

}
