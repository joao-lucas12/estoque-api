package estoque_api.controller;

import estoque_api.dto.ProdutoRequestDTO;
import estoque_api.dto.ProdutoResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import estoque_api.model.Produto;
import estoque_api.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping
    public ProdutoResponseDTO criar(@RequestBody ProdutoRequestDTO produtoRequestDto) {
        return produtoService.salvar(produtoRequestDto);
    }

    @GetMapping
    public List<ProdutoResponseDTO> listarTodos(){
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public ProdutoResponseDTO buscarPorId(@PathVariable Long id){
        return produtoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id){
        produtoService.deletar(id);
    }

    @PutMapping("/{id}")
    public ProdutoResponseDTO atualizar(@PathVariable Long id, @RequestBody Produto produtoAtualizado) {
       return produtoService.atualizar(id, produtoAtualizado);
    }
}
