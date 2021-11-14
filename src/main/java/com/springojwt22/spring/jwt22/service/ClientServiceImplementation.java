package com.springojwt22.spring.jwt22.service;

import com.springojwt22.spring.jwt22.model.Client;
import com.springojwt22.spring.jwt22.model.Role;
import com.springojwt22.spring.jwt22.repository.ClientRepository;
import com.springojwt22.spring.jwt22.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientServiceImplementation implements ClientService, UserDetailsService {
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String clientUsername) throws UsernameNotFoundException {
        Client client = clientRepository.findByClientUsername(clientUsername);
        if (client == null) {
            log.error("User not found on the database");
            throw new UsernameNotFoundException("user not found on database!");
        } else {
            log.info("user found on database");
        }
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        client.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority(role.getRoleName())));
        return new User(client.getClientUsername(), client.getClintPassword(), authorities);
    }


    @Override
    public Client saveClient(Client client) {
        client.setClintPassword(passwordEncoder.encode(client.getClintPassword()));
        log.info("saving new client {}", client.getClientName());
        return clientRepository.save(client);
    }

    @Override
    public Role saveRole(Role role) {
        log.info("Saving new role {}", role.getRoleName());
        return roleRepository.save(role);
    }

    @Override
    public void addRoleToClient(String clientUsername, String roleName) {
        Client client = clientRepository.findByClientUsername(clientUsername);
        Role role = roleRepository.findByRoleName(roleName);
        log.info("adding role {} to client {}", role.getRoleName(), client.getClientName());
        client.getRoles().add(role);
    }

    @Override
    public Client getClient(String clientUsername) {
        log.info("finding client by username {}", clientUsername);
        return clientRepository.findByClientUsername(clientUsername);
    }

    @Override
    public List<Client> getClient() {
        log.info("see all clients");
        return clientRepository.findAll();
    }


}
