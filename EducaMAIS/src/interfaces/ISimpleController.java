package interfaces;

import java.util.List;

/**
 *
 * @author adriano
 */
public interface ISimpleController<T, Id> {

    /**
     * Salva o objeto no banco de dados.
     */
    void salvar(T entidade);

    /**
     * Atualiza o objeto no banco de dados.
     */
    void atualizar(T entidade);

    /**
     * Deleta o objeto no banco de dados.
     */
    void deletar(T entidade);
    
    /**
     * @return Retorna uma listagem de todos os objetos cadastrados no banco de dados sem filtros.
     */
    List<T> listagem();
    
    /**
     * @return Retorna o objeto de ID informado. Caso o objeto não exista no banco de dados é retornado NULL
     */
    T carregar(Id id);
}
