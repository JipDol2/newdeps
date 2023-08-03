package lotte.newdevps.common.response;

import lombok.Getter;

@Getter
public enum ResponseType {

    A001("A001","로그인 성공"),

    U001("U001","회원가입 성공"),
    U002("U002","프로필 이미지 업로드 성공"),

    P001("P001","게시글 저장 성공"),
    P002("P002","게시글 전체 조회 성공"),
    P003("P003","게시글 단건 조회 성공"),
    P004("P004","게시글 업데이트 성공"),
    P005("P005","게시글 삭제 성공"),
    ;

    private String code;
    private String message;
    ResponseType(String code,String message) {
        this.code = code;
        this.message = message;
    }
}
