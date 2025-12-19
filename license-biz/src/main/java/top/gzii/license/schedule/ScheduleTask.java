package top.gzii.license.schedule;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import top.gzii.license.mapper.GenerationRecordMapper;


@Slf4j
@EnableScheduling
@Configuration
public class ScheduleTask {
    @Autowired
    GenerationRecordMapper generationRecordMapper;
    @Value("${app.clean-job-day}")
    int day;


    @Scheduled(cron = "0 0/1 * * * ? ")
    public void cleanOldRecord(){
       long count= generationRecordMapper.deleteExpireRecord(day);
        log.info("执行定时任务：清理过期{}天的证书，本次共清理了{}条记录",day,count);

    }
}
