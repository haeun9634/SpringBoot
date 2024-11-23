package umc.spring.apiPayLoad.exception.handler;

import umc.spring.apiPayLoad.code.BaseErrorCode;
import umc.spring.apiPayLoad.exception.GeneralException;

public class PreferenceFoodHandler extends GeneralException {
    public PreferenceFoodHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
