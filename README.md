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
|   +-- Main.java
|   +-- hall.Manager.java
|   +-- Menu.java
```



#### Main

- BufferedReader로 주문을 받고, 주문을 넣는 Thread 실행.

#### hall.Manager

- takeOrder() 메서드로 셰프의 상태를 체크하고 준비된 상태의 셰프에게 요리를 시킴.
- Singleton으로 구현.

#### Chef

- Singleton으로 구현.(?)
- 두 개의 인스턴스를 생성해놓고 식당에 두 명의 셰프만 존재하게 함.(추후 Chef 클래스를 상속받는 각각의 셰프로 구현할 예정)
- 모든 스레드에서 공유.
- 동기화 문제 해결을 위해 status를 AtomicBoolean으로 선언.
- cook() 메서드로 3초간 요리함.

#### ingredients

- Singleton으로 구현.
- 모든 스레드에서 공유.
- 동기화 문제 해결을 위해 재료의 남은 갯수(amount)를 AtomicInteger로 선언.

#### Menu

- Singleton으로 구현.
- 모든 스레드에서 공유.
- showMenu() 메서드로 메뉴판 구성 및 출력.

![image](https://user-images.githubusercontent.com/87376840/177657507-9ad7ddd0-0b99-4ef5-b5d3-7313119351b9.png)

|업로드 날짜|내용|피드백|
|------------|---|---|
|2022.07.06 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|- multithread와 singleton 패턴을 적용하여 식당 구현.<br>- 식재료 4개/셰프2명/홀매니저1명 세팅.<br>- 식재료의 남은 갯수와 셰프의 상태 필드를 수정할 수 있게 만들고 스레드 동기화 문제 해결을 위해 Atomic Wrapper, synchronized 사용|- singleton 패턴을 적용할 때 인스턴스를 초기에 선언시켜놓는 것보다는 사용 시점에 생성하는 방향으로 수정.<br> (singleton pattern의 장점은 **생성시점을 조절할 수 있다**는 것!)| 


