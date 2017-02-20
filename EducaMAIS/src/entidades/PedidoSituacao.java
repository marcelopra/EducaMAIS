/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Objects;

/**
 *
 * @author Marcelo
 */
public class PedidoSituacao {
    
    public static PedidoSituacao MONTAGEM_PENDENTE = new PedidoSituacao(1, "Montagem pendente");
    public static PedidoSituacao ENTREGA_PENDENTE = new PedidoSituacao(2, "Entrega pendente");
    public static PedidoSituacao RECEBIMENTO_PENDENTE = new PedidoSituacao(3, "Recebimento pendente");
    public static PedidoSituacao PEDIDO_CONCLUIDO = new PedidoSituacao(4, "Pedido concluído");
    public static PedidoSituacao DESCONHECIDO = new PedidoSituacao(-1, "Desconhecido");
    
    public static PedidoSituacao[] getArray() {
        return new PedidoSituacao[] {MONTAGEM_PENDENTE, ENTREGA_PENDENTE, RECEBIMENTO_PENDENTE, PEDIDO_CONCLUIDO};
    }
    
    public static PedidoSituacao getByCodigo(Integer codigo) {
        for(PedidoSituacao p : getArray()) {
            if(p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return DESCONHECIDO;
    }
    
    private Integer codigo;
    private String descricao;

    public PedidoSituacao(Integer codigo, String descricao) {
        this.codigo = codigo;
        this.descricao = descricao;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final PedidoSituacao other = (PedidoSituacao) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return descricao;
    }
    
}
