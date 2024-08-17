package kr.or.ddit.admin.faq.vo;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
public class FaqVO {
	private int rnum;
	private int faqNo;
	private String faqQestn;
	private String faqAnswer;
	private String mngrId;
}
