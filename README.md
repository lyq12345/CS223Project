# CS223Project

##数据库配置
先创建3个database

数据库名：leader, follower1, follower2

在每一个db中执行：
```
create table users
(
    id       int not null
        constraint users_pk
            primary key,
    username varchar(200),
    password varchar(200)
);
INSERT INTO public.users (id, username, password) VALUES (1, 'zhangsan', '123');
INSERT INTO public.users (id, username, password) VALUES (2, 'lisi', '1234');
INSERT INTO public.users (id, username, password) VALUES (3, 'wangwu', '12345');
```

修改db连接配置：
src/main/resources/application.yml

切换数据源：
在service类或方法前加上注解`@DS("dbsouceName")`

测试类在src/test/java/com/team8/cs223project/Cs223ProjectApplicationTests.java里


