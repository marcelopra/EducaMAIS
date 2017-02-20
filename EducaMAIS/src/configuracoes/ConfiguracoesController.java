package configuracoes;

import entidades.Configuracoes;
import interfaces.ISimpleController;
import java.util.List;

public class ConfiguracoesController implements ISimpleController<Configuracoes, Integer> {
    
    private final ConfiguracoesDAO dao = new ConfiguracoesDAO();

    public Configuracoes carregar() {
        return dao.carregar();
    }

    @Override
    public void salvar(Configuracoes entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Configuracoes entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(Configuracoes entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Configuracoes> listagem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Configuracoes carregar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
