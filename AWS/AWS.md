AWS TODO & STUDY

## ��Ʈ ���� 2�� ��� ����

## Admin ���� �����

[����](https://docs.aws.amazon.com/ko_kr/IAM/latest/UserGuide/getting-started_create-admin-group.html)

1. ���� ���� ����

2. IAM ���� Ŭ��

3. ���� �޴����� �׼��� �����ڿ� �ִ� ����� Ŭ��

4. ����� �߰� Ŭ��

5. User Details ����
	- ����� �̸� ����
	- AWS Managemnet Cosole �׼��� üũ
	- �ڵ� ������ ��й�ȣ üũ
	- ��й�ȣ �缳�� �ʿ� üũ ����

6.�׷쿡 ����� �߰�
	1. �׷� ����
	2. �׷� �̸��� ���ϰ� AdminstratorAccess üũ �� �׷� ����
	3. ������ �׷��� üũ�ϰ� ����


7. �±� �߰�

8. ����� �����

9. credentials.csv �� �ٿ�޴´�

## Power User ���� �����

[����](https://docs.aws.amazon.com/ko_kr/IAM/latest/UserGuide/getting-started_create-delegated-user.html)

1. ���� ���� ����

2. IAM ���� Ŭ��

3. ���� �޴����� �׼��� �����ڿ� �ִ� ����� Ŭ��

4. ����� �߰� Ŭ��

5. User Details ����
	- ����� �̸� ����
	- AWS Managemnet Cosole �׼��� üũ
	- �ڵ� ������ ��й�ȣ üũ
	- ��й�ȣ �缳�� �ʿ� üũ ����


6.�׷쿡 ����� �߰�
	1. �׷� ����
	2. �׷� �̸��� ���ϰ� ���ͷ� PowerUserAccess �˻�, üũ �� �׷� ����
	3. ������ �׷��� üũ�ϰ� ����

7. �±� �߰�

8. ����� �����

9. credentials.csv �� �ٿ�޴´�

## EC2 ����

0. �α��� �� ���� ���� ����

1. ���񽺿��� EC2�� �����ϰ� Launch Instance

2. OS ����
	- Ubuntu 18.04 LTS(Type : Free Tier eligible)

Next

3. �ν��Ͻ� ���� ���� ����
	- ��Ʈ��ũ �⺻ �� ����
	- ����� �⺻ ���� ���� ����

Next

4. ����� ����
	- 8GB
Next

5. Add Tags
	- �����ǿ� ���� �����鿡�� ������ �� �ְ� �±׸� ���̴� ���̶�� ��������
	- Name : Web Server
	- ������ 	: Ever

Next

6. Configure Security Group
	- ��Ʈ�� �����ϴ� AWS�� �⺻���� ��ȭ������ ����
	- SSH�� ���� ������ SSH�� ������ ���� �ʴ´�
	- 0.0.0.0/0 �� ��� IP ������ ����Ѵٴ� �ǹ�

	1. �� ���� �׷� ����(������ �̹� ������ٸ� ���� ���� �׷� ����)
	
	2. Security Group Name �Է�

	3. SSH�� ���� �����ϰ� �����Ƿ� Source���� �� IP�� ����

	4. Spring�� ����ϴϱ� 8080 ��Ʈ�� �߰�������Ѵ�. 
		- Custom TCP ���� �� 8080 ��Ʈ�� �����ش�. ��, �� IP�� ����

	5. �츮�� �������� ����ű� ������ HTTP, HTTPS �� ���ٵ� ����Ͽ� �ش�
		- HTTP ����, 80 ��Ʈ
		- ������ ������ �� �־�� �ϱ� ������ �ҽ��� ��ġ �������� �������ش�

7. ���� �� �����ϱ� ��ư�� ���� �� Ű �� �ٿ�޴´�

Review And Launch
	
## SSH �����ϱ�

������� SSH�� ������ �ֱ� ������ ���� ���� ������ �ϴ� ���α׷��� ��ƾ� �Ѵ�. Putty�� ������, ���⼭�� Xshell6�� ����Ѵ�
�� [��ũ](https://www.netsarang.com/ko/free-for-home-school/)���� XShell6�� �ٿ�޴´�.

1. �ڽ��� �ν��Ͻ��� â���� IPv4 �ۺ� IP�� �����Ѵ�(ex.54.238.222.246)

2. ���� ���� �ν��Ͻ����� ������ ��ư�� Ŭ�� �� �����᡹ �� ������

3. Xshell���α׷��� �����ϰ� ���� ��ư�� Ŭ���Ѵ�
- ���⼭ ������ �� ���������� �ϸ�ũ���� ������ ���ش�

4. ���θ���⸦ Ŭ���Ѵ�

5. �̸��� �Է� �� ȣ��Ʈ �κп� �Ʊ� ������ IP�� �ٿ��ֱ� �Ѵ�

6. �޴����� ����� ������ ���� ������̸��� ��ȣ�� �Է��Ѵ�
	- ������� ������ٸ� ����� �̸��� ubuntu, ������� �ƴ� �ٸ� ��������� ec2-user
	- ������� �ۺ� Ű �� �����Ѵ�
	- �����Ű ���� �ִ� ��ã�ƺ��⡹�� Ŭ���ϰ� �ڽ��� ��й�ȣ Ű(.per)�� ��������� ������ �� Ȯ�� ��ư�� ������
	- �Ʒ� ��ȣ�� ���� �ʴ´�

7. �� �� ���� ȣ��ƮŰ ��� ��� �����ϸ� ���� �� ������ Ŭ���Ѵ�

8. Ubuntu�� ���ӵǾ���

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

���� �����ϰ� �ʹٸ� exit �� �Է��Ѵ�

�ٽ� ���� �ϰ� �ʹٸ� Xshell ���� �޴����� ���� ������ ����Ŭ�����ش�

## �� ���� ����

1. ������ �������� `sudo apt-get install apache2` �� �Է��Ͽ� ��ġ�Ѵ�
���� Unable to fetch some, archives, maybe run ~~ ������ �߻��Ѵٸ�,

`sudo apt-get update;` �� �Է��ϰ� �ٽ� `sudo apt-get install apache2`�� �Է��Ѵ�

2. ������ �� ���� �ν��Ͻ��� IP�� �������� �˾ƾ��Ѵ�
�ν��Ͻ� �Ʒ��� ���� �ǿ��� IP�� �������� �� �� �ִ�
	IP : IPv4 �ۺ� IP
	������ : �ۺ� DNS

3. �ν��Ͻ��� ������ �ƴ� ���� OS���� IP�� �������� ������ �ǿ��� �ٿ��ֱ� �غ���
![����ġ](https://user-images.githubusercontent.com/56301069/77841089-14fa7e00-717e-11ea-8f83-12da12f28c25.png)

4. index.html ���� ����
���� html ������ /var/www/html ��ο� �ִ�

�� ������ �����غ��� ���� `cd /var/www/html`�� �Է��Ѵ�

5. ls �� �������� index.html �� ���´�

```bash
ubuntu@ip-172-31-43-241:/var/www/html$ ls
index.html
```

6. sudo rm -rf index.html �� �Է��Ͽ� index.html ������ ����

7. ������ ���� �����

```
sudo vi index.html
```

8. HTML ���� ���� �Է�

```
<html>
        <body>
                Hello AWS
        </body>
</html>
```

9. �ٽ� �ν��Ͻ� ������ �ƴ� ���� OS�� ���ƿͼ� �Ʊ� �Է��ߴ� ������ �Ǵ� IP�� �Է��Ѵ�

10. Hello AWS�� ��µȴ�!

## ������ ��Ʈ �������

## 