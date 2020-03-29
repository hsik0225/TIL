AWS TODO & STUDY

## 루트 계정 2중 잠금 설정

## Admin 계정 만들기

[참고](https://docs.aws.amazon.com/ko_kr/IAM/latest/UserGuide/getting-started_create-admin-group.html)

1. 서울 리전 서택

2. IAM 서비스 클릭

3. 왼쪽 메뉴에서 액세스 관리자에 있는 사용자 클릭

4. 사용자 추가 클릭

5. User Details 설정
	- 사용자 이름 설정
	- AWS Managemnet Cosole 액세스 체크
	- 자동 생성된 비밀번호 체크
	- 비밀번호 재설정 필요 체크 해제

6.그룹에 사용자 추가
	1. 그룹 생성
	2. 그룹 이름을 정하고 AdminstratorAccess 체크 후 그룹 생성
	3. 생성된 그룹을 체크하고 다음


7. 태그 추가

8. 사용자 만들기

9. credentials.csv 를 다운받는다

## Power User 계정 만들기

[참고](https://docs.aws.amazon.com/ko_kr/IAM/latest/UserGuide/getting-started_create-delegated-user.html)

1. 서울 리전 서택

2. IAM 서비스 클릭

3. 왼쪽 메뉴에서 액세스 관리자에 있는 사용자 클릭

4. 사용자 추가 클릭

5. User Details 설정
	- 사용자 이름 설정
	- AWS Managemnet Cosole 액세스 체크
	- 자동 생성된 비밀번호 체크
	- 비밀번호 재설정 필요 체크 해제


6.그룹에 사용자 추가
	1. 그룹 생성
	2. 그룹 이름을 정하고 필터로 PowerUserAccess 검색, 체크 후 그룹 생성
	3. 생성된 그룹을 체크하고 다음

7. 태그 추가

8. 사용자 만들기

9. credentials.csv 를 다운받는다

## EC2 생성

0. 로그인 후 서울 리전 선택

1. 서비스에서 EC2를 선택하고 Launch Instance

2. OS 선택
	- Ubuntu 18.04 LTS(Type : Free Tier eligible)

Next

3. 인스턴스 세부 정보 구성
	- 네트워크 기본 값 설정
	- 서브넷 기본 설정 없음 설정

Next

4. 저장소 설정
	- 8GB
Next

5. Add Tags
	- 서버실에 많은 전선들에서 구별할 수 있게 태그를 붙이는 것이라고 생각하자
	- Name : Web Server
	- 관리자 	: Ever

Next

6. Configure Security Group
	- 포트를 제어하는 AWS의 기본적인 방화벽같은 개념
	- SSH를 열지 않으면 SSH로 접속이 되지 않는다
	- 0.0.0.0/0 은 모든 IP 접속을 허용한다는 의미

	1. 새 보안 그룹 생성(기존에 이미 만들었다면 기존 보안 그룹 선택)
	
	2. Security Group Name 입력

	3. SSH로 나만 접속하고 싶으므로 Source에서 내 IP로 변경

	4. Spring을 사용하니깐 8080 포트를 추가해줘야한다. 
		- Custom TCP 선택 후 8080 포트를 열어준다. 또, 내 IP로 변경

	5. 우리는 웹서버를 만들거기 때문에 HTTP, HTTPS 의 접근도 허락하여 준다
		- HTTP 선택, 80 포트
		- 누구나 접속할 수 있어야 하기 때문에 소스를 위치 무관으로 설정해준다

7. 검토 후 시작하기 버튼을 눌러 새 키 페어를 다운받는다

Review And Launch
	
## SSH 접속하기

윈도우는 SSH를 가지고 있기 땜누에 원격 제어 역할을 하는 프로그램을 깔아야 한다. Putty도 있지만, 여기서는 Xshell6를 사용한다
이 [링크](https://www.netsarang.com/ko/free-for-home-school/)에서 XShell6를 다운받는다.

1. 자신의 인스턴스의 창에서 IPv4 퍼블릭 IP를 복사한다(ex.54.238.222.246)

2. 내가 만든 인스턴스에서 오른쪽 버튼을 클릭 후 「연결」 을 누른다

3. Xshell프로그램을 실행하고 열기 버튼을 클린한다
- 여기서 세션은 웹 브라우저에서 북마크같은 역할을 해준다

4. 새로만들기를 클릭한다

5. 이름을 입력 후 호스트 부분에 아까 복사한 IP를 붙여넣기 한다

6. 메뉴에서 사용자 인증을 눌러 사용자이름과 암호를 입력한다
	- 우분투로 만들었다면 사용자 이름은 ubuntu, 우분투가 아닌 다른 리눅스라면 ec2-user
	- 방법에서 퍼블릭 키 를 선택한다
	- 사용자키 옆에 있는 「찾아보기」를 클릭하고 자신의 비밀번호 키(.per)를 가져오기로 가져온 후 확인 버튼을 누른다
	- 아래 암호는 적지 않는다

7. 알 수 없는 호스트키 라는 경고가 등장하면 수락 및 저장을 클릭한다

8. Ubuntu가 접속되었다

```bash
Connecting to 15.164.224.43:22...
Connection established.
To escape to local shell, press 'Ctrl+Alt+]'.

Welcome to Ubuntu 18.04.3 LTS (GNU/Linux 4.15.0-1057-aws x86_64)

 * Documentation:  https://help.ubuntu.com
 * Management:     https://landscape.canonical.com
 * Support:        https://ubuntu.com/advantage

  System information as of Sun Mar 29 05:19:56 UTC 2020

  System load:  0.0               Processes:           88
  Usage of /:   13.8% of 7.69GB   Users logged in:     0
  Memory usage: 14%               IP address for eth0: 172.31.43.241
  Swap usage:   0%


0 packages can be updated.
0 updates are security updates.


Last login: Sun Mar 29 05:18:44 2020 from 58.123.123.20
To run a command as administrator (user "root"), use "sudo <command>".
See "man sudo_root" for details.

ubuntu@ip-172-31-43-241:~$ ^C
```

만약 종료하고 싶다면 exit 를 입력한다

다시 접속 하고 싶다면 Xshell 왼쪽 메뉴에서 만든 세션을 더블클릭해준다

## 웹 서버 생성

1. 접속한 서버에서 `sudo apt-get install apache2` 를 입력하여 설치한다
만약 Unable to fetch some, archives, maybe run ~~ 에러가 발생한다면,

`sudo apt-get update;` 를 입력하고 다시 `sudo apt-get install apache2`를 입력한다

2. 접속을 할 때는 인스턴스의 IP나 도메인을 알아야한다
인스턴스 아래에 설명 탭에서 IP와 도메인을 알 수 있다
	IP : IPv4 퍼블릭 IP
	도메인 : 퍼블릭 DNS

3. 인스턴스의 서버가 아닌 원래 OS에서 IP나 도메인을 윈도우 탭에서 붙여넣기 해본다
![아파치](https://user-images.githubusercontent.com/56301069/77841089-14fa7e00-717e-11ea-8f83-12da12f28c25.png)

4. index.html 파일 변경
나온 html 파일은 /var/www/html 경로에 있다

이 파일을 변경해보기 위해 `cd /var/www/html`를 입력한다

5. ls 를 눌러보면 index.html 이 나온다

```bash
ubuntu@ip-172-31-43-241:/var/www/html$ ls
index.html
```

6. sudo rm -rf index.html 을 입력하여 index.html 파일을 삭제

7. 파일을 새로 만든다

```
sudo vi index.html
```

8. HTML 파일 내용 입력

```
<html>
        <body>
                Hello AWS
        </body>
</html>
```

9. 다시 인스턴스 서버가 아닌 원래 OS로 돌아와서 아까 입력했던 도메인 또는 IP를 입력한다

10. Hello AWS가 출력된다!

## 스프링 부트 띄워보기

## 