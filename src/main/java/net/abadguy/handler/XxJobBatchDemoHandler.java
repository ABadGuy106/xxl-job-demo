package net.abadguy.handler;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class XxJobBatchDemoHandler {
    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private JobLauncher jobLauncher;

    @XxlJob("batchJobDemo")
    public ReturnT<String> batchJobDemo(String param){
        XxlJobLogger.log("作业调度开始。。。");
        log.info("开始。。。。。");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Job forTestXxjob = xxlJobBatchJob();
        try {
            JobExecution run = jobLauncher.run(forTestXxjob, new JobParameters());
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }

        log.info("结束.......");
        XxlJobLogger.log("作业调度完成。。。");
        return ReturnT.SUCCESS;
    }


    public Job xxlJobBatchJob(){
        return jobBuilderFactory.get("forTestXxjob").start(xxjobBatchStep()).build();
    }


    public Step xxjobBatchStep() {
        return stepBuilderFactory.get("xxjobBatchStep")
                .tasklet(new Tasklet() {
                    @Override
                    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
                        log.info("操作开始.....");
                        Thread.sleep(10000);
                        log.info("操作结束.....");


                        return null;
                    }
                }).build();
    }
}
