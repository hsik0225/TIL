## 설치

이 [링크](https://www.chiark.greenend.org.uk/~sgtatham/putty/latest.html)에서 putty 를 다운받는다

## PuTTYgen을 사용하여 프라이빗 키 변환

PuTTY에서는 SSH 키의 프라이빗 키 형식을 기본적으로 지원하지 않습니다. PuTTY에는 PuTTYgen이라는 도구가 있는데, 이 도구는 키를 필요한 PuTTY 형식으로 변환할 수 있습니다. PuTTY를 사용하여 인스턴스에 연결하려면 프라이빗 키(.pem 파일)를 이 형식(.ppk 파일)으로 변환해야 합니다.

1. **시작** 메뉴에서 **모든 프로그램**, **PuTTY**, **PuTTYgen**을 선택합니다.
2. **Type of key to generate(생성할 키 유형)**에서 **RSA**를 선택합니다. 이전 버전의 PuTTYgen을 사용하는 경우 **SSH-2 RSA**를 선택합니다.

![puttygen-key-type](https://user-images.githubusercontent.com/56301069/77850238-a4288580-71c0-11ea-9717-922ecf94e9cb.png)

3. **로드**를 선택합니다. 기본적으로 PuTTYgen에는 확장명이 `.ppk`인 파일만 표시됩니다. `.pem` 파일을 찾으려면 모든 유형의 파일을 표시하는 옵션을 선택합니다.

![puttygen-load-key](https://user-images.githubusercontent.com/56301069/77850241-a559b280-71c0-11ea-9a72-6a1e4e10887a.png)

4. 인스턴스를 시작할 때 지정한 키 페어의 `.pem` 파일을 선택하고 **열기**를 선택합니다. `.pem` 파일을 가져왔다는 알림이 PuTTYgen에 표시됩니다. **확인**을 선택합니다.

5. **Save private key(프라이빗 키 저장)**을 선택하여 PuTTY에서 사용할 수 있는 형식으로 키를 저장합니다. PuTTYgen에서 암호 없이 키 저장에 대한 경고가 표시됩니다. **예**를 선택합니다.

6. 키 페어에 사용한 것과 동일한 키 이름을 지정하고(예: `my-key-pair`) **저장**을 선택합니다. PuTTY가 자동으로 `.ppk` 파일 확장자를 추가합니다.

이제 개인 키가 PuTTY에 사용하기에 올바른 형식으로 되어 있으므로 PuTTY의 SSH 클라이언트를 사용하여 인스턴스에 연결할 수 있습니다.

## Linux 인스턴스에 연결

**PuTTY를 사용하여 인스턴스에 연결하려면**

1. PuTTY를 시작합니다. 즉, **시작** 메뉴에서 **모든 프로그램, PuTTY, PuTTY**를 선택합니다.
2. **범주** 창에서 **세션**을 선택하고 다음 필드를 작성합니다.

    a. **호스트 이름** 상자에서 다음 중 하나를 수행합니다.

    - (퍼블릭 DNS) 인스턴스의 퍼블릭 DNS를 사용하여 연결하려면 *`user_name`*@*`public_dns_name`*을 입력합니다.
    - (IPv6) 또는 인스턴스에 IPv6 주소가 있는 경우 인스턴스의 IPv6 주소를 사용하여 연결하려면 *`user_name`*@*`ipv6_address`*를 입력합니다.

    *`user_name`*에는 AMI에 적합한 사용자 이름을 지정해야 합니다. 예:

    - Amazon Linux 2 또는 Amazon Linux AMI의 경우 사용자 이름은 `ec2-user`입니다.
    - Ubuntu AMI의 경우 사용자 이름은 ubuntu입니다.

    b. **Port(포트)** 값이 22인지 확인합니다.

    c. **연결 유형** 아래에서 **SSH**를 선택합니다.

    ![putty-session-config](https://user-images.githubusercontent.com/56301069/77850302-139e7500-71c1-11ea-855b-8cab9bffba0f.png)

3. **범주** 창에서 **연결**, **SSH**를 확장한 다음 **Auth**를 선택합니다. 다음 작업을 완료합니다.
    1. **찾아보기**를 선택합니다.
    2. 키 페어에 대해 생성한 `.ppk` 파일을 선택한 다음 **열기**를 선택합니다.
    3. (선택 사항) 이 세션을 나중에 다시 시작하려는 경우 세션 정보를 나중에 사용할 수 있게 저장할 수 있습니다. **범주**에서 **세션**을 선택하고 **저장된 세션(Saved Sessions)**에 세션 이름을 입력한 다음 **저장**을 선택합니다.
    4. **열기**를 선택합니다.
4. 이 인스턴스에 처음 연결한 경우 PuTTY에서 연결하려는 호스트를 신뢰할 수 있는지 묻는 보안 알림 대화 상자가 표시됩니다.
    1. **예**를 선택합니다. 창이 열리고 인스턴스에 연결됩니다.

[참고](https://docs.aws.amazon.com/ko_kr/AWSEC2/latest/UserGuide/putty.html)