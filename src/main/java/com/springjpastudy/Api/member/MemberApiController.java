package com.springjpastudy.Api.member;


import com.springjpastudy.domain.address.Address;
import com.springjpastudy.domain.member.Member;
import com.springjpastudy.service.memberService.MemberService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.xml.transform.Result;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class MemberApiController {
	
	private final MemberService memberService;
	
	@PostMapping("/api/v1/members")
	public CreateMemberResponse saveMemberV1(@Valid @RequestBody Member member) {
		Long id = memberService.join(member);
		return new CreateMemberResponse(id);
	}
	
	// 이게젤 안전
	@PostMapping("api/v2/members")
	public CreateMemberResponse saveMemberV2(@Valid @RequestBody CreateMemberRequest requset) {
		
		Member member = new Member();
		member.setName(requset.getName());
		
		Long id = memberService.join(member);
		return new CreateMemberResponse(id);
	}
	
	@PutMapping("/api/v2/members/{id}")
	public UpdateMemberRequest updateMemberV2(
			@PathVariable Long id, @RequestBody
			@Valid UpdateMemberRequest request) {
		memberService.update(id, request.getName());
		Member findMember = memberService.findOne(id);
		return  new UpdateMemberRequest(findMember.getId(),findMember.getName());
	}
	
	@GetMapping("api/v1/members")
	public List<Member> membersv1() {
		return memberService.findMembers();
	}
	
	@GetMapping("api/v2/members")
	public Result membersv2() {
		
		List<Member> findMembers = memberService.findMembers();
		List<MemberDto> collect = findMembers.stream()
				.map(m -> new MemberDto(m.getName(),m.getAddress()))
				.collect(Collectors.toList());
		return new Result(collect.size(), collect);
	}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Data
	@AllArgsConstructor
	static class Result<T> {
		private int count;
		private T data;
	}
	
	@Data
	@AllArgsConstructor
	static class MemberDto {
		private String name;
		private Address address;
	}
	
	
	@Data
	@AllArgsConstructor
	static  class UpdateMemberRequest {
		private Long id;
		private String name;
	}

	
	@Data
	static class CreateMemberRequest {
		private String name;
	}

    @Data
    static class  CreateMemberResponse {
		private Long id;
		
		public CreateMemberResponse(Long id) {
			this.id = id;
		}
	}
	
}
