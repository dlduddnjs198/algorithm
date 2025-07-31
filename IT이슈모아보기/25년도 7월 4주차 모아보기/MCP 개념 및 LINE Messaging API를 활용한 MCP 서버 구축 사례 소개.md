# **MCP 개념 및 LINE Messaging API를 활용한 MCP 서버 구축 사례 소개**

## 1️⃣ [🔍 MCP란? 외부 기능을 쓰는 AI를 위한 '프로토콜']

- **MCP (Model Context Protocol)**

  → **Claude 같은 LLM이 외부 API·도구를 직접 호출할 수 있게 도와주는 통신 규약**

- Anthropic이 2024년 말에 발표
    - 누구나 사용할 수 있는 **오픈 프로토콜**
- 기존 LLM처럼 추론만 하는 게 아니라, **진짜 API 요청까지 날릴 수 있음**

🧠 **왜 필요한가?**

> 예: Claude에게 “Armeria에서 특정 기능 찾아줘”라고 하면,
>
>
> 기존 LLM은 이미 학습한 지식으로만 답함 → 정보가 틀릴 수도 있음 (할루시네이션)
>
> **MCP가 연결된 GitHub MCP 서버**가 있다면, Claude가 진짜로 GitHub API에 접속해 코드를 찾고 답해줌.
>

---

## 2️⃣ [🧱 아키텍처: LLM, 클라이언트, MCP 서버의 삼각 구조]

MCP는 기본적으로 호스트의 클라이언트가 서버와 연결되는 클라이언트-서버 아키텍처를 따른다.

| **구성요소** | **설명** |
| --- | --- |
| **호스트** | Claude 같은 LLM 애플리케이션 |
| **클라이언트** | 호스트 내부 모듈, 외부 MCP 서버와 통신 |
| **서버** | 외부 기능/API를 제공하는 서버 (예: GitHub, LINE 등) |

📌 Claude 같은 LLM이 내부적으로 MCP 클라이언트를 통해 외부 MCP 서버와 통신하며 툴을 호출함.

---

## 3️⃣ [🔧 핵심 개념: Tools와 Resources]

MCP 호스트가 사용할 수 있는 요소는 다양하다. 그중 가장 핵심적인 요소는 다음과 같다.

| **요소** | **설명** | **예시** |
| --- | --- | --- |
| **툴 (Tools)** | 특정 기능 실행 (툴 호출용 API 제공) | `create_pull_request`, `broadcast_message` |
| **리소스 (Resources)** | 외부 데이터 제공 (정적 콘텐츠, 파일 등) | GitHub의 `repo/contents` API |

🧠 Claude는 툴 목록을 받아서 **프롬프트의 의도에 맞는 툴을 고르고 호출**하는 방식으로 작동해.

---

## 4️⃣ [⚙️ 작동 방식 요약: Claude가 툴을 호출하는 순서]

1. Claude 데스크톱 앱 실행 → MCP 서버 자동 실행
2. Claude는 tools/list 호출 → 가능한 툴 목록 확보
3. 사용자가 프롬프트 입력 → Claude는 툴 필요 여부 판단
4. 필요하면 tools/call API로 해당 툴 호출
5. 툴 결과 + 프롬프트 → 다시 Claude에 입력
6. Claude가 최종 응답 생성 → 사용자에게 반환

---

## 5️⃣ [💬 실전 예시 1: LINE Messaging API + Claude]

**상황**

- 초밥집 '오이시' 사장님이 LINE 공식 계정을 통해 마케팅 메시지 전송하고 싶음

**구성**

- Claude 데스크톱 앱 (호스트)
- 내가 만든 MCP 서버 (LINE API 연동)
- 툴: `broadcast_message`

**실행 결과**

- Claude가 “홍보 메시지 보내줘” → 내부적으로 LINE API를 호출해서 메시지를 친구들에게 발송

---

## 6️⃣ [⛅ 실전 예시 2: 날씨 기반 초밥 추천 메시지 보내기]

**도입된 새 MCP 서버**

- **Brave Search MCP Server**
    - 날씨 정보를 검색할 수 있는 MCP 툴 제공

**흐름 요약**

1. Claude가 “내일 날씨에 맞는 초밥 홍보해줘” 프롬프트 수신
2. Brave MCP 서버의 `brave_web_search` 툴로 날씨 검색
3. 검색 결과로 메시지 생성
4. LINE MCP 서버의 `broadcast_message` 툴로 메시지 전송

🧠 **포인트**

> 여러 개의 MCP 서버를 조합해서 다중 API 연결 → 복합 응답 생성까지 수행 가능함
>

---

## 7️⃣ [📁 실제 MCP 서버 구축 흐름 요약]

### 설치 및 설정 요약

- Python + `uv` + `FastMCP` 활용
- LINE Messaging API 연동: `broadcast_message` 툴 생성
- 환경 변수에 `CHANNEL_ACCESS_TOKEN` 주입
- Claude 설정 파일(`claude_desktop_config.json`)에 MCP 서버 등록

### 실행 예

```bash
uv run main.py
```

🧠 **Claude 실행과 동시에 MCP 서버도 자동 기동됨**

→ Claude 앱에서 **툴 목록 표시**됨 → 프롬프트 처리 가능

---

## 8️⃣ [🌐 확장성: MCP는 앞으로 어떻게 쓰일까?]

- GitHub, LINE, Brave 외에도 **검색엔진, DB, 날씨, 캘린더, 금융, IoT** 등 확장 가능
- Claude뿐 아니라 **OpenAI, Google도 유사한 시스템 구축 중**
- 앞으로는 “LLM + API 연동”이 **기본 작업 방식**이 될 가능성 큼

---

## ✅ 요약표: MCP 개요

| **항목** | **내용** |
| --- | --- |
| 정식 명칭 | Model Context Protocol |
| 만든 곳 | Anthropic |
| 목적 | LLM이 외부 API나 데이터 소스를 안전하게 사용 |
| 구성 요소 | 호스트 (Claude), 클라이언트, 서버 |
| 핵심 개념 | Tools / Resources |
| 주요 사용 사례 | GitHub 코드 PR, LINE 메시지 발송, Brave 검색 등 |
| 특징 | 오픈 프로토콜, 누구나 MCP 서버 개발 가능 |
| 도입 이유 | 할루시네이션 방지, 실제 데이터 기반 응답 생성 |

---

## ✨ 결론

> MCP는 LLM이 외부 기능을 안전하고 정교하게 사용하는 연결 표준이다.
>
>
> 단순 텍스트 생성에서 벗어나, **진짜 기능을 호출하고 연계하는 차세대 LLM 환경의 기반 기술**로 자리잡고 있다.
>
> Claude + MCP의 조합은 AI를 **"실행자(agent)"로 진화**시키며, 앞으로 API-first 시대의 중심이 될 가능성이 크다.
>

---

**🔗 공식 리소스**

- [LINE 공식 MCP 서버 GitHub](https://github.com/line/line-bot-mcp-server)
- [Anthropic 공식 MCP 설명](https://docs.anthropic.com/mcp)
- [Brave MCP 서버 GitHub](https://github.com/anthropics/mcp-servers)

---

**출처 : https://techblog.lycorp.co.jp/ko/introduction-to-mcp-and-building-mcp-server-using-line-messaging-api**