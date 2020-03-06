package com.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.entity.NewEntity;

@Repository
public interface NewRepository extends JpaRepository<NewEntity, Long> {

}
