package kr.purred.fc.mfriend.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum BoardType
{
	notice("공지사항"),

	free("자유게시판"),

	;

	private final String value;
}
