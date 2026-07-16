package servlet;

import java.io.IOException;
import java.net.Authenticator.RequestorType;

import model.SiteEV;
import model.SiteEVLogic;import

@WebServlet("/MinatoIndex")

public class MinatoIndex extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException{
        ServletContext application = this.getServletContext();
        SiteEV siteEV = (SiteEV)application.getAttribute("siteEV");

        if(siteEV == null){
            siteEV = new SiteEV();
        }
        
        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action")

        SiteEVLogic siteEVLogic = new siteEVLogic();
        if (action != null && action.equals("like")){
            siteEVLogic.like(siteEV);
        } else if (action != null && action.equals("dislike")){
            siteEVLogic.dislike(siteEV);
        }

        application.setAttribute("siteEV",siteEV);
        

        RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/jsp/minatoIndex.jsp");

        dispatcher.forward(request,response);

        }
}
