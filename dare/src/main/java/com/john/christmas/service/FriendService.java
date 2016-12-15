package com.john.christmas.service;

import java.util.List;

import com.john.christmas.entity.Friend;

public interface FriendService {
	public Friend findOne(Integer friendID);
	public List<Friend> findAll();
	public long count();
}
