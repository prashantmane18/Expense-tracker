package com.expence.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.expence.Repository.ExpenseRepository;
import com.expence.model.Expense;
import com.expence.model.User;

@Service
public class ExpenseService 
{

    
	@Autowired
	private ExpenseRepository expenseRepo;
	
	public List<Expense> getExpenseByUser(User user)
	{
		return expenseRepo.findByUser(user);
	}
	
	public Expense saveExpense(Expense expense)
	{
		return expenseRepo.save(expense);
	}
	
	public Expense getExpenseByid(Long id)
	{
		return expenseRepo.getById(id);
	}
	
	
}
