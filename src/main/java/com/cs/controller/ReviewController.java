package com.cs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cs.model.Product;
import com.cs.model.Review;
import com.cs.model.User;
import com.cs.repository.ProductRepository;
import com.cs.repository.ReviewRepository;
import com.cs.repository.ReviewService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/reviews")
public class ReviewController {
	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private ProductRepository productRepository;


	

	    @PostMapping("/saveComment")
	    public String submitCommentForm(Review review,Model model, HttpSession session, @PathVariable int productID) {
	        reviewRepository.insert(review); // Lưu đánh giá vào cơ sở dữ liệu
	        // Kiểm tra xem người dùng đã đăng nhập chưa
		    User loggedInUser = (User) session.getAttribute("loggedInUser");
		    if (loggedInUser == null) {
		        // Nếu chưa đăng nhập, chuyển hướng đến trang đăng nhập
		        String currentUrl = "/comment/" + productID;
		        session.setAttribute("redirectUrl", currentUrl);
		        return "redirect:/login"; // Thay thế "/login" bằng đường dẫn của trang đăng nhập của bạn
		    }
		    else {
		    // Kiểm tra xem người dùng đã mua sản phẩm đó chưa
		    boolean hasPurchased = productRepository.hasPurchasedProduct(loggedInUser.getUserID(), productID);
		    if (!hasPurchased) {
		        // Nếu chưa mua sản phẩm, chuyển hướng đến trang thông báo
		        return "redirect:/message/not-purchased"; // Thay thế "/message/not-purchased" bằng đường dẫn của trang thông báo
		    }
		    
		    // Truyền thông tin user vào model
		    model.addAttribute("user", loggedInUser);
		    // Truyền thông tin product vào model
		    Product product = productRepository.findByID(productID);
		    model.addAttribute("product", product);
		    
		    // Trả về tên của trang HTML chứa form nhập comment
		    return "comment-form";
		    }
	        
	    }
}
