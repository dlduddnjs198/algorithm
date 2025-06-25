# 코딩 에이전트 구축을 위해 LangGraph를 선택한 이유

## 🛠 Qodo의 코딩 보조 에이전트: LangGraph로 유연성과 품질 모두 잡기

---

### 📌 배경: 초기 구조화된 워크플로우에서 동적인 시스템으로

- GPT-3 시절부터 AI 코딩 보조 도구를 개발해오던 Qodo는,
- 초기에는 `테스트 생성`, `코드 리뷰`, `코드 개선` 등을 **정해진 흐름 (rigid workflow)** 으로 처리.
- 당시 LLM의 한계로 인해, **명확한 플로우**와 **도메인 지식 내장**이 중요했음.

하지만,

- Claude Sonnet 3.5 출시 이후 LLM의 능력이 비약적으로 향상되며,
- **정해진 흐름 대신 유연하게 사용자 요청을 처리하는 에이전트형 시스템**의 가능성이 열림.

👉 목표: **유연하면서도 Qodo의 코드 품질 기준을 유지할 수 있는 시스템** 구축

### ✅ 코딩 에이전트(Coding Agent)란?

**🔹 개념**

**코딩 에이전트**는 GPT-4 같은 **대규모 언어 모델(LLM)** 을 기반으로 동작하는 AI 도우미로, 단순 코드 자동완성 수준을 넘어서 *프로그래밍 전 과정에 개입할 수 있는 에이전트(지능형 실행자)*

**🔹 무슨 일을 하냐면…**

- **사용자 의도를 분석**하고,
- **필요한 정보를 수집**하고,
- **작업 계획을 세우고**,
- **코드를 생성**하고,
- **테스트하거나 리뷰**하고,
- **필요시 다시 고쳐서** 결과를 완성

예를 들어, 사용자가 “슬랙 봇 만들어줘”라고 하면,

> 관련 API 문서를 찾고, 구조를 설계하고, Python으로 코드를 짜고, 테스트까지 수행하는 게 코딩 에이전트의 역할
>

---

### ✅ 랭체인(LangChain)이란?

**🔹 개념**

**LangChain**은 LLM(예: GPT-4)을 이용한 다양한 에이전트와 앱을 만들기 위한 **Python 프레임워크이다**.

LLM을 쓸 때는 보통 다음과 같은 것들을 다뤄야 한다:

- 사용자 질문을 이해하기
- 툴(예: 계산기, DB, API 등) 사용
- 메모리 관리 (과거 대화 기억)
- 복잡한 작업을 분할해서 처리

LangChain은 이를 쉽게 만들 수 있도록 **기능 모듈과 추상화**를 제공해준다.

**🔹 LangChain이 하는 일 예시**

- “검색 → 요약 → 코드 생성” 같은 다단계 워크플로우 관리
- 툴 사용 (예: Google 검색, 코드 실행기)
- 체인 구성 (작업 단계별 구성 가능)

---

### ✅ 랭그래프(LangGraph)란?

**🔹 개념**

**LangGraph**는 LangChain에서 파생된 프로젝트로, 에이전트의 작업 흐름을 **그래프(노드와 엣지로 구성된 상태 머신)** 형태로 설계하게 해준다.

즉, LLM의 행동을 **구조적으로 표현**하고, 복잡한 플로우를 명확하게 다룰 수 있게 해주는 툴이다다.

**🔹 왜 그래프 구조를 쓰냐면?**

- 기존 LangChain만으로는 워크플로우가 길어질수록 **복잡해지고 유지보수가 어려움**
- LangGraph는 각 단계를 노드로 분리하고, 조건부 흐름을 명확히 연결할 수 있음
    - 예: `코드 생성 → 테스트 → 실패시 다시 생성 → 통과시 종료`

**🔹** LangGraph는 **상태 기반 그래프(StateGraph)** 로 워크플로우를 정의한다:

- **노드(Node)**: 각 단계의 기능 (예: 컨텍스트 수집, 플래닝, 실행, 검증 등)
- **에지(Edge)**: 단계 간 전이 조건 및 흐름
- **조건 분기(Conditional Edge)**: 예를 들어, 검증 실패 시 다시 실행 노드로 루프

```python
workflow = StateGraph(name="coding_assistant")
workflow.add_node("context_collector", collect_relevant_context)
workflow.add_node("task_planner", create_execution_plan)
workflow.add_node("task_executor", execute_plan)
workflow.add_node("validator", validate_output)

workflow.add_edge("context_collector", "task_planner")
workflow.add_edge("task_planner", "task_executor")
workflow.add_edge("task_executor", "validator")
workflow.add_conditional_edges("validator", should_revise, {
    True: "task_executor",
    False: END
})

```

> ✅ 이 방식은 워크플로우를 선언적으로 정의할 수 있고, 코드 자체가 다이어그램처럼 읽힘.
>

---

### ✅ 왜 Qodo는 LangGraph를 선택했는가?

**🔹 Qodo 팀의 상황**

