## 安装一下lombok插件


## VIP MyBatis上课分支为vip-mybatis

**集成式使用 mybatis**

gupao-generator 子工程为 mybatis generator 示例。

gupao-dao 子工程的BlogMapper.xml 文件有联合查询示例（使用相同的sqlSession
执行不同的Mapper接口类，入口调用 BlogMapper.selectBlogAuthor，然后调用AuthorMapper，
查出Author信息，挂在BlogMapper namespace对应的二级缓存下，这样通过AuthorMapper更新了二级缓存，
BlogMapper下的二级缓存不会更新 ）

gupao-web 子工程的 TestMapperTest#insert 方法为一个测试用例
将 MybatisConfig.localSessionFactoryBean()中 sqlSessionFactoryBean.setPlugins(new Interceptor[]{new TestPlugin()});
语句去注释，然后运行 TestMapperTest.select ，用来测试 plugin

TestMapperTest.example 方法为通过Example类拼装，物理分页（example.setLimitClause("0,10");）。
通过generator 生成的Example类没有setLimitCause方法，是手工加入的。
参考TestExample对orderByClause成员的处理，类似加入对limitCause的处理，从而实现分页，并在TestMapper.xml里，
参照orderByClause，加入limitCause。（此方法不适合Oracle数据库（Oracle数据库没有limit 0,10这样的短语限制，
所以Mybatis生成的代码不加limit限制的原因）

##example类
Example 类的使用，不需要定义 mapper xml 文件，直接设置 Example 类相应字段即可
Example类不好的地方：不需要写sql，自动构造sql，建的索引和where语句可能不一致，影响性能
Example类通常拿来做测试

org.apache.ibatis.executor.resultset.DefaultResultSetHandler#handleRowValues() -> handleRowValuesForSimpleResultMap()  -> skipRows() ，
该方法实现，是基于ResultSet，然后按照rowBounds中的offset和limit，取出ResultSet里的记录（ResultSet是数据
库返回给我们的数据，即数据库还是没有分页）。

##分页插件
PageHelper分页插件使用方法(它利用了Mybatis的Plugin机制做的)
```java
private PageInterceptor pageInterceptor() {
    PageInterceptor pageInterceptor = new PageInterceptor();
    Properties properties = new Properties();
    properties.put("helperDialect", "mysql");  //指定是mysql数据库
    pageInterceptor.setProperties(properties);
    return pageInterceptor;
}
    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory localSessionFactoryBean() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeHandlers(new TypeHandler[]{new TestTypeHandle()});
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageInterceptor()});
        SqlSessionFactory factory = sqlSessionFactoryBean.getObject();
        //lazy loading switch
        factory.getConfiguration().setLazyLoadingEnabled(true);
        factory.getConfiguration().setAggressiveLazyLoading(false);
        factory.getConfiguration().setProxyFactory(new CglibProxyFactory());
        return factory;
    }
```
然后执行 com.gupao.test.dal.TestMapperTest.pagination分页插件 用例。

##批量插入
###最土方式
批量插入最土的方法（参考 TestMapperTest#insertBatch() 方法）：
for循环，一个一个插入，每次插入都会需要一次IO，都要一次sqlSession，性能最差。
（被别人打电话说十句话，打十个电话）
###动态SQL
动态SQL，通过foreach，拼接SQL（参考gupao-dal\src\main\resources\com\gupao\dal\dao\TestMapper.xml
中的 insertBatch 的定义(对应mapper方法原型：TestMapper.insertBatch(List<Test> tests))：
对应用例：com.gupao.test.dal.TestMapperTest.insertBatch拼Sql
```xml
  <insert id="insertBatch" parameterType="list">
    insert into test (id, nums, name
    )
    values
    <foreach collection="list" item="item" separator=",">
    (#{item.id,jdbcType=INTEGER}, #{item.nums,jdbcType=INTEGER}, #{item.name,jdbcType=VARCHAR}
    )
    </foreach>
  </insert>
```
拼装的SQL： insert into test (id, nums, name ) values (?, ?, ? ) , (?, ?, ? ) , ...
###优点：
性能最好。

