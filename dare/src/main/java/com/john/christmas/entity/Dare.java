package com.john.christmas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Dare {

	@Id
	@GeneratedValue
	@Column(name="dare_id")
	private Integer dareID;
	
	@Column
	private String description;
	
	@Column(name="friend_id",insertable = false, updatable = false)
	private Integer friendID;
	
	public Integer getFriendID() {
		return friendID;
	}

	public void setFriendID(Integer friendID) {
		this.friendID = friendID;
	}

	@ManyToOne
	@JoinColumn(name="friend_id", nullable=false)
	private Friend friend;
	
	public Friend getFriend() {
		return friend;
	}

	public void setFriend(Friend friend) {
		this.friend = friend;
	}

	@Column(name="is_completed")
	private Integer isCompleted;

	public Integer getDareID() {
		return dareID;
	}

	public void setDareID(Integer dareID) {
		this.dareID = dareID;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

//	public Friend getFriend() {
//		return friend;
//	}
//
//	public void setFriend(Friend friend) {
//		this.friend = friend;
//	}

	public Integer getIsCompleted() {
		return isCompleted;
	}

	public void setIsCompleted(Integer isCompleted) {
		this.isCompleted = isCompleted;
	}
}
