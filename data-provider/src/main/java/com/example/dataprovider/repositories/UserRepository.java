package com.example.dataprovider.repositories;

import com.example.dataprovider.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {

}
