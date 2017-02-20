/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package processos.emitircertificado;

import configuracoes.ConfiguracoesController;
import entidades.Configuracoes;
import entidades.Pedido;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import processos.criarpedido.PedidoController;
import reports.OpenReport;
import util.Msg;

/**
 *
 * @author Marcelo
 */
public class EmitirCertificado {
    
    public void emitir(Pedido pedido) {
        
        if(pedido != null) {
            if(!pedido.isAntigo()) {
        
                Integer numeroRegistro = pedido.getNumeroRegistro();
                Date dataEmissao = pedido.getDataEmissaoCertificado();
                boolean reemissao = true;

                //Verifica se já tem numero de registro, senão tiver então gera o mesmo
                if(pedido.getNumeroRegistro()== null || pedido.getNumeroRegistro()== 0) {
                    /*Carrega o proximo numero de registro*/
                    numeroRegistro = new PedidoController().getProximoNumeroRegistro();
                    dataEmissao = pedido.getDataTermino();
                    reemissao = false;
                }

                FormEmitiCertificado f = new FormEmitiCertificado(pedido.getAluno(), pedido.getCurso(), 
                        numeroRegistro, dataEmissao, false);
                f.setVisible(true);

                if(f.BTN_PRESSIONADO == f.BTN_EMITIR) {
                    /*Atualiza o numero do registro*/
                    new PedidoController().atualizarNumeroRegistro(pedido.getCodigo(), numeroRegistro);
                    new PedidoController().atualizarDataEmissao(pedido.getCodigo(), new java.sql.Date(f.getDataEmissao().getTime()));

                    new OpenReport().abrir("/processos/emitircertificado/CertificadoAtras.jasper", getParametros(pedido.getCodigo(), numeroRegistro));
                    new OpenReport().abrir("/processos/emitircertificado/CertificadoFrente.jasper", getParametros(pedido.getCodigo(), numeroRegistro));
                }
            } else {
                new Msg().msgAtencao("Este curso foi realizado na empresa antiga, e não pode ser reemitido.");
            }
        } else {
            new Msg().msgAtencao("Nenhum curso selecionado para emissão do certificado.");
        }
    }
    
    private Map getParametros(Integer codigoPedido, Integer numeroRegistro) {
        
        Configuracoes conf = new ConfiguracoesController().carregar();
        
        Map<String, Object> list = new HashMap<>();
        list.put("codigo_pedido", codigoPedido);
        
        DecimalFormat df = new DecimalFormat("0.00");     
        
        double resultDivisao = Float.valueOf(df.format((double)numeroRegistro/18).replaceAll(",", "."));
        int antesVirgula = (int) resultDivisao;
        double depoisVirgula = resultDivisao - antesVirgula;
        if(depoisVirgula == 0) {
            depoisVirgula = 1;
        }
        System.out.println(resultDivisao+1);
        System.out.println(depoisVirgula);
        int folha = (int)((resultDivisao+1) - (depoisVirgula));
        
        System.out.println("Resultado divisao: " + resultDivisao);
        System.out.println("Antes virgula: " + antesVirgula);
        System.out.println("Depois virgula: " + depoisVirgula);
        System.out.println("Folha: " + folha);
        
        list.put("numero_folha", folha);
        
        list.put("SUBREPORT_DIR", conf.getCaminhoAplicacao() + "Certificado\\");
        list.put("SUBREPORT_DIR2", "");
        
        
        return list;
    }
    
}
