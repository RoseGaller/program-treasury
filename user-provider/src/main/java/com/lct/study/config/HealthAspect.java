//package com.lct.study.config;
//
//import org.aspectj.lang.JoinPoint;
//import org.aspectj.lang.ProceedingJoinPoint;
//import org.aspectj.lang.annotation.Around;
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.web.bind.annotation.RequestMapping;
//
//@Configuration
//@Aspect
//@Order(10)
//public class HealthAspect {
//
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
//    public void pointCut(){}
//
//    @Before("pointCut()")
//    public Object record(ProceedingJoinPoint joinPoint) {
//        long startTime = System.currentTimeMillis();
//        String methodName =  joinPoint.getSignature().getName();
//        System.out.println("方法名  = " + methodName + " ,"+" class = " + joinPoint.getSignature().getDeclaringType().getName());
//        try {
//            return joinPoint.proceed();
//        } catch (Throwable throwable) {
//            throw new RuntimeException(throwable);
//        }finally {
//
//        }
//    }
//}
