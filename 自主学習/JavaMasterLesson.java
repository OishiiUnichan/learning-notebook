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

  }

}
