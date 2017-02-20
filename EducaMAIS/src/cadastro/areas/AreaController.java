package cadastro.areas;

import cadastro.alunos.*;
import entidades.Aluno;
import entidades.Area;
import entidades.ValidacaoEntidadeException;
import interfaces.ISimpleController;
import java.util.List;
import util.Msg;

public class AreaController implements ISimpleController<Area, Integer> {
    
    private final AreaDAO dao = new AreaDAO();

    @Override
    public void salvar(Area area) {
        try {
            area.valida();
            dao.persist(area);
        } catch (ValidacaoEntidadeException ex) {
            new Msg().msgAtencao(ex.getMessage());
        }
    }

    @Override
    public void atualizar(Area area) {
        try {
            area.valida();
            dao.atualizar(area);
        } catch (ValidacaoEntidadeException ex) {
            new Msg().msgAtencao(ex.getMessage());
        }
    }

    @Override
    public void deletar(Area area) {
        if(new Msg().msgConfirmacao("Deseja realmente excluir a área '" + area.getDescricao()+ "'?") == 0) {
            dao.delete(area);
        }
    }

    @Override
    public List<Area> listagem() {
        return dao.listagem();
    }
    
    public List<Area> listagem(String descricao) {
        return dao.listagem(descricao);
    }

    @Override
    public Area carregar(Integer id) {
        return dao.carregar(id);
    }
}
