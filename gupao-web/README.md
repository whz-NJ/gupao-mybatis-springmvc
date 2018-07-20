## gupao 出品 web项目 ##

集成式使用 mybatis
<!-- 生成的表的测试类使用方法：参考 gupao-web 工程里 TestExample 的使用方法 -->
Example 类的使用，不需要定义 mapper xml 文件，直接设置 Example 类相应字段即可
Example类不好的地方：不需要写sql，自动构造sql，建的索引和where语句可能不一致，影响性能
Example类通常拿来做测试
    
    运行 TestMapperTest#insert 测试用例
    
    将 MybatisConfig.localSessionFactoryBean()中 sqlSessionFactoryBean.setPlugins(new Interceptor[]{new TestPlugin()});
    语句去注释，然后运行 TestMapperTest.select