package top.gzii.license.mapper;

import top.gzii.license.model.GenerationRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【license_generation_record(许可证生成记录表)】的数据库操作Mapper
* @createDate 2025-12-09 15:01:12
* @Entity top.gzii.license.model.GenerationRecord
*/
public interface GenerationRecordMapper extends BaseMapper<GenerationRecord> {

    long deleteExpireRecord(int day);
}




