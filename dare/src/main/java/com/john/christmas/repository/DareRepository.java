package com.john.christmas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.john.christmas.entity.Dare;

@Repository
public interface DareRepository extends JpaRepository<Dare, Integer> {
	List<Dare> findByIsCompleted(Integer isCompleted);
	
	@Query("select dare from Dare dare ORDER BY dareID desc")
	List<Dare> findAllOrderByDesc();
}
