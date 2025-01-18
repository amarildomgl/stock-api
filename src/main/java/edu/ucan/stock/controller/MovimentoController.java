package edu.ucan.stock.controller;

import edu.ucan.stock.dto.mapper.MovimentoMapper;
import edu.ucan.stock.dto.request.MovimentoRequest;
import edu.ucan.stock.dto.response.MovimentoResponse;
import edu.ucan.stock.entities.Movimento;
import edu.ucan.stock.enums.TipoMovimento;
import edu.ucan.stock.services.MovimentoService;
import edu.ucan.stock.utils.BaseController;
import edu.ucan.stock.utils.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/movimento")
public class MovimentoController extends BaseController {

    @Autowired
    private MovimentoService service;

    @Autowired
    private MovimentoMapper mapper;


    @PostMapping()
    public ResponseEntity<ResponseBody> create(@RequestBody MovimentoRequest request) {
        Movimento movimento = service.create(mapper.toEntity(request));
        MovimentoResponse response = mapper.toResponse(movimento);
        return this.ok("Movimento registrado com sucesso", response);
    }

    @GetMapping("/filtro")
    public ResponseEntity<ResponseBody> findFilter(
            @RequestParam(required = false) Integer fkArmazem,
            @RequestParam(required = false) Integer fkProduto,
            @RequestParam(required = false) TipoMovimento tipoMovimento,
            @RequestParam(required = false) LocalDateTime data,
            @RequestParam(required = false) LocalDateTime dataInicial,
            @RequestParam(required = false) LocalDateTime dataFinal
    ) {
        return this.ok("Lista",
                this.service.findAllFilterBy(
                                fkArmazem,
                                fkProduto,
                                tipoMovimento,
                                data,
                                dataInicial, dataFinal)
                        .stream()
                        .map(mapper::toResponse)
                        .collect(Collectors.toList()));
    }

}
