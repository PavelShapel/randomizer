package com.pavelshapel.randomizer.aop;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.Level;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Log4j2
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class AspectLogMethodResult {
    static final String LOG_PATTERN = "[{}.{}] {}: {}";
    static final String NO_MESSAGE = "no message";

    MethodSpecification methodSpecification;
    JoinPoint joinPoint;

    @Pointcut("@annotation(LogMethodResult)")
    public void callLogMethodResult() {
    }

    @AfterReturning(pointcut = "callLogMethodResult()", returning = "result")
    private void onSuccess(JoinPoint joinPoint, Object result) {
        initializeMethodSpecification(joinPoint);

        logSuccess(result);
    }

    @AfterThrowing(pointcut = "callLogMethodResult()", throwing = "throwable")
    private void onFailed(JoinPoint joinPoint, Throwable throwable) {
        initializeMethodSpecification(joinPoint);

        logException(throwable);
    }

    private void initializeMethodSpecification(JoinPoint joinPoint) {
        this.methodSpecification = new MethodSpecification(joinPoint);
        this.joinPoint = joinPoint;
    }

    private void logSuccess(Object result) {
        if (!logResponseEntityError(result)) {
            final Level level = Level.toLevel(methodSpecification.getLogMethodResult().logLevel());
            log.log(level,
                    LOG_PATTERN,
                    methodSpecification.getDeclaringClassName(),
                    methodSpecification.getName(),
                    methodSpecification.getLogMethodResult().successPrefix(),
                    getVerifiedMessage(result.toString())
            );
        }
    }

    private boolean logResponseEntityError(Object result) {
        if (result instanceof ResponseEntity) {
            final ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;

            if (responseEntity.getStatusCode().isError()) {
                log.error(LOG_PATTERN,
                        methodSpecification.getDeclaringClassName(),
                        methodSpecification.getName(),
                        methodSpecification.getLogMethodResult().exceptionPrefix(),
                        getVerifiedMessage(responseEntity.toString()));
                return true;
            }
        }
        return false;
    }

    private void logException(Throwable throwable) {
        log.error(
                LOG_PATTERN,
                methodSpecification.getDeclaringClassName(),
                methodSpecification.getName(),
                methodSpecification.getLogMethodResult().exceptionPrefix(),
                getVerifiedMessage(throwable.getMessage())
        );
    }

    private String getVerifiedMessage(String message) {
        return StringUtils.isEmpty(message) ? NO_MESSAGE : message;
    }
}