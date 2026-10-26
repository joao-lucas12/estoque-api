package estoque_api.service;

import estoque_api.dto.ProdutoRequestDTO;
import estoque_api.dto.ProdutoResponseDTO;
import estoque_api.exceptions.ProdutoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import estoque_api.model.Produto;
import estoque_api.repository.ProdutoRepository;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public  List <ProdutoResponseDTO> listarTodos() {

        return produtoRepository.findAll().stream()
                .map(produto -> converterParaDto(produto))
                .collect(Collectors.toList());

    }

    private Produto buscarEntidade(Long id){
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado com id: " + id));
    }

    public ProdutoResponseDTO buscarPorId(Long id) {

        Produto produto = buscarEntidade(id);

        return converterParaDto(produto);
    }

    public void deletar(Long id) {

       buscarEntidade(id);

        produtoRepository.deleteById(id);
    }

    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produtoExistente = buscarEntidade(id);

        produtoExistente.setNome(dto.getNome());
        produtoExistente.setDescricao(dto.getDescricao());
        produtoExistente.setPreco(dto.getPreco());
        produtoExistente.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produtoExistente.setCategoria(dto.getCategoria());

        Produto produtoSalvo = produtoRepository.save(produtoExistente);

        return converterParaDto(produtoSalvo);
    }

    public ProdutoResponseDTO salvar(ProdutoRequestDTO dto) {
        Produto produto = new Produto();

        produto.setNome(dto.getNome());
        produto.setDescricao(dto.getDescricao());
        produto.setPreco(dto.getPreco());
        produto.setQuantidadeEstoque(dto.getQuantidadeEstoque());
        produto.setCategoria(dto.getCategoria());

        Produto produtoBanco = produtoRepository.save(produto);
        return converterParaDto(produtoBanco);
    }

    public ProdutoResponseDTO converterParaDto(Produto produto){
        ProdutoResponseDTO produtoDto = new ProdutoResponseDTO();

        produtoDto.setId(produto.getId());
        produtoDto.setNome(produto.getNome());
        produtoDto.setDescricao(produto.getDescricao());
        produtoDto.setPreco(produto.getPreco());
        produtoDto.setQuantidadeEstoque(produto.getQuantidadeEstoque());
        produtoDto.setCategoria(produto.getCategoria());

        return produtoDto;
    }



}