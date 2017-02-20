package processos.criarpedido;

import entidades.Aluno;
import entidades.Pedido;
import entidades.PedidoItem;
import entidades.ValidacaoEntidadeException;
import entidades.Vendedor;
import interfaces.ISimpleController;
import java.util.Date;
import java.util.List;
import util.Msg;

public class PedidoController implements ISimpleController<Pedido, Integer> {
    
    private final PedidoDAO dao = new PedidoDAO();

    @Override
    public void salvar(Pedido pedido) {
        try {
            pedido.valida();
            dao.persist(pedido);
        } catch (ValidacaoEntidadeException ex) {
            new Msg().msgAtencao(ex.getMessage());
        }
    }

    @Override
    public void atualizar(Pedido pedido) {
        try {
            pedido.valida();
            dao.atualizar(pedido);
        } catch (ValidacaoEntidadeException ex) {
            new Msg().msgAtencao(ex.getMessage());
        }
    }
    
    public boolean verificaSeTodosItensTiveramCertificadoImpresso(List<PedidoItem> listagemItens) {
        for(PedidoItem i : listagemItens) {
            if(i.getNumeroRegistro() == null || i.getNumeroRegistro() == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void deletar(Pedido pedido) {
        if(!pedido.isAntigo()) {
            if(pedido.getDataEmissaoCertificado() == null) {
                if(new Msg().msgConfirmacao("Deseja realmente excluir o pedido de código '" + pedido.getCodigo()+ "'?") == 0) {
                    dao.delete(pedido);
                }
            } else {
                new Msg().msgAtencao("Você não pode excluir um pedido que já teve o certificado emitido.");
            }
        } else {
            new Msg().msgAtencao("Este curso foi realizado na empresa antiga, e não pode ser excluído.");
        }
    }

    @Override
    public List<Pedido> listagem() {
        return dao.listagem();
    }
    
    public List<Pedido> listagem(Aluno aluno) {
        if(aluno != null) {
            return dao.listagem(aluno);
        }
        return null;
    }
    
    public List<Pedido> listagem(Vendedor vendedor, Date dataInicio, Date dataFim) {
        if(vendedor != null && dataInicio != null && dataFim != null) {
            return dao.listagem(vendedor, dataInicio, dataFim);
        }
        return null;
    }
    
    public List<Pedido> listagemRecebimentoPendente(Vendedor vendedor) {
        if(vendedor != null) {
            return dao.listagemRecebimentoPendente(vendedor);
        }
        return null;
    }
    
    @Override
    public Pedido carregar(Integer codigo) {
        return dao.carregar(codigo);
    }
    
    public void atualizarNumeroRegistro(Integer codigoPedido, Integer numeroRegistro) {
        dao.atualizarNumeroRegistro(codigoPedido, numeroRegistro);
    }
    
    public void atualizarDataEmissao(Integer codigoPedido, java.sql.Date dataEmissao) {
        dao.atualizarDataEmissao(codigoPedido, dataEmissao);
    }
    
    public void atualizarCdGravado(Pedido pedido) {
        dao.atualizarCdGravado(pedido.getCodigo());
    }
    
    public void atualizarCertificadoEmitido(Pedido pedido) {
        dao.atualizarCertificadoEmitido(pedido.getCodigo());
    }
    
    public void cancelarCurso(Pedido pedido) {
        if(pedido != null) {
            if(!pedido.isAntigo()) {
                if(new Msg().msgConfirmacao("Confirma o cancelamento?") == 0) {
                    dao.cancelarCurso(pedido.getCodigo());
                }
            } else {
                new Msg().msgAtencao("Este curso foi realizado na empresa antiga, e não pode ser cancelado.");
            }
        }
    }
    
    public void receberCurso(Pedido pedido) {
        if(pedido != null) {
            if(new Msg().msgConfirmacao("Confirma o recebimento?") == 0) {
                dao.receberCurso(pedido.getCodigo());
            }
        }
    }
    
    public int getProximoNumeroRegistro() {
        return dao.getProximoNumeroRegistro();
    }
}
