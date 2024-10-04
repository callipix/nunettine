package kr.or.ddit.admin.faq.dto;

import lombok.*;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FaqDto {
	private int rnum;
	private int faqNo;
	private String faqQestn;
	private String faqAnswer;
	private String mngrId;
}