- Qodo는 GPT-3 시절부터 코딩 에이전트를 만들어 온 팀이다.
- 초기에는 “**테스트 코드 생성**”, “**리팩토링**”, “**코드 리뷰**” 같은 **정형화된 플로우**로 구성된 워크플로우를 썼음.
- 그런데 Claude 3.5, GPT-4 같은 최신 모델이 등장하면서, **더 자유롭고 유연한 인터페이스**를 시도할 수 있게 되었다.

**🔹 요구사항**

- 유저가 어떤 요청을 해도 잘 대응하는 **유연한 흐름**
- 하지만 Qodo 내부의 개발 철학(코딩 스타일, 품질 기준)은 유지할 수 있는 **구조화된 통제력**

**🔹 그래서 LangGraph가 딱이었던 이유**

| **필요** | **LangGraph가 해준 것** |
| --- | --- |
| 유연성과 구조의 균형 | 그래프 기반 상태 머신으로 구현 가능 |
| 테스트, 리팩토링 같은 정형 태스크 지원 | 각 노드를 재사용 가능 (예: validation node) |
| 결과 검증 및 반복 흐름 | 실패 시 특정 노드로 루프 가능 |
| 다양한 플로우 실험 | 플로우 연결만 바꾸면 새로운 기능 구성 가능 |
| 상태 저장 | Postgres, SQLite로 중간 상태 저장 가능 (Checkpoints) |

**🔹 실제 플로우 예시**

```
[Context 수집] → [계획 수립] → [코드 생성] → [결과 검증]
                                           ↑
                                           └─── 실패 시 다시 코드 생성

```

LangGraph는 이 과정을 코드 몇 줄로 간결하게 선언할 수 있게 해줘.

---

### 📌 실제 적용한 구조

Qodo의 주 워크플로우는 다음과 같은 4단계 구성:

1. **Context Collector**: 코드베이스 및 외부 리소스(MCP 연동)에서 필요한 맥락 수집
2. **Task Planner**: 문제를 하위 작업 단위로 나눔 (사고 체계 구성)
3. **Executor**: 실제 코드 생성
4. **Validator**: 코드 품질과 요구사항 충족 여부 검증

   ⮕ 실패 시 피드백 포함하여 Executor로 재진입 (루프)


---

### 📌 구조적 유연성 = “Opinionated yet adaptable”

- LangGraph는 **선형 흐름 (rigid flow)** 도 만들 수 있고,
- 반대로 모든 노드가 서로 연결된 **동적 에이전트 (dense graph)** 도 가능.

> 💡 노드 연결 밀도(density) 에 따라 구조화 정도를 조절할 수 있음
>
>
> → 지금은 반유연 구조지만, 미래에 더 강력한 LLM이 등장하면 구성 변경 가능
>

---

### 📌 공통 노드 재사용: 생산성 향상의 핵심

- 각 노드는 **다른 플로우에서도 쉽게 재사용** 가능.
    - `context_collector`, `validator` 노드는 TDD 기반 플로우 등에서도 그대로 활용.
- 재사용 가능한 구성요소가 늘어날수록 **워크플로우 확장 속도**도 증가.

---

### 📌 상태 저장 & 복원 (State Persistence)

LangGraph는 **워크플로우 상태 저장 기능도 내장**:

```python
from langgraph.checkpoint.postgres import PostgresSaver

checkpointer = PostgresSaver.from_conn_string(
    "postgresql://user:password@localhost:5432/db"
)
checkpointer.setup()
graph = workflow.compile(checkpointer=checkpointer)
```

- 몇 줄의 코드로 **Postgres 기반 상태 저장** 가능.
- 지원되는 저장소: PostgreSQL, SQLite, In-Memory
- 단순 세션 저장뿐 아니라 **체크포인트와 브랜치 관리 기능**도 제공
    - 이전 단계로 롤백하거나 변경 사항을 시뮬레이션 가능

---

### 📌 한계와 과제

**1. 문서화 부족**

- 프레임워크 자체가 빠르게 발전 중이라 **문서가 부족하거나 갱신 안 된 경우 있음**
- Slack에서 직접 개발자(Harrison, Nuno)와 커뮤니케이션이 필요할 때도 있음

**2. 테스트 환경 구축 어려움**

- LLM 기반 에이전트는 **비결정성(nondeterminism)** 때문에 자동화 테스트가 어려움
- IDE와의 상호작용까지 포함된 흐름은 **mocking이 매우 제한적**
- 현재는 모의 repo 환경을 사용해 테스트하지만 실제 IDE 환경과 100% 동일하지 않음

---

### ✨ 시사점

- LangGraph는 **복잡한 AI 에이전트 워크플로우를 구조화하면서도 유연하게 조정할 수 있는 프레임워크**로 뛰어난 선택지
- Qodo의 사례는, **“AI 코딩 도구는 구조화된 사고를 어떻게 설계할 수 있느냐”** 가 중요한 시대에 LangGraph가 실용적 도구가 될 수 있음을 보여줌
- 다만, **문서와 테스트 인프라 측면에서 아직 성숙하지 않았으며**, 실무 환경에서 직접 구현 능력이 요구됨

출처 : https://www.qodo.ai/blog/why-we-chose-langgraph-to-build-our-coding-agent/