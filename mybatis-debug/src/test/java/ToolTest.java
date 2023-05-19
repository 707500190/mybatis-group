import com.dragon.entity.Depart;
import com.dragon.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import ognl.Ognl;
import ognl.OgnlException;
import org.apache.ibatis.executor.*;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.parsing.XPathParser;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author sunshilong
 * @version 1.0
 * @date 2023/5/17
 */
@Slf4j
public class ToolTest {

    private Configuration configuration;
    private Connection connection;
    private JdbcTransaction jdbcTransaction;

    @Before
    public void init() throws SQLException, IOException {

        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);

        SqlSessionFactoryBuilder factoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory build = factoryBuilder.build(inputStream);
        configuration = build.getConfiguration();
        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/lock",
                "root", "root");
        jdbcTransaction = new JdbcTransaction(connection);
    }


    @Test
    public void testXPathParser() throws IOException {
        String resource = "mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        XPathParser x = new XPathParser(inputStream);
        x.evalNode("/configuration/properties/properties");
        System.out.println();
    }

    @Test
    public void ognlTest() throws OgnlException {
        Employee e = new Employee("李老八", 18, new Depart("CLP"));
        Object deptName = Ognl.getValue("dept.name", e);
        Object ifNull = Ognl.getValue("dept.name !=null and dept.name == 'CLP'", e);
        log.info("deptName -> {}", ifNull);
        log.info("deptName -> {}", deptName);
    }

public static String QUERY_MAPPED_STATEMENT = "com.dragon.mapper.ContentMapper.queryContent";
public static String UPDATE_MAPPED_STATEMENT = "com.dragon.mapper.ContentMapper.update";
    @Test
    public void simpleTest() throws SQLException {
        SimpleExecutor executor = new SimpleExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement(QUERY_MAPPED_STATEMENT);
        executor.doQuery(ms, "10", RowBounds.DEFAULT,
                SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));

        executor.doQuery(ms, "10", RowBounds.DEFAULT,
                SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
    }

    @Test
    public void reuseTest() throws SQLException {
        ReuseExecutor executor = new ReuseExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement(QUERY_MAPPED_STATEMENT);
        executor.doQuery(ms, "10", RowBounds.DEFAULT,
                SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));

        executor.doQuery(ms, "10", RowBounds.DEFAULT,
                SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
    }

    @Test
    public void batchTest() throws SQLException {
        BatchExecutor executor = new BatchExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement(QUERY_MAPPED_STATEMENT);
        executor.doQuery(ms, "李老八", RowBounds.DEFAULT,
                SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));

        executor.doQuery(ms, "李老八", RowBounds.DEFAULT,
                SimpleExecutor.NO_RESULT_HANDLER, ms.getBoundSql(10));
    }


    //只针对修改有作用
    @Test
    public void batchTest2() throws SQLException {
        BatchExecutor executor = new BatchExecutor(configuration, jdbcTransaction);
        MappedStatement ms = configuration.getMappedStatement(UPDATE_MAPPED_STATEMENT);
        Map<String, Object> map  = new HashMap<String, Object>();
        Map<String, Object> map2  = new HashMap<String, Object>();
        map.put("id", 1);
        map.put("title", "李老八");
        map.put("id", 2);
        map.put("title", "李老八2");
        executor.doUpdate(ms, map);
        executor.doUpdate(ms, map);
    }

    @Test
    public void cacheExecutorTest() throws SQLException {
        SimpleExecutor executor = new SimpleExecutor(configuration, jdbcTransaction);
        CachingExecutor cachingExecutor = new CachingExecutor(executor); //二级缓存相关逻辑
        MappedStatement ms = configuration.getMappedStatement(QUERY_MAPPED_STATEMENT);
        Map<String, Object> map  = new HashMap<String, Object>();
        cachingExecutor.query(ms, "10", RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
        cachingExecutor.query(ms, "10", RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER);
    }


}
