## mybatis generator 使用手册 ##
generator 的pom不应该污染 gupao-dal（DAO层）的pom
generator 生成实体类（DAO），Mapper接口类 和Example类 以及 mapper xml 配置文件
generator 创建过程： 加maven依赖；配置 genreatorConfig.xml ；执行generator Maven 插件
实体类用lombok @Data 修饰下，免得手工写 get/set方法
生成的表的测试类使用方法：参考 gupao-web 工程里 TestExample 的使用方法
Example 类的使用，不需要定义 mapper xml 文件，直接设置 Example 类相应字段即可
Example类不好的地方：不需要写sql，自动构造sql，建的索引和where语句可能不一致，影响性能
Example类通常拿来做测试
1. 修改 src/main/resources/mybatis/generatorConfig.xml
    classPathEntry 数据库驱动jar的位置
    jdbcConnection 连接信息、用户名、密码
    targetPackage  输出路径改成自己的
    table          定义各个表的信息，一个表一个table

2. 运行mvn mybatis-generator:generate