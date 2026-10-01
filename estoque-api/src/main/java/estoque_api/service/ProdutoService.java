package estoque_api.service;

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

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public  Produto salvar(Produto produto) {

        return produtoRepository.save(produto);
    }

    public  List<Produto> listarTodos() {

        return produtoRepository.findAll();
    }

    public Produto buscarPorId(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new ProdutoNaoEncontradoException("Produto não encontrado com id: " + id));
    }

    public void deletar(Long id) {

        produtoRepository.deleteById(id);
    }

    public Produto atualizar(Long id, Produto produtoAtualizado){
        Produto produtoExistente = buscarPorId(id);
        produtoExistente.setNome(produtoAtualizado.getNome());
        produtoExistente.setDescricao(produtoAtualizado.getDescricao());
        produtoExistente.setPreco(produtoAtualizado.getPreco());
        produtoExistente.setQuantidadeEstoque(produtoAtualizado.getQuantidadeEstoque());
        produtoExistente.setCategoria(produtoAtualizado.getCategoria());

        return produtoRepository.save(produtoExistente);
    }



}