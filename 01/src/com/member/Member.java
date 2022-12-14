package com.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data	// Getter, Setter 메서드 동시 생성
@AllArgsConstructor
@NoArgsConstructor
public class Member {
	private String userid;
	private String userpass;
}
