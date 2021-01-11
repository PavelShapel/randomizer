package com.pavelshapel.randomizer.aop;

import lombok.NonNull;
import lombok.Value;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;

@Value
public class MethodSpecification {
    String declaringClassName;
    String name;
    LogMethodResult logMethodResult;

    public MethodSpecification(@NonNull JoinPoint joinPoint) {
        final Method method = getMethod(joinPoint);

        this.declaringClassName = method.getDeclaringClass().getSimpleName();
        this.name = method.getName();
        this.logMethodResult = method.getAnnotation(LogMethodResult.class);
    }

    private Method getMethod(JoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        return methodSignature.getMethod();
    }
}
