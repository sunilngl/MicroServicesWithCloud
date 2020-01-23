package com.clientone.app;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientOneController {
	
	@Autowired
	MemberRepository memberRepo;
	
	@Autowired
	IMemberService memberService;

	@RequestMapping(value = "/")
	public String home() {
	   return "Eureka Client One application is running.....";
	 }
	
	@GetMapping(value = "/healthcheck", produces = "application/json; charset=utf-8")
	public String getHealthCheck()
	{
		return "{ \"isWorking\" : true }";
	}
	
	@GetMapping(value = "/memdetails", produces = "application/json; charset=utf-8")
	public List<MemberDTO> getMemDetails() {
		
		List<MemberDTO> memberslist = memberRepo.findAll();
		return memberslist;
		
	}
	
	@GetMapping(value = "/memdetails/{id}", produces = "application/json; charset=utf-8")
	public MemberDTO getMemDetailsByID(@PathVariable("id") int id) {
		
		MemberDTO memberDetails = memberService.getMemDetailsByID(id);
		return memberDetails;
		
	}
	
	
	@PostMapping(value = "/mem", produces = "application/json; charset=utf-8")
	public MemberDTO getMemDetails(@RequestBody MemberDTO memberDto) {
		memberRepo.save(memberDto);
		return null;
		
	}
	
	@GetMapping("/getmember/{id}")
	public MemberDTO getMemberByID(@PathVariable("id") int id) {
		
		MemberDTO member = memberRepo.getOne(id);
		return member;
		
	}
}
