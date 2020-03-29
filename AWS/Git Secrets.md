[원글](https://qiita.com/pottava/items/4c602c97aacf10c058f1)

## 클라우드가 파산하지 않도록 git-secrets를 사용하자!

## git-secrets

AWS가 공개한 툴로, 패스워드같은 비밀정보를 `실수로 git 레포에 커밋`하는 것을 막아줍니다.

[https://github.com/awslabs/git-secrets](https://github.com/awslabs/git-secrets)

## 설정

### 1. 인스톨

툴을 저장할 디렉토리를 만들고, 소스를 거기에 둔 후 `make install`합니다.

### Linux / macOS

    $ cd /path/to/tools/directory
    $ git clone https://github.com/awslabs/git-secrets.git
    $ cd git-secrets/
    $ make install

### Windows

powershell script로 클론한 디렉토리에 간 후

    PS > ./install.ps1

만약 오류 발생시 그림과 같이 실행한다.

![제목 없음](https://user-images.githubusercontent.com/56301069/77831101-f1efb000-7124-11ea-803e-2c5e48ff4d48.png)

## 2. 기존 프로젝트에 훅을 설정

이 툴을 사용하고 싶은 프로젝트마다 아래의 커맨드

    $ cd /path/to/your/repository
    $ git secrets --install
    ✓ Installed commit-msg hook to .git/hooks/commit-msg
    ✓ Installed pre-commit hook to .git/hooks/pre-commit
    ✓ Installed prepare-commit-msg hook to .git/hooks/prepare-commit-msg

또는, 경로를 인수로 전하여 `--install`합니다

    $ git secrets --install /path/to/your/repository

## 3. 거부조건을 설정

다음은 `--add`로 거부(또는 false-positives를 막는 허가) 패턴을 설정합니다.

AWS에 대해서는 `--register-aws`만 치면 OK.

프로젝트마다가 아닌 글로벌하게 사용하기 위해 아래의 명령어를 칩니다.

    $ git secrets --register-aws --global

아래의 패턴이 적용됩니다.

    $ git secrets --list
    secrets.providers git secrets --aws-provider
    secrets.patterns [A-Z0-9]{20}
    secrets.patterns ("|')?(AWS|aws|Aws)?_?(SECRET|secret|Secret)?_?(ACCESS|access|Access)?_?(KEY|key|Key)("|')?\s*(:|=>|=)\s*("|')?[A-Za-z0-9/\+=]{40}("|')?
    secrets.patterns ("|')?(AWS|aws|Aws)?_?(ACCOUNT|account|Account)_?(ID|id|Id)?("|')?\s*(:|=>|=)\s*("|')?[0-9]{4}\-?[0-9]{4}\-?[0-9]{4}("|')?
    secrets.allowed AKIAIOSFODNN7EXAMPLE
    secrets.allowed wJalrXUtnFEMI/K7MDENG/bPxRfiCYEXAMPLEKEY

## 기본적인 사용 방법

도입으로 가장 단순한 예로, git commit 시, 그 커밋 안에 AWS의 비밀 정보가 포함되어있는가 아닌가를 검사하는 방법을 소개합니다.

먼저, 새로 만든 Git Repo 또는 기존 Repo에서 git secrets —install을 실행하는 것으로,  Repo를 git-screts에 대응시킵니다.

    $ git init
    $ git secrets --install
    ✓ Installed commit-msg hook to .git/hooks/commit-msg
    ✓ Installed pre-commit hook to .git/hooks/pre-commit
    ✓ Installed prepare-commit-msg hook to .git/hooks/prepare-commit-msg

이어서, 해당 Repo의 git-secrets 설정을 진행합니다. git-secretes에서는 미리 예약되어있는 커맨드옵션 `git secrets --register-aws`를 실행하는 것으로, 자력으로 세세한 설정을 할 필요없이 AWS의 커밋 검사설정을 도입시킬 수 있습니다.

    $ git secrets --register-aws

시험삼아 AWS의 액세스 키(더미)를 넣은 파일을 작성, `git add`하여 커밋해봅니다

    $ touch secret.txt
    $ echo "aws_secret_access_key = ABcDe1F2hIjkl3nop45sTUv6XYz7aBcDEFghIJKL" > secret.txt
    $ git commit -a
    secret.txt:1:aws_secret_access_key = ABcDe1F2hIjkl3nop45sTUv6XYz7aBcDEFghIJKL
    
    [ERROR] Matched one or more prohibited patterns
    
    Possible mitigations:
    - Mark false positives as allowed using: git config --add secrets.allowed ...
    - Mark false positives as allowed by adding regular expressions to .gitallowed at repository's root directory
    - List your configured patterns: git config --get-all secrets.patterns
    - List your configured allowed patterns: git config --get-all secrets.allowed
    - List your configured allowed patterns in .gitallowed at repository's root directory
    - Use --no-verify if this is a one-time false positive
    
    no changes added to commit

무사히  리젝트되었습니다.

또한, 위의 에러메세지에 써져 있듯이, 일시적으로 검사를 제외하고 커밋하고 싶을 경우는 `git commit`커맨드에서 `--no-verify`옵션을 부여하면 됩니다. 또, 기존 Repo에 이후부터 git-secrets를 대응하고 싶을 경우, 과거 commit이력을 검사하고 싶을 경우가 있을 겁니다. 그럴 경우, `git secrets --scan-history`를 실행하는 것으로, git history를 스캔하여 검사할 수 있습니다. 정리하여 아래에 예를 들어보겠습니다

    git commit -a --no-verify
    [master 61d9545] added credential with --no-verify
     1 file changed, 1 insertion(+), 1 deletion(-)
    
     $ git secrets --scan-history
    61d954586149283a4f9ab70355320c15b846aa75:secret.txt:1:aws_secret_access_key = ABcDe1F2hIjkl3nop45sTUv6XYz7aBcDEFghIJKL
    
    [ERROR] Matched one or more prohibited patterns
    
    Possible mitigations:
    - Mark false positives as allowed using: git config --add secrets.allowed ...
    - Mark false positives as allowed by adding regular expressions to .gitallowed at repository's root directory
    - List your configured patterns: git config --get-all secrets.patterns
    - List your configured allowed patterns: git config --get-all secrets.allowed
    - List your configured allowed patterns in .gitallowed at repository's root directory
    - Use --no-verify if this is a one-time false positive

## git secrets —install의 생략

`git init`후 `git secret --install`을 잊지 않게

    $ git secrets --install ~/.git-templates/git-secrets
    $ git config --global init.templatedir '~/.git-templates/git-secrets'

를 실행 후, `git init`시 템플렛을 작성해둡시다. 이것으로 `git init`을 실행 시 `git secrets --install`을 실행한 것과 마찬가지인 상태가 됩니다

## 정리

AWS이외의 경우, 스스로 검출 패턴을 지정하는 것이 좀 귀찮았지만, '자신의 비밀정보를 불시에 공개하지 않게 한다'라고 하는 목적을 위해 git-secrets는 무척 유용합니다. 특히 AWS의 설정에 대해서는 각 Repo들마다가 아닌 `--register-aws --global`로 단말전체의 global설정으로 보험을 걸 수 있다는 것이 장점이라고 생각합니다
