package cadastro.areas;

import dao.ExecutarSql;
import dao.GenericSimpleDAO;
import entidades.Area;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.TrataException;

public class AreaDAO extends GenericSimpleDAO<Area, Integer> {
    
    @Override
    public void persist(Area area) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("INSERT INTO AREA "
                                                             + "(DESCRICAO)"
                                                             + "VALUES "
                                                             + "(?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, area.getDescricao());
            ExecutarSql.update(stmt);
            
            Integer codigo = ExecutarSql.getAutoIncrementIdGeradoPeloInsert(stmt);
            
            area.setCodigo(codigo);
            
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }

    @Override
    public void atualizar(Area area) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("UPDATE AREA "
                                                              + "SET DESCRICAO=? "
                                                              + "WHERE CODIGO=?");
            stmt.setString(1, area.getDescricao());
            stmt.setInt(2, area.getCodigo());
            ExecutarSql.update(stmt);
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    @Override
    public void delete(Area area) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("DELETE FROM AREA "
                                                              + "WHERE CODIGO=?");
            stmt.setInt(1, area.getCodigo());
            ExecutarSql.update(stmt);
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    @Override
    public Area carregar(Integer codigo) {
        Area a = null;
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT * "
                                                              + "FROM AREA "
                                                              + "WHERE CODIGO=?");
            stmt.setInt(1, codigo);
            ResultSet rs = ExecutarSql.consulta(stmt);
            if(rs.next()) {
                a = new Area();
                a.setCodigo(codigo);
                a.setDescricao(rs.getString("DESCRICAO"));
                
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return a;
    }
    

    @Override
    public List<Area> listagem() {
        List<Area> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT CODIGO "
                                                              + "FROM AREA "
                                                              + "ORDER BY DESCRICAO");
            ResultSet rs = ExecutarSql.consulta(stmt);
            while(rs.next()) {
                listagem.add(carregar(rs.getInt("CODIGO")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return listagem;
    }
    
    public List<Area> listagem(String descricao) {
        List<Area> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT CODIGO "
                                                              + "FROM AREA "
                                                              + "WHERE DESCRICAO LIKE ('%" + descricao + "%')"
                                                              + "ORDER BY DESCRICAO");
            ResultSet rs = ExecutarSql.consulta(stmt);
            while(rs.next()) {
                listagem.add(carregar(rs.getInt("CODIGO")));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return listagem;
    }

}
