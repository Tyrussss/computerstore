package com.cs.controller; // Gói chứa các lớp điều khiển của ứng dụng

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

import com.cs.config.VNPayConfig;
import com.cs.model.*;
import com.cs.repository.*;
import com.cs.util.FileUploadUtil;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired; // Nhập gói org.springframework.beans.factory.annotation.Autowired để sử dụng chú thích @Autowired
import org.springframework.stereotype.Controller; // Nhập gói org.springframework.stereotype.Controller để đánh dấu lớp này là một controller trong Spring
import org.springframework.ui.Model; // Nhập gói org.springframework.ui.Model để sử dụng đối tượng Model trong Spring MVC
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller // Đánh dấu lớp này là một controller trong Spring MVC
@RequestMapping("") // Ánh xạ yêu cầu đến URL gốc của ứng dụng đến phương thức xử lý trong controller này
public class ClientController {

    @Autowired // Chú thích @Autowired để tiêm các dependency vào các trường của lớp
    private ProductRepository productRepository; // Repository cho sản phẩm

    @Autowired
    private ProductImageRepository productImageRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private OrderDetailRepository orderDetailRepository;
    Float totalResult = 0f;
    String totalText = "";
    Integer orderId = 0;

    @Autowired // Chú thích @Autowired để tiêm các dependency vào các trường của lớp
    private CategoryRepository cate; // Repository cho danh mục
    
    
    
   
    @PostMapping("/account/register")
    public String registerUser(@ModelAttribute User user, @RequestParam("avatar") MultipartFile avatarFile, Model model) {
        if (avatarFile.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "register";
        }

        if (!avatarFile.getContentType().startsWith("image")) {
            model.addAttribute("message", "Please upload an image file.");
            return "register";
        }

        try {
            String fileName = FileUploadUtil.saveFile(Paths.get("src/static/clic/img/"), avatarFile.getOriginalFilename(), avatarFile);
            
            user.setAvatar(fileName); // Set the avatar file name to the user object
            userRepository.insert(user); // Assuming you have a service method to handle user registration
            model.addAttribute("message", "User registered successfully.");
        } catch (IOException e) {
            model.addAttribute("message", "Error registering user: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/login";
    }
	
    
    @GetMapping("/products") // Ánh xạ yêu cầu GET đến "/products" đến phương thức xử lý products
    public String products(Model model) { // Phương thức xử lý cho "/products"
        List<Product> products = productRepository.findAll();
        List<Product> newProducts = new ArrayList<>();
        for (Product product : products) {
            ProductImage productImage = productImageRepository.findFirstImageByProductID(product.getProductID());
            if(productImage == null) {
                product.setImage("");
            }
            else {
                product.setImage(productImage.getImage());
            }
            newProducts.add(product);
        }
        model.addAttribute("products", newProducts); // Thêm danh sách sản phẩm vào model
        model.addAttribute("categories", cate.findAll()); // Thêm danh sách danh mục vào model
        return "client/view-products"; // Trả về view "view-products" trong thư mục client
    }

    @GetMapping("/products/find") // Ánh xạ yêu cầu GET đến "/products/find" đến phương thức xử lý findProducts
    public String findProducts(@RequestParam("categoryId") int ProductID, Model model) { // Phương thức xử lý cho "/products/find"
        model.addAttribute("products", productRepository.findByID(ProductID)); // Tìm sản phẩm theo ID và thêm vào model
        model.addAttribute("categories", cate.findAll()); // Thêm danh sách danh mục vào model
        return "client/view-products"; // Trả về view "view-products" trong thư mục client
    }

    @GetMapping("/cart") // Ánh xạ yêu cầu GET đến "/cart" đến phương thức xử lý cart
    public String cart(HttpSession session, Model model) { // Phương thức xử lý cho "/cart"
    	
        Integer UserID = (Integer) session.getAttribute("UserID");
        double total = cartRepository.getTotal(UserID);
        model.addAttribute("total", total);
        List<Cart> carts = cartRepository.findByUserID(UserID);
        model.addAttribute("carts", carts); // Thêm danh sách cart vào model
        return "client/cart"; // Trả về view "cart" trong thư mục client
    }

    @GetMapping("/checkout") // Ánh xạ yêu cầu GET đến "/checkout" đến phương thức xử lý checkout
    public String checkout(HttpSession session, Model model) { // Phương thức xử lý cho "/checkout"
    	model.addAttribute("categories", cate.findAll()); // Thêm danh sách danh mục vào model
        Integer userId = (Integer) session.getAttribute("UserID");
        List<Cart> carts = cartRepository.findByUserID(userId);

        double total = cartRepository.getTotal(userId);
        model.addAttribute("total", total);
        session.setAttribute("totalOrder", total);
        model.addAttribute("carts", carts); // Thêm danh sách cart vào model
        return "client/checkout"; // Trả về view "checkout" trong thư mục client
    }

    @PostMapping("/create-order")
    @ResponseBody
    public Map<String, Object> createOrder(@RequestBody Order orderRequest, HttpSession session) {
        Integer accountId = 1;
        // Thực hiện tạo đơn hàng
        Order newOrder = new Order();
        newOrder.setUserID(accountId);
        newOrder.setPaymentStatus(true);
        newOrder.setPayment("4");
        int orderId = orderRepository.insert(newOrder);

        session.setAttribute("orderId", orderId);
        
        // Trả về ID của đơn hàng
        Map<String, Object> response = new HashMap<>();
        response.put("orderId", orderId);
        return response;
    }
    
    @GetMapping("/addToCart")
    public String addToCart(HttpSession session,  @ModelAttribute("productId") Integer productId) {
        Integer userId = (Integer) session.getAttribute("UserID");
        Cart cart = new Cart();
        Product product = productRepository.findByID(productId);
        cart.setProductID(productId);
        cart.setUserID(userId);
        cart.setPrice(product.getPrice());
        cart.setQuantity(1);
        cartRepository.insert(cart);
        
        session.setAttribute("cartQuantity", userRepository.countCart(userId));
        return "redirect:/cart";
    }
    
    @GetMapping("/cart/delete/{id}") // Ánh xạ yêu cầu POST đến "/cart/delete" đến phương thức xử lý xóa cart
    public String deleteCart(@PathVariable int id, HttpSession session) { // Phương thức xử lý cho yêu cầu xóa cart
        cartRepository.deleteById(id);
        Integer userId = (Integer) session.getAttribute("UserID");
        session.setAttribute("cartQuantity", userRepository.countCart(userId));// Xóa cart từ ID được cung cấp
        return "redirect:/cart"; // Chuyển hướng trở lại trang giỏ hàng sau khi xóa
    }
    
    

    @GetMapping("/after-checkout")
    public String after(Model model, HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        User user = userRepository.findByID(userId);
        model.addAttribute("user", user);
        model.addAttribute("orderId", this.orderId);
        model.addAttribute("total", this.totalText);
        return "client/after-checkout";
    }


    @GetMapping("/vnpay_return")
    public String getVnpay(@RequestParam(value = "vnp_BankCode") String bankCode,
		            @RequestParam(value = "vnp_TxnRef") String vnpCode,
		            @RequestParam(value = "vnp_Amount") String amount, Model model, HttpSession session
		){
		Integer orderId = (Integer) session.getAttribute("orderId");
    	//Integer orderId = 36;
		orderRepository.updatePaymentOrder(orderId, vnpCode);
		Order order = orderRepository.findById(orderId);
        Integer userId = (Integer) session.getAttribute("UserID");
		//Integer userId = 1;
        User user = userRepository.findByID(userId);
        model.addAttribute("user", user);
        model.addAttribute("orderId", orderId);
		model.addAttribute("order", order);
		model.addAttribute("bankCode", bankCode);
		model.addAttribute("vnpCode", vnpCode);
		model.addAttribute("amount", amount);
		model.addAttribute("total", amount);
		return "client/vnpay_return";
		}
    
	
}
