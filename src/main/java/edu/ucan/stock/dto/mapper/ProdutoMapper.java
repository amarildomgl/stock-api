package edu.ucan.stock.dto.mapper;

import edu.ucan.stock.dto.request.ProdutoRequest;
import edu.ucan.stock.dto.response.ProdutoResponse;
import edu.ucan.stock.entities.Categoria;
import edu.ucan.stock.entities.Produto;
import edu.ucan.stock.exceptions.CategoriaException;
import edu.ucan.stock.repositories.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto toEntity(ProdutoRequest request) {

        Categoria categoria = categoriaRepository.findById(request.getFkCategoria()).orElseThrow(() -> new CategoriaException("Categoria não encontrada"));

        Produto produto = new Produto();
        produto.setNome(request.getNome());
        produto.setCodigo(request.getCodigo());
        produto.setCategoria(categoria);

        return produto;
    }


    public ProdutoResponse toResponse(Produto produto) {
        ProdutoResponse response = new ProdutoResponse();
        response.setPkProduto(produto.getPkProduto());
        response.setNome(produto.getNome());
        response.setCodigo(produto.getCodigo());
        response.setCategoria(produto.getCategoria().getNome());

        return response;
    }

}
