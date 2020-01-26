package com.clientone.app;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberController {

	@Autowired
	MemberRepository memberRepo;
	
	@GetMapping(value = "/test", produces="application/json;charset=utf-8")
	public String getTest() {
		
		return "Working";
	}
	
	@PostMapping("/savemember")
	public MemberDTO addMember(@RequestBody MemberDTO newMember) {
		
		String id = String.valueOf(new Random().nextInt());
		MemberDTO mem = new MemberDTO(id, newMember.getFirstName(), newMember.getLastName());
		memberRepo.save(mem);
		return mem;
	}
	
	
	@GetMapping("/memdetails")
	public List<MemberDTO> getMemDetails(){
		Iterable<MemberDTO> result = memberRepo.findAll();
		List<MemberDTO> memList = new ArrayList<MemberDTO>();
		result.forEach(memList::add);
		return memList;
	}
	
	@GetMapping("mem/{id}")
	public Optional<MemberDTO> getMemById(@PathVariable String id){
		Optional<MemberDTO> mem = memberRepo.findById(id);
		return mem;
	}
	
	@PutMapping("member/{id}")
	public Optional<MemberDTO> memUpdate(@RequestBody MemberDTO newMember, @PathVariable String id){
		Optional<MemberDTO> mem = memberRepo.findById(id);
		if(mem.isPresent()) {
			MemberDTO memDto = mem.get();
			memDto.setFirstName(newMember.getFirstName());
			memDto.setLastName(newMember.getLastName());
			memberRepo.save(memDto);
		}
		return mem;		
	}
	
	
	@DeleteMapping(value ="/delete/{id}", produces="application/json;charset=utf-8")
	public String deleteMember(@PathVariable String id) {
		Boolean result = memberRepo.existsById(id);
		memberRepo.deleteById(id);
		return "Deleted";
	}
	
}
