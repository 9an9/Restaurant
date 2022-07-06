# Restaurant
#### 식당에서 음식을 주문, 요리가 나오는 과정을 MultiThread, Singleton Pattern으로 구현

```
.
+-- src
|   +-- ingredients
|   	 +-- Egg.java
|        +-- Onion.java
|   	 +-- Potato.java
|   	 +-- Tomato.java
|   +-- kitchen
|        +-- Chef.java
|   +-- Hall.java
|   +-- Manager.java
|   +-- Menu.java
```



#### Hall

- BufferedReader로 주문을 받고, 주문을 넣는 Thread 실행.

#### Manager

- takeOrder() 메서드로 셰프의 상태를 체크하고 준비된 상태의 셰프에게 요리를 시킴.
- Singleton으로 구현.

#### Chef

- Singleton으로 구현.
- 두 개의 인스턴스를 생성해놓고 식당에 두 명의 셰프만 존재하게 함.(이게 Singleton이 맞는지...)
- 모든 스레드에서 공유.
- 동기화 문제 해결을 위해 status를 AtomicBolean으로 선언.
- cook() 메서드로 3초간 요리함.

#### ingredients

- Singleton으로 구현.
- 모든 스레드에서 공유.
- 동기화 문제 해결을 위해 재료의 남은 갯수(amount)를 AtomicInteger로 선언.

#### Menu

- Singleton으로 구현.
- 모든 스레드에서 공유.
- showMenu() 메서드로 메뉴판 구성 및 출력.



#### [추가 계획]

- 갯수가 제한된 테이블 만들기
