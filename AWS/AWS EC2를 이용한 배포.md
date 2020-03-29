## Elastic IP(����)

�ν��Ͻ��� ���� Ű�� IP�� ���ϰ� �ȴ�.

������ ������ Xshell ���ǿ����� ����������ϱ� ������ �������Ƿ� ����IP�� �Ҵ��ϵ��� �Ѵ�.

���� IP�� ����ϸ� ��ǻ�͸� ��ü�ص� ����ڴ� ������ IP�� ���ؼ� ���񽺿� ������ �� �ִ�.

1���� �ν��Ͻ��� Elastic IP�� ���� ��� ����, ���� ������� �ʰ� ������ ������ ����� �ٴ´�.

### ���� ���

1. ���� �޴����� ź���� IP�� Ŭ���Ѵ�
2. ź���� IP �ּ� �Ҵ��� ������
3. Amazon�� IPv4 �ּ� Ǯ �� üũ�ϰ� �Ҵ�޴´�
4. ź���� �ּ� ���� Actions���� ź���� IP �ּ� ������ ������

![ź��](https://user-images.githubusercontent.com/56301069/77846606-bc8ba680-71a6-11ea-8f34-bff046278379.png)

5. IP�� �Ҵ��� �ν��Ͻ��� ���� �� �����Ѵ�

## Xshell IP ����

�ν��Ͻ��� IP�� ����Ǿ����� Xshell�� ȣ��Ʈ IP�� ����Ǿ�� �Ѵ�

1. Xshell �� ���� �� �޴��� �ִ� ���ǿ��� ������ ��ư�� Ŭ�� �� ��� ������ Ŭ��

![ź��](https://user-images.githubusercontent.com/56301069/77847107-0e81fb80-71aa-11ea-9afa-716212e5de90.png)

2. ���� �޴����� ���� ���� �ִ� ������ ������ ȣ��Ʈ�� �Ҵ���� Elastic IP�� �Ҵ��Ѵ�.

3. ���� �޴��� �ִ� ���� ���� �ǿ��� ���� ������ ����Ŭ���ϸ� ������ �ȴ�.

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
    
    

## �ڹ� 8 ��ġ

�ڹ� ������ Ȯ���ؼ� ��ġ�� �Ǿ��ִ��� Ȯ���մϴ�

    java -version

    Command 'java' not found, but can be installed with:
    
    sudo apt install default-jre            
    sudo apt install openjdk-11-jre-headless
    sudo apt install openjdk-8-jre-headless

��ġ�� �ȵǾ� ������ �ڹٸ� ��ġ�غ��ô�

�Ʒ��� ��ɾ ����Ͽ� �ڹٸ� ��ġ�մϴ�

    sudo apt-get update;
    sudo apt install openjdk-8-jdk

���� �ڹٰ� �� ��ġ�Ǿ����� Ȯ���غ��ô�

    ubuntu@ip-172-31-43-241:~$ java -version
    openjdk version "1.8.0_242"
    OpenJDK Runtime Environment (build 1.8.0_242-8u242-b08-0ubuntu3~18.04-b08)
    OpenJDK 64-Bit Server VM (build 25.242-b08, mixed mode)

�� ��ġ�Ǿ��׿�!

[����:JAVA ȯ�溯�� ����](https://all-record.tistory.com/181)

## ����꿡 �ִ� ����� Ŭ���ϱ�

1. ����꿡�� ������ Ŭ���ؿ��� ���� Ŭ�� URL�� �����մϴ�
2. git clone ��ɾ ���� ������Ʈ�� Ŭ���մϴ�

    ubuntu@ip-172-31-43-241:~$ git clone https://github.com/codesquad-member-2020/signup-6.git
    Cloning into 'signup-6'...
    remote: Enumerating objects: 34, done.
    remote: Counting objects: 100% (34/34), done.
    remote: Compressing objects: 100% (27/27), done.
    remote: Total 1667 (delta 5), reused 25 (delta 4), pack-reused 1633
    Receiving objects: 100% (1667/1667), 135.04 MiB | 15.92 MiB/s, done.
    Resolving deltas: 100% (733/733), done.

Ŭ���� ���������� �Ǿ����ϴ�

## ������Ʈ �����ϱ�

1. ������Ʈ�� �����ϱ� ���� �ڽ��� ������ �̵��մϴ�

    ubuntu@ip-172-31-43-241:~$ cd signup-6/BE
    ubuntu@ip-172-31-43-241:~/signup-6/BE$ ls
    build.gradle  gradle  gradlew  gradlew.bat  logs  settings.gradle  src
    ubuntu@ip-172-31-43-241:~/signup-6/BE$ 

2. checkout���� ������ �ڽ��� �귣ġ�� �̵��մϴ�

3. gradlew ���Ͽ� ������ �ο��մϴ�

    sudo chmod 777 ./gradlew

ls ��ɾ ���� �� �ʷϻ����� ǥ�ð� �Ǿ���մϴ�

3. �Ʒ� ��ɾ ���� �����մϴ�

    ./gradlew build
    ./gradlew bootRun

- ���� 75%���� ����ٸ� ��Ʈ�� + C�� �Է� �� ���͸� ������

���� ����

      .   ____          _            __ _ _
     /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
    ( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
     \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
      '  |____| .__|_| |_|_| |_\__, | / / / /
     =========|_|==============|___/=/_/_/_/
     :: Spring Boot ::        (v2.2.5.RELEASE)
    
    

���� ���� Ȯ��

./build/libs ���� �ȿ� jar ������ �����Ǿ����ϴ�

- ������ ��Ʈ������ ��Ĺ ������ ����Ǿ��ֱ� ������ ����� ���ϸ� �����ϸ� ������ �����˴ϴ�

5. ����

    java -jar ���ϸ�.jar

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

6. ������� ������ ��û�� �����ϴ�

    public ip:8080