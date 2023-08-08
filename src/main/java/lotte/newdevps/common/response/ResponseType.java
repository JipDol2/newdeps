package lotte.newdevps.common.response;

import lombok.Getter;

@Getter
public enum ResponseType {

    A001("A001","로그인 성공"),

    U001("U001","회원가입 성공"),
    U002("U002","프로필 이미지 업로드 성공"),

    B001("B001","북마크 저장 성공"),
    B002("B002","북마크 조회 성공"),
    B003("B003","북마크(게시글) 조회 성공"),
    B004("B004","북마크(추천장소) 조회 성공"),
    B005("B005","북마크 리스트 조회 성공"),

    P001("P001","게시글 저장 성공"),
    P002("P002","게시글 전체 조회 성공"),
    P003("P003","게시글 단건 조회 성공"),
    P004("P004","게시글 업데이트 성공"),
    P005("P005","게시글 삭제 성공"),

    L001("L001","추천 장소 조회 성공"),
    L002("L002","추천 장소 전체 조회 성공"),
    L003("L003","추천 장소 저장 성공"),
    ;

    private String code;
    private String message;
    ResponseType(String code,String message) {
        this.code = code;
        this.message = message;
    }
}
