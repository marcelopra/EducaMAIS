package configuracoes;

import dao.ExecutarSql;
import dao.GenericSimpleDAO;
import entidades.Configuracoes;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import util.TrataException;

public class ConfiguracoesDAO extends GenericSimpleDAO<Configuracoes, Integer> {
    
    public Configuracoes carregar() {
        Configuracoes a = null;
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT * "
                                                              + "FROM CONFIGURACOES ");
            ResultSet rs = ExecutarSql.consulta(stmt);
            if(rs.next()) {
                a = new Configuracoes();
                a.setCaminhoAplicacao(rs.getString("CAMINHO"));
                
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return a;
    }

    @Override
    public void persist(Configuracoes entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void atualizar(Configuracoes entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Configuracoes entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Configuracoes> listagem() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Configuracoes carregar(Integer id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
