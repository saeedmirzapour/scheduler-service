package io.handyprojects.schedulerservice.config.exception;

import io.handyprojects.schedulerservice.exception.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;
import java.util.Map;

@Component
public class CustomErrorAttributes extends DefaultErrorAttributes {

    private final HttpServletRequest httpServletRequest;
    private final MessageSource messageSource;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CustomErrorAttributes(HttpServletRequest httpServletRequest,
                                 MessageSource messageSource) {
        this.httpServletRequest = httpServletRequest;
        this.messageSource = messageSource;
    }

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, includeStackTrace);
        Throwable ex = getError(webRequest);
        if (ex instanceof ApiException) {
            ApiException exception = (ApiException) ex;
            String errorClassName = getErrorClassName(exception);
            errorAttributes.put("error", errorClassName);
            errorAttributes.put("message", findMessage(LocaleContextHolder.getLocale(), errorClassName));
        }
        return errorAttributes;
    }

    private String getErrorClassName(Throwable ex) {
        try {
            String[] exceptionClassNameArray = ex.getClass().getName().split("\\.");
            return exceptionClassNameArray[exceptionClassNameArray.length - 1];
        } catch (Exception e) {
            logger.error("error in getErrorClassName()", e);
        }
        return null;
    }

    private String findMessage(Locale locale, String code) {
        try {
            return messageSource.getMessage(code, new String[]{}, locale);
        } catch (Exception e) {
            logger.error("error in findMessage()", e);
        }
        return null;
    }
}
