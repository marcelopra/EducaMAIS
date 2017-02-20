package cadastro.cursos;

import entidades.Apostila;
import entidades.Curso;
import entidades.PedidoItem;
import entidades.ValidacaoEntidadeException;
import interfaces.ISimpleController;
import java.io.IOException;
import java.util.List;
import util.CopiarArquivos;
import util.ExcluirArquivos;
import util.Msg;

public class CursoController implements ISimpleController<Curso, Integer> {
    
    private final CursoDAO dao = new CursoDAO();

    @Override
    public void salvar(Curso curso) {
        try {
            
            curso.valida();
            dao.persist(curso);
            
            for(Apostila a : curso.getListagemApostilas()) {
                new CopiarArquivos().copiarArquivoParaDiretorio(a.getCaminhoCompleto(), curso.getDiretorioCurso());
            }
            
        } catch (ValidacaoEntidadeException ex) {
            new Msg().msgAtencao(ex.getMessage());
        } catch (IOException ex) {
            new Msg().msgAtencao(ex.getMessage());
        } catch (InterruptedException ex) {
            new Msg().msgAtencao(ex.getMessage());
        }
    }

    @Override
    public void atualizar(Curso curso) {
        try {
            curso.valida();
            dao.atualizar(curso);
            
            for(Apostila a : curso.getListagemApostilas()) {
                new CopiarArquivos().copiarArquivoParaDiretorio(a.getCaminhoCompleto(), curso.getDiretorioCurso());
            }
            
        } catch (ValidacaoEntidadeException ex) {
            new Msg().msgAtencao(ex.getMessage());
        } catch (IOException ex) {
            new Msg().msgAtencao(ex.getMessage());
        } catch (InterruptedException ex) {
            new Msg().msgAtencao(ex.getMessage());
        }
    }

    @Override
    public void deletar(Curso Curso) {
        if(new Msg().msgConfirmacao("Deseja realmente excluir o curso '" + Curso.getNome()+ "'?") == 0) {
            dao.delete(Curso);
            ExcluirArquivos.deleteDir(Curso.getDiretorioCurso());
        }
    }

    @Override
    public List<Curso> listagem() {
        return dao.listagem();
    }
    
    public List<Curso> listagem(String nomeCurso) {
        return dao.listagem(nomeCurso);
    }

    @Override
    public Curso carregar(Integer codigo) {
        return dao.carregar(codigo);
    }
}
