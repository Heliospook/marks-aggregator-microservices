package com.example.dataprovider.repositories;

import com.example.dataprovider.models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestRepository extends JpaRepository<Test, Long> {

}
