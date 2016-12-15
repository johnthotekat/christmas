package com.john.christmas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.john.christmas.entity.Dare;
import com.john.christmas.entity.Friend;
import com.john.christmas.service.DareService;
import com.john.christmas.service.FriendService;

@Controller
public class DareController {

	@Autowired
	DareService dareService;
	
	@Autowired
	FriendService friendService;

	@RequestMapping(value="/create",method = RequestMethod.POST)
	public String createDare(@ModelAttribute (value="dareForm") Dare dare) {
		Dare d;
		Friend friend;
		try {
			friend=friendService.findOne(dare.getFriendID());
			d=new Dare();
			d.setFriend(friend);
			d.setDescription(dare.getDescription());
			d.setIsCompleted(0);
			dareService.save(d);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return "index";
		}
		return "redirect:/index";
	}
	
	@RequestMapping("/index")
	public String getDares(Model model){
		List<Dare> dare= dareService.findAll();
		List<Friend> friends=friendService.findAll();
		model.addAttribute("dare",dare);
		model.addAttribute("friend",friends);
		model.addAttribute("dareForm",new Dare());
		return "index";
	}
}
