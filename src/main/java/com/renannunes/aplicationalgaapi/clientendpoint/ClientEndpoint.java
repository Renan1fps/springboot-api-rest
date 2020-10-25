package com.renannunes.aplicationalgaapi.clientendpoint;

import com.renannunes.aplicationalgaapi.domain.model.Client;
import com.renannunes.aplicationalgaapi.domain.service.CadastroClienteService;
import com.renannunes.aplicationalgaapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RequestMapping(path = "/v1")
@RestController
public class ClientEndpoint {
    @Autowired
    private CadastroClienteService cadastroCliente;

    @Autowired
    private ClientRepository clientRepository;


    @GetMapping(path = "/clients")
    public ResponseEntity<?> getClients() {
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/findByName")
    public ResponseEntity<?> findByName() {
        return new ResponseEntity<>(clientRepository.findByName("renan"), HttpStatus.OK);
    }

    @GetMapping(path = "/findId/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping(path = "/clients")
    @ResponseStatus(HttpStatus.CREATED)
    public Client save(@Valid @RequestBody Client client) {
        return cadastroCliente.salvar(client);
    }

    @PutMapping(path = "/clients/{id}")
    public ResponseEntity<Client> update(@Valid @PathVariable Long id, @RequestBody Client client) {
        if (!clientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        client.setId(id);
        cadastroCliente.salvar(client);
        return ResponseEntity.ok(client);

    }

    @DeleteMapping(path = "/clients/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!clientRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        clientRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


}
