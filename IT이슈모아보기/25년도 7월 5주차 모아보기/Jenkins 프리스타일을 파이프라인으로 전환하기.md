# Jenkins 프리스타일을 파이프라인으로 전환하기

## 🧰 배경 설명

**Jenkins**는 오픈소스 자동화 도구로, 코드 변경 시 자동으로 **빌드 → 테스트 → 배포(CI/CD)** 를 수행할 수 있게 도와준다. Jenkins에서 작업을 구성하는 방식은 크게 두 가지가 있다:

- **프리스타일(Freestyle)**: GUI 기반, 간단하지만 유연성 부족
- **파이프라인(Pipeline)**: 코드(Groovy 기반)로 작성, 조건·병렬·버전관리 등 강력한 기능 제공

파이프라인 방식에서는 Groovy 언어로 **Jenkinsfile**을 작성해 자동화 흐름을 정의하며, 실무에선 유지보수와 협업을 위해 대부분 이 방식을 사용한다.

Groovy는 **Java 기반의 스크립트 언어**로, 문법이 간결하고 Java와 100% 호환된다.

---

## 1️⃣ [🧱 Jenkins 프리스타일 vs 파이프라인, 뭐가 다른가?]

- **프리스타일(Freestyle)**: GUI 기반으로 단계별 작업을 설정하는 방식 (직관적이나 유연성 ↓)
- **파이프라인(Pipeline)**: 코드를 기반으로 빌드, 테스트, 배포까지 자동화하는 방식 (유연성 ↑, Git에 기록 가능)

> ❓ 이름은 ‘프리스타일’인데, 사실 자유도는 파이프라인이 훨씬 높다.
>

---

## 2️⃣ [📦 코드 가져오기: Git → Jenkins]

- **CI 시작의 핵심** = “코드가 있어야 뭔가 한다.”
- Jenkins에서 코드를 가져오는 방법은 **사실상 Git 연동이 대부분**.

### ✅ 프리스타일 방식

> Git Repository 설정 탭에서
>
>
> `https://github.com/sparta-MOIM/MOIM-Server.git` 와 브랜치 `develop` 설정
>

### ✅ 파이프라인 방식

```groovy
stage('Git Clone') {
    steps {
        git branch: 'develop', url: 'https://github.com/sparta-MOIM/MOIM-Server.git'
    }
}
```

> 📁 코드가 내려오는 위치:
>
>
> `/var/lib/docker/volumes/jenkins_jenkins_home/_data/workspace/프로젝트명/`
>

🧠 **팁**

- `sudo find / -name "*.jar"`로 `.jar` 파일 위치 확인 가능
- IDE에서 실행해도 내부적으로는 빌드 → 실행 과정이 포함됨

---

## 3️⃣ [⚙️ 빌드: Gradle이나 Maven으로 JAR 만들기]

- 직접 `.java` 파일을 옮기기보다는, **.jar 파일로 빌드해서 옮기는 게 훨씬 간단**
- Jenkins는 `build.gradle`이나 `pom.xml`을 보고 어떤 빌드 도구를 쓸지 판단

### ✅ 파이프라인 예시 (Gradle 사용 시)

```groovy
stage('Gradle Build') {
    steps {
        sh "./gradlew :${SERVICE_NAME}:clean :${SERVICE_NAME}:build -x test"
    }
}
```

> -x test: 테스트 생략
>
>
> `${SERVICE_NAME}`은 환경변수로 정의
>

---

## 4️⃣ [🚚 배포(Deploy): 서버에 jar 파일 전송]

### 🔐 EC2에 접속하려면?

- `ssh -i key.pem ubuntu@ip` 필요
- Jenkins에서는 pem 파일 경로를 지정하기 번거로움 → **플러그인 활용**

---

## 5️⃣ [🔌 방법 ①: Publish Over SSH 플러그인]

- Jenkins 서버에 pem 키 등록 후 사용
- 프리스타일에서는 GUI로 jar 파일 경로, 원격 디렉토리, 실행 명령 입력 가능

> ❗ 파이프라인에서는 잘 동작하지 않는 경우 있음
>

---

## 6️⃣ [🔐 방법 ②: SSH Agent 플러그인 (추천)]

- **파이프라인 전용**
- 여러 pem 키를 등록해 다수 서버 대응 가능

### ✅ 파이프라인 예시

