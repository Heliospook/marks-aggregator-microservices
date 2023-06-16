package com.example.dataprovider.repositories;

import com.example.dataprovider.models.Batch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchRepository extends JpaRepository<Batch, String> {
}
