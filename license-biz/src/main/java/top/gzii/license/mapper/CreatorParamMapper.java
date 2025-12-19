package top.gzii.license.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Param;
import top.gzii.license.model.CreatorParam;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import top.gzii.license.vo.request.CreatorParamPageReqVo;
import top.gzii.license.vo.response.CreatorParamPageRespVo;

/**
* @author admin
* @description 针对表【license_creator_param(证书生成参数表)】的数据库操作Mapper
* @createDate 2025-12-09 15:01:11
* @Entity top.gzii.license.model.CreatorParam
*/
public interface CreatorParamMapper extends BaseMapper<CreatorParam> {

    IPage<CreatorParamPageRespVo> pageInfo(Page<Object> page,@Param("reqVo") CreatorParamPageReqVo reqVo);
}




