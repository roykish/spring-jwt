package com.springojwt22.spring.jwt22.service;

import com.springojwt22.spring.jwt22.model.Client;
import com.springojwt22.spring.jwt22.model.Role;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientService  {

    Client saveClient(Client client);

    Role saveRole(Role role);

    void addRoleToClient(String clientUsername, String roleName);

    Client getClient(String clientUsername);

    List<Client> getClient();


}
