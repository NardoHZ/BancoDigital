/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOCliente;

import ConnectionFactory.ConnectionCliente;
import ModelCliente.Endereco;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Anderson
 */
public class EnderecoDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public EnderecoDAO(){
        this.con = new ConnectionCliente().getConnectionCliente();
    }
    
    public List<Endereco> getEndereco(int ID_Cliente){
        List<Endereco> endereco = new ArrayList<>();
        String sql = "SELECT * FROM Endereco_Cliente WHERE ID_Cliente = "+ ID_Cliente +";";
        
        try {
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Endereco e = new Endereco();
                e.setBairro(rs.getString("Bairro"));
                e.setCEP(rs.getString("CEP"));
                e.setID_Endereco(rs.getInt("ID_EnderecoCliente"));
                e.setLogradouro(rs.getString("Logradouro"));
                e.setUF(rs.getString("UF"));
                
                endereco.add(e);
            }
            
            stmt.close();
            rs.close();
            con.close();
            
        } catch (SQLException ex) {
            ex.printStackTrace();   
            return null;
        }finally {
            ConnectionCliente.closeConnection(con, stmt, rs);
        }
        return endereco;
    }
    
    public boolean update(Endereco e){
        String sql = "CALL pUpdateEndereco(?, ?, ?, ?, ?)";
        
        try {
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, e.getID_Cliente());
            stmt.setString(2, e.getLogradouro());
            stmt.setString(3, e.getBairro());
            stmt.setString(4, e.getCEP());
            stmt.setString(5, e.getUF());
            
            stmt.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally{
            ConnectionCliente.closeConnection(con, stmt);
        }
    }
}
