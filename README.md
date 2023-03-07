# 전국 축제 알리미 앱(축제로)
공공 데이터 API를 이용한 전국 지역 축제 정보 알리미 안드로이드 앱 프로젝트

시연영상 : https://youtu.be/qQepOlb0urM

## 📁 프로젝트 소개
'축제로'는 사용자가 손쉽게 찾고 싶은 지역축제의 정보를 검색, 조회할 수 있고 또한 사용자의 축제 참여를 위한 여러 가지 부가 기능을 제공하는 앱으로서 사용자 친화적인 앱을 만드는 것을 목표로 한다. 그러므로 사용자가 손쉽게 사용할 수 있고, 별도의 설명 없이도 알기 쉽도록 손쉬운 조작과 사용의 편리성, 사용자 친화적 UI에 초점을 맞추어 개발하고자 하였다.

## :bulb: 개발 개요
### :two_women_holding_hands: 개발 인원
4명
(본인이 로그인, 회원가입, PW찾기, 로그아웃, 축제 리뷰, 포인트, 마이페이지 등 담당함)

### :hourglass: 개발 기간
2022/07/05 ~ 2022/10/28

### ⚙️ 개발 환경
* 언어 : Java
* IDE : Andorid Studio
* DB : firebase, SQLite
* API : 전국문화축제표준데이터 Open API, Naver Map API, Kakao Link API
* 테스트 환경 : 삼성 갤럭시 S10

## :pushpin: 주요 기능
### 1. 로그인
### 2. 회원가입
### 3. 비밀번호 찾기(변경)
### 4. 마이페이지
* 나의 댓글
* 찜한 목록
### 5. 메인화면
### 6. 축제 정보 조회
* 지도 모드
* 리스트모드
### 7. 축제 검색
### 8. 축제 정보 창
* 전화 연결
* 좋아요
* 홈페이지 이동
* 길찾기
* 지도 보기
* 카카오톡 공유
* 현재 진행 중인 축제 표시
### 9. 축제 리뷰
* 리뷰 조회(포인트 사용)
* 리뷰 작성(포인트 적립)
* 리뷰 수정 및 삭제
### 10. 포인트
* 포인트 적립
* 포인트 차감
### 11. 스케줄러
* 일정 등록
* 캘린더
* 일정 수정 및 삭제

## 1. 로그인
<img src = "https://user-images.githubusercontent.com/122970061/222653820-a33995ea-abcb-4f9a-bb55-d6776a2f0e44.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223120675-4c2413df-805e-4b66-b0ab-55e02666a279.png" width="24%" height="24%">
* 로그인이 성공 하면 메인 화면으로 전환 된다.
* 비로그인 상태이면 로그인 없이 사용할 수 없는 기능 사용 시 로그인 후 이용 가능 하다는 메시지를 출력한다. 


## 2. 회원가입
<img src = "https://user-images.githubusercontent.com/122970061/222707085-da152aaf-cd02-462a-80e1-0c662f3d4549.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/222707307-3c011248-bcce-42ee-9065-2f341d9f50e9.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/222707416-c15323a0-1f5f-4769-8d24-98df030c63bc.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223485737-a92b7ecf-30ef-4f5a-b692-d7e2f0defa79.png" width="24%" height="24%">
* 입력 되지 않은 칸이 있을 경우 입력 되지 않은 칸에 입력 해달라는 에러 메시지를 출력한다.
* 파이어베이스에 이미 저장된 아이디를 입력한 경우 "이미 가입된 아이디입니다." 라는 메시지를 출력한다.
* 비밀번호를 8자 미만으로 입력 시 8자 이상으로 입력해달라는 메시지를 출력한다.
* 입력한 회원정보가 파이어베이스 서버에 정상적으로 저장 되었을 경우 메인화면으로 전환되며 "회원가입이 완료되었습니다" 라는 메시지를 출력한다.


## 3. 비밀번호 찾기
<img src = "https://user-images.githubusercontent.com/122970061/223056062-942a189e-3c2c-4743-9a49-fbf24f3fc023.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223107167-f313782f-afbf-4458-8fef-eeb8dcbcb578.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223109620-92561788-4bb3-45b4-93c9-ea3d8d6af2dd.png" width="24%" height="24%">
* 가입된 아이디(이메일)을 입력하면 입력한 이메일으로 비밀번호를 재설정 할 수 있는 링크를 보낸다.
* 가입되지 않은 이메일 입력 시 가입되지 않은 이메일이라는 메시지를 출력한다.

