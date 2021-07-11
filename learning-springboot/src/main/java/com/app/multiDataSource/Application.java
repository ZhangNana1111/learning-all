package com.app.multiDataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author ZhangNana
 * @DATE 2021/7/10 22:08
 * @Version 1.0
 */
@SpringBootApplication
public class Application  {

    private Logger logger = LoggerFactory.getLogger(Application.class);


    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }


    /*@Override
    public void run(String... args) throws Exception {
        logger.info("[run][获得数据源：{}]",ordersDataSource.getClass());
        Connection connection = ordersDataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("select id,username from ums_admin");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<User> userList = new ArrayList<>();
        if (resultSet.next()){
            User user = new User();
            String id = resultSet.getString("id");
            String username = resultSet.getString("username");
            user.setId(id);
            user.setUsername(username);
            userList.add(user);
        }
        userList.stream().forEach(x -> {
            System.out.println("orderDataSource"+ x.getId() + " ---- " + x.getUsername());
        });
        resultSet.close();
        connection.close();

        Connection connection1 = ordersDataSource.getConnection();
        PreparedStatement preparedStatement1 = connection1.prepareStatement("select id,username from ums_admin");
        ResultSet resultSet1 = preparedStatement1.executeQuery();
        List<User> userList1 = new ArrayList<>();
        while (resultSet1.next()){
            User user = new User();
            String id = resultSet1.getString("id");
            String username = resultSet1.getString("username");
            user.setId(id);
            user.setUsername(username);
            userList1.add(user);
        }
        userList1.stream().forEach(x -> {
            System.out.println("usersDataSource"+ x.getId() + " ---- " + x.getUsername());
        });
        resultSet1.close();
        connection1.close();
        logger.info("[run][获得数据源：{}]",usersDataSource.getClass());
    }*/


}
