package com.nomizo.coverage;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nomizo.log.Logging;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ProductAspect {

    @Autowired
    Logging logs;
//
//    Logger logger = LoggerFactory.getLogger(ProductAspect.class);

    @Pointcut(value = "execution(* com.nomizo.*.*.*(..) )")
    public void myPointcut(){ }


    @Around("myPointcut()")
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

       // Enables the code to properly come in as a Array
        mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        Object object = proceedingJoinPoint.proceed();
        logs.logger.info(className + " : "
                + methodName + "()"
                + "Response : "
                + mapper.writeValueAsString(object));
        return object;
    }

}
