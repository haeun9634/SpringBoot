package umc.spring.apiPayLoad.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.spring.apiPayLoad.code.BaseErrorCode;
import umc.spring.apiPayLoad.code.ErrorReasonDTO;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관려 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "MEMBER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "MEMBER4002", "닉네임은 필수 입니다."),


    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),
    STORE_NOT_FOUND(HttpStatus.NOT_FOUND, "STORE4001","해당 가게를 찾을 수 없습니다."),
    REGION_NOT_FOUND(HttpStatus.NOT_FOUND,"REGION4001","해당 지역을 찾을 수 없습니다."),
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,"MISSION4001","해당 미션을 찾을 수 없습니다."),
    DUPLICATE_STORE_REGION(HttpStatus.CONFLICT, "STOREREGION4001","해당 지역에 이미 같은 가게가 존재합니다."),
    DUPLICATE_MISSION_REGION(HttpStatus.CONFLICT, "STOREMISSION4001","해당 가게에 이미 같은 미션이 존재합니다."),
    ALREADY_CHALLENGE_MISSION(HttpStatus.CONFLICT, "STOREMISSION4002","이미 도전중인 미션입니다. "),
    NOT_STORE_MISSION(HttpStatus.CONFLICT, "STOREMISSION4003","해당 가게의 미션이 아닙니다."),

    FOOD_CATEGORY_NOT_FOUND(HttpStatus.NOT_FOUND, "FOOD4001","해당하는 음식 카테고리를 찾을 수 없습니다."),

    NOT_PAGE_MISSION(HttpStatus.BAD_REQUEST, "PAGE4001","유효한 숫자가 아닙니다."),

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    public String getMessage() {
        return message;
    }


    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }

    public static ErrorStatus fromMessage(String message) {
        for (ErrorStatus status : values()) {
            if (status.getMessage().equals(message)) {
                return status;
            }
        }
        throw new IllegalArgumentException("No enum constant with message " + message);
    }

}