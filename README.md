# chaos-monkey 실습

송금
```` http request
http localhost:8080/remittance
````

restController 장애 주입 설정
```` http request
http post localhost:8080/actuator/chaosmonkey/watchers restController=true
````
카오스 몽키 조회 설정
```` http request
http localhost:8080/actuator/chaosmonkey
````
100% 비율로 지연 발생 설정
```` http request
http POST localhost:8080/actuator/chaosmonkey/assaults level=1 latencyRangeStart=2000 latencyRangeEnd=5000 latencyActive=true
````

