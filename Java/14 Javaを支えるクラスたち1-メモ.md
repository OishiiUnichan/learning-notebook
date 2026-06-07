# 14 Javaを支えるクラスたち1-メモ

subject: Java
day: 2026年5月13日
memo: オブジェクトクラス、toString()による暗黙の継承

## オブジェクトクラス（`Object`クラス）とは

Javaでは、**すべてのクラスは自動的に`Object`クラスを継承**しています。

---

### コードの解説

```java
public class Empty extends Object {
    // extends Object は書かなくても同じ
}
```

`extends Object` を明示的に書いているが、書かなくても**Javaが自動的に継承する**。

```java
Empty e = new Empty();
String s = e.toString(); // ← ここ！
System.out.println(s);
```

`Empty`クラスには`toString()`メソッドを定義していないのに、なぜ呼び出せるのか？

→ **`Object`クラスが`toString()`を持っているから！**

---

### 継承の構造

```
Object        ← すべての頂点（親）
  └── Empty   ← toString()を継承している
```

---

### 実行結果のイメージ

```
code14_01.Empty@1b6d3586
```

`Object`の`toString()`はデフォルトで **「パッケージ名.クラス名@ハッシュ値」** を返す。

---

### `Object`クラスの主なメソッド

| メソッド | 説明 |
| --- | --- |
| `toString()` | オブジェクトを文字列で表現 |
| `equals()` | オブジェクトの同一性を比較 |
| `hashCode()` | ハッシュ値を返す |
| `getClass()` | クラス情報を返す |

---

### まとめ

```java
// この2つは完全に同じ意味
public class Empty { }
public class Empty extends Object { }
```

> **ポイント：** Javaのすべてのクラスは`Object`の子孫であるため、`toString()`などのメソッドはどのクラスからでも呼び出せる。これがJavaの「単一ルート階層」の仕組み。
>