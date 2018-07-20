package com.gupao.dal.config;

import com.github.pagehelper.PageInterceptor;
import com.gupao.dal.typehandlers.GPTypeHandler;
import com.gupao.dal.typehandlers.TestTypeHandle;
import org.apache.ibatis.executor.loader.cglib.CglibProxyFactory;
import org.apache.ibatis.executor.loader.javassist.JavassistProxyFactory;
import org.apache.ibatis.type.TypeHandler;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Primary;
import com.gupao.dal.enums.TestEnum;
import com.gupao.dal.plugins.TestPlugin;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.EnumOrdinalTypeHandler;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by James
 * Description:
 */
@Configuration
@MapperScan(basePackages = "com.gupao.dal.dao")
@EnableTransactionManagement(proxyTargetClass = true)
// managed 方式使用 mybatis （通过 java configuration 类） 配置datasource，
// 类似编程式里的负责解析mybatis xml的 org.apache.ibatis.session.Configuration
// 工作中使用方式：分析业务 -> 定义表 -> generator 生成我们需要的类
public class MybatisConfig {
    @Autowired
    @Qualifier("dataSource")
    public DataSource dataSource;


    @Lazy(false)
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory localSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{new TestTypeHandle()});
//        sqlSessionFactoryBean.setTypeHandlersPackage("com.gupao.dal.typehandles");
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{new TestPlugin()}); //一次将所有plugin都传入
        // sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor()}); //一次将所有plugin都传入
        SqlSessionFactory factory = sqlSessionFactoryBean.getObject();
        //lazy loading switch
        factory.getConfiguration().setLazyLoadingEnabled(true);  // 对应编程式里的 mybatis xml 配置文件
        factory.getConfiguration().setAggressiveLazyLoading(false);
        // factory.getConfiguration().setProxyFactory(new CglibProxyFactory());
        factory.getConfiguration().setProxyFactory(new JavassistProxyFactory());
        return factory;
    }

    private PageInterceptor pageInterceptor() {
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.put("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);
        return pageInterceptor;
    }

    @Primary
    @Lazy(false)
    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate() throws Exception {
        return new SqlSessionTemplate(localSessionFactoryBean(), ExecutorType.SIMPLE);
    }

    @Lazy(false)
    @Bean(name = "batchSst")
    public SqlSessionTemplate batchSst() throws Exception {
        return new SqlSessionTemplate(localSessionFactoryBean(), ExecutorType.BATCH);
    }

    @Bean(name = "txManager")
    public DataSourceTransactionManager dataSourceTransactionManager() {
        DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
        dataSourceTransactionManager.setDataSource(dataSource);
        return dataSourceTransactionManager;
    }
}
