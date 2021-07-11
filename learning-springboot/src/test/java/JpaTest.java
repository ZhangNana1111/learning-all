import com.app.multiDataSource.Application;
import com.app.springbootjpa.JpaApplication;
import com.app.springbootjpa.repository.UserRepository;
import com.app.springbootjpa.service.UserInfoService;
import com.app.springbootjpa.vo.UserInfo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

/**
 * @Author ZhangNana
 * @DATE 2021/7/11 17:32
 * @Version 1.0
 */
@SpringBootTest(classes = JpaApplication.class)
public class JpaTest {

    @Resource
    private UserInfoService userInfoService;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRepository() throws Exception{

       /* Optional<UserInfo> byId = userInfoService.findById(1);
        System.out.println(byId.get());*/

        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("王耀3");
        userInfo.setAddress("上海浦东新区寿光路3");
        userRepository.save(userInfo);

       /* List<UserInfo> all = userInfoService.findAll();
        System.out.println(all.size());*/



        // 分页查询
        PageRequest pageRequest = PageRequest.of(1, 3);
        Page<UserInfo> all = userInfoService.findAll(pageRequest);
        System.out.println(all.getTotalPages() + ""+ all.getSize());



    }
}
