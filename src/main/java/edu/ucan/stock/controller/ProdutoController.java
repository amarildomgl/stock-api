package edu.ucan.stock.controller;


import edu.ucan.stock.dto.mapper.ProdutoMapper;
import edu.ucan.stock.dto.request.ProdutoRequest;
import edu.ucan.stock.entities.Produto;
import edu.ucan.stock.services.ProdutoService;
import edu.ucan.stock.utils.BaseController;
import edu.ucan.stock.utils.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/produto")
public class ProdutoController extends BaseController {

    @Autowired
    private ProdutoService service;

    @Autowired
    private ProdutoMapper mapper;

    @GetMapping("/pagina")
    public ResponseEntity<ResponseBody> get(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int itens) {
        return this.ok("Lista paginada",
                this.service.findAll(pagina, itens)
                        .getContent()
                        .stream()
                        .map(mapper::toResponse)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping
    public ResponseEntity<ResponseBody> get() {
        return this.ok("Lista",
                this.service.findAll()
                        .stream()
                        .map(mapper::toResponse)
                        .collect(Collectors.toList())
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> find(@PathVariable Integer id) {
        Optional<Produto> entidade = this.service.findById(id);
        if (entidade.isPresent())
            return this.ok("Encontrado com sucesso.",
                    mapper.toResponse(entidade.get()));
        return this.notFound("Não encontrada.", null);
    }

    @GetMapping("/filtro")
    public ResponseEntity<ResponseBody> findFilter(@RequestParam(required = false) String nome, @RequestParam(required = false) String codigo) {
        Optional<Produto> entidade = this.service.findByFilter(nome, codigo);
        if (entidade.isPresent())
            return this.ok("Encontrado com sucesso.", mapper.toResponse(entidade.get()));
        return this.notFound("Não encontrada.", null);
    }

    @PostMapping
    public ResponseEntity<ResponseBody> create(@RequestBody ProdutoRequest request) {
        return this.created("Adicionado com sucesso.",
                mapper.toResponse(this.service.create(mapper.toEntity(request))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> delete(@PathVariable("id") Integer id) {
        return this.ok("Eliminado com sucesso.", mapper.toResponse(service.delete(id)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> edit(@PathVariable("id") Integer id, @RequestBody ProdutoRequest request) {
        return this.ok("Editado com sucesso.", service.update(id, mapper.toEntity(request)));
    }
}
