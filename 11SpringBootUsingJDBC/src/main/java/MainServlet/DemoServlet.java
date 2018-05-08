package MainServlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import controller.AppJavaConfig;
import model.Customer;
import model.Goods;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.RetailerService;
import service.RetailerServiceImpl;

/**
 * Servlet implementation class DemoServlet
 */

// @WebServlet("/ramServlet")
public class DemoServlet extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");

		ApplicationContext context = new AnnotationConfigApplicationContext(AppJavaConfig.class);
		// CustomerService customerService = (CustomerService)
		// context.getBean("customerService",CustomerServiceImpl.class);
		RetailerService retailerService = (RetailerService) context.getBean("retailerService",
				RetailerServiceImpl.class);

		final String ID = request.getParameter("id");
		final int id = Integer.parseInt(ID);

		List<Customer> customerList = retailerService.viewCustomer(id);
		// List<Goods> goodsList = retailerService.viewGoods();

		// request.setAttribute("glist", goodsList);
		request.setAttribute("list", customerList);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("home1.jsp");
		// RequestDispatcher requestDispatchergoods =
		// request.getRequestDispatcher("viewgoods.jsp");
		requestDispatcher.forward(request, response);
		// requestDispatchergoods.forward(request, response);
	}

}
