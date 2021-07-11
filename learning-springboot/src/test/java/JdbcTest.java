import com.app.multiDataSource.Application;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import com.app.multiDataSource.po.User;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Author ZhangNana
 * @DATE 2021/7/10 21:54
 * @Version 1.0
 */

@SpringBootTest(classes = Application.class)
public class JdbcTest {

    @Resource
    private JdbcTemplate jdbcTemplate;

    @Test
    public void mysqlTest(){
        String sql = "select id,username from ums_admin";
        List<User> userList = jdbcTemplate.query(sql, new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user = new User();
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));

                return user;
            }
        });
        System.out.println("查询成功");
        for (User user : userList) {
            System.out.println("id:" + user.getId() + ",name:" + user.getUsername());
        }
    }
    
}