## 4. 마이페이지
<img src = "https://user-images.githubusercontent.com/122970061/223120872-d1474f66-03e6-4c8e-91a9-3c0216a7a56d.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223121494-ad0921b8-da54-4440-932e-af561176ffa0.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223121662-000cc7eb-aca6-4e94-b106-3c5e67f4b66f.png" width="24%" height="24%">
* 가입할 때 입력한 회원정보와 포인트 정보를 확인할 수 있다.
* 자신이 작성한 댓글을 한번에 볼 수 있으며 이 창에서 바로 수정과 삭제가 가능하다.
* 자신이 좋아요 누른 축제를 한 번에 볼 수 있으며 좋아요 한 축제 목록에서 축제 클릭시 축제 정보 창으로 바로 넘어간다.


## 5. 메인화면
<img src = "https://user-images.githubusercontent.com/122970061/223141936-ad7457f7-2209-4941-981c-ec3e8497ea48.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223142166-d78da336-f716-46ac-98d7-a4ca63cbcee4.png" width="24%" height="24%">
* 상단의 축제 검색 창이 있으며 디폴트 화면은 지도 모드이다.
* 좌측 상단에 드로어 메뉴를 열 수 있는 아이콘을 클릭하면 드로어 메뉴 창이 열린다.


## 6. 축제 정보 조회
### 6-1. 지도 모드
<img src = "https://user-images.githubusercontent.com/122970061/223145506-82db137b-cc4a-459f-966c-a006c955bc78.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223145908-337fbf99-87a5-4c44-8cb6-6c6c16202498.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223150198-8e127874-380e-4b00-aa02-07e3fdc507ae.png" width="24%" height="24%">
* 전국의 시/도 단위의 지도에서 사용자가 조회하고자 하는 지역을 클릭하면 해당 도의 행정구역 단위의 지도가 출력된다.
* 잘못 클릭한 경우를 위해 도의 행정구역 지도화면에서 전체 지도 보기 버튼을 누르면 다시 전국 단위 지도가 출력된다.
* 지역 축제가 개최 되고 있는 곳에만 .
* 조회 하고자 하는 행정구역 클릭시 그 행정구역에서 개최되고 있는 축제들이 리스트로 출력된다.


### 6-2. 리스트모드
<img src = "https://user-images.githubusercontent.com/122970061/223153392-e79b82fc-30da-419e-9176-a4a65ae67d59.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223153801-36bea627-5bfd-4355-99b3-a957c9dc82cb.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223154129-1b1debba-0b23-4c48-9411-d8155d49fa64.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223154422-17b5aeaa-1661-400d-8329-4a103b0b5d11.png" width="24%" height="24%">
* 상단의 MAP / LIST 탭 중 LIST 탭을 선택하면 전체, 축제테마, 이달의 축제로 나눠서 볼 수 있고 기본은 전체 축제를 볼 수 있는 페이지에 고정되어 있다.
* 전체 페이지에서는 월별로 나누어서 볼 수 있는 스피너 위젯으로 개최되는 월 기준으로 축제들을 필터링 할 수 있다.
* 축제테마 페이지에서는 바다, 꽃, 자연, 액티비티, 문화예술 카테고리가 존재하여 이 중 사용자가 원하는 카테고리를 선택하면 해당 카테고리와 연관된 모든 축제 정보를 리스트 형태로 보여준다.
* 이달의 축제 페이지는 현재 앱이 실행되고 있는 시간의 달을 인식하여 해당 달에 개최되는 축제 정보들을 리스트 형태로 보여준다.


## 7. 축제 검색
<img src = "https://user-images.githubusercontent.com/122970061/223155856-1744db34-a261-403f-ba58-6e75ee81faa5.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223156008-307d85bb-8f04-4880-b5c0-b6c6fa7c0f95.png" width="24%" height="24%">
* 사용자가 검색하고자 하는 키워드를 메인화면 상단 검색 창에 입력하면 입력한 키워드를 포함하는 축제가 리스트 형태로 출력된다.


## 8. 축제 정보 창
<img src = "https://user-images.githubusercontent.com/122970061/223169776-22a39807-a046-461a-9e78-6f55da98de04.png" width="24%" height="24%">
* 축제 정보 리스트에서 축제를 클릭하면 위와 같은 해당 축제의 정보 창이 출력된다.

