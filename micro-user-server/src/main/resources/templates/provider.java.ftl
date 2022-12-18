package ${package.Mapper};

import com.autumn.mapper.BaseSqlProvider;

/**
* <p>
    * ${table.comment!} Mapper 接口
    * </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public class ${entity}SqlProvider implements BaseSqlProvider {

}
</#if>
