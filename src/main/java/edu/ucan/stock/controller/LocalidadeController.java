package edu.ucan.stock.controller;

import edu.ucan.stock.dto.LocalidadeDTO;
import edu.ucan.stock.entities.Localidade;
import edu.ucan.stock.services.LocalidadeService;
import edu.ucan.stock.utils.BaseController;
import edu.ucan.stock.utils.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/localidade")
public class LocalidadeController extends BaseController {

    @Autowired
    private LocalidadeService service;

    @GetMapping("/pagina")
    public ResponseEntity<ResponseBody> get(@RequestParam(defaultValue = "0") int pagina, @RequestParam(defaultValue = "10") int itens) {
        List<LocalidadeDTO> dtos = service.findAll(pagina, itens).getContent().stream().map(service::toDTO).collect(Collectors.toList());
        return this.ok("Lista", dtos);
    }

    @GetMapping
    public ResponseEntity<ResponseBody> get() {
        List<LocalidadeDTO> dtos = service.findAll().stream().map(service::toDTO).collect(Collectors.toList());
        return this.ok("Lista completa", dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> find(@PathVariable Integer id) {
        Optional<Localidade> entidade = this.service.findById(id);
        if (entidade.isPresent()) {
            LocalidadeDTO dto = service.toDTO(entidade.get());
            return this.ok("Encontrado com sucesso.", dto);
        }
        return this.notFound("Não encontrada.", null);
    }

    @GetMapping("/filtro")
    public ResponseEntity<ResponseBody> findFilter(@RequestParam(required = false) String nome) {
        Optional<Localidade> entidade = this.service.findByFilter(nome);
        if (entidade.isPresent()) {
            LocalidadeDTO dto = service.toDTO(entidade.get());
            return this.ok("Encontrado com sucesso.", dto);
        }
        return this.notFound("Não encontrada.", null);
    }

    @PostMapping
    public ResponseEntity<ResponseBody> create(@RequestBody LocalidadeDTO dto) {
        Localidade entidade = service.toEntity(dto);
        return this.created("Adicionado com sucesso.", this.service.create(entidade));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> delete(@PathVariable("id") Integer id) {
        return this.ok("Eliminado com sucesso.", this.service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> edit(@PathVariable("id") Integer id, @RequestBody LocalidadeDTO dto) {
        Localidade entidade = service.toEntity(dto);
        return this.ok("Editado com sucesso.", this.service.update(id, entidade));
    }
}
