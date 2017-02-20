package cadastro.vendedor;

import dao.ExecutarSql;
import dao.GenericSimpleDAO;
import entidades.Vendedor;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import util.TrataException;

public class VendedorDAO extends GenericSimpleDAO<Vendedor, Integer> {
    
    @Override
    public void persist(Vendedor usuario) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("INSERT INTO VENDEDOR "
                                                             + "(NOME)"
                                                             + "VALUES "
                                                             + "(?)", PreparedStatement.RETURN_GENERATED_KEYS);
            stmt.setString(1, usuario.getNome());
            ExecutarSql.update(stmt);
            
            Integer codigo = ExecutarSql.getAutoIncrementIdGeradoPeloInsert(stmt);
            
            usuario.setCodigo(codigo);
            
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }

    @Override
    public void atualizar(Vendedor usuario) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("UPDATE VENDEDOR "
                                                              + "SET NOME=? "
                                                              + "WHERE CODIGO=?");
            stmt.setString(1, usuario.getNome());
            stmt.setInt(2, usuario.getCodigo());
            ExecutarSql.update(stmt);
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
  
    
    @Override
    public void delete(Vendedor entidade) {
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("DELETE FROM VENDEDOR "
                                                              + "WHERE CODIGO=?");
            stmt.setInt(1, entidade.getCodigo());
            ExecutarSql.update(stmt);
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
    }
    
    @Override
    public Vendedor carregar(Integer codigo) {
        Vendedor usuario = null;
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT * "
                                                              + "FROM VENDEDOR "
                                                              + "WHERE CODIGO=?");
            stmt.setInt(1, codigo);
            ResultSet rs = ExecutarSql.consulta(stmt);
            if(rs.next()) {
                usuario = new Vendedor();
                usuario.setCodigo(codigo);
                usuario.setNome(rs.getString("NOME"));
            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            TrataException.fatal(ex);
        }
        return usuario;
    }

    @Override
    public List<Vendedor> listagem() {
        List<Vendedor> listagem = new ArrayList<>();
        try {
            PreparedStatement stmt = conexaoDb.prepareStatement("SELECT CODIGO "
                                                              + "FROM VENDEDOR "
                                                              + "ORDER BY NOME");
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
