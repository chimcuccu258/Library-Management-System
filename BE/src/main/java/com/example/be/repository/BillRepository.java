package com.example.be.repository;

import com.example.be.model.Bill;
import com.example.be.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BillRepository extends JpaRepository<Bill,Long> {
  List<Bill> findByUserAndIsReturned(User user, Boolean is_returned);
}
