package estoque_api.repository;

import estoque_api.dto.ProdutoResponseDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import estoque_api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

        boolean existsByNome(String nome);
    }



