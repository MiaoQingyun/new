package org.example.Mapper;

import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Param;
import org.example.Pojo.User;
import org.example.Pojo.User1;

import java.util.List;
import java.util.Map;

public interface UserMapper {


   /*
       1.映射文件的namespace要跟mapper 接口的类名字一致
       2.sql语句的id要个方法名一致
    */
   int insertUser();

   void delete();

   User getUser();

   List<User> getFromUser();

   List<User1> getFromUser1();
   //传入多个参数，Mybatis会以map的形式存储，以arg为键，参数为值写进sql语句中
   List<User> getUserName(Integer id, String name);

   //直接传map对象
   List<User> getUserMap(Map<String, Object> map);

   List<User> getUser1(User user);

   //使用注解的方式，形成键值对，sql语句输入就可以直接使用Param里面的值
   List<User> getUser2(@Param("id") Integer id, @Param("username") String name);

   //单条数据将获取的数据以map 形式来接收
   Map<String, Object> getUserMap1(@Param("id") Integer id);

   //将获取的数据以List<map> 形式来接收
   @MapKey("id")
//以查询到的信息中的某一个字段为键
   List<Map<String, Object>> getUserMap2(Integer id);

   //模糊查询
   List<User> getUserName10(@Param("username") String name);

   //批量删除
   void deleteUserId(@Param("id") String id);

   //查询动态表名，本质上跟查询是一样的，只不过是将#{}占位符改成了${};
   List<User> seleteUser11(@Param("username") String username);

   //设置自增主键
   void insetUser( User user);

   //将实体类跟表重新映射
   List<User> getUser11(User user);
   //多表查询
   User getUserId(@Param("userid") Integer id);
   //多表查询 使用ass标签
   User getUserId1(@Param("userid") Integer id);

   //分布式查询1.先查询员工信息
   User getUserOne(@Param("id") Integer id);

   //条件查询,注意不要在多个条件下不是最后一个条件就不要写顿号

   List<User> getUser4(@Param("name") String name,@Param("age") int age);

   //通过数组实现批量删除
   void deleteUser(@Param("id") Integer[] id);

   //使用list批量添加
   void insetUser1(@Param("users") List<User> users);
   List<User> getUser12();
}
