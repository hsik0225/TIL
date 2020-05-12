### Install & Boot

```docker
> docker pull mysql:5.7
> docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
mysql               5.7                 f965319e89de        9 days ago          448MB

> docker run -d --name sql -p 3306:3306 -v /Users/kimhyunsik/Desktop/docker/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=asdqwe1! mysql --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

> docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED              STATUS              PORTS                               NAMES
070ab2bff1b6        mysql:5.7           "docker-entrypoint.s…"   About a minute ago   Up About a minute   0.0.0.0:3306->3306/tcp, 33060/tcp   baseball_mysql

> docker exec -it sql /bin/bash
```

### Setting

- Vim

```docker
> apt-get update
> apt-get install vim -y
```

- Create DB, User

```sql
> mysql -u root -p
mysql> update mysql.user set plugin='mysql_native_password' where user='root';
mysql> ALTER user 'root'@'%' IDENTIFIED WITH mysql_native_password BY '변경할비밀번호';
mysql> flush privileges;

mysql> CREATE USER 'ever'@`%` IDENTIFIED BY 'asdqwe1!';
mysql> create database mydb;
mysql> show databases;
mysql> GRANT ALL ON mydb.* TO 'ever'@'%' WITH GRANT OPTION;
mysql> FLUSH PRIVILEGES;
```

- /etc/mysql/my.cnf

```sql
[client]
default-character-set = utf8

[mysqld]
init_connect = SET collation_connection = utf8_general_ci
init_connect = SET NAMES utf8
character-set-server = utf8
collation-server = utf8_general_ci
lower_case_table_names = 1

[mysqldump]
default-character-set = utf8

[mysql]
default-character-set = utf8
```

```sql
mysql> status

...
Server characterset:	utf8
Db     characterset:	utf8
Client characterset:	utf8
Conn.  characterset:	utf8
...
