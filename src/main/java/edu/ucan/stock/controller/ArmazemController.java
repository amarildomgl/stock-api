package edu.ucan.stock.controller;

import edu.ucan.stock.dto.ArmazemDTO;
import edu.ucan.stock.entities.Armazem;
import edu.ucan.stock.services.ArmazemService;
import edu.ucan.stock.utils.BaseController;
import edu.ucan.stock.utils.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/armazem")
public class ArmazemController extends BaseController {

    @Autowired
    private ArmazemService service;

    @GetMapping("/pagina")
    public ResponseEntity<ResponseBody> get(
            @RequestParam(defaultValue = "0") int pagina,
            @RequestParam(defaultValue = "10") int itens) {
        List<ArmazemDTO> armazens = this.service.findAll(pagina, itens)
                .getContent()
                .stream()
                .map(service::toDto)
                .collect(Collectors.toList());
        return this.ok("Lista paginada", armazens);
    }

    @GetMapping
    public ResponseEntity<ResponseBody> get() {
        return this.ok("Lista", this.service.findAll()
                .stream()
                .map(service::toDto)
                .collect(Collectors.toList()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseBody> find(@PathVariable Integer id) {
        Optional<Armazem> entidade = service.findById(id);
        if (entidade.isPresent()) {
            ArmazemDTO dto = service.toDto(entidade.get());
            return this.ok("Encontrado com sucesso.", dto);
        }
        return this.notFound("Não encontrado.", null);
    }

    @GetMapping("/filtro")
    public ResponseEntity<ResponseBody> findFilter(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String codigo) {
        Optional<Armazem> entidade = service.findByFilter(nome, codigo);
        if (entidade.isPresent()) {
            ArmazemDTO dto = service.toDto(entidade.get());
            return this.ok("Encontrado com sucesso.", dto);
        }
        return this.notFound("Não encontrado.", null);
    }

    @PostMapping
    public ResponseEntity<ResponseBody> create(@RequestBody ArmazemDTO entidade) {
        Armazem savedEntity = this.service.create(service.toEntity(entidade));
        ArmazemDTO savedDto = service.toDto(savedEntity);
        return this.created("Adicionado com sucesso.", savedDto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseBody> edit(@PathVariable("id") Integer id, @RequestBody ArmazemDTO dto) {
        Armazem entidade = service.toEntity(dto);
        Armazem updatedEntity = service.update(id, entidade);
        ArmazemDTO updatedDto = service.toDto(updatedEntity);
        return this.ok("Editado com sucesso.", updatedDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseBody> delete(@PathVariable("id") Integer id) {
        return this.ok("Eliminado com sucesso.", service.delete(id));
    }
}
