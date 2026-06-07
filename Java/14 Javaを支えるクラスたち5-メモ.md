# 14 Javaを支えるクラスたち5-メモ

subject: Java
day: 2026年5月13日
memo: static（静的）の使い方

## `static`とは

まず、このコードで重要な**前提知識**から整理する。

---

### ① 通常のフィールドとstaticフィールドの違い

通常のフィールドは**インスタンスごとに別々に存在**する。

```java
Hero h1 = new Hero();
Hero h2 = new Hero();

h1.name = "ミナト";
h2.name = "サクラ";
// h1とh2はそれぞれ別のnameを持つ
```

`static`フィールドは**クラス全体で1つだけ存在**する。

```java
static int money;

Hero.money = 1000;
// h1もh2も同じmoneyを共有している
```

```
メモリのイメージ

【インスタンスごと】
h1 → [ name="ミナト", hp=100 ]
h2 → [ name="サクラ", hp=100 ]

【クラスで1つだけ】
Hero.money → [ 1000 ]  ← h1もh2もここを見ている
```

---

### ② code14_11の動きを追う

```java
Hero h1 = new Hero();
Hero h2 = new Hero();
Hero.money = 1000;

System.out.println("今の所持金：" + Hero.money); // 1000
System.out.println("h1の所持金：" + h1.money);   // 1000（同じ場所を見ている）

h1.money = 300; // ← Heroクラス全体のmoneyを書き換える

System.out.println("h2の所持金：" + h2.money);   // 300（h2も影響を受ける！）
```

`h1.money`を変えたのに`h2.money`も変わる。**全員が同じ`money`を共有しているから。**

---

### ③ staticメソッドとは

```java
public static void setRandomMoney() {
    Hero.money = (int)(Math.random() * 1000);
}
```

`static`メソッドは**インスタンスを作らずに呼び出せる。**

```java
// ❌ インスタンスが必要なメソッド（通常）
Hero h = new Hero();
h.setName("ミナト");

// ✅ インスタンス不要（static）
Hero.setRandomMoney();
```

ただし、staticメソッドの中では**`this`や通常のフィールドは使えない。**

```java
public static void setRandomMoney() {
    this.name = "ミナト"; // ❌ エラー！staticの中でthisは使えない
    Hero.money = 500;     // ✅ staticフィールドはOK
}
```

---

### ④ staticの使いどころ

| 用途 | 例 |
| --- | --- |
| 全インスタンスで共有したい値 | 所持金、ゲームのスコア |
| インスタンス不要で呼び出したいメソッド | `Math.random()`, `Integer.valueOf()` |

実は`Math.random()`もstaticメソッドなので、`new Math()`せずに呼び出せる。

---

### まとめ

```
通常フィールド  → インスタンスごとに別々に存在
staticフィールド → クラスで1つだけ、全員で共有

通常メソッド   → インスタンスが必要（h1.attack()）
staticメソッド → インスタンス不要（Hero.setRandomMoney()）
```

> **ポイント：** `static`は「インスタンスに属する」のではなく「**クラスに属する**」。だからインスタンスを作らなくても使えるし、全インスタンスで値を共有できる。
>