package kr.or.ddit.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FaqDto {
	private int faqNo;
	private String faqQestn;
	private String faqAnswer;
	private String mngrId;
}