### 8-1. 전화 연결
<img src = "https://user-images.githubusercontent.com/122970061/223170328-80acf256-d0d7-42be-bb90-162f40321d9f.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223170801-918bb6b5-1df6-4bd5-af26-c223a46042f4.png" width="24%" height="24%">
* 표시된 전화 아이콘을 클릭하면 즉시 해당 축제의 전화번호가 입력된 전화 창으로 연결되어 바로 전화를 할 수 있도록 연결한다.


### 8-2. 좋아요
<img src = "https://user-images.githubusercontent.com/122970061/223171858-684e5220-7f9a-4832-b724-194578ca9d7e.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223171975-9f6ff325-5055-487c-af3a-d0d43b1a67a6.png" width="24%" height="24%">
* 표시된 하트 아이콘을 클릭하면 해당 축제가 좋아요 처리되어 마이페이지의 찜한 목록에서 확인 가능하다.
* 로그인 된 상태에서 이용이 가능하며 비로그인 상태에서는 좋아요 버튼 클릭이 비활성화 되어있어 버튼을 클릭할 수 없다.


### 8-3. 홈페이지 이동
<img src = "https://user-images.githubusercontent.com/122970061/223188002-126c9fdf-1866-46ed-97d9-8703fb25ca58.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223172989-292ffda7-19cc-4ee8-9428-cebb4da041b0.png" width="24%" height="24%">
* 표시된 홈페이지 아이콘을 클릭하면 해당 축제의 공식 홈페이지로 연결된다.


### 8-4. 길찾기
<img src = "https://user-images.githubusercontent.com/122970061/223177220-68df2bb1-e605-4a71-abd0-00bec0d9a40d.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223177979-6ba70074-5658-4800-9511-81f5e95e98a7.png" width="24%" height="24%">
* 표시된 지도 아이콘을 클릭하면 현재 사용자의 위치를 인식하고 축제의 위도 경도 정보를 이용하여 축제의 좌표를 구하여 네이버 지도 길찾기 기능으로 바로 넘어간다.


### 8-5. 지도 보기
<img src = "https://user-images.githubusercontent.com/122970061/223184046-d022c3fa-72fb-4f22-8876-836e4b66613c.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223184232-6055c72a-f0d5-4fff-b2d2-129952c9b36c.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223184378-0456cd66-853f-49eb-925e-408f7d432a9e.png" width="24%" height="24%">
* 표시된 지도보기 버튼을 클릭하면 네이버 지도 API를 이용해 위도, 경도 정보만 있는 경우 위도, 경도 정보를 이용하여 주소를 알아내고, 주소만 있는 경우 주소 정보를 이용하여 위도, 경도를 구하여 좌표를 구해 마커를 표시한다.
* 우측 하단의 좌표 아이콘 클릭시 현재 사용자의 위치 주변의 지도를 표시한다.


### 8-6. 카카오톡 공유
<img src = "https://user-images.githubusercontent.com/122970061/223188623-cb06cae2-b75c-47fe-b22a-c16fac8a0483.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223185278-21f1d317-a7b5-4b7a-8847-5ed6283fbc9b.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223185516-16ed4e86-4ef1-4b52-9d53-75052c7cd027.png" width="24%" height="24%">
* 표시된 공유하기 버튼을 클릭하면 바로 카카오톡 앱으로 이동하여 공유 대상을 선택하는 창으로 연결된다.
* 공유할 대상 선택 후 확인 버튼을 클릭하면 공유할 축제명, 해당 축제 기간, 주소, 앱 연결 링크가 해당 대화방에 전송된다.


### 8-7. 현재 진행 중인 축제 표시
<img src = "https://user-images.githubusercontent.com/122970061/223186415-a57d6f07-a68d-4f15-b59b-3cb018d754f7.png" width="24%" height="24%">
* 현재 날짜에 진행 중인 축제이면 우측 상단에 진행중이라는 표시를 제공한다.
* 그렇지 않은 축제이면 아무런 표시가 없다.


## 9. 축제 리뷰
### 9-1. 리뷰 조회(포인트 사용)
<img src = "https://user-images.githubusercontent.com/122970061/223361069-6c5b7923-e221-461b-a711-393bb2182f66.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223361531-2d8109f1-e5d2-4866-b2f0-72bdbecb100f.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223361288-2f87dbaa-95d4-4b3f-950e-cf43f326b96b.png" width="24%" height="24%">
* 축제 정보 창 맨 밑 하단에 댓글이 있다.
* 카드형식으로 된 미리보기로 댓글 3개를 볼 수 있다.
* 더보기 버튼 클릭시 해당 축제 댓글을 열람하기 위한 포인트를 사용하지 않았을 경우에 포인트 결제 창이 출력된다.
* 포인트가 있을 경우, 포인트 사용 버튼 클릭시 포인트가 차감되었다는 메시지와 함께 포인트가 차감되며 해당 축제의 모든 댓글이 리스트 형태로 출력된다.
* 이미 해당 축제에 포인트를 사용한 경우에는 더보기 버튼 클릭시 바로 해당 축제의 모든 댓글이 리스트 형태로 출력된다.

### 9-2. 리뷰 작성(포인트 적립)
<img src = "https://user-images.githubusercontent.com/122970061/223364671-c64dec65-c267-493e-ad50-73bc63b28374.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223364535-7a9bd5ae-af74-4c3b-a9d7-cadc629c2a4c.png" width="24%" height="24%">
* 댓글 창 하단의 등록하기 버튼 클릭시 리뷰를 입력할 수 있는 입력 창이 출력된다.
* 입력 창에 내용 입력 후 우측 상단의 펜 아이콘을 클릭하면 즉시 파이어베이스 서버에 댓글이 등록된다.
* 댓글 등록 시 해당 회원의 포인트가 10 포인트 적립된다.
* 추후 코드 수정으로 등록된 댓글이 등록 시간을 기준으로 내림차순 출력된다. (사진은 코드 수정 전)


### 9-3. 리뷰 수정 및 삭제
<img src = "https://user-images.githubusercontent.com/122970061/223377062-0223a2c0-5855-4daf-bd25-0e09600721ae.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223377191-0de7cf73-49f1-4e06-b090-e17d0e279747.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223499382-92dc5f2a-48bb-40bb-9c61-21f6dae93746.png" width="24%" height="24%">
* 자신이 입력한 댓글을 클릭하면 수정하기/삭제하기 작업을 선택할 수 있는 창이 출력된다.
* 자신이 입력 하지 않은 댓글의 경우 클릭 자체가 비활성화 된다.
* 수정하기를 선택하면 원래 저장된 댓글 내용이 입력된 입력 창이 뜬다.
* 수정할 내용 입력 후 아이콘을 클릭하면 수정된 내용이 반영된다.
* 삭제하기 버튼을 누르면 즉시 댓글이 삭제된다.


## 10. 스케줄러
### 10-1. 일정 등록
<img src = "https://user-images.githubusercontent.com/122970061/223379200-cbde1e46-b8db-494d-a8ee-aece599d0f17.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223379525-0dc7a34e-1fea-4e18-aef5-1b2a5dd080d2.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223379604-a14dd387-afc3-4cd1-810c-5e716f1efeb4.png" width="24%" height="24%">
* 우측 하단의 + 버튼 클릭시 일정을 입력할 수 있는 창이 뜬다.
* 입력 창에서 일정 입력 후 우측 상단의 아이콘을 클릭하면 일정이 저장된다.
* 저장된 일정은 리스트 형태로 확인 가능하다.

### 10-2. 캘린더
<img src = "https://user-images.githubusercontent.com/122970061/223379958-d9fb96be-26df-4a72-829a-ca4933dbb901.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223380123-646158c0-93ec-4964-b4df-01fcf6de2a99.png" width="24%" height="24%">
* 캘린더 탭으로 저환하면 현재 날짜를 표시하고 있는 해당 달의 캘린더가 표시된다.
* 일정이 등록된 날짜 클릭시 등록된 일정이 하단에 표시된다.


### 10-3. 일정 수정 및 삭제
<img src = "https://user-images.githubusercontent.com/122970061/223380224-19fe764b-3f78-43ba-a1c5-56bc70fe2b44.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223380409-8f668866-522d-41fa-9766-a072853db4ce.png" width="24%" height="24%"> <img src = "https://user-images.githubusercontent.com/122970061/223380514-966799ff-d73a-4705-bfd4-444c663f1712.png" width="24%" height="24%">
* 댓글과 동일하게 수정하고자 하는 일정을 클릭하면 수정하기/삭제하기 작업을 선택할 수 있는 창이 출력된다.
* 수정하기를 선택하면 원래 저장된 일정 내용이 입력된 입력 창이 뜬다.
* 수정할 내용 입력 후 아이콘을 클릭하면 수정된 일정이 반영된다.
* 삭제하기 버튼을 누르면 즉시 일정이 삭제된다.




