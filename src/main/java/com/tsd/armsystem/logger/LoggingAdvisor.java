package com.tsd.armsystem.logger;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvisor {

    Logger log= LoggerFactory.getLogger(LoggingAdvisor.class);

    @Pointcut(value = "execution(* com.tsd.armsystem.*.*.*(..))")
    public void projectPointCut(){

    }

    @Pointcut(value = "execution(* com.tsd.armsystem.service.*.*(..))")
    public void servicePointCut(){

    }

    @Pointcut(value = "execution(* com.tsd.armsystem.controller.*.*(..))")
    public void controllerPointCut(){

    }

    @Pointcut(value = "execution(* com.tsd.armsystem.repository.*.*(..))")
    public void repositoryPointCut(){

    }


//    @Around("servicePointCut() && controllerPointCut()")
    @Around("projectPointCut()")
    public Object applicationLogger(ProceedingJoinPoint pjp) {

        ObjectMapper mapper = new ObjectMapper();
        String methodName = pjp.getSignature().getName();
        String className = pjp.getTarget().getClass().toString();
        Object[] array = pjp.getArgs();

        Object proceed=null;
        try {
            log.info("Method invoked " + className + " : " + methodName + "() " + "arguments : " + mapper.writeValueAsString(array));

            proceed = pjp.proceed();
            log.info(className+ " : "+methodName+"() "+"Response : "+mapper.writeValueAsString(proceed));
        }catch (Throwable e){

        }



        return proceed;
    }
}
