package com.springojwt22.spring.jwt22.controller;

import com.springojwt22.spring.jwt22.model.Client;
import com.springojwt22.spring.jwt22.model.Role;
import com.springojwt22.spring.jwt22.service.ClientService;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping("/display")
    public ResponseEntity<List<Client>> displayAllClient() {
        return ResponseEntity.ok().body(clientService.getClient());
    }

    @GetMapping("display/{clientUsername}")
    public ResponseEntity<Client> showByUsername(@PathVariable String clientUsername) {
        return ResponseEntity.ok().body(clientService.getClient(clientUsername));
    }

    @PostMapping("/add/client")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        System.out.println("Called!!!");
        return ResponseEntity.ok().body(clientService.saveClient(client));
    }

    @PostMapping("/add/role")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        return ResponseEntity.ok().body(clientService.saveRole(role));
    }

    @PostMapping("/assign/role")
    public ResponseEntity<?> assignRole(@RequestBody RoleToClient assign) {
        clientService.addRoleToClient(assign.getClientUsername(), assign.getRoleName());
        return ResponseEntity.ok().build();
    }

}

@Data
class RoleToClient {
    private String clientUsername;
    private String roleName;
}
