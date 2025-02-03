# CH3 일정 관리 앱 만들기

## API명세서
|    기능    | Method |            URI             | Request | Response | 상태 |
|:--------:| :-: |:--------------------------:| :-: |:--------:| :-: |
|  일정 등록   | POST |       /api/schedules       | 요청 Body |  등록 정보   | 200:정상등록 |
| 일정 전체 조회 | GET | /api/schedules/seach?날짜&이름 | 요청 Param | 다건 응답 정보 | 200:정상조회 |
|  일정 조회   | GET | /api/schedules/{id} | 요청 Param | 단건 응답 정보 | 200:정상조회 |
|  일정 수정   | PATCH | /api/schedules/{id}/{password} | 요청 Body |  수정 정보   | 200:정상수정 |
|  일정 삭제   | DELETE | /api/schedules/{id}/{password} | 요청 Param |    -     | 200:정상삭제 |

## ERD
![image](https://github.com/user-attachments/assets/6fa0afdf-f3fd-43f3-85f3-e1fdad73aea2)