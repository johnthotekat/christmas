package com.john.christmas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.john.christmas.entity.Friend;
import com.john.christmas.repository.FriendRepository;

@Service
public class FriendServiceImpl implements FriendService{

	@Autowired
	FriendRepository friendRepository;
	
	@Override
	public Friend findOne(Integer friendID) {
		return friendRepository.findOne(friendID);
	}

	@Override
	public List<Friend> findAll() {
		return friendRepository.findAll();
	}

	@Override
	public long count() {
		return friendRepository.count();
	}

}
