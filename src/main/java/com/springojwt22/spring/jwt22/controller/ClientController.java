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

    //API to show all the client by the client/admin
    @GetMapping("/client")
    public ResponseEntity<List<Client>> displayAllClient() {
        return ResponseEntity.ok().body(clientService.getClient());
    }

    //API to show the client by username by the client/admin
    @GetMapping("client/{clientUsername}")
    public ResponseEntity<Client> showByUsername(@PathVariable String clientUsername) {
        return ResponseEntity.ok().body(clientService.getClient(clientUsername));
    }

    //API to save a client only by the client
    @PostMapping("client/save")
    public ResponseEntity<Client> addClient(@RequestBody Client client) {
        System.out.println("Called!!!");
        return ResponseEntity.ok().body(clientService.saveClient(client));
    }

    //API to approve a client only by the admin
    @PostMapping("client/approve")
    public ResponseEntity<Client> approveClient(@RequestBody Client client) {
        System.out.println("Called!!!");
        return ResponseEntity.ok().body(clientService.saveClient(client));
    }

    //API to save roles only by the admin
    @PostMapping("client/save/role")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        return ResponseEntity.ok().body(clientService.saveRole(role));
    }

    //API to assign role to the client only by the admin
    @PostMapping("client/assign/role")
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
