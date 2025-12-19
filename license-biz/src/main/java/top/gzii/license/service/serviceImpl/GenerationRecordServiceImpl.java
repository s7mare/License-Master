package top.gzii.license.service.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.gzii.license.model.GenerationRecord;
import top.gzii.license.service.GenerationRecordService;
import top.gzii.license.mapper.GenerationRecordMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【license_generation_record(许可证生成记录表)】的数据库操作Service实现
* @createDate 2025-12-09 15:01:12
*/
@Service
public class GenerationRecordServiceImpl extends ServiceImpl<GenerationRecordMapper, GenerationRecord>
    implements GenerationRecordService{

}




