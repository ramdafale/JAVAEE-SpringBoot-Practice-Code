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
import model.Supplier;
import service.CustomerService;
import service.CustomerServiceImpl;
import service.RetailerService;
import service.RetailerServiceImpl;

/**
 * Servlet implementation class DemoServlet
 */

// @WebServlet("/ramServlet")
public class DemoServlet3 extends HttpServlet {

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		ApplicationContext context = new AnnotationConfigApplicationContext(AppJavaConfig.class);
		//CustomerService customerService = (CustomerService) context.getBean("customerService",CustomerServiceImpl.class);
		RetailerService retailerService = (RetailerService) context.getBean("retailerService",
				RetailerServiceImpl.class);
		
		
		String ID = request.getParameter("id1");
		try {
		int id1 = Integer.parseInt(ID);
		
		
		
		
	//	List<Customer> customerList = retailerService.viewCustomer(106);
		
		List<Supplier> supplierList = retailerService.viewSupplier(id1);
		
		
		
		
		request.setAttribute("slist", supplierList);
	//	request.setAttribute("list", customerList);
		
	//	RequestDispatcher requestDispatcher = request.getRequestDispatcher("home1.jsp");
		RequestDispatcher requestDispatcherSupplier = request.getRequestDispatcher("viewsupplier.jsp");
		//requestDispatcher.forward(request, response);
		requestDispatcherSupplier.forward(request, response);
		}catch (NumberFormatException e){
		       System.out.println("not a number"); 
		   } 
	}

}