```groovy
stage('SSH 접속 및 전송') {
    steps {
        sshagent (credentials: ['ec2-ssh-key']) {
            sh "scp -o StrictHostKeyChecking=no ${JAR_FILE} ubuntu@${server}:${REMOTE_PATH}/"
        }
    }
}

stage('Run Deploy Script') {
    steps {
        sshagent (credentials: ['ec2-ssh-key']) {
            sh """
            ssh -o StrictHostKeyChecking=no ubuntu@${server} '
            chmod +x /home/ubuntu/deploy/deploy.sh &&
            /home/ubuntu/deploy/deploy.sh
            '
            """
        }
    }
}
```

> deploy 오타: ‘deloly.sh’ → ‘deploy.sh’
>
>
> EC2에 사전에 배포 스크립트를 올려놔야 함
>

---

## 7️⃣ [📜 Jenkins Pipeline 문법 정리]

### ✅ 기본 구조

```groovy
pipeline {
    agent any

    environment {
        SERVICE_NAME = 'chat-service'
        REMOTE_PATH = '/home/ubuntu/deploy'
        JAR_FILE = "build/libs/${SERVICE_NAME}.jar"
        server = '1.2.3.4'  // EC2 IP
    }

    stages {
        stage('Git Clone') { steps { git ... } }
        stage('Build') { steps { sh './gradlew build' } }
        stage('Deploy') { steps { sshagent { sh '...' } } }
    }
}
```

---

## 8️⃣ [✨ 유용한 기능들 요약]

| 기능 | 설명 | 예시 |
| --- | --- | --- |
| `environment` | 전역 변수 정의 | `SERVICE_NAME = 'chat-service'` |
| `parameters` | 유저가 입력한 값 받기 | 배포 환경 선택, 롤백 여부 등 |
| `when` | 조건부 실행 | 특정 브랜치일 때만 배포 |
| `triggers` | 자동 빌드 트리거 | `githubPush()`, `cron(...)` |
| `tools` | Gradle, Maven 등 지정 | `tools { gradle 'Gradle-8.5' }` |
| `post` | 빌드 후 처리 | 실패 시 슬랙 알림 등 |
| `parallel` | 병렬 Stage 실행 | 단위/통합 테스트 병렬 실행 |

---

## 9️⃣ [📌 실전 예제: CI/CD Pipeline 요약 코드]

```groovy
pipeline {
    agent any

    environment {
        SERVICE_NAME = 'chat-service'
        REMOTE_PATH = '/home/ubuntu/deploy'
        JAR_FILE = "build/libs/${SERVICE_NAME}.jar"
        server = '1.2.3.4'
    }

    stages {
        stage('Git Clone') {
            steps {
                git branch: 'develop', url: 'https://github.com/user/repo.git'
            }
        }

        stage('Gradle Build') {
            steps {
                sh "./gradlew :${SERVICE_NAME}:clean :${SERVICE_NAME}:build -x test"
            }
        }

        stage('Transfer Jar') {
            steps {
                sshagent (credentials: ['ec2-ssh-key']) {
                    sh "scp -o StrictHostKeyChecking=no ${JAR_FILE} ubuntu@${server}:${REMOTE_PATH}/"
                }
            }
        }

        stage('Run Deploy') {
            steps {
                sshagent (credentials: ['ec2-ssh-key']) {
                    sh """
                    ssh -o StrictHostKeyChecking=no ubuntu@${server} '
                    chmod +x ${REMOTE_PATH}/deploy.sh &&
                    ${REMOTE_PATH}/deploy.sh
                    '
                    """
                }
            }
        }
    }
}
```

---

## 🔚 마무리: Jenkins CI/CD 전환 핵심 요약

| 구분 | 프리스타일 | 파이프라인 |
| --- | --- | --- |
| 코드 연동 | Git 탭 설정 | `git branch...` 코드 작성 |
| 빌드 실행 | Gradle/Maven GUI 설정 | `sh "./gradlew build"` |
| 배포 방식 | Publish Over SSH 주로 사용 | `sshagent + scp + ssh` |
| 확장성 | 낮음 (GUI 제한) | 높음 (스크립트 기반 자유도) |

> Jenkins를 제대로 쓰려면 결국 프리스타일 → 파이프라인 전환은 필수!
>
>
> 자동화된 배포 시스템을 만들기 위한 최소 조건이기도 하다.
>