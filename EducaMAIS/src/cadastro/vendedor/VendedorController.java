package cadastro.vendedor;

import entidades.Vendedor;
import entidades.ValidacaoEntidadeException;
import interfaces.ISimpleController;
import java.util.List;
import util.Msg;

public class VendedorController implements ISimpleController<Vendedor, Integer> {
    
    private final VendedorDAO usuarioDAO = new VendedorDAO();

    @Override
    public void salvar(Vendedor usuario) {
        try {
            usuario.valida();
            usuarioDAO.persist(usuario);
        } catch (ValidacaoEntidadeException ex) {
            new Msg().msgAtencao(ex.getMessage());
        }
    }

    @Override
    public void atualizar(Vendedor usuario) {
        try {
            usuario.valida();
            usuarioDAO.atualizar(usuario);
        } catch (ValidacaoEntidadeException ex) {
            new Msg().msgAtencao(ex.getMessage());
        }
    }

    @Override
    public void deletar(Vendedor aluno) {
        if(new Msg().msgConfirmacao("Deseja realmente excluir o vendedor '" + aluno.getNome()+ "'?") == 0) {
            usuarioDAO.delete(aluno);
        }
    }

    @Override
    public List<Vendedor> listagem() {
        return usuarioDAO.listagem();
    }

    @Override
    public Vendedor carregar(Integer id) {
        return usuarioDAO.carregar(id);
    }

}
