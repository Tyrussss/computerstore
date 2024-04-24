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
	/*
	 * public String registerUser(@Valid @ModelAttribute User user, BindingResult
	 * bindingResult, @RequestParam("avatar") MultipartFile avatarFile, Model model)
	 * {
	 * 
	 * if (bindingResult.hasErrors()) { System.out.println("Binding Errors: " +
	 * bindingResult.getAllErrors()); return "register"; // Return to the
	 * registration page with an error message } String fileName =
	 * avatarFile.getOriginalFilename();
	 * 
	 * try { String uploadDir = "src/main/resources/static/clic/img/"; String
	 * savedFileName = FileUploadUtil.saveFile(Paths.get(uploadDir), fileName,
	 * file);
	 * 
	 * // Set the avatar file name in the user object user.setAvatar(savedFileName);
	 * 
	 * // Save user details in database userRepository.insert(user);
	 * 
	 * model.addAttribute("message", "User registered successfully."); } catch
	 * (IOException e) { model.addAttribute("message", "Error registering user: " +
	 * e.getMessage()); e.printStackTrace(); return "admin/register"; // Return to
	 * the registration page with an error message }
	 * 
	 * return "redirect:/login"; }
	 */
    
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
        
        List<Cart> carts = cartRepository.findByUserID(UserID);
        model.addAttribute("carts", carts); // Thêm danh sách cart vào model
        return "client/cart"; // Trả về view "cart" trong thư mục client
    }

    @GetMapping("/checkout") // Ánh xạ yêu cầu GET đến "/checkout" đến phương thức xử lý checkout
    public String checkout(HttpSession session, Model model) { // Phương thức xử lý cho "/checkout"
        model.addAttribute("categories", cate.findAll()); // Thêm danh sách danh mục vào model
        Integer userId = (Integer) session.getAttribute("userId");
        List<Cart> carts = cartRepository.findByUserID(userId);

        Float total = 0f;
        for(Cart cart : carts) {
            total += cart.getTotalPrice();
        }
        this.totalResult = total;
        model.addAttribute("total", total);
        model.addAttribute("carts", carts); // Thêm danh sách cart vào model
        return "client/checkout"; // Trả về view "checkout" trong thư mục client
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
        return "redirect:/cart";
    }


    @GetMapping("/checkoutFinal")
    public String checkoutFinal(HttpSession session) throws UnsupportedEncodingException {

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType = "other";
        String bankCode = "NCB";

        String vnp_TxnRef = VNPayConfig.getRandomNumber(8);
        String vnp_IpAddr = "127.0.0.1";

        String vnp_TmnCode = VNPayConfig.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version", vnp_Version);
        vnp_Params.put("vnp_Command", vnp_Command);
        vnp_Params.put("vnp_TmnCode", vnp_TmnCode);
        long fix = (long) Math.round(this.totalResult * 100);
        long fix2 = (long) Math.round(this.totalResult);
        this.totalText = String.valueOf(fix2);
        vnp_Params.put("vnp_Amount", String.valueOf(fix));
        System.out.println(fix);
        vnp_Params.put("vnp_CurrCode", "VND");

        vnp_Params.put("vnp_BankCode", bankCode);
        vnp_Params.put("vnp_TxnRef", vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        vnp_Params.put("vnp_Locale", "vn");
        vnp_Params.put("vnp_ReturnUrl", VNPayConfig.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr", vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String vnp_CreateDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_CreateDate", vnp_CreateDate);

        cld.add(Calendar.MINUTE, 15);
        String vnp_ExpireDate = formatter.format(cld.getTime());
        vnp_Params.put("vnp_ExpireDate", vnp_ExpireDate);

        List fieldNames = new ArrayList(vnp_Params.keySet());
        Collections.sort(fieldNames);
        StringBuilder hashData = new StringBuilder();
        StringBuilder query = new StringBuilder();
        Iterator itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) vnp_Params.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                //Build hash data
                hashData.append(fieldName);
                hashData.append('=');
                hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                //Build query
                query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII.toString()));
                query.append('=');
                query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString()));
                if (itr.hasNext()) {
                    query.append('&');
                    hashData.append('&');
                }
            }
        }
        String queryUrl = query.toString();
        String vnp_SecureHash = VNPayConfig.hmacSHA512(VNPayConfig.secretKey, hashData.toString());
        queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
        String paymentUrl = VNPayConfig.vnp_PayUrl + "?" + queryUrl;
        return "redirect:" + paymentUrl;
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

    @GetMapping("/getPaymentInfo")
    public String getInfo(@RequestParam("vnp_TransactionNo") String transactionNo,
                          @RequestParam("vnp_OrderInfo") String orderInfo,
                          @RequestParam("vnp_TransactionStatus") String transactionStatus, HttpSession session) {
        if(!Objects.equals(transactionNo, "0")) {
            Integer userId = (Integer) session.getAttribute("userId");
            List<Cart> carts = cartRepository.findByUserID(userId);
            Order order = new Order();
            order.setPaymentStatus(true);
            order.setCreated_Date(Date.valueOf(LocalDate.now()));
            order.setUserID(userId);
            int orderId = orderRepository.insert(order);
            this.orderId = orderId;
            for(Cart cart : carts) {
                OrderDetail orderDetail = new OrderDetail();
                orderDetail.setProductID(cart.getProductID());
                orderDetail.setTotal(cart.getTotalPrice());
                orderDetail.setSubTotal(cart.getPrice());
                orderDetail.setQuantity(cart.getQuantity());
                orderDetail.setOrderID(orderId);
                orderDetailRepository.insert(orderDetail);
                cartRepository.deleteById(cart.getCartID());
            }
        }
        return "redirect:/after-checkout";
    }
    
    @GetMapping("/register") // Ánh xạ yêu cầu GET đến "/register" đến phương thức xử lý clientReg
    public String clientReg(Model model, UserReg userReg) { // Phương thức xử lý cho "/register"
        model.addAttribute("userReg", userRep.findAll()); // Lấy danh sách người dùng đã đăng ký và thêm vào model
        
        List<String> listRole = Arrays.asList("Admin", "Customer", "Subscriber"); // Tạo danh sách vai trò
        model.addAttribute("listRole", listRole); // Thêm danh sách vai trò vào model
        return "client/register"; // Trả về view "register" trong thư mục client
    }
}
