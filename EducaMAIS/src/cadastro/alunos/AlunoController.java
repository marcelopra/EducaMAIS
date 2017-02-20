package cadastro.alunos;

import entidades.Aluno;
import entidades.ValidacaoEntidadeException;
import interfaces.ISimpleController;
import java.util.List;
import util.Msg;

public class AlunoController implements ISimpleController<Aluno, Integer> {
    
    private final AlunoDAO dao = new AlunoDAO();

    @Override
    public void salvar(Aluno aluno) {
        try {
            Aluno alunoRg = new AlunoController().carregarByRG(aluno.getRg());
            if(alunoRg == null) {
                aluno.valida();
                dao.persist(aluno);
            } else {
                new Msg().msgAtencao("O aluno '" + alunoRg.getNome() + "' já está cadastrado com o RG informado.");
            }
        } catch (ValidacaoEntidadeException ex) {
            new Msg().msgAtencao(ex.getMessage());
        }
    }

    @Override
    public void atualizar(Aluno aluno) {
        try {
            aluno.valida();
            dao.atualizar(aluno);
        } catch (ValidacaoEntidadeException ex) {
            new Msg().msgAtencao(ex.getMessage());
        }
    }

    @Override
    public void deletar(Aluno aluno) {
        if(new Msg().msgConfirmacao("Deseja realmente excluir o aluno '" + aluno.getNome()+ "'?") == 0) {
            dao.delete(aluno);
        }
    }

    @Override
    public List<Aluno> listagem() {
        return dao.listagem();
    }
    
    public List<Aluno> listagem(String nome) {
        return dao.listagem(nome);
    }

    @Override
    public Aluno carregar(Integer codigo) {
        return dao.carregar(codigo);
    }
    
    public Aluno carregarByRG(String rg) {
        return dao.carregarByRG(rg);
    }
}
