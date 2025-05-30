# 게임 컨셉
- ### High Concept
    점프를 통해 장애물을 뛰어넘어 목적지에 도달하여 클리어 하는 기록형 게임 (Geometry Dash 모작)

- ### 핵심 메카닉
    기본적으로 플레이어 캐릭터는 맵 좌->우 로 이동한다. \
    화면의 아무 곳이나 터치하면 플레이어 캐릭터가 일정 높이 점프하여 지형지물을 극복하고 장애물을 회피한다. \
    플레이어-지형지물, 플레이어-장애물, 플레이어-상호작용 패달의 충돌 체크.


# 개발 범위
- ### 버튼
    게임 시작 버튼 \
    게임 종료 버튼 \
    스테이지 선택 버튼 \
    다른 스테이지를 선택할 수 있는 버튼 \
    시작화면으로 돌아가는 버튼 \
    점프 버튼 \
    다시 시작 버튼 \
    스테이지 선택 화면으로 돌아가기 버튼  

- ### 충돌 처리
    플레이어 - 지형지물 충돌 처리 \
    플레이어 - 상호작용 패달 충돌 처리 \
    플레이어 - 장애물 충돌 처리 \
    플레이어 - 클리어 포탈 충돌 처리  

- ### UI
    메인 화면 UI \
    스테이지 선택 화면 UI \
    인게임 UI (점수) \
    게임 종료 시 출력할 UI (클리어, 실패)  

- ### 레벨 디자인
    두 개 이상의 스테이지 구현


# 예상 게임 실행 흐름
- ### 시작 화면
    게임 시작 버튼(->스테이지 선택 화면), 게임 종료 버튼(->게임 종료)  
    ![시작 화면](https://github.com/808Jade/Geometry_Run/blob/master/readme_img/start_scene.png)

- ### 스테이지 선택 화면
    각 스테이지를 시작할 수 있는 버튼(->해당 스테이지 게임 시작), 시작화면으로 돌아가는 버튼(->시작 화면), 다른 스테이지를 선택할 수 있는 버튼
    ![스테이지 선택 화면](https://github.com/808Jade/Geometry_Run/blob/master/readme_img/stage_select_scene.png)

- ### 게임 화면
    플레이어, 지형지물, 상호작용 패달  
    ![게임 화면](https://github.com/808Jade/Geometry_Run/blob/master/readme_img/ingame_scene.png)

- ### 게임 종료 시 화면
    클리어 여부, 점수, 다시 시작 버튼(->현재 스테이지 다시 시작), 스테이지 선택 화면으로 돌아가기 버튼(->스테이지 선택 화면)
    ![게임 종료 시 화면](https://github.com/808Jade/Geometry_Run/blob/master/readme_img/gameover_scene.png)


# 개발 일정
|주차(날짜)|개발 내용|진행도|
|:--|:--:|:--:|
|1 (4/ 7)| 리소스 수집 | 100% |
|2 (4/14)| 시작 화면, 스테이지 선택 화면 구현 | 50% |
|3 (4/21)| 플레이어 구현(점프, Spaceship 모드) | 50% |
|4 (4/28)| 맵 구현(지형지물) | 50% |
|5 (5/ 5)| 맵 구현(장애물) | 50% |
|6 (5/12)| 충돌 처리(플레이어-지형지물,장애물) | 50% |
|7 (5/19)| 충돌 처리(플레이어-상호작용 패달) | 0% |
|8 (5/26)| 최종 테스트 | 0% |

# 주별 커밋 수
![커밋 수](https://github.com/808Jade/Geometry_Run/blob/master/readme_img/commits.png)
|주차(날짜)|커밋 수|
|:--|:--:|
|1 (4/ 7)| 10 |
|2 (4/14)| 2 |
|3 (4/21)| 0 |
|4 (4/28)| 0 |
|5 (5/ 5)| 0 |
|6 (5/12)| 8 |

# 변경된 목표
없음

# 게임 화면
 ![게임 실행 시 화면](https://github.com/808Jade/Geometry_Run/blob/master/readme_img/startscene.png)
 ![게임 시작 화면](https://github.com/808Jade/Geometry_Run/blob/master/readme_img/gamescene.png)

# MainScene 에 등장하는 game object
|Object|구성 정보|상호작용 정보|클래스의 책임|
|:--|:--:|:--:|:--:|
|Player| 위치, 상태, 중력, 점프 파워, 충돌사각형 | 장애물, 패달, FloorBlock과 상호작용 | 사용자의 점프 명령 처리 |
|Obstacle| 위치, 충돌사각형 | 플레이어와 충돌 상호작용 | 장애물 위치 갱신 |
|Floor| 위치, y정보 | 플레이어의 y좌표를 제한함 | 플레이어의 y좌표를 제한함 |
|FloorBlock| 위치, 충돌 사각형 | 플레이어가 밟을 수 있는 발판이자, 측면에 충돌 시 장애물로서의 역할도 함 | FloorBlock위치 갱신 |
|JumpPedal| 위치, 충돌 사각형, 점프 파워 | 플레이어와 상호작용하여 플레이어를 높이 점프시킴 | 점프 파워만큼 플레이어를 점프시킴 |
|MapLoader| Scene, floor, item, obstacle | Scene과 상호작용하여 Map을 효과적으로 로드함 | Map의 효과적인 로드 |
|CollisionChecker| Scene, player | Scene(에서 제공하는 객체목록), Player과 상호작용 | Scene의 오브젝트들과 플레이어 간의 충돌을 처리함 |

# 구현이 특히 어려웠던/어려운 부분
아직 개발 중이지만, 플레이어가 회전 중에 FloorBlock을 만났을 때, FloorBlock위로 올라가기/플레이어 사망하기 를 결정하고 처리하는 부분이 어렵다.








