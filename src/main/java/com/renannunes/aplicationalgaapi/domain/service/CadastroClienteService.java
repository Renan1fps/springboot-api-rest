package com.renannunes.aplicationalgaapi.domain.service;

import com.renannunes.aplicationalgaapi.domain.exceptions.SalvaException;
import com.renannunes.aplicationalgaapi.domain.model.Client;
import com.renannunes.aplicationalgaapi.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CadastroClienteService {
    @Autowired
    private ClientRepository clientRepository;

    public Client salvar(Client client) {
        Client clientExist = clientRepository.findByEmail(client.getEmail());
        if (clientExist != null && !clientExist.equals(client)) {
            throw new SalvaException("Cliente existente com esse email");
        }
        return clientRepository.save(client);
    }

    public void excluir(Long id) {
        clientRepository.deleteById(id);
    }
}
