package com.renannunes.aplicationalgaapi.clientendpoint;

import com.renannunes.aplicationalgaapi.domain.model.OrdemServico;
import com.renannunes.aplicationalgaapi.domain.service.GestaoOrdemServicoService;
import com.renannunes.aplicationalgaapi.repository.OrdemServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/ordens-servico")
public class OrdemServicoEndpoint {
    @Autowired
    private GestaoOrdemServicoService gestaoOrdemServico;

    @Autowired
    private OrdemServicoRepository ordemServicoRepository;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrdemServico criar(@Valid @RequestBody OrdemServico ordemServico) {
        return gestaoOrdemServico.criar(ordemServico);
    }


}
