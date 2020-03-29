[원본](https://engineer.crowdworks.jp/entry/2016/11/01/000000)

## 시작

여기서 구축하는 구조는 「Lambda + CloudWatch Events + KMS」입니다. CloudWatch Event로 AWS 콘솔의 로그인을 감자히고, 그것을 Lambda를 경유하여 Slack으로 알려줍니다. KMS에는 Slack 알림에 필요한 비밀정보를 Lambda에 가지게할 때 사용합니다

![구조](https://user-images.githubusercontent.com/56301069/77831730-c40c6a80-7128-11ea-968b-85bcad0e876b.png)

## 사전준비
후반 AWS CLI가 나오기때문에 인스톨이 아직인 사람은 [공식  문서](https://docs.aws.amazon.com/ko_kr/cli/latest/userguide/cli-chap-install.html)를 참고하여 인스톨해주시기 바랍니다.

## 버저니아 북부 리전을 변경

갑자기 주의점입니다만, AWS 콘솔의 로그인 이벤트는 버지니아 북부 지역으로 밖에 보내지지 않습니다. 그러므로, 서울 지역 등을 사용하고 있는 사람은 먼저 지역을 바꿔야합니다.

## Lambda의 초기 Set Up

CloudWatch Events에서 Lamda를 Kick하기 때문에, 이전에 Lamda Function을 작성합니다.

1. Lambda 페이지를 엽니다

	- [https://console.aws.amazon.com/lambda/home](https://console.aws.amazon.com/lambda/home)

 2. 「함수 생성」 버튼을 클릭(버지니아 북부 지역에서 처음 Lambda를 만드는 경우)

3. Runtime에서 「Python2.7」을 선택하고, blueprint의 「hello-world-python」을 클랙

<img width="500" alt="hello" src="https://user-images.githubusercontent.com/56301069/77831949-4ba6a900-712a-11ea-8b8c-aed3d00eb4a6.png">

4. 그대로 「Next」를 클릭

<img width="500" alt="next" src="https://user-images.githubusercontent.com/56301069/77831967-709b1c00-712a-11ea-8284-cab757a66d41.png">

5. Lambda function의 설정을 합니다

a. 임의의 이름과 설명을 입력

<img width="500" alt="설명" src="https://user-images.githubusercontent.com/56301069/77831987-988a7f80-712a-11ea-8c09-2f0b473f4d81.png">

b. Lambda function handler and role에서 새로운 Role 작성

- 실행 역할에서 「기본 Lambda 권한을 가진 새 역할 생성」을 선택

c. IAM 설정 페이지가 다른 탭으로 자동으로 열리고 그대로 버튼을 클릭

- 이것으로 LAMBDA_BASIC_EXECUTION이라는 Role이 자동작성된다

<img width="500" alt="role" src="https://user-images.githubusercontent.com/56301069/77832025-dbe4ee00-712a-11ea-9713-d23a0bc362c7.png">

d. 방금전의 Role을 선택하고, 타임아웃 시간을 변경 한 뒤 Next 버튼을 클릭

- Role : Choose an existing role
- Existing role : lambda_basic_execution
- Timeout：1 min 0 sec

<img width="500" alt="role" src="https://user-images.githubusercontent.com/56301069/77832044-0171f780-712b-11ea-86ae-6be6c0007c2a.png">

6. 입력 내용에 문제가 없는 것을 확인하고 Create Function 버튼을 클릭

7. 정상적으로 Lambda Function이 작성되었는가를 확인

8. 작성한 Lambda Function의 수정

- lambda_handler 메소드 안에 첫 번째 행의 Comment Out을 벗어나, 기술된 내용을 모두 삭제
- 8행의 ident=2 를 삭제(삭제하지 않아도 동작에 영향은 없지만, 삭제한 편이 로그가 보기 좋다)
- 코드를 수정했다면 Save 버튼을 클릭

<img width="500" alt="save" src="https://user-images.githubusercontent.com/56301069/77832091-6299cb00-712b-11ea-90c6-1bb7f854bdbc.png">

단계별로 하면 Lambda Function은 이렇게 된다

    from __future__ import print_function
    
    import json
    
    print('Loading function')
    
    
    def lambda_handler(event, context):
        print("Received event: " + json.dumps(event))

## CloudWatch Events의 설정

CloudWatch Events에서 AWS Console로의 로그인을 감지하도록 설정합니다

1. CloudWatch Events 페이지를 엽니다

    [https://console.aws.amazon.com/cloudwatch/home?region=us-east-1#events:](https://console.aws.amazon.com/cloudwatch/home?region=us-east-1#events:)

2. 시작하기 버튼을 클릭
3. 룰을 아래와 같이 작성하고 설정 상세 버튼을 클릭
    - 이벤트 선택으로 「AWS 콘솔의 로그인」을 선택

        「임의의 유저」에 체크가 들어와있는가를 확인

    - 타겟의 타입 선택에서 「Lambda 변수」를 선택
        - 기능에 방금전 작성한 Function이름을 입력

        <img width="500" alt="이름" src="https://user-images.githubusercontent.com/56301069/77832169-e9e73e80-712b-11ea-9fa4-0feed1b758e6.png">

4. 룰 이름과 설명을 입력하고 룰 작성 버튼을 클릭

<img width="500" alt="룰 이름" src="https://user-images.githubusercontent.com/56301069/77832191-07b4a380-712c-11ea-988e-9a55ee510f5c.png">

5. 방금전 작성한 룰이 있는지를 확인

## CloudWatch Logs의 설정과 확인

CloudWatch Events에서 Lambda가 킥 되고 있는지를 CloudWatch Logs에서 확인해봅시다

1. 먼저 로그아웃하고 다시 한 번 로그인합니다
    - 버지니아 북부 지역인지를 잊지 않고 확인
2. CloudWatch Logs의 페이지를 엽니다
    - [https://console.aws.amazon.com/cloudwatch/home?region=us-east-1#logs:](https://console.aws.amazon.com/cloudwatch/home?region=us-east-1#logs:)
3. 로그그룹 「/aws/lambda/sign-in-event-monitor-python」의  「실효하지 않는다」 링크를 클릭

<img width="500" alt="실효" src="https://user-images.githubusercontent.com/56301069/77832266-ae00a900-712c-11ea-92d4-f14044a8ead0.png">

4. 보존기간을 임의의 기간(여기에서는 1주로 설정함)으로 변경 후 OK 버튼을 클릭

5. 로그 그룹 「/aws/lambda/sign-in-event-monitor-python」의 링크를 클릭

<img width="500" alt="로그" src="https://user-images.githubusercontent.com/56301069/77832293-e0120b00-712c-11ea-8b8d-83028ac12565.png">

6. 로그스트림의  「20XX/XX/XX/[$LATEST]xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx」 링크를 클릭

<img width="500" alt="스트림" src="https://user-images.githubusercontent.com/56301069/77832307-fcae4300-712c-11ea-98ad-a9625ac62bc5.png">

7. Received Event: 라는 행을 찾아 클릭

<img width="500" alt="received" src="https://user-images.githubusercontent.com/56301069/77832320-13549a00-712d-11ea-8fbf-2dbc063c269c.png">

8. CloudWatch Events에서 보낸 파라미터가 JSON으로 기록되고 있는지를 확인

<img width="500" alt="JSON" src="https://user-images.githubusercontent.com/56301069/77832334-2cf5e180-712d-11ea-92e4-a34acc131a3f.png">

## Slack으로 Incoming Webhooks의 URL 지불

드이어 Slack 설정입니다

1. Slack의 Incoming Webhooks의 페이지를 엽니다
    - [https://my.slack.com/services/new/incoming-webhook/](https://my.slack.com/services/new/incoming-webhook/)
2. Post to Channel에서 「#general」을 선택하고, 「Add Incoming Webhooks Integration」 버튼을 클릭

    알림 장소의 채널은 나중에 변경가능하기 때문에 「#general」이 아니라도 상관없다

3. 「Copy URL」의 링크를 클릭하여 Webhook URL을 클립보드에 복사하고 「Save Settings」의 버튼을 클릭

![save](https://user-images.githubusercontent.com/56301069/77832388-9544c300-712d-11ea-8c0e-069609c9b7ad.png)

## Lambda에서 Slack으로 알림보내게 수정

밑준비가 끝났기 때문에 Lambda를 수정해보도록 합시다. 할 것은 3가지 입니다.

1. CloudWatch Events에서 보낸 JSON을 파싱
2. Slack으로 보내는 메세지 성형
3. Slack에 Incoming Webhooks으로 알림을 보냄

예를 들면 이런 코드를 만들 수 있습니다

또한, WEB_HOOK_URL에는 방금전 지불한 Webhook URL을 설정해주세요. 또, CHANNEL에는 알림을 보낸 임의의 채널을 설정해주세요.

    # -*- encoding:utf-8 -*-
    
    import json
    import commands
    import urllib
    
    WEB_HOOK_URL = 'https://hooks.slack.com/services/your/web_hook_url'
    CHANNEL = 'your_channel'
    
    def lambda_handler(event, context):
        print("Received event: " + json.dumps(event))
        message = create_message(event['detail'])
        return notify(message, CHANNEL, WEB_HOOK_URL)
    
    def create_message(detail):
        user_name = get_user_name(detail['userIdentity'])
        event_time = detail['eventTime']
        event_name = detail['eventName']
        event_result = detail['responseElements'][event_name]
    
        message = "AWSコンソールへのログインを検知けんちしました\n" \
                + "・ユーザ名めい：" + user_name + "\n" \
                + "・イベント名めい：" + event_name + "\n" \
                + "・結果けっか：" + event_result + "\n" \
                + "・発生はっせい時刻じこく：" + event_time
        return message
    
    def get_user_name(user_identity):
        if user_identity['type'] == 'Root':
            return 'root'
        elif user_identity['type'] == 'IAMUser':
            return user_identity['userName']
        else:
            # RootアカウントとIAM User以外いがいのパターンがあるかどうかわからないが念ねんのため
            return json.dumps(user_identity)
    
    def notify(message, channel, web_hook_url):
        payload = {
            "text": message,
            "channel": channel,
            "username": "AWSアカウントモニターBot",
            "icon_emoji": ":ghost:"
        }
        escaped_payload = urllib.quote_plus(json.dumps(payload).encode('utf-8'))
        curl_command = 'curl -s -X POST -d "payload=%s" %s' % (escaped_payload, web_hook_url)
        return commands.getoutput(curl_command)

설명할 건 거의 없지만, IAM USER와 ROOT 어카운트로 미묘하게 데이터 구조가 달라지기 때문에 그 점은 주희해주세요.(get_user_name 메소드 부분)

이것을 저장하고 로그아웃 & 로그인을 하면, Slack에 알림이 날라올겁니다. 여담이지만, 이단계확증(MFA)을 설정하면 알림이 2개 날라옵니다.

<img width="500" alt="BOT" src="https://user-images.githubusercontent.com/56301069/77832890-2cf7e080-7131-11ea-9f1a-a2ed9cda8119.png">

이런게 날라오면 성공입니다! 나머지는 메세지 내용 등을 좋아하는 대로 커스터마이징 하면 됩니다.

## KMS로 마스터 키를 작성

이대로도 작동하지만 Incoming Webhooks의 URL이 그대로 Lambda에 써지는 것은 기분 나쁘네요. GitHub등에 그대로 올라가면 참을 수 없습니다.그래서,URL을 KMS으로 암호화하여 Lambda에는 암호화한 문자열을 가지게 합니다.

그럼, 어서 마스터키를 만들어죠

1. KMS 페이지를 엽니다
    - [https://console.aws.amazon.com/iam/home#encryptionKeys](https://console.aws.amazon.com/iam/home#encryptionKeys)
2. 처음 만들 경우 「지금 바로 시작한다」 버튼을 클릭

<img width="500" alt="시작" src="https://user-images.githubusercontent.com/56301069/77832552-e0130a80-712e-11ea-99a5-c5e03bbe21f5.png">

3. 필터로 「미국동부(버지니아북부)」를 선택

<img width="500" alt="필터" src="https://user-images.githubusercontent.com/56301069/77832575-16e92080-712f-11ea-9903-7db40ec95953.png">

4. 「키의 작성」 버튼을 클릭

<img width="500" alt="작성" src="https://user-images.githubusercontent.com/56301069/77832569-0d5fb880-712f-11ea-93dc-6eda8e75219b.png">

5. 에이리아스와 설명을 입력하고 「다음 스텝」 버튼을 클릭

<img width="500" alt="설명" src="https://user-images.githubusercontent.com/56301069/77832597-308a6800-712f-11ea-8300-12a2ad8889ea.png">

6. 키의 관리자로 자신의 IAM USER에 체크를 하고「「다음 스텝」 버튼을 클릭

<img width="500" alt="CHECK" src="https://user-images.githubusercontent.com/56301069/77832614-4730bf00-712f-11ea-9763-113298c9833a.png">

7. 키 Policy의 Preview를 확인하고, 완료 버튼을 클릭

8. 키 목록 화면에서 방금 전 만든 「lambda_encryption」를 클릭

<img width="500" alt="lambda" src="https://user-images.githubusercontent.com/56301069/77832652-8101c580-712f-11ea-982e-f60076bd4cd4.png">

9. 키 유저의 추가 버튼을 클릭

<img width="500" alt="추가" src="https://user-images.githubusercontent.com/56301069/77832663-90810e80-712f-11ea-8c1e-b72552ffff38.png">

10. Lambda 용에 작성한 Role에 체크를 하고 연결버튼을 클릭

<img width="500" alt="연결" src="https://user-images.githubusercontent.com/56301069/77832680-ad1d4680-712f-11ea-86fd-06516d7a8c01.png">

## AWS CLI로 비밀정보를 암호화

그럼 Incoming Webhooks의 URL을 AWS CLI로 실제로 암호화해봅시다. 또한, 여기에서 한 가지 주의할 점이 있습니다.

그것은 지정한 URL에서 「」를 삭제하는 것입니다.

AWS CLI의 사양에 따라 kms encrpyt의 —plaintext에 URL을 그대로 쓰면, 그 URL로 HTTP Request를 날려, 원격에 있는 파일을 취득하러 가려고 합니다.

    $ aws kms encrypt --region us-east-1 --output text --query CiphertextBlob --key-id alias/lambda_encryption --plaintext //hooks.slack.com/services/your/web_hook_url
    AQECAHismBxxxxxxxxxxxxx

이런 느낌으로 커맨드를 실행하면 암호화된 문자열이 반환되고, Lambda에서는 이것을 사용합니다.

## Lambda의 최종형을 구현

아까 만든 sign-in-event-monitor-python에 이런 메소드를 추가해봅시다. 「https:」없는 문자열을 암호화하고 있기 때문에 복호시에 수동으로 부여하는 것을 잊지 않고 실행합니다.

    def get_web_hook_url(web_hook_url_encrypted):
        return 'https:' + decrypt(web_hook_url_encrypted)
    
    def decrypt(encrypted):
        import boto3
        import base64
        return boto3.client('kms').decrypt(CiphertextBlob=base64.b64decode(encrypted))['Plaintext']

Lambda Function 전체로는 이런 느낌입니다

    # -*- encoding:utf-8 -*-
    
    import json
    import commands
    import urllib
    
    WEB_HOOK_URL_ENCRYPTED = 'AQECAHismBxxxxxxxxxxxxx' # 暗号あんごう化かしたWEB_HOOK_URL
    CHANNEL = 'your_channel'
    
    def lambda_handler(event, context):
        print("Received event: " + json.dumps(event))
        message = create_message(event['detail'])
    
        # 修正しゅうせい部分ぶぶん
        web_hook_url = get_web_hook_url(WEB_HOOK_URL_ENCRYPTED)
        return notify(message, CHANNEL, web_hook_url)
    
    def create_message(detail):
        user_name = get_user_name(detail['userIdentity'])
        event_time = detail['eventTime']
        event_name = detail['eventName']
        event_result = detail['responseElements'][event_name]
    
        message = "AWSコンソールへのログインを検知けんちしました\n" \
                + "・ユーザ名めい：" + user_name + "\n" \
                + "・イベント名めい：" + event_name + "\n" \
                + "・結果けっか：" + event_result + "\n" \
                + "・発生はっせい時刻じこく：" + event_time
        return message
    
    def get_user_name(user_identity):
        if user_identity['type'] == 'Root':
            return 'root'
        elif user_identity['type'] == 'IAMUser':
            return user_identity['userName']
        else:
            # RootアカウントとIAM User以外いがいのパターンがあるかどうかわからないが念ねんのため
            return json.dumps(user_identity)
    
    
    # 追加ついか部分ぶぶん
    def get_web_hook_url(web_hook_url_encrypted):
        return 'https:' + decrypt(web_hook_url_encrypted)
    
    
    # 追加ついか部分ぶぶん
    def decrypt(encrypted):
        import boto3
        import base64
        return boto3.client('kms').decrypt(CiphertextBlob=base64.b64decode(encrypted))['Plaintext']
    
    
    def notify(message, channel, web_hook_url):
        payload = {
            "text": message,
            "channel": channel,
            "username": "AWSアカウントモニターBot",
            "icon_emoji": ":ghost:"
        }
        escaped_payload = urllib.quote_plus(json.dumps(payload).encode('utf-8'))
        curl_command = 'curl -s -X POST -d "payload=%s" %s' % (escaped_payload, web_hook_url)
        return commands.getoutput(curl_command)

조금 길지만, Incoming Webhooks의 URL도 암호화되어있기때문에 버전 관리해버려도 괜찮습니다.

## 정리

개인적으로는 Lambda + CloudWatch Events + KMS라는 것은 '강력한 콤보다!'라고 생각했지만 의외로 이것들을 조합하여 사용하는 정보가 적기 때문에 글을 적어봤습니다. KMS은 수수한 존재지만, Lambda에서 비밀정보를 다룰 경우, 사실상 필수 컴포넌트입니다. 또, CloudWatch Events도 정기실행할 수 있기때문에 cron대신에 사용하기도 하고, 실은 잠재력이 높습니다. 예를 들면 로그인 가능한 IAM User가 이단계인증을 설정하고 있는가를 정기적으로 체크하는 것이 이 글에서 등록한 컴포넌트만으로 구현가능합니다.

덧붙여, 이 글에서는 실습으로 그대로 사용하는 것을 목표로 적었습니다.