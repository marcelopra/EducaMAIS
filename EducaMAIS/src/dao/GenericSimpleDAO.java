package dao;

import entidades.AbstractEntity;
import java.util.List;

public abstract class GenericSimpleDAO<E extends AbstractEntity, Id> extends DAO {
    
    public abstract void persist(E entidade);

    public abstract void atualizar(E entidade);

    public abstract void delete(E entidade);

    public abstract List<E> listagem();
    
    public abstract E carregar(Id id);
}
