## Elastic IP(유로)

인스턴스를 껐다 키면 IP가 변하게 된다.

기존에 저장한 Xshell 세션에서도 변경해줘야하기 때문에 귀찮으므로 고정IP를 할당하도록 한다.

고정 IP를 사용하면 컴퓨터를 교체해도 사용자는 동일한 IP를 통해서 서비스에 접근할 수 있다.

1개의 인스턴스에 Elastic IP를 붙일 경우 무료, 만약 사용하지 않고 가지고만 있으면 요금이 붙는다.

### 설정 방법

1. 왼쪽 메뉴에서 탄력적 IP를 클릭한다
2. 탄력적 IP 주소 할당을 누른다
3. Amazon의 IPv4 주소 풀 을 체크하고 할당받는다
4. 탄력적 주소 옆에 Actions에서 탄력적 IP 주소 연결을 누른다

![탄력](https://user-images.githubusercontent.com/56301069/77846606-bc8ba680-71a6-11ea-8f34-bff046278379.png)

5. IP를 할당할 인스턴스를 선택 후 연결한다

## Xshell IP 변경

인스턴스의 IP가 변경되었으니 Xshell의 호스트 IP도 변경되어야 한다

1. Xshell 을 실행 후 메뉴에 있는 세션에서 오른쪽 버튼을 클릭 후 등록 정보를 클릭

![탄력](https://user-images.githubusercontent.com/56301069/77847107-0e81fb80-71aa-11ea-9afa-716212e5de90.png)

2. 왼쪽 메뉴에서 제일 위에 있는 연결을 누르고 호스트를 할당받은 Elastic IP로 할당한다.

3. 왼쪽 메뉴에 있는 세션 관리 탭에서 만든 세션을 더블클릭하면 연결이 된다.

    Connecting to 3.34.23.92:22...
    Connection established.
    To escape to local shell, press 'Ctrl+Alt+]'.
    
    Welcome to Ubuntu 18.04.3 LTS (GNU/Linux 4.15.0-1057-aws x86_64)
    
     * Documentation:  https://help.ubuntu.com
     * Management:     https://landscape.canonical.com
     * Support:        https://ubuntu.com/advantage
    
      System information as of Sun Mar 29 10:51:36 UTC 2020
    
      System load:  0.0               Processes:           93
      Usage of /:   20.3% of 7.69GB   Users logged in:     0
      Memory usage: 18%               IP address for eth0: 172.31.43.241
      Swap usage:   0%
    
    
    33 packages can be updated.
    0 updates are security updates.
    
    

## 자바 8 설치

자바 버전을 확인해서 설치가 되어있는지 확인합니다

    java -version

    Command 'java' not found, but can be installed with:
    
    sudo apt install default-jre            
    sudo apt install openjdk-11-jre-headless
    sudo apt install openjdk-8-jre-headless

설치가 안되어 있으니 자바를 설치해봅시다

아래의 명령어를 사용하여 자바를 설치합니다

    sudo apt-get update;
    sudo apt install openjdk-8-jdk

이제 자바가 잘 설치되었는지 확인해봅시다

    ubuntu@ip-172-31-43-241:~$ java -version
    openjdk version "1.8.0_242"
    OpenJDK Runtime Environment (build 1.8.0_242-8u242-b08-0ubuntu3~18.04-b08)
    OpenJDK 64-Bit Server VM (build 25.242-b08, mixed mode)

잘 설치되었네요!

[참고:JAVA 환경변수 설정](https://all-record.tistory.com/181)

## 깃허브에 있는 저장소 클론하기

1. 깃허브에서 파일을 클론해오기 위해 클론 URL을 복사합니다
2. git clone 명령어를 통해 프로젝트를 클론합니다

    ubuntu@ip-172-31-43-241:~$ git clone https://github.com/codesquad-member-2020/signup-6.git
    Cloning into 'signup-6'...
    remote: Enumerating objects: 34, done.
    remote: Counting objects: 100% (34/34), done.
    remote: Compressing objects: 100% (27/27), done.
    remote: Total 1667 (delta 5), reused 25 (delta 4), pack-reused 1633
    Receiving objects: 100% (1667/1667), 135.04 MiB | 15.92 MiB/s, done.
    Resolving deltas: 100% (733/733), done.

클론이 정상적으로 되었습니다

## 프로젝트 빌드하기

1. 프로젝트를 빌드하기 위해 자신의 폴더로 이동합니다

    ubuntu@ip-172-31-43-241:~$ cd signup-6/BE
    ubuntu@ip-172-31-43-241:~/signup-6/BE$ ls
    build.gradle  gradle  gradlew  gradlew.bat  logs  settings.gradle  src
    ubuntu@ip-172-31-43-241:~/signup-6/BE$ 

2. checkout으로 배포할 자신의 브랜치로 이동합니다

3. gradlew 파일에 권한을 부여합니다

    sudo chmod 777 ./gradlew

ls 명령어를 쳤을 때 초록색으로 표시가 되어야합니다

3. 아래 명령어를 통해 빌드합니다

    ./gradlew build
    ./gradlew bootRun

- 만약 75%에서 멈춘다면 컨트롤 + C를 입력 후 엔터를 누른다

정상 빌드

      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::        (v2.2.5.RELEASE)
    
    

정상 접속 확인

./build/libs 폴더 안에 jar 파일이 생성되었습니다

- 스프링 부트에서는 톰캣 서버가 내장되어있기 때문에 빌드된 파일만 실행하면 서버가 가동됩니다

5. 실행

    java -jar 파일명.jar

     .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::        (v2.2.5.RELEASE)
    
    29-03-2020 14:30:18.774 [main] INFO  com.codesquad.signup.SignupApplication.logStarting - Starting SignupApplication on ip-172-31-43-241 with PID 1669 (/home/ubuntu/signup-6/BE/build/libs/signup-0.0.1-SNAPSHOT.jar started by ubuntu in /home/ubuntu/signup-6/BE/build/libs)
    29-03-2020 14:30:18.779 [main] DEBUG com.codesquad.signup.SignupApplication.logStarting - Running with Spring Boot v2.2.5.RELEASE, Spring v5.2.4.RELEASE
    29-03-2020 14:30:18.784 [main] INFO  com.codesquad.signup.SignupApplication.logStartupProfileInfo - No active profile set, falling back to default profiles: default

6. 만들어진 서버로 요청을 보냅니다

    public ip:8080