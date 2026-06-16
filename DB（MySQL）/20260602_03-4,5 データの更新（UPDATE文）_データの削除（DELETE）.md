# 3-4 データの更新（UPDATE文）

subject: DB
day: 2026年6月2日
memo: 3-4,5 データの更新（UPDATE文）/削除（DELETE）

---

【基本形】

```sql
UPDATE テーブル名
SET カラム名1 = 更新したい値　←更新したい値が複数ある場合は、,区切りで列挙
　　,カラム名2
WHERE レコードを絞り込む条件;　**←必須**
```

※更新するレコードを絞り込む必要がある

　WHERE句がないと、すべてのレコードのカラムの値が更新されてしまう

# 3-5 データの削除（DELETE文）

【基本形】

```sql
DELETE FROM テーブル名
WHERE レコードを絞り込む条件式;　←必須
```

※更新するレコードを絞り込む必要がある

　WHERE句がないと、すべてのレコードが削除されてしまう

【確認】［p.082］コラム

```sql
【DELETE文と同じ条件でSELECT文で事前確認！】
SELECT * FROM jusho WHERE company = 'チキュウ社';

【事前確認で問題ないことを確認後、削除を実行！】
DELETE FROM jusho WHERE company = 'チキュウ社';
```
