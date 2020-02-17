package priv.wmc.common.exception;

import priv.wmc.common.exception.core.ApiError;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * 基础异常类
 *
 * @author 王敏聪
 * @date 2019/11/18 9:17
 */
@Getter
@RequiredArgsConstructor
public class ApiBasicException extends RuntimeException implements ApiError {

    private final String message;

    public ApiBasicException(ApiError apiError) {
        this(apiError.getMessage());
    }

}