package com.dawes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dawes.modelo.ProductoVO;

@Repository
public interface ProductoRepository extends JpaRepository<ProductoVO,Integer> {

}
