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
public class PedidoSituacaoGravacao {
    
    public static PedidoSituacaoGravacao PENDENTE = new PedidoSituacaoGravacao(1, "Pendente");
    public static PedidoSituacaoGravacao CONCLUIDA = new PedidoSituacaoGravacao(2, "Concluída");
    public static PedidoSituacaoGravacao DESCONHECIDO = new PedidoSituacaoGravacao(-1, "Desconhecido");
    
    public static PedidoSituacaoGravacao[] getArray() {
        return new PedidoSituacaoGravacao[] {PENDENTE, CONCLUIDA};
    }
    
    public static PedidoSituacaoGravacao getByCodigo(Integer codigo) {
        for(PedidoSituacaoGravacao p : getArray()) {
            if(p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return DESCONHECIDO;
    }
    
    private Integer codigo;
    private String descricao;

    public PedidoSituacaoGravacao(Integer codigo, String descricao) {
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
        final PedidoSituacaoGravacao other = (PedidoSituacaoGravacao) obj;
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
