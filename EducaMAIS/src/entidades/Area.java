/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Objects;

/**
 *
 * @author u0181181
 */
public class Area implements AbstractEntity {
    
    private Integer codigo;
    private String descricao;

    public static Area TODOS = new Area(999999, "Todas");
    
    public Area() {
    }
    
    private Area(Integer codigo, String descricao) {
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
        return descricao.toUpperCase();
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public void valida() throws ValidacaoEntidadeException {
        if(descricao.trim().isEmpty()) {
            throw new ValidacaoEntidadeException("O nome é obrigatório.");
        }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.codigo);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Area other = (Area) obj;
        if (!Objects.equals(this.codigo, other.codigo)) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return descricao.toUpperCase();
    }
}
