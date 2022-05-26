package net.betvictor.core.aspect;

import net.betvictor.core.dto.StatisticDto;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class ExecutionTimeAdvice {

    @Around("@annotation(net.betvictor.core.aspect.TrackExecutionStatisticTime)")
    public Object executionTime(ProceedingJoinPoint point) throws Throwable {
        StopWatch generalTimeStopWatch = new StopWatch();
        generalTimeStopWatch.start();
        Object object = point.proceed();
        generalTimeStopWatch.stop();
        if (object instanceof StatisticDto) {
            StatisticDto dto = (StatisticDto) object;
            dto.setTotalProcessingTime(generalTimeStopWatch.getLastTaskTimeMillis());
        }
        return object;
    }
}