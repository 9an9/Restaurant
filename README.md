# Restaurant
#### 식당에서 음식을 주문, 요리가 나오는 과정을 MultiThread, 디자인 패턴을 적용하여 구현

```
.
+-- src
|   +-- hall
|   	 +-- Hall.java
|      +-- Manager.java
|   	 +-- Table.java
|   +-- kitchen
|   	 +-- dish
|        +-- DishFactory.java
|        +-- Dish.java
|        +-- Dessert.java
|        +-- Stir_Fry.java
|        +-- Soup.java
|        +-- Menu.java
|   	 +-- ingredients
|        +-- Egg.java
|        +-- Onion.java
|        +-- Potato.java
|        +-- Tomato.java
|   	 +-- Chef.java
|   +-- Main.java
```



#### Main

- hall 객체 생성 후 매니저, 테이블, 셰프, 메뉴 세팅
- 주방으로 주문을 넣고, 완성된 음식을 받아오는 Thread 실행.
- BufferedReader로 주문을 받고, 큐를 사용하여 요리 주문을 넣음.
- 주문을 받아 음식을 우선순위대로 정렬(SOUP>STIR>DESSERT)

#### hall.hall

- ConcurrentHashMap으로 메뉴판 관리.
- LinkedBlockingQueue로 완성된 음식 관리.
- CopyOnWriteArrayList로 주문이 들어온 테이블 순서 관리.
- ConcurrentHashMap으로 테이블 번호 별 주문 관리.

#### hall.Manager

- takeOrder() 메서드로 셰프의 상태를 체크하고(checkChefStatus()) 준비된 상태의 셰프에게 요리를 시킴.
- 음식이 우선순위대로 완성되게 하기 위해 각 음식 사이에 3초의 delay를 넣음.
- checkMenu()로 현재 남은 재료의 갯수를 확인하고 showMenu()로 메뉴판 출력.
- assignTable()로 현재 각 테이블의 상태를 체크하고 비어있는 테이블을 리턴.

#### hall.Table

#### kitchen.dish.DishFactory

- Dish(음식)을 입력 받으면 type에 따라 새로운 객체를 리턴.

#### kitchen.dish.Dish

#### kitchen.dish.Dessert/Stir_Fry/Soup

- type에 따라 cookDish() 메서드의 sleep 시간이 다르게 실행.

#### kitchen.ingredients

- Singleton으로 구현.
- 동기화 문제 해결을 위해 재료의 남은 갯수(amount)를 AtomicInteger로 선언.

#### Chef

- 동기화 문제 해결을 위해 status를 AtomicBoolean으로 선언.
- 입력받은 음식을 factory에 넣어 새로운 음식을 생성하고 해당 음식 객체의 cookDish()를 실행함.

![image](https://user-images.githubusercontent.com/87376840/179461461-f25d491c-3afb-4c10-b4d2-4e4553f69f89.png)

|업로드 날짜|내용|피드백|
|------------|---|---|
|2022.07.06 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|- multithread와 singleton 패턴을 적용하여 식당 구현.<br>- 식재료 4개/셰프2명/홀매니저1명 세팅.<br>- 식재료의 남은 갯수와 셰프의 상태 필드를 수정할 수 있게 만들고 스레드 동기화 문제 해결을 위해 Atomic Wrapper, synchronized 사용|- singleton 패턴을 적용할 때 인스턴스를 초기에 선언시켜놓는 것보다는 사용 시점에 생성하는 방향으로 수정.<br> (singleton pattern의 장점은 **생성시점을 조절할 수 있다**는 것!)| 
|2022.07.18&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;|- factory method 패턴을 사용하여 음식 생성.<br>- 홀을 객체로 분리.<br>- 테이블 객체를 만들어 테이블 별 우선순위 부여|| 

