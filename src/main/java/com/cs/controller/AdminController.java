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
    public String saveForm(@RequestParam("Avatar") MultipartFile file,Model model, User user, HttpServletRequest request) {
        
        userRep.insert(user);        
        
		return "admin/account";
    }
	
	/*
	 * @PostMapping("/register/save") public String saveUser(Model model, User user,
	 * HttpServletRequest request) {
	 * 
	 * userRep.insert(user);
	 * 
	 * return "redirect:/login"; }
	 */
	@GetMapping("/account/reg")
    public String show(Model model) {       
		return "client/clientreg";
    }
	
	@PostMapping("/register/save")
    public String saveUser(@RequestParam("avatar") MultipartFile file,
                           @RequestParam("username") String username,
                           @RequestParam("email") String email,
                           @RequestParam("password") String password,
                           @RequestParam("fullName") String fullName,
                           @RequestParam("phone") String phone,
                           @RequestParam("role") int role,
                           Model model) {

        String fileName = file.getOriginalFilename();

        try {
            // Save the file with its original name in the specified folder
            String uploadDir = "src/main/resources/static/clic/img/";
            FileUploadUtil.saveFile(Paths.get(uploadDir), fileName, file);
            
            // Create a new user object and set its attributes
            User user = new User();
            user.setUsername(username);
            user.setEmail(email);
            user.setPassword(password);
            user.setFullName(fullName);
            user.setPhone(phone);
            user.setAvatar(fileName);
            user.setRole(role);
            
            // Save user details in database
            userRep.insert(user);
            
            model.addAttribute("message", "User registered successfully.");
        } catch (IOException e) {
            model.addAttribute("message", "Error registering user: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/login";
    }
	
	/* <--- admin create---> */
	@PostMapping("/account/create")
    public String create(@RequestParam("avatar") MultipartFile file, @ModelAttribute User user, Model model) {
        String fileName = file.getOriginalFilename();
        String message;
        
        try {
            // Save the file with its original name in the specified folder
            String uploadDir = "src/main/resources/static/clic/img/";
            FileUploadUtil.saveFile(Paths.get(uploadDir), fileName, file);
            user.setAvatar(fileName);
            model.addAttribute(user);
            // Save user details in database
            userRep.insert(user);
            
            model.addAttribute("message", "User registered successfully.");
        } catch (IOException e) {
            model.addAttribute("message", "Error registering user: " + e.getMessage());
            e.printStackTrace();
        }

        return "redirect:/login";

		/*
		 * try { // Save the file with its original name in the specified folder String
		 * uploadDir = "src/static/clic/img/";
		 * FileUploadUtil.saveFile(Paths.get(uploadDir), fileName, file, "avatar");
		 * user.setAvatar(fileName); // Set the Avatar field with the original file name
		 * message = "Image uploaded successfully: " + fileName; } catch (IOException e)
		 * { message = "Error uploading image: " + e.getMessage(); e.printStackTrace();
		 * }
		 * 
		 * model.addAttribute("message", message);
		 * 
		 * String email = user.getEmail(); // Access email from the user object
		 * 
		 * int count = userRep.findEmail(email);
		 * 
		 * if (count > 0) { message = "This email exists in the database."; User
		 * existingUser = userRep.findIDByEmail(email);
		 * existingUser.setUsername(user.getUsername());
		 * existingUser.setPassword(user.getPassword());
		 * existingUser.setFullName(user.getFullName());
		 * existingUser.setPhone(user.getPhone()); existingUser.setRole(user.getRole());
		 * existingUser.setNewsletter(user.getNewsletter());
		 * existingUser.setAddress(user.getAddress());
		 * existingUser.setAvatar(user.getAvatar());
		 * 
		 * userRep.update(existingUser);
		 * 
		 * model.addAttribute("UserID", existingUser.getUserID());
		 * model.addAttribute("Email", existingUser.getEmail());
		 * model.addAttribute("Role", 0); model.addAttribute("Avatar",
		 * existingUser.getAvatar()); } else { message =
		 * "Email does not exist in the database."; userRep.insert(user); }
		 * 
		 * return "redirect:/login";
		 */
    }
	
	
	
	/*
	 * @PostMapping("/account/create") public String create(@RequestParam("Avatar")
	 * MultipartFile file, Model model, User user, HttpServletRequest request) {
	 * String fileName = file.getOriginalFilename(); String message;
	 * 
	 * try { String fileCode = "avatar"; // Or replace with dynamic code generation
	 * String filePath =
	 * fileUploadUtil.saveFile(Paths.get("src/static/client/img/"), fileName, file,
	 * fileCode); message = "Image uploaded successfully: " + fileName; } catch
	 * (IOException e) { message = "Error uploading image: " + e.getMessage();
	 * e.printStackTrace(); } model.addAttribute("message", message);
	 * 
	 * String email = request.getParameter("Email"); // Access form field value
	 * 
	 * 
	 * int count = userRep.findEmail(email);
	 * 
	 * if (count > 0) { message = "This email exists in the database."; User
	 * subscriber = userRep.findIDByEmail(email); model.addAttribute("UserID",
	 * subscriber.getUserID()); model.addAttribute("Email", subscriber.getEmail());
	 * model.addAttribute("Role", 0); model.addAttribute("Avatar", fileName);
	 * 
	 * userRep.update(user);
	 * 
	 * } else { message = "Field value does not exist in the database.";
	 * model.addAttribute("Avatar", fileName); userRep.insert(user); }
	 * 
	 * return "redirect:/login"; }
	 */
	
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
        FileUploadUtil.saveFile(uploadPath, fileName, imageFile);

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
            String filePath = fileUploadUtil.saveFile(Paths.get("static/client/img/"), fileName, file);
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
        FileUploadUtil.saveFile(uploadPath, fileName, imageFile);

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
