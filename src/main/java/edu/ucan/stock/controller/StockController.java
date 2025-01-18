package edu.ucan.stock.controller;

import edu.ucan.stock.dto.mapper.StockMapper;
import edu.ucan.stock.dto.request.StockRequest;
import edu.ucan.stock.entities.Stock;
import edu.ucan.stock.services.ArmazemService;
import edu.ucan.stock.services.ProdutoService;
import edu.ucan.stock.services.StockService;
import edu.ucan.stock.utils.BaseController;
import edu.ucan.stock.utils.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stock")
public class StockController extends BaseController {

    @Autowired
    private StockService service;

    @Autowired
    private ArmazemService armazemService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private StockMapper mapper;

    @GetMapping("/pagina")
    public ResponseEntity<ResponseBody> get(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int itens) {
        return this.ok("Lista paginada", this.service.findAll(pagina, itens).getContent().stream().map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping
    public ResponseEntity<ResponseBody> get() {
        return this.ok("Lista", this.service.findAll().stream().map(mapper::toResponse).collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> find(@PathVariable Integer id) {
        Optional<Stock> entidade = this.service.findById(id);
        if (entidade.isPresent()) return this.ok("Encontrado com sucesso.", mapper.toResponse(entidade.get()));
        return this.notFound("Não encontrada.", null);
    }

    @GetMapping("/filtro")
    public ResponseEntity<ResponseBody> findFilter(@RequestParam(required = false) Integer fkArmazem, @RequestParam(required = false) Integer fkProduto, @RequestParam(required = false, defaultValue = "false") boolean quantidadeCritica) {
        return this.ok("Lista", this.service.findAllFilterBy(fkArmazem, fkProduto, quantidadeCritica).stream().map(mapper::toResponse).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<ResponseBody> create(@RequestBody StockRequest request) {
        return this.created("Adicionado com sucesso.", mapper.toResponse(this.service.create(mapper.toEntity(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> delete(@PathVariable("id") Integer id) {
        return this.ok("Eliminado com sucesso.", mapper.toResponse(service.delete(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> edit(@PathVariable("id") Integer id, @RequestBody StockRequest request) {
        return this.ok("Editado com sucesso.", mapper.toResponse(service.update(id, mapper.toEntity(request))));
    }
}
