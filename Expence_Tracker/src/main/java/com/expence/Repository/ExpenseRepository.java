package com.expence.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.expence.model.Expense;
import com.expence.model.User;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long>
{
	List<Expense> findByUser(User user);
}
