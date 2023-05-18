import com.dragon.entity.Depart;
import com.dragon.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import ognl.Ognl;
import ognl.OgnlException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.parsing.XPathParser;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author sunshilong
 * @version 1.0
 * @date 2023/5/17
 */
@Slf4j
public class ToolTest {
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
        Employee e = new Employee("lilaoba", 18, new Depart("CLP"));
        Object deptName = Ognl.getValue("dept.name", e);
        Object ifNull = Ognl.getValue("dept.name !=null and dept.name == 'CLP'", e);
        log.info("deptName -> {}", ifNull);
        log.info("deptName -> {}", deptName);
    }
}
