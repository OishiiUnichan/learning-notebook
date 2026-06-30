// 1. データの準備
var obj = [
  { name: '太郎', age: 21, score: { suugaku: 80, eigo: 75, kokugo: 87 } },
  { name: '花子', age: 20, score: { suugaku: 70, eigo: 85, kokugo: 78 } },
  { name: '次郎', age: 21, score: { suugaku: 90, eigo: 65, kokugo: 96 } }
];

// 2. HTMLの「箱」を取得
var outputDiv = document.getElementById('output');
var htmlText = "";

// 3. for文でループ処理
for (var i = 0; i < obj.length; i++) {
  var person = obj[i];
  
  // 計算
  var total = person.score.suugaku + person.score.eigo + person.score.kokugo;
  var average = total / 3;
  
  // 文字列を組み立て
  htmlText += "<h3>" + person.name + " (" + person.age + "歳)</h3>";
  
  // 条件分岐（数学80点以上）
  if (person.score.suugaku >= 80) {
    htmlText += "<strong style='color: red;'>【数学優秀者】</strong><br>";
  }
  
  htmlText += "数学: " + person.score.suugaku + "点<br>";
  htmlText += "英語: " + person.score.eigo + "点<br>";
  htmlText += "国語: " + person.score.kokugo + "点<br>";
  htmlText += "<strong>合計: " + total + "点 / 平均: " + average.toFixed(1) + "点</strong><br>";
  htmlText += "<hr>"; 
}

// 4. まとめて画面に出力！
outputDiv.innerHTML = htmlText;