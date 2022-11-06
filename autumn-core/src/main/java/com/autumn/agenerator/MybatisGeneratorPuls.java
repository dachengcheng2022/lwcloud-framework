package com.autumn.agenerator;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.select.BranchBuilder;
import com.baomidou.mybatisplus.generator.config.converts.select.Selector;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @author: jlm
 * @date: 2020/6/30 17:26
 */
public class MybatisGeneratorPuls {


    /**
     * 微服务名
     */
    private static String SERVICE_NAME = "wallet-api";

    /**
     * 表名
     */
    private static String[] TABLES = {
            "coin_extended"
    };

    /**
     * 模块名，user/order/other
     */
    private static String MODULE_NAME = "/coin/info";

    /**
     * 实体类放在mall-core 下 生成实体类路径 entity
     */
    private static String SERVICE_NAME_CORE = "wallet-core";

    /**
     * 父包名路径(文件输出路径,也是导包的路径)
     */
    private static String PARENT_PACKAGE_PATH = "/com/autumn/wallet";

    /**
     * 作者
     */
    private static String AUTHOR = "jlm";
    /**
     * 生成的实体类忽略表前缀: 不需要则置空
     */
    private static String ENTITY_IGNORE_PREFIX = "";

    // 各层文件输出到模块, 没有则置空
    /**
     * Entity.java, Mapper.java, Mapper.xml输出模块路径
     */
    private static String ENTITY_OUTPUT_MODULE = "";

    private static String DAO_OUTPUT_MODULE = "";
    /**
     * mapper.xml输出模块路径(需要注意放置的位置:默认从模块/src/main下开始)
     */
    private static String XML_OUTPUT_MODULE = "";
    /**
     * IService.java, serviceImpl.java输出模块路径
     */
    private static String SERVICE_OUTPUT_MODULE = "";
    /**
     * Controller.java输出模块路径
     */
    private static String Controller_OUTPUT_MODULE = "";


    // 各层包名
    private static String ENTITY_PATH = "/domain";
    private static String MAPPER_PATH = "/mapper";
    private static String XML_PATH = "/mapper";
    private static String SERVICE_PATH = "/service";

    private static String CONTROLLER_PATH = "/controller";


    // 数据库
    private static String username = "root";
    private static String password = "731a57188443f637";
    private static String url = "jdbc:mysql://192.168.0.102:3306/hdwallet?serverTimezone=GMT%2B8&characterEncoding=UTF-8&allowMultiQueries=true";
    private static DbType DB_TYPE = DbType.MYSQL;
    private static String driverClassName = "com.mysql.cj.jdbc.Driver";


    // 自定义输出模板和位置
    // 文件位置输出模式: file output path = projectPath + XX_OUTPUT_PATH  + File
    // XX_OUTPUT_PATH = modulePath + packagePath
    final static String PROJECT_PATH = System.getProperty("user.dir") + File.separator + SERVICE_NAME;

    /**
     * entity模板位置
     */
    private static String ENTITY_TEMPLATE = "/templates/entity.java.ftl";

    /**
     * entity类输出位置
     */
    private static String ENTITY_OUTPUT_PATH = DAO_OUTPUT_MODULE + "/src/main/java" + PARENT_PACKAGE_PATH + DAO_OUTPUT_MODULE + ENTITY_PATH;
    /**
     * mapper.xml 模板
     */
    private static String XML_TEMPLATE = "/templates/mapper.xml.ftl";
    /**
     * mapper 输出位置
     */
    private static String XML_OUTPUT_PATH = DAO_OUTPUT_MODULE + "/src/main/resources" + XML_OUTPUT_MODULE + XML_PATH;
    /**
     * mapper.java模板
     */
    private static String MAPPER_TEMPLATE = "/templates/mapper.java.ftl";
    /**
     * mapper.java 输出位置
     */
    private static String MAPPER_OUTPUT_PATH = DAO_OUTPUT_MODULE + "/src/main/java" + PARENT_PACKAGE_PATH + DAO_OUTPUT_MODULE + MAPPER_PATH;

    /**
     * provider.java模板
     */
    private static String PROVIDER_TEMPLATE = "/templates/provider.java.ftl";
    /**
     * provider.java 输出位置
     */
    private static String PROVIDER_OUTPUT_PATH = DAO_OUTPUT_MODULE + "/src/main/java" + PARENT_PACKAGE_PATH + DAO_OUTPUT_MODULE + MAPPER_PATH;


    /**
     * service输出模板
     */
    private static String SERVICE_TEMPLATE = "/templates/service.java.ftl";
    private static String SERVICE_OUTPUT_PATH = SERVICE_OUTPUT_MODULE + "/src/main/java" + PARENT_PACKAGE_PATH + SERVICE_OUTPUT_MODULE + SERVICE_PATH;
    /**
     * serviceImpl输出模板
     */
    private static String SERVICE_IMPL_TEMPLATE = "/templates/serviceImpl.java.ftl";
    private static String SERVICE_IMPL_OUTPUT_PATH = SERVICE_OUTPUT_MODULE + "/src/main/java" + PARENT_PACKAGE_PATH + SERVICE_OUTPUT_MODULE + SERVICE_PATH + MODULE_NAME + "/impl";
    /**
     * controller输出模板
     */
    private static String CONTROLLER_TEMPLATE = "/templates/controller.java.ftl";
    private static String CONTROLLER_OUTPUT_PATH = Controller_OUTPUT_MODULE + "/src/main/java" + PARENT_PACKAGE_PATH + Controller_OUTPUT_MODULE + CONTROLLER_PATH;


    public static void main(String[] args) {

        // 全局配置
        GlobalConfig globalConfig = globalConfig();
        // 数据源配置
        DataSourceConfig dataSourceConfig = dataSourceConfig();
        // 策略配置
        StrategyConfig strategyConfig = strategyConfig();
        // 包配置
        PackageConfig packageConfig = packageConfig();
        // 模板配置
        TemplateConfig templateConfig = templateConfig();
        // 自定义配置
        InjectionConfig injectionConfig = injectionConfig();

        // 执行
        new AutoGenerator().setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                // 因为使用了自定义模板,所以需要把各项置空否则会多生成一次
                .setTemplate(templateConfig)
                // 使用的模板引擎，如果不是默认模板引擎则需要添加模板依赖到pom
                .setTemplateEngine(new FreemarkerTemplateEngine())
                .setCfg(injectionConfig)
                .execute();
    }

    /**
     * 全局配置
     */
    private static GlobalConfig globalConfig() {

        return new GlobalConfig()
                // 打开文件
                .setOpen(false)
                // 文件覆盖
                .setFileOverride(true)
                .setOutputDir(PROJECT_PATH)
                // 开启activeRecord模式
                .setActiveRecord(true)
                // XML ResultMap: mapper.xml生成查询映射结果
                .setBaseResultMap(true)
                // XML ColumnList: mapper.xml生成查询结果列
                .setBaseColumnList(true)
                // swagger注解; 须添加swagger依赖
                .setSwagger2(true)
                // 作者
                .setAuthor(AUTHOR)
                .setServiceName("%sService");
        // 设置实体类名称
        //.setEntityName("");
    }

    /**
     * 数据源配置
     */
    private static DataSourceConfig dataSourceConfig() {
        return new DataSourceConfig()
                // 数据库类型
                .setDbType(DB_TYPE)
                // 连接驱动
                .setDriverName(driverClassName)
                // 地址
                .setUrl(url)
                // 用户名
                .setUsername(username)
                // 密码
                .setPassword(password)
                .setTypeConvert(new MySqlTypeConvert() {
                    @Override
                    public IColumnType processTypeConvert(GlobalConfig config, String fieldType) {
                        return use(fieldType)
                                .test(containsAny(new CharSequence[]{"char", "text", "json", "enum"}).then(DbColumnType.STRING))
                                .test(contains("bigint").then(DbColumnType.LONG))
                                .test(containsAny(new CharSequence[]{"tinyint(1)", "bit(1)"}).then(DbColumnType.BOOLEAN))
                                .test(containsAny("tinyint", "bit").then(DbColumnType.BYTE))
                                .test(contains("int").then(DbColumnType.INTEGER))
                                .test(contains("decimal").then(DbColumnType.BIG_DECIMAL))
                                .test(contains("clob").then(DbColumnType.CLOB))
                                .test(contains("blob").then(DbColumnType.BLOB))
                                .test(contains("binary").then(DbColumnType.BYTE_ARRAY))
                                .test(contains("float").then(DbColumnType.FLOAT))
                                .test(contains("double").then(DbColumnType.DOUBLE))
                                .test(containsAny(new CharSequence[]{"date", "time", "year"}).then(t -> toDateType(config, t)))
                                .or(DbColumnType.STRING);
                    }
                })
                ;
    }



    /**
     * 策略配置
     */
    private static StrategyConfig strategyConfig() {
        return new StrategyConfig()
                // 表名生成策略：下划线连转驼峰
                .setNaming(NamingStrategy.underline_to_camel)
                // 表字段生成策略：下划线连转驼峰
                .setColumnNaming(NamingStrategy.underline_to_camel)
                // 需要生成的表
                .setInclude(TABLES)
                // 生成controller
                .setRestControllerStyle(true)
                // 去除表前缀
                .setTablePrefix(ENTITY_IGNORE_PREFIX)
                // controller映射地址：驼峰转连字符
                .setControllerMappingHyphenStyle(true)
                // 是否启用builder 模式
                .setChainModel(true)
                // 是否为lombok模型; 需要lombok依赖
                .setEntityLombokModel(true)
                // 生成实体类字段注解
                .setEntityTableFieldAnnotationEnable(true);
    }

    /**
     * 包配置
     * 设置包路径用于导包时使用，路径示例：com.path
     */
    private static PackageConfig packageConfig() {

        String entity = ENTITY_OUTPUT_MODULE + ENTITY_PATH + MODULE_NAME + "/";
        String mapper = DAO_OUTPUT_MODULE + MAPPER_PATH + MODULE_NAME + "/";
        String xml = DAO_OUTPUT_MODULE + XML_PATH + MODULE_NAME + "/";
        String service = SERVICE_OUTPUT_MODULE + SERVICE_PATH + MODULE_NAME + "/";
        String serviceImpl = SERVICE_OUTPUT_MODULE + SERVICE_PATH + MODULE_NAME + "/impl" + "/";
        String controller = Controller_OUTPUT_MODULE + CONTROLLER_PATH + MODULE_NAME + "/";

        return new PackageConfig()
                // 父包名
                .setParent(PARENT_PACKAGE_PATH.replace('/', '.').substring(1))
                .setEntity(entity.replace('/', '.').substring(1, entity.length() - 1))
                .setMapper(mapper.replace('/', '.').substring(1, mapper.length() - 1))
                .setXml(xml.replace('/', '.').substring(1, xml.length() - 1))
                .setService(service.replace('/', '.').substring(1, service.length() - 1))
                .setServiceImpl(serviceImpl.replace('/', '.').substring(1, serviceImpl.length() - 1))
                .setController(controller.replace('/', '.').substring(1, controller.length() - 1));

    }

    /**
     * 模板配置
     */
    private static TemplateConfig templateConfig() {
        return new TemplateConfig()
                // 置空默认实现，使用自定义模板
                .setEntity(null)
                .setXml(null)
                .setMapper(null)
                .setService(null)
                .setServiceImpl(null)
                .setController(null);
    }

    /**
     * 自定义配置
     */
    private static InjectionConfig injectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                // 注入配置
            }
        }    // 自定义输出文件
                .setFileOutConfigList(fileOutConfigList());
    }

    /**
     * 自定义输出文件配置
     */
    private static List<FileOutConfig> fileOutConfigList() {
        List<FileOutConfig> list = new ArrayList();
        // 当前项目路径
        final String projectPath = System.getProperty("user.dir") + "/" + SERVICE_NAME;

        // 实体类文件输出
        list.add(new FileOutConfig(ENTITY_TEMPLATE) {
            @Override
            public String outputFile(TableInfo tableInfo) {

                String projectPath1 = System.getProperty("user.dir") + "/" + SERVICE_NAME_CORE;
                return projectPath1 + ENTITY_OUTPUT_PATH + MODULE_NAME + "/" + tableInfo.getEntityName() + StringPool.DOT_JAVA;
            }
        });
        return list;
    }

    /**
     * 判断文件是否存在
     *
     * @param path 路径
     * @return
     */
    private static boolean isExists(String path) {
        File file = new File(path);
        return file.exists();
    }

    /**
     * 这个分支构建器用于构建用于支持 {@link String#contains(CharSequence)} 的分支
     *
     * @param value 分支的值
     * @return 返回分支构建器
     * @see #containsAny(CharSequence...)
     */
    private static BranchBuilder<String, IColumnType> contains(CharSequence value) {
        return BranchBuilder.of(s -> s.contains(value));
    }

    /**
     * @see #contains(CharSequence)
     */
    private static BranchBuilder<String, IColumnType> containsAny(CharSequence... values) {
        return BranchBuilder.of(s -> {
            for (CharSequence value : values) {
                if (s.contains(value)) return true;
            }
            return false;
        });
    }

    /**
     * 使用指定参数构建一个选择器
     *
     * @param param 参数
     * @return 返回选择器
     */
    private static Selector<String, IColumnType> use(String param) {
        return new Selector<>(param.toLowerCase());
    }
}
