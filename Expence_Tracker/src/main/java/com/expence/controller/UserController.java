package com.expence.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.expence.model.Expense;
import com.expence.model.User;
import com.expence.service.ExpenseService;
import com.expence.service.UserService;

@Controller
public class UserController
{
	@Autowired
	private UserService userService;
	
	@Autowired
	private ExpenseService expenseService;
	
	@GetMapping("/login")
	public String loginPage()
	{
		return "login";
	}
	
	@GetMapping("/register")
	public String registerPage(Model model)
	{
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String saveUser(@ModelAttribute User user)
	{
		userService.saveUser(user);
		return "redirect:/login";
	}
	
	@GetMapping("/dashboard")
	public String dashboard()
	{
		return "dashboard";
	}
	
	@GetMapping("/updatePage/{id}")
	public String updateExpensePage(Model model, @PathVariable Long id)
	{
		Expense expense = expenseService.getExpenseByid(id);
		model.addAttribute("expense", expense);
		return "/update";
	}
	
	@PostMapping("/update")
	public String updateExpense(@ModelAttribute Expense expense, Authentication auth)
	{
		User user = userService.getUserByUsername(auth.getName());
		expense.setUser(user);
		expenseService.saveExpense(expense);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/add")
	public String addExpense(Model model)
	{
		model.addAttribute("expense", new Expense());
		return "/addExpense";
	}
	
	@PostMapping("/save")
	public String saveExpense(@ModelAttribute Expense expense, Authentication auth)
	{
		User user = userService.getUserByUsername(auth.getName());
		expense.setUser(user);
		
		expenseService.saveExpense(expense);
		return "redirect:/dashboard";
	}
	
	@GetMapping("/expenseList")
	public String allExpenses(Model model ,Authentication auth)
	{
		User user = userService.getUserByUsername(auth.getName());
		model.addAttribute("list", expenseService.getExpenseByUser(user));
		return "viewExpense";
	}
	
}
