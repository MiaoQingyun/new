import Utils.SqlSessionUtils;
import com.example.Mapper.TeachMapper;
import com.example.Pojo.Teach;
import com.example.Pojo.TeachExample;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.example.Mapper.UserMapper;
import org.example.Pojo.User;
import org.example.Pojo.User1;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class MybatisTest {
    @Test
    public void test1() throws Exception {

        //加载核心配置文件
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        // 获取SqlSessionFactoryBuilder
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        // 获取SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        //主要获取mybatis提供的数据库操作对象 获取SqlSession

        SqlSession session = sqlSessionFactory.openSession(true);

        // 获取mapper接口对象，这个过程由mybatis自己做，为你实现了一个实现类，

        UserMapper mapper = session.getMapper(UserMapper.class); // 底层会自动为我们创建好mapper接口的实现类
        // 测试功能
        int i = mapper.insertUser();
//        int result = mapper.insertUser(new User(null, "qing", "123456"));
        // 提交事务
        // session.commit();
        //
//         提交事务
        session.commit();
        System.out.println("result = " + i);
        session.close();
    }

    @Test
    public void delete() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class); // 底层会自动为我们创建好mapper接口的实现类
        // 测试功能
        mapper.delete();

    }

    @Test
    public void selete() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class); // 底层会自动为我们创建好mapper接口的实现类
        // 测试功能
        User user = mapper.getUser();
        System.out.println(user);
    }

    @Test
    public void seleteFrom() throws IOException {
        InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(is);
        SqlSession session = sqlSessionFactory.openSession(true);
        UserMapper mapper = session.getMapper(UserMapper.class);
        // 测试功能
        List<User> user = mapper.getFromUser();
//        System.out.println(user);
        user.forEach(a -> System.out.println(a));
    }

    @Test
    //使用Utils工具类获取Sqlsession对象
    public void seleteFrom1() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();

        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        // 测试功能
        List<User> user = mapper.getFromUser();
        user.forEach(a -> System.out.println(a));
    }

    @Test
    //Mybatis对象获取参数值只有两种：
    //#{}:占位符赋值
    //${}:字符串拼接
    public void seleteFrom2() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //传入多个参数，以map的形式存储，以arg为键，参数为值
        List<User> user = mapper.getUserName(2, "miao");
        user.forEach(a -> System.out.println(a));
    }

    @Test
    //传递map对象
    public void seleteFrom3() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //传入多个参数，以map的形式存储，以arg为键，参数为值
        Map<String, Object> map = new HashMap<>();
        //使用键值对
        map.put("id", "2");
        map.put("name", "miao");
        List<User> user = mapper.getUserMap(map);
        user.forEach(a -> System.out.println(a));
    }

    @Test
    //传递对象,从中获取属性
    public void seleteUser4() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        //传入多个参数，以map的形式存储，以arg为键，参数为值
        User user = new User();
        user.setId(2);
        user.setName("miao");
        List<User> list = mapper.getUser1(user);
        list.forEach(a -> System.out.println(a));
    }

    @Test
    //在声明方法的参数中使用注解,绑定属性
    public void seleteUser5() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> list = mapper.getUser2(2, "miao");
        list.forEach(a -> System.out.println(a));
    }

    @Test
    //将查询到的单条信息以map的形式返回
    public void seleteMap() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        System.out.println(mapper.getUserMap1(2));

    }

    @Test
    //将查询到的信息以List<map>的形式返回
    public void seleteMap2() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<Map<String, Object>> map = mapper.getUserMap2(18);
        System.out.println(map);

    }

    @Test
    //模糊查询，使用%#{username}，xml文件中的格式为 “#”{username}“#”
    public void selete10() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> i = mapper.getUserName10("ing");
        System.out.println(i);

    }

    @Test
    //批量删除，xml中要使用${},里面可以输入字符串“1，2，3，”,使用$，是为了sql语句不带 ’‘
    public void delete1() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        mapper.deleteUserId("2,3,4");

    }

    @Test
    //查询动态表名
    public void seleteUsername14() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> users = mapper.seleteUser11("User");
        System.out.println(users);

    }

    @Test
    //设置自增主键
    public void insetUser() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setAge(19);
        user.setName("liao");
        user.setPassword("100");
        mapper.insetUser(user);


    }

    @Test
    //设置自增主键
    public void select() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User1> user1 = mapper.getFromUser1();
        System.out.println(user1);

    }

    @Test
    //多表查询，有多个实体类
    public void getUserId() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserId(5);
        System.out.println(user);

    }

    @Test
    //多表查询，有多个实体类,使用了ass标签
    public void getUserId1() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserId1(5);
        System.out.println(user);
    }

    //分布式查询
    @Test
    public void getUserId2() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = mapper.getUserOne(5);
        System.out.println(user);
    }

    @Test
    //多表查询，有多个实体类,使用了ass标签
    public void getUserId3() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> user = mapper.getUser4(null, 18);
        System.out.println(user);
    }

    @Test
    //批量删除，使用了foreach，借助数组
    public void deleteUser1() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        Integer[] id = {1, 2, 3, 4, 5, 6};
        mapper.deleteUser(id);
    }

    @Test
    //批量添加使用了数组
    public void insectUser1() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        User user = new User();
        user.setName("聊");
        User user1 = new User();
        user1.setName("缪");
        List<User> user3 = Arrays.asList(user, user1);

        mapper.insetUser1(user3);
    }

    @Test
    //批量添加使用了数组
    public void getUser11() throws IOException {
        SqlSession sqlSession = SqlSessionUtils.getsqlSession();
        UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        List<User> user11 = mapper.getUser12();
        System.out.println(user11);
    }

    @Test
    public void getUser12() {
        try {
            InputStream is = Resources.getResourceAsStream("mybatis-config.xml");
            SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(is);
            SqlSession sqlSession = factory.openSession(true);
            TeachMapper mapper = sqlSession.getMapper(TeachMapper.class);
            //无条件查询，参数是null就代表返回所有
            List<Teach> teaches = mapper.selectByExample(null);
//            teaches.forEach(System.out::println);
            //QBC查询条件：
            //先调用条件类，通过方法设定好参数跟条件
//            TeachExample teachExample=new TeachExample();
//            teachExample.createCriteria().andAgeEqualTo(16);
            //再将条件的值传递给mapper接口
//            List<Teach> teaches = mapper.selectByExample(teachExample);
            Page<Object> objects = PageHelper.startPage(2, 1);
            teaches.forEach(System.out::println);
            System.out.println(objects);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


