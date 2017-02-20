/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import java.util.Iterator;
import java.util.List;
import mc.ConfiguracoesGerais;

/**
 *
 * @author u0181181
 */
public class Curso implements AbstractEntity {
    
    private Integer codigo;
    private Area area;
    private String nome;
    private Float valorBase;
    private Integer cargaHoraria;
    private List<Apostila> listagemApostilas;
    private List<Assunto> listagemAssuntos;

    public String getAreaToString() {
        if(area != null) {
            return area.getDescricao().toUpperCase();
        }
        return "";
    }
    
    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
    
    public List<Assunto> getListagemAssuntos() {
        return listagemAssuntos;
    }

    public void setListagemAssuntos(List<Assunto> listagemAssuntos) {
        this.listagemAssuntos = listagemAssuntos;
    }
    
    public List<Apostila> getListagemApostilas() {
        return listagemApostilas;
    }

    public void setListagemApostilas(List<Apostila> listagemApostilas) {
        this.listagemApostilas = listagemApostilas;
    }

    public Integer getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(Integer cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public Float getValorBase() {
        return valorBase;
    }

    public void setValorBase(Float valorBase) {
        this.valorBase = valorBase;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome.toUpperCase();
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public int getTotalCargaHoraria() {
        int total = 0;
        if(listagemAssuntos != null) {
            for(Assunto a : listagemAssuntos) {
                total += a.getCargaHoraria();
            }
        }    
        return total;
    }
    
    @Override
    public void valida() throws ValidacaoEntidadeException {
        if(area == null) {
            throw new ValidacaoEntidadeException("A área do curso é obrigatória.");
        }
        if(nome.trim().isEmpty()) {
            throw new ValidacaoEntidadeException("O nome é obrigatório.");
        }
        if(listagemApostilas == null || listagemApostilas.isEmpty()) {
            throw new ValidacaoEntidadeException("É necessário selecionar pelo menos uma apostila.");
        } else {
            for (Apostila a : listagemApostilas) {
                a.valida();
            }
        }
        if(listagemAssuntos == null || listagemAssuntos.isEmpty()) {
            throw new ValidacaoEntidadeException("É necessário adicionar pelo menos um assunto na grade.");
        }
    }
    
    public String getDiretorioCurso() {
        return ConfiguracoesGerais.DIRETORIO_CURSOS + codigo + "\\";
    }
}
