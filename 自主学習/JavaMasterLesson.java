public class JavaMasterLesson {

  // 1.フィールドとカプセル化
  private String studentName;

  // コンストラクタ
  public JavaMasterLesson(String name) {
    this.studentName = name;
  }

  // メインメソッド
  public static void main(String[] args) {
    System.out.println("--Java学習スタート--");

    // インスタンスの作成
    JavaMasterLesson lesson = new JavaMasterLesson("Java初学者");

    // 2.基本的なデータ型と演算の実行
    lesson.demonstrateDataType();

    // 3.条件分岐
    lesson.demonstrateControlFlow(85);
    lesson.demonstrateSwitch("Go");

    // 4.ループ処理
    lesson.demonstrateLoops();

    // 5.配列操作
    lesson.demonstrateArrays();

    System.out.println("--全ての学習プログラムが終了しました--");
  }

  /**
   * 2.基本的なデータ型
   */

  public void demonstrateDataType() {
    System.out.println("\n[2.データ型と演算]");

    // 整数型(int)、浮動小数点型(double)、論理型(boolean)
    int age = 20;
    double height = 170.5;
    boolean isStudent = true;

    // 文字列の結合と出力
    System.out.println("名前:" + this.studentName);
    System.out.println("年齢:"+age+",身長:"+height+"cm");

    //基本的な四則演算
    int calculation = (10+5)*2/3;
    System.out.println("(10+5)*2/3の結果:"+calculation); //結果は１０
  }

  /**
   * 20260628
   * 3-1.条件分岐(if-else)を学ぶメソッド
   * @param score テストの点数
   */

  public void demonstrateControlFlow(int score) {
    System.out.println("\n[3-1.条件分岐(if文)]");
    System.out.println("点数:"+score);

    //比較演算子での条件分け
    if (score >= 90){
      System.out.println("素晴らしい！優秀な成績です。");
    } else if (score>=70){
      System.out.println("合格です！よく頑張りました。");
    } else {
      System.out.println("不合格です。復習しましょう");
    }
  }

  /** 
   * 20260628
   * 3-2.条件分岐(switch)を学ぶメソッド
   * 
   * @param signal 信号の色
   * 
  */

  public void demonstrateSwitch(String signal){
    System.out.println("\n[3-2. 条件分岐(switch文)]");

    //特定の値に応じて処理を分ける（等価比較に強い）
    switch (signal) {
      case "Stop":
          System.out.println("赤信号：止まってください。");
        break; //breakを忘れると次のcaseに進むので注意
    
      case "Go":
        System.out.println("青信号：進んでも良いです。");
        break;
      default:
        System.out.println("黄信号または不明:注意してください");
        break;
    }
  }

}
