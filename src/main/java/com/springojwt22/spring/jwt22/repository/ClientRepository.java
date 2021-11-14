package com.springojwt22.spring.jwt22.repository;

import com.springojwt22.spring.jwt22.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByClientUsername(String clientUsername);
}
