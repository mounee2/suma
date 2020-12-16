package home.spring;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.taglibs.standard.tag.rt.fmt.RequestEncodingTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.virtusa.buyit.dao.CustomerDAO;
import com.virtusa.buyit.dao.OrderDAO;
import com.virtusa.buyit.dao.ProductDAO;
import com.virtusa.buyit.dto.Customer;
import com.virtusa.buyit.dto.Order;
import com.virtusa.buyit.dto.Product;
import com.virtusa.buyit.dto.ProductWrapperList;
import com.virtusa.buyit.exception.BuyitException;

@Controller
public class ControlMain {
	
	@Autowired
	CustomerDAO cd;
	@Autowired
	ProductDAO pd;
	@Autowired()
	OrderDAO od;
	
	public ControlMain() {
		super();
		// TODO Auto-generated constructor stub
	}
	@RequestMapping(value = "/Login")
	public String doGet() {

		return "login";
	}

	@RequestMapping("/userCheck")
	public ModelAndView login(@ModelAttribute() Customer customer, HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ModelAndView view = new ModelAndView("/login");
		String customerId = customer.getUserName();
		String password = customer.getPassword();
		System.out.println("Customer id is"+customerId+"Password is"+password);
		//Customer customer = null;
		//Product products = null;
		try {
			customer = cd.get(customerId); 
			if (null != customer) {
				if (customer.getPassword().equals(password)) {
					System.out.println("Valid user");
					HttpSession session = request.getSession();
					session.setAttribute("customerID", customerId);
					ModelAndView view1=new ModelAndView("/userHome");
					view1.addObject("customers",customer);
					view1.addObject("products",getProducts(request, response));
					
					System.out.println("1");
					return view1;
				} else {
					request.setAttribute("errorMessage", "Invalid password");
					return view;
				}
			} else {
				request.setAttribute("errorMessage", "Invalid username");
				return view;
			}
		} 
		catch (BuyitException e) {
			request.setAttribute("errorMessage", "Invalid username or password");
			return view;
		} 
	

	}

	private void addCustomer(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String name = request.getParameter("Name");
		String userName = request.getParameter("UserName");
		String password = request.getParameter("Password");
		String gender = request.getParameter("Gender");
		int mobileNumber = Integer.parseInt(request.getParameter("MobileNumber"));
		String email = request.getParameter("Email");
		String address = request.getParameter("Address");
		String city = request.getParameter("City");
		String state = request.getParameter("State");
		int postalCode = Integer.parseInt(request.getParameter("PostalCode"));
		Customer customer = new Customer(name, address, city, state, postalCode, userName, password, email,
				mobileNumber, gender);
		CustomerDAO customerDAO = new CustomerDAO();
		try {
			customer = customerDAO.insert(customer);
			request.setAttribute("customer", customer);
		} catch (BuyitException e) {

		}

		request.getRequestDispatcher("registerCustomer.jsp").forward(request, response);
	}

	/*private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int customerId = Integer.parseInt(request.getParameter("userName"));
		String password = request.getParameter("password");
		Customer customer = null;
		CustomerDAO cd = new CustomerDAO();
		// Product products = null;
		try {
			customer = cd.get(customerId);
			if (null != customer) {
				if (customer.getPassword().equals(password)) {

					HttpSession session = request.getSession();
					session.setAttribute("customerID", customerId);
					getProducts(request, response);

					// store products in request scope

				} else {
					request.setAttribute("errorMessage", "Invalid password");
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}
			} else {
				request.setAttribute("errorMessage", "Invalid username");
				request.getRequestDispatcher("/login.jsp").forward(request, response);
			}
		} catch (BuyitException e) {
			request.setAttribute("errorMessage", "Invalid username or password");
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}*/

	private ProductWrapperList getProducts(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ProductWrapperList products=new ProductWrapperList();
		try {
			products.setProducts(new ArrayList<Product>(pd.get()));
		} catch (BuyitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return products;
	}

	/*private void addProduct(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String productDescription = request.getParameter("productDescription");
		String productFinish = request.getParameter("productFinish");
		double standardPrice = Double.parseDouble(request.getParameter("standardPrice"));

		Product product = new Product(productDescription, productFinish, standardPrice);
		ProductDAO productdao = new ProductDAO();
		Product productAdded = null;

		try {
			productAdded = productdao.insert(product);
			if (null != productAdded) {
				request.setAttribute("productAdded", "product added succesfully");
				getProducts(request, response);
			}
		} catch (BuyitException e) {
			// TODO Auto-generated catch block
		}
	}*/

	@RequestMapping("/place_order")
	private void placeOrder(@ModelAttribute(value="products")ProductWrapperList products,HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Order order=new Order();
		Customer customer=null;
		try {
            HttpSession session=request.getSession();
            System.out.println(session.getAttribute("customerId"));
            customer = cd.get((String)session.getAttribute("customerId"));
            System.out.println(customer);
			order.setCustomer(customer);
			order.setOrderDate(new Date());
			order.setOrderedProductsList(products.getProducts());
			int orderID = od.insert(order);
			// request.setAttribute("OrderID", orderID);
			// request.getRequestDispatcher("Output.jsp").forward(request,response);
			System.out.println("Order id is"+orderID);
		} catch (BuyitException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
