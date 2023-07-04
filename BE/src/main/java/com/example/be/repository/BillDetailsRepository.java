package com.example.be.repository;

import com.example.be.model.BillDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface BillDetailsRepository extends JpaRepository<BillDetails, Long> {

}
