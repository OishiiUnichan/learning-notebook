# 14 Javaを支えるクラスたち4-メモ

subject: Java
day: 2026年5月13日
memo: 等値（==）と等価（equals）の比較

## `equals()`のオーバーライドとは

まず、このコードで重要な**前提知識**から整理する。

---

### ① 等値と等価の違い

Javaには「同じ」の概念が2種類ある。

```
等値（同一）：同じメモリ上のインスタンスかどうか  →  == で比較
等価（同等）：中身の値が同じかどうか              → equals() で比較
```

具体例で見てみる。

```java
Hero h1 = new Hero("ミナト");
Hero h2 = new Hero("ミナト");

System.out.println(h1 == h2);        // false ← 別々のインスタンス
System.out.println(h1.equals(h2));   // ???  ← 中身は同じ？
```

```
メモリのイメージ

h1 → [ Hero: name="ミナト", hp=100 ]  アドレス: 0x001
h2 → [ Hero: name="ミナト", hp=100 ]  アドレス: 0x002

== はアドレスを比べる → 0x001 ≠ 0x002 → false
```

---

### ② デフォルトの`equals()`は等値比較

`Object`クラスの`equals()`はデフォルトで**`==`と同じ動作**をする。

```java
// Objectクラスのデフォルト実装（イメージ）
public boolean equals(Object obj) {
    return (this == obj); // アドレスを比べているだけ
}
```

つまりオーバーライドしないと、中身が同じでも`false`が返ってしまう。

```java
h1.equals(h2); // → false（中身は同じなのに！）
```

---

### ③ `equals()`をオーバーライドする

「名前とHPが同じなら同じ勇者とみなす」という等価の定義を自分で決める。

```java
@Override
public boolean equals(Object obj) {
    // ① 同じインスタンスなら当然true
    if (this == obj) return true;

    // ② nullや別クラスなら false
    if (obj == null || this.getClass() != obj.getClass()) return false;

    // ③ Heroにキャストして中身を比較
    Hero other = (Hero) obj;
    return this.name.equals(other.name) && this.hp == other.hp;
}
```

```java
Hero h1 = new Hero("ミナト");
Hero h2 = new Hero("ミナト");

System.out.println(h1 == h2);       // false（別インスタンス＝等値ではない）
System.out.println(h1.equals(h2));  // true （中身が同じ＝等価である）
```

---

### ④ `String`はすでにオーバーライド済み

`String`の比較でよく言われる「`==`を使うな」はこれが理由。

```java
String s1 = new String("ミナト");
String s2 = new String("ミナト");

System.out.println(s1 == s2);      // false（別インスタンス）
System.out.println(s1.equals(s2)); // true （Stringはequals済み）
```

`String`クラスはすでに文字列の中身で比較するよう`equals()`をオーバーライドしている。

---

### まとめ

| 比較方法 | 何を比べる | 種類 |
| --- | --- | --- |
| `==` | メモリ上のアドレス | 等値（同一） |
| `equals()`（デフォルト） | アドレス（`==`と同じ） | 等値（同一） |
| `equals()`（オーバーライド） | 中身の値 | 等価（同等） |

> **ポイント：** デフォルトの`equals()`は`==`と変わらない。「中身が同じなら同じとみなしたい」場合は、**何をもって等価とするかを自分で定義してオーバーライドする必要がある。**
> 

## なぜ「違う内容」になるのか

---

### ① まず結論から

このコードが「違う内容です」と出力される理由は、**`equals()`をオーバーライドしていないから。**

```java
Hero h1 = new Hero("ミナト");
Hero h2 = new Hero("ミナト");

if (h1.equals(h2) == true) {
    System.out.println("同じ内容です");  // ← ここには来ない
} else {
    System.out.println("違う内容です");  // ← こっちが実行される
}
```

---

### ② 処理の流れを追う

```java
h1.equals(h2)
```

`Hero`クラスに`equals()`が定義されていない場合、**親クラス`Object`の`equals()`が呼ばれる。**

```java
// Objectクラスのデフォルト実装（イメージ）
public boolean equals(Object obj) {
    return (this == obj); // アドレスを比べているだけ！
}
```

つまり内部でやっていることは`==`と同じ。

```
h1 → [ Hero: name="ミナト", hp=100 ]  アドレス: 0x001
h2 → [ Hero: name="ミナト", hp=100 ]  アドレス: 0x002

h1.equals(h2)
  → 0x001 == 0x002
  → false
```

---

### ③ `== true`は冗長

ちなみにこの書き方は動くが、一般的には省略する。

```java
// この2つは全く同じ意味
if (h1.equals(h2) == true)
if (h1.equals(h2))          // ← こちらが一般的
```

`equals()`はすでに`true`か`false`を返すので、`== true`で比較するのは二重になっている。

---

### ④ 解決策：`equals()`をオーバーライドする

```java
@Override
public boolean equals(Object obj) {
    if (this == obj) return true;
    if (obj == null || this.getClass() != obj.getClass()) return false;

    Hero other = (Hero) obj;
    return this.name.equals(other.name) && this.hp == other.hp;
}
```

これで`h1.equals(h2)`が**中身を比較するようになり`true`が返る。**

```
h1.equals(h2)
  → name: "ミナト" == "ミナト" → true
  → hp:    100    ==   100   → true
  → true && true → true ✅
```

---

### まとめ

```
オーバーライドなし → Objectのequals()が動く → アドレス比較 → false →「違う内容」
オーバーライドあり → Heroのequals()が動く  → 中身比較   → true  →「同じ内容」
```

> **ポイント：** `equals()`を定義していない場合、Javaは親クラスの実装を使う。`Object`のデフォルトは**アドレス比較**なので、中身が同じでも別インスタンスであれば必ず`false`になる。
>