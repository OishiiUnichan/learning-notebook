package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Mutter;
import model.User;
import model.PostMutterLogic;

@WebServlet("/Main")
public class Main extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // つぶやきをリストをアプリケーションスコープから取得
        ServletContext application = this.getServletContext();
        List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

        // 取得できなかった場合：つぶやきリスト新規作成→アプリケーションスコープに保存
        if (mutterList == null) {
            mutterList = new ArrayList<>();
            application.setAttribute("mutterList", mutterList);
        }

        // ログイン確認：セッションスコープからユーザー情報取得
        HttpSession session = request.getSession();
        User loginUser = (User) session.getAttribute("loginUser");

        if (loginUser == null) {
            // 未ログインの場合
            // リダイレクト処理
            response.sendRedirect("index.jsp");
        } else {
            // ログイン済みの場合：フォワード
            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // リクエストパラメータの取得
        request.setCharacterEncoding("UTF-8");
        String text = request.getParameter("text");

        // 入力値チェック
        if (text != null && text.length() != 0) {
            // アプリケーションスコープに保存されたつぶやきリストを取得
            ServletContext application = this.getServletContext();
            List<Mutter> mutterList = (List<Mutter>) application.getAttribute("mutterList");

            // セッションスコープに保存されたユーザー情報を取得
            HttpSession session = request.getSession();
            User loginUser = (User) session.getAttribute("loginUser");

            // つぶやきを作成してつぶやきリストに追加
            Mutter mutter = new Mutter(loginUser.getName(), text);
            PostMutterLogic postMutterLogic = new PostMutterLogic();
            postMutterLogic.execute(mutter, mutterList);

            // アプリケーションスコープにつぶやきリストを保存
            application.setAttribute("mutterList", mutterList);
        }

        // メイン画面にフォワード
        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/main.jsp");
    }
}