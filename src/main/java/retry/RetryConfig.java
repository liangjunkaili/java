package retry;

import com.github.rholder.retry.Retryer;
import com.github.rholder.retry.RetryerBuilder;
import com.github.rholder.retry.StopStrategies;
import com.github.rholder.retry.WaitStrategies;
import com.google.common.base.Predicates;

import java.util.concurrent.TimeUnit;

public class RetryConfig {
    public static Retryer<Boolean> retry(){
        Retryer<Boolean> retryer = RetryerBuilder.<Boolean>newBuilder()
                .retryIfResult(Predicates.equalTo(false))
                .retryIfExceptionOfType(Exception.class) // 发生IO异常则重试
                .retryIfRuntimeException() // 发生运行时异常则重试
                .withWaitStrategy(WaitStrategies.incrementingWait(1, TimeUnit.SECONDS, 1, TimeUnit.SECONDS)) // 等待
                .withStopStrategy(StopStrategies.stopAfterAttempt(4)) // 允许执行4次（首次执行 + 最多重试3次）
                .build();
        return retryer;
    }
}
