create table author
(
    id          int(10) auto_increment comment '用户主键id'
        primary key,
    username    varchar(50)  not null comment '用户名',
    password    varchar(50)  not null comment '用户密码',
    description varchar(200) not null comment '用户描述信息'
);

INSERT INTO test.author (id, username, password, description) VALUES (1, '张三', 'zhangsan', '这是一个测试用户');
INSERT INTO test.author (id, username, password, description) VALUES (2, '李四', 'zhangsan', '这是一个测试用户');
INSERT INTO test.author (id, username, password, description) VALUES (4, '赵六', 'zhangsan', '这是一个测试用户');
INSERT INTO test.author (id, username, password, description) VALUES (5, '王二', 'wanger', '第一次修改数据');
INSERT INTO test.author (id, username, password, description) VALUES (6, '王二', 'wanger', '第一次修改数据');
INSERT INTO test.author (id, username, password, description) VALUES (7, '王二', 'wanger', '第一次修改数据');
INSERT INTO test.author (id, username, password, description) VALUES (8, '王二', 'wanger', '第一次修改数据');
INSERT INTO test.author (id, username, password, description) VALUES (9, '王二', 'wanger', '第一次修改数据');