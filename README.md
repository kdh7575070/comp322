# comp322 database project
개발 환경
-OS : ubuntu 18.04\
-DBMS : PostgreSQL 13.0

## ** DB Application 구조 설명 **
src
 - entity
    - Account.java
    - Movie.java
    - Rating.java
  - service
    - AccountService.java
    - AdminService.java
    - [MAIN.java](https://github.com/kdh7575070/comp322/blob/main/Phase4/src/service/MAIN.java)
    - MovieService.java

### 1. Entity package 안에는 Account, Movie, Rating class와 각각의 생성자, getter, setter를 만들었습니다.

### 2. service package는 MAIN.java, AccountService.java, AdminService.java, MovieService.java 로 이루어져있다.
- Methods 를 정리해보면
  - [AccountService.java](https://github.com/kdh7575070/comp322/blob/main/Phase4/src/service/AccountService.java)
    - create_account()      : 1.A 회원 가입
    - update_user_info()    : 1.B 회원정보 수정
    - update_user_pwd()     : 1.C 비밀번호 수정
    - login()               : 1.D 로그인
    - delete_account()      : 1.E 회원 탈퇴 & 1.F 관리자 계정은 최소 1개 이상 필요
    - Is_admin()            : 1.F 관리자 계정은 최소 1개 이상 필요에서 사용
    - check_my_ratinglist() : 3.B 회원은 자신의 평가 내역을 확인 가능 
  - [MovieService.java](https://github.com/kdh7575070/comp322/blob/main/Phase4/src/service/MovieService.java)
    - show_all_movies()     : 2.A 회원은 로그인 이후 영상물 전체를 볼 수 있다.
    - search_movies()       : 2.B 회원은 로그인 이후 제목으로 등록된 영상물을 검색 가능 & 2.E 회원이 평가한 영상물은 이후 검색 대상에서 제외
    - srch_movie()          : 2.C 회원은 로그인 이후 특정 조건으로 등록된 영상물을 검색 가능 & 2.E 회원이 평가한 영상물은 이후 검색 대상에서 제외
    - movie_info()          : 2.D 검색한 영상물 목록에서 한 영상물을 선택하면 그 영상물의 정보 조회 가능 & 3.D 평균 평점 확인 가능
    - movie_rate()          : 2.D 해당 영상물을 평가할 수 있다.
  - [AdminService.java](https://github.com/kdh7575070/comp322/blob/main/Phase4/src/service/AdminService.java)
    - view_all_ratings()    : 3.C 관리자는 모든 평가 내역을 확인 가능 
    - create_movie()        : 4.A 새로운 영상물을 등록 할 수 있다.
    - update_movie_info()   : 4.B 등록된 영상물의 정보를 수정할 수 있다.
