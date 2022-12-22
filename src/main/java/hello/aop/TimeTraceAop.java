package hello.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
//@Component // @Component 어노테이션 붙이면 컴포넌트 스캔이 된다.  이렇게 하는것 보단 빈에 등록하는 것이 좋음 (특별한 케이스이기 때문에)
public class TimeTraceAop {

    @Around("execution(* hello.hellospring.*(..))")      // Around 는 어디에 적용할 지 적음
    public Object execute(ProceedingJoinPoint joinPoint)throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println(joinPoint.toString());

        try {
            return joinPoint.proceed();
        }
        finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;

            System.out.println(joinPoint.toString() + " " +timeMs);
        }
    }


}
