package servlet;

import java.io.IOException;

import model.SiteEV;
import 

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
        
        
    }
}
