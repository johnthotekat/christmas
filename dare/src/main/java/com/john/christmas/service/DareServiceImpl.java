package com.john.christmas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.john.christmas.entity.Dare;
import com.john.christmas.repository.DareRepository;

@Service
public class DareServiceImpl implements DareService{

	@Autowired
	DareRepository dareRepository;
	
	@Override
	public Dare save(Dare dare) {
		return dareRepository.save(dare);
	}

	@Override
	public List<Dare> findAll() {
		return dareRepository.findAllOrderByDesc();
	}

	@Override
	public long count() {
		return dareRepository.count();
	}

	@Override
	public void delete(Integer dareID) {
		dareRepository.delete(dareID);
	}

	@Override
	public List<Dare> findByIsCompleted(Integer isCompleted) {
		return dareRepository.findByIsCompleted(isCompleted);
	}
}
