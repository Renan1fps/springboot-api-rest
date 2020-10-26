package com.renannunes.aplicationalgaapi.domain.service;

import com.renannunes.aplicationalgaapi.domain.exceptions.NegocioException;
import com.renannunes.aplicationalgaapi.domain.model.Cliente;
import com.renannunes.aplicationalgaapi.domain.model.OrdemServico;
import com.renannunes.aplicationalgaapi.domain.model.StatusOrdemServico;
import com.renannunes.aplicationalgaapi.repository.ClienteRepository;
import com.renannunes.aplicationalgaapi.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class GestaoOrdemServicoService {
    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    public OrdemServico criar(OrdemServico ordemServico) {
        Cliente cliente = clienteRepository.findById(ordemServico.getCliente().getId())
                .orElseThrow(() -> new NegocioException("Cliente não encontrado"));

        ordemServico.setCliente(cliente);
        ordemServico.setStatus(StatusOrdemServico.ABERTA);
        ordemServico.setDataAbertura(OffsetDateTime.now());

        return ordemServicoRepository.save(ordemServico);
    }

}
