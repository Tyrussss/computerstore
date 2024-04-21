package com.cs.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cs.model.User;
import com.cs.model.UserReg;
import com.cs.repository.UserRegRepository;
import com.cs.repository.UserRepository;
import com.cs.util.FileUploadUtil;
import com.cs.util.ImageService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/admin")

public class AdminController {
	@Autowired
	UserRepository userRep;
	UserRegRepository userRegRep;
	@Autowired
	private FileUploadUtil fileUploadUtil;
	
	@GetMapping("")
	public String adindex() {
		return "admin/ad_index";
	}
	
	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public String account(Model model) {
		
		model.addAttribute("user", userRep.findAll());
				
		List<String> listRole = Arrays.asList("Admin", "Customer", "Subcriber");
        model.addAttribute("listRole", listRole);
		return "admin/account";
	}
	@GetMapping("/account/register")
    public String showForm(Model model) {
        
        model.addAttribute("userReg", new UserReg());
        
         
        List<String> listRole = Arrays.asList("Admin", "Customer", "Subcriber");
        model.addAttribute("listRole", listRole);
		return "client/register";
    }
	
	/* <--- user register---> */
	@PostMapping("/account/save")
    public String saveForm(Model model, User user, HttpServletRequest request) {
        
        userRep.insert(user);        
        
		return "admin/account";
    }
	
	@PostMapping("/register/save")
    public String saveUser(Model model, User user, HttpServletRequest request) {
        
        userRep.insert(user);        
        
		return "redirect:/login";
    }
	
	/* <--- admin create---> */
	@PostMapping("/account/create")
    public String create(@RequestParam("imageFile") MultipartFile file, Model model, User user, HttpServletRequest request) {
		String fileName = file.getOriginalFilename();
        String message;

        try {
            String fileCode = "avatar"; // Or replace with dynamic code generation
            String filePath = fileUploadUtil.saveFile(Paths.get("static/client/img/"), fileName, file, fileCode);
            message = "Image uploaded successfully: " + fileName;
        } catch (IOException e) {
            message = "Error uploading image: " + e.getMessage();
            e.printStackTrace();
        }        
        model.addAttribute("message", message);        
        
        String email = request.getParameter("Email"); // Access form field value
        
        
        int count = userRep.findEmail(email);
        
        if (count > 0) {
            message = "This email exists in the database.";
            User subscriber = userRep.findIDByEmail(email);
            model.addAttribute("UserID", subscriber.getUserID());
            model.addAttribute("Email", subscriber.getEmail());
            model.addAttribute("Role", 0);
            model.addAttribute("Avatar", fileName);
            
            userRep.update(user);
            
        } else {
            message = "Field value does not exist in the database.";
            model.addAttribute("Avatar", fileName);
            userRep.insert(user);
        } 
               
        return "redirect:/login";
    }
	
	/* <--- user subscribe---> */
	@PostMapping("/account/subscribe")
	public String subscribe(Model model, User user, HttpServletRequest request) {
        
        userRep.subscribe(user);        
        
		return "redirect:/";
    }
	
	/*
	 * @GetMapping("/account/{id}/edit") 
	 * public String showForm(@RequestParam("id")
	 * int id,@ModelAttribute("user") User user) { user.setUserID(id);
	 * userRep.update(user);
	 * 
	 * return "client/edituser"; }
	 */
	
	
	/*
	 * @GetMapping("/edit/{id}") public String showEditForm(@RequestParam("id") int
	 * id, Model model) { User user = userRep.findByID(id);
	 * model.addAttribute("user", user); return "client/edituser"; }
	 */

    @PostMapping("/edit")  // Assuming form submits to /edit
    public String updateUser(@ModelAttribute User user, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
    	
    	if (imageFile.isEmpty()) {
            // Handle empty image scenario (optional)
            return "redirect:/edit/" + user.getUserID(); // Redirect to edit page
        }

        String fileName = imageFile.getOriginalFilename();
        String fileCode = "avatar/" + user.getUserID() + "_avatar"; // Consider using a more robust file code generation

        Path uploadPath = Paths.get("static/client/img/"); // Modify if needed
        FileUploadUtil.saveFile(uploadPath, fileName, imageFile, fileCode);

        // Update user object with image path or code (optional)
        // user.setImagePath(fileCode); // Adjust based on your data structure
        
    	
        userRep.update(user);  // Saves the updated user object
        return "redirect:/admin/account"; // Redirect to user list page
    }
    
    @PostMapping("/edit/save")  // Assuming form submits to /edit
    public String updateU(@ModelAttribute User user) {    	
        userRep.update(user);  // Saves the updated user object
        return "redirect:/admin/account"; // Redirect to user list page
    }
    
    @GetMapping("/edit/{id}")
    public String showUpdateForm(@PathVariable("id") int id, Model model, @RequestParam("imageFile") MultipartFile file) {
        User user = userRep.findByID(id);
        String fileName = file.getOriginalFilename();
        String message;

        try {
            String fileCode = "avatar"; // Or replace with dynamic code generation
            String filePath = fileUploadUtil.saveFile(Paths.get("static/client/img/"), fileName, file, fileCode);
            message = "Image uploaded successfully: " + fileName;
        } catch (IOException e) {
            message = "Error uploading image: " + e.getMessage();
            e.printStackTrace();
        }

        model.addAttribute("message", message);
        
        model.addAttribute("user", user);
        return "client/edituser";
    }
    
    
    @PostMapping("/uploadImage")  // Adjust path based on your needs
    public String uploadImage(@ModelAttribute User user, @RequestParam("imageFile") MultipartFile imageFile) throws IOException {

        if (imageFile.isEmpty()) {
            // Handle empty image scenario (optional)
            return "redirect:/edit/" + user.getUserID(); // Redirect to edit page
        }

        String fileName = imageFile.getOriginalFilename();
        String fileCode = user.getUserID() + "_avatar"; // Consider using a more robust file code generation

        Path uploadPath = Paths.get("static/client/img/"+ fileCode); 
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        FileUploadUtil.saveFile(uploadPath, fileName, imageFile, fileCode);

        // Update user object with image path or code (optional)
        // user.setImagePath(fileCode); // Adjust based on your data structure
        userRep.update(user); // If updating user object with image info

        return "redirect:/admin/account"; // Redirect to user list page (adjust as needed)
    }
    
    @Autowired
    private ImageService imgServ; // Service for image saving logic

    @GetMapping("/upload")
    public String uploadForm(@ModelAttribute User user) {
    	return "/client/success";
    }
    @PostMapping("/upload")
    public String uploadImg(@ModelAttribute User user, @RequestParam("imageFile") MultipartFile imageFile) {
    	try {
			imgServ.saveImg(user, imageFile);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      return "redirect:/client/success"; // Redirect to success page
    }
	
}
