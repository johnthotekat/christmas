package com.john.christmas.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Friend {

	@Id
	@GeneratedValue
	@Column(name="friend_id")
	private Integer FriendID;
	
	@Column
	private String name;
	
	@OneToMany(mappedBy="friend",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
	private Set<Dare> dares=new HashSet<Dare>(0);

	public Integer getFriendID() {
		return FriendID;
	}

	public void setFriendID(Integer friendID) {
		FriendID = friendID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Dare> getDares() {
		return dares;
	}

	public void setDares(Set<Dare> dares) {
		this.dares = dares;
	}
}
