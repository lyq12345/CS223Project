# CS223Project

##数据库配置
数据库名：leader, follower1, follower2
```
create table users
(
    id       int not null
        constraint users_pk
            primary key,
    username varchar(200),
    password varchar(200)
);
```
```
create table users
INSERT INTO public.users (id, username, password) VALUES (1, 'zhangsan', '123');
INSERT INTO public.users (id, username, password) VALUES (2, 'lisi', '1234');
INSERT INTO public.users (id, username, password) VALUES (3, 'wangwu', '12345');
```
