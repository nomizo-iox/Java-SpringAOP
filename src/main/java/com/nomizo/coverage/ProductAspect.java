package com.nomizo.coverage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomizo.log.Logging;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductAspect {

    @Autowired
    Logging logs;

    @Pointcut(value = "execution(* com.nomizo.*.*.*(..))")
    public void myPointcut(){ }

    public Object applicationLogger (ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        ObjectMapper mapper = new ObjectMapper();
        String methodName = proceedingJoinPoint.getSignature().getName();
        String className = proceedingJoinPoint.getTarget().getClass().toString();
        Object[] array = proceedingJoinPoint.getArgs();
        logs.logger.info("Method invoked "
                + className + " : "
                + methodName + " () "
                + "arguments : "
                + mapper.writeValueAsString(array));

        Object object = proceedingJoinPoint.proceed();
        logs.logger.info(className + " : "
                + methodName + "()"
                + "Response : "
                + mapper.writeValueAsString(object));
        return object;
    }

}
