package net.abadguy.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.IJobHandler;
import com.xxl.job.core.handler.annotation.JobHandler;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@JobHandler(value="demoJobHandler")
@Component
@Slf4j
public class DemoJobHandler extends IJobHandler {
    @Override
    public ReturnT<String> execute(String s) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World.");
        for (int i = 0; i < 5; i++) {
            XxlJobLogger.log("beat at:" + i);
            TimeUnit.SECONDS.sleep(2);
        }
        XxlJobLogger.log("XXL-JOB, end ...");
        return SUCCESS;
    }
}
