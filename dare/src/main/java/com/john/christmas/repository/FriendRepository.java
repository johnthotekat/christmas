package com.john.christmas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.john.christmas.entity.Friend;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Integer>{

}
