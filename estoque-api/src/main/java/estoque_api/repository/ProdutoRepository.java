package estoque_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import estoque_api.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
