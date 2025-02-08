package edu.ucan.stock.controller;

import edu.ucan.stock.dto.records.CategoriaRecord;
import edu.ucan.stock.entities.Categoria;
import edu.ucan.stock.services.CategoriaService;
import edu.ucan.stock.utils.BaseController;
import edu.ucan.stock.utils.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoriaController extends BaseController {

    @Autowired
    private CategoriaService service;

    @GetMapping("/pagina")
    public ResponseEntity<ResponseBody> get(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int itens) {
        List<CategoriaRecord> categorias = service.findAll(pagina, itens)
                .getContent()
                .stream()
                .map(service::toDto)
                .collect(Collectors.toList());
        return this.ok("Lista paginada", categorias);
    }


    @GetMapping
    public ResponseEntity<ResponseBody> get() {
        List<CategoriaRecord> categorias = service.findAll()
                .stream()
                .map(service::toDto)
                .collect(Collectors.toList());
        return this.ok("Lista completa", categorias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> find(@PathVariable Integer id) {
        Optional<Categoria> entidade = service.findById(id);
        if (entidade.isPresent()) {
            CategoriaRecord dto = service.toDto(entidade.get());
            return this.ok("Encontrado com sucesso.", dto);
        }
        return this.notFound("Não encontrada.", null);
    }

    @GetMapping("/filtro")
    public ResponseEntity<ResponseBody> findFilter(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String codigo) {
        Optional<Categoria> entidade = service.findByFilter(nome, codigo);
        if (entidade.isPresent()) {
            CategoriaRecord dto = service.toDto(entidade.get());
            return this.ok("Encontrado com sucesso.", dto);
        }
        return this.notFound("Não encontrada.", null);
    }

    @PostMapping
    public ResponseEntity<ResponseBody> create(@RequestBody CategoriaRecord dto) {
        Categoria entidade = service.toEntity(dto);
        Categoria savedEntity = service.create(entidade);
        CategoriaRecord savedDto = service.toDto(savedEntity);
        return this.created("Adicionado com sucesso.", savedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> delete(@PathVariable("id") Integer id) {
        return this.ok("Eliminado com sucesso.", service.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> edit(@PathVariable("id") Integer id, @RequestBody CategoriaRecord dto) {
        Categoria entidade = service.toEntity(dto);
        Categoria updatedEntity = service.update(id, entidade);
        CategoriaRecord updatedDto = service.toDto(updatedEntity);
        return this.ok("Editado com sucesso.", updatedDto);
    }
}
