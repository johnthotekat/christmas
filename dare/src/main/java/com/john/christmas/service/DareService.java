package com.john.christmas.service;

import java.util.List;

import com.john.christmas.entity.Dare;

public interface DareService {
	  public Dare save(Dare dare);
	  public List<Dare> findAll();
	  public long count();
	  public void delete(Integer dareID);
	  public List<Dare> findByIsCompleted(Integer isCompleted);
}