###缺点：
SQL长度限制：
       show variables like '%packet%'; // max_allowed_packet
       show variables like '%net_buffer%'; //net_buffer_length
       需要根据每条记录对应SQL长度大小，根据上面的mysql核心参数值，限制List大小。
       批量插入的场景，一般不需要回滚（回滚比较困难）。
       另外操作系统数据包大小有限制。
       数据库rollback缓冲区要足够大？一次发送一个SQL，所有记录

### Mybatis提供：ExecutorType.BATCH。
参考 TestMapperTest.insertBatchExType 用例。
关闭autocommit开关，手工在插入若干条记录后批量commit。
一个SqlSession，发送多个SQL，但批量提交，所以磁盘IO和第二种一样大。
该方法需要注入一个 BATCH的SqlSessionTemplate：
```java
    @Lazy(false)
    @Bean(name = "batchSst")
    public SqlSessionTemplate batchSst() throws Exception {
        return new SqlSessionTemplate(localSessionFactoryBean(), ExecutorType.BATCH);
    }
```
代码略显复杂，    
推荐使用第二种方式的批量操作。

##联合查询
分为：嵌套结果（内联，join） 和 嵌套查询（外联）

实体关系：Author 1:1  Blog 1:N Post（评论）

1:1嵌套查询样例（xml：association）：BlogMapper.xml 里的 selectBlogAuthor 定义。
测试用例：com.gupao.test.dal.BlogMapperTest.selectByAuthorId嵌套查询
Preparing: select bid, name, author_id from blog where bid = ?
Preparing: select aid, author_name from author where aid = ? 

1:N嵌套查询（xml：collection）：BlogMapper.xml 里的 selectBlogPosts 定义，对应mapper函数原型：
BlogPostsResultMap selectBlogPosts(Integer bid)
测试用例：com.gupao.test.dal.BlogMapperTest.selectBlogPosts嵌套查询
两次执行select：
Preparing: select bid, name, author_id from blog where bid = ? order by bid asc
Preparing: select pid,post_name as postName,blog_id as blogId from posts where blog_id = ?

1:1嵌套结果（xml：association，两个表join）：BlogMapper.xml 里的 selectBlogAuthor2 定义，对应mapper函数原型：
BlogResultMap selectBlogAuthor2(Integer bid)
测试用例：com.gupao.test.dal.BlogMapperTest.selectByAuthorId2
Preparing: select * from blog b,author a where bid = ? and b.author_id = a.aid

1:N嵌套结果（xml：collection，两个表join)：BlogMapper.xml 里的 selectBlogPosts2 定义，对应mapper函数
BlogPostsResultMap com.gupao.dal.dao.BlogMapper#selectBlogPosts2
测试用例：com.gupao.test.dal.BlogMapperTest.selectBlogPosts嵌套结果
Preparing: select * from blog b,posts p where b.bid = ? and b.bid = p.blog_id

嵌套结果只有一个SQL语句，不存在N+1问题，只有1:N嵌套查询时才存在。

嵌套查询N+1问题：只需要展示博客信息，博客下面的评论暂时不用展示：
测试用例： com.gupao.test.dal.BlogMapperTest.selectBlogPosts嵌套查询
此时可以启动懒加载：
        factory.getConfiguration().setLazyLoadingEnabled(true);  // 对应编程式里的 mybatis xml 配置文件
        factory.getConfiguration().setAggressiveLazyLoading(false);
        factory.getConfiguration().setProxyFactory(new CglibProxyFactory());

N:N，拆解出来是1:N。

懒加载是通过创建的代理类实现的：
通过CglibProxyFactory.EnhancedResultObjectProxyImpl#createProxy() 创建的DTO/Entity实体类的代理类方法
（另外一种是通过JavassistProxyFactory#crateProxy()方法创建的代理对象，其实也是通过CJLib创建的代理，
没有通过JDK创建动态代理，因为Entity/DTO都是实体类，不是接口，无法通过JDK创建。）
该代理的实现类是 EnhancedResultObjectProxyImpl
