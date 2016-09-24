package br.cs.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

@EnableScheduling
@EnableAsync
public class SchedulerConfig
  implements SchedulingConfigurer
{
  @Bean(destroyMethod="shutdown")
  public Executor taskExecutor()
  {
    return Executors.newScheduledThreadPool(100);
  }
  
  public void configureTasks(ScheduledTaskRegistrar taskRegistrar)
  {
    taskRegistrar.setScheduler(taskExecutor());
  }
}
