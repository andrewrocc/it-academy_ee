package my.first.servlet;

import my.first.dao.ProductInfoDaoImpl;
import my.first.model.ProductInfo;
import my.first.service.SearchService;

import java.util.List;
import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "SearchServlet", urlPatterns = "/search.do")
public class SearchServlet extends HttpServlet {

    private SearchService searchService;

    public void setSearchService(SearchService searchService) {
        this.searchService = searchService;
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        searchService = new SearchService(new ProductInfoDaoImpl());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // read input from HTTP request
        final String pname = req.getParameter("pname");
        // handle inout data with business service
        final List<ProductInfo> searchResult = searchService.search(pname);
        // save output for view/UI (JSP)
        req.setAttribute("searchResult", searchResult);

        getServletContext().getRequestDispatcher("/jsp/searchResult.jsp").forward(req, resp);
    }
}
