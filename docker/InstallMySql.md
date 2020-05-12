### Install & Boot

자신이 conf파일을 저장하고자 하는 폴더를 생성한다
나는 Desktop/docker/mysql/conf 폴더를 생성했다

여기에 docker mysql에 적용시킬 cnf 파일을 생성한다

```
> cd Desktop/docker/mysql/conf

> vi custom.cnf
```

> 복사, 붙여넣기 후 저장(:wq)
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

docker-compose.yml 파일을 이용하여 자동으로 컨테이너를 생성해보자
```
version: '3.8'

services:
  db:
      container_name: sql
	      image: mysql:5.7
		      restart: always
			      ports:
				       - 3306:3306
					       volumes:
						        - /Users/kimhyunsik/Desktop/docker/mysql/conf:/etc/mysql/conf.d
								    environment:
									     - MYSQL_ROOT_PASSWORD=asdqwe1!
										      - MYSQL_DATABASE=baseball
											       - MYSQL_USER=ever
												        - MYSQL_PASSWORD=asdqwe1!
```
이 yml 파일을 자신이 원하는 폴더에 생성한다

> -d 옵션
docker run 에서 d옵션으로  백그라운드(터미널을 꺼도 실행되어 있는 상태, d옵션이 없으면 터미널이 꺼지면 docker도 꺼진다) 에서 실행하였었다

docker-compose에서도 -d 옵션으로 실행한다

```docker
docer-compose up -d
```

```docker
> docker pull mysql:5.7
> docker images
REPOSITORY          TAG                 IMAGE ID            CREATED             SIZE
mysql               5.7                 f965319e89de        9 days ago          448MB

```

## docker-compse.yml
여기서 yml 파일로 실행한다

```dcoker
docker-compose up -d
```

### docker-run
yml 파일을 이용하지 않고 run으로 싫행한다
```
> docker run -d --name sql -p 3306:3306 -v /Users/kimhyunsik/Desktop/docker/mysql/conf:/etc/mysql/conf.d -e MYSQL_ROOT_PASSWORD=asdqwe1! mysql --character-set-server=utf8mb4 --collation-server=utf8mb4_unicode_ci

> docker ps
CONTAINER ID        IMAGE               COMMAND                  CREATED              STATUS              PORTS                               NAMES
070ab2bff1b6        mysql:5.7           "docker-entrypoint.s…"   About a minute ago   Up About a minute   0.0.0.0:3306->3306/tcp, 33060/tcp   baseball_mysql
```

생성된 컨테이너를 실행시킨다
```
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
mysql> ALTER user 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'asdqwe1!';
mysql> flush privileges;

mysql> CREATE USER 'ever'@`%` IDENTIFIED BY 'asdqwe1!';
mysql> create database mydb;
mysql> show databases;
mysql> GRANT ALL ON *.* TO 'ever'@'%' WITH GRANT OPTION;
mysql> FLUSH PRIVILEGES;
```

```sql
mysql> status

...
Server characterset:	utf8
Db     characterset:	utf8
Client characterset:	utf8
Conn.  characterset:	utf8
...

- 제대로 cnf가 적용되었는지 확인
mysql> show varialbes like 'lower_case_table_names';

기존 Value 0
적용 Value 1
