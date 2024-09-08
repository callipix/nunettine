package kr.or.ddit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AuthorDto {
	// 권한테이블
	private String author;            // 권한(관리자/일반회원/프로)
	private String userId;            // 아이디

}
