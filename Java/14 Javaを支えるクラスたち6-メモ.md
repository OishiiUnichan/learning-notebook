# 14 Javaを支えるクラスたち6-メモ

subject: Java
day: 2026年5月13日
memo: 静的（static）メソッドの制約

## 静的メソッドの制約とは

まず、なぜエラーになるのか**根本的な理由**から整理する。

---

元コード／code14_15

```java
package code14_15;

public class Hero {

    // フィールド
    private String name;
    private int hp;
    static int money;//code14_08,09

    // コンストラクタ
    public Hero(String name, int hp) {
        this.name = name;
        this.hp = hp;
    }

    public Hero() {}

    public static void setRandomMoney() {
    	Hero.money=(int)(Math.random()*1000);
    	System.out.println(this.name+"たちの所持金を初期化しました");
    }

    // アクセサ
    public String getName() {return this.name;}
    public void setName(String name) {this.name = name;}
    public int getHp() {return this.hp;}
    public void setHp(int hp) {this.hp = hp;}
}
```

### ① エラーの箇所

```java
public static void setRandomMoney() {
    Hero.money = (int)(Math.random() * 1000);
    System.out.println(this.name + "たちの所持金を初期化しました"); // ❌ エラー！
}
```

`this.name`の`this`が問題。

---

### ② `this`とは何か

`this`は「**今このメソッドを呼び出しているインスタンス自身**」を指す。

```java
Hero h1 = new Hero();
h1.setName("ミナト"); // このとき this = h1
```

つまり`this`が存在するためには、**インスタンスが必要。**

---

### ③ なぜstaticメソッドで`this`が使えないのか

staticメソッドは**インスタンスなしで呼び出せる**のが特徴。

```java
Hero.setRandomMoney(); // ← インスタンスを作っていない！
```

インスタンスが存在しないので、**`this`が何を指せばいいかわからない。**

```
通常メソッド呼び出し
h1.setName("ミナト")
  → this = h1  ✅ 指す対象がある

staticメソッド呼び出し
Hero.setRandomMoney()
  → this = ???  ❌ 指す対象がない！
```

---

### ④ staticメソッドで使えるもの・使えないもの

```java
public static void setRandomMoney() {

    Hero.money = 500;       // ✅ staticフィールドはOK（クラスに属するから）
    this.name = "ミナト";   // ❌ 通常フィールドはNG（インスタンスに属するから）
    this.getHp();           // ❌ 通常メソッドもNG（インスタンスが必要だから）
    Math.random();          // ✅ 別クラスのstaticメソッドはOK

}
```

---

### ⑤ 解決策

`this.name`を使いたいなら、**引数でインスタンスを受け取る**か、**staticをやめる**。

```java
// 解決策① 引数でインスタンスを受け取る
public static void setRandomMoney(Hero h) {
    Hero.money = (int)(Math.random() * 1000);
    System.out.println(h.name + "たちの所持金を初期化しました"); // ✅
}

// 解決策② staticをやめる（通常メソッドにする）
public void setRandomMoney() {
    Hero.money = (int)(Math.random() * 1000);
    System.out.println(this.name + "たちの所持金を初期化しました"); // ✅
}
```

---

### まとめ

```
staticメソッド → クラスに属する → インスタンス不要で呼べる
                                  ↓
                         thisが存在しない
                                  ↓
                  通常フィールド・通常メソッドは使えない
```

> **ポイント：** staticメソッドは「インスタンスがなくても動く」という性質上、インスタンスに紐づく`this`を持てない。使えるのは**staticなもの（クラスに属するもの）だけ**という制約がある。
>