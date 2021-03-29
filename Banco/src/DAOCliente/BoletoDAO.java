/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOCliente;

import ConnectionFactory.ConnectionCliente;
import ModelCliente.Boleto;
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
public class BoletoDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public BoletoDAO(){
        this.con = new ConnectionCliente().getConnectionCliente();
    }
    
    public boolean insert(Boleto b){
        String sql = "CALL pGerarBoleto(?, ?, ?, ?, ?, ?);";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, b.getID_Cliente());
            stmt.setDouble(2, b.getValor());
            stmt.setString(3, b.getCodigo());
            stmt.setString(4, b.getDataEmissao());
            stmt.setString(5, b.getValidade());
            stmt.setString(6, b.getStatus());
            
            stmt.execute();
            
            return true;
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }finally {
            ConnectionCliente.closeConnection(con, stmt);
        }
    }
    
    public List<Boleto> getList(int idCliente){
        List<Boleto> boleto = new ArrayList<>();
        String sql = "SELECT * FROM BOLETO WHERE ID_Cliente = " + idCliente + ";";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Boleto b = new Boleto();
                b.setID_Boleto(rs.getInt("ID_Boleto"));
                b.setID_Cliente(rs.getInt("ID_Cliente"));
                b.setValor(rs.getDouble("Valor"));
                b.setCodigo(rs.getString("Codigo"));
                b.setDataEmissao(rs.getString("Data_Emissao"));
                b.setValidade(rs.getString("Validade"));
                b.setStatus(rs.getString("Status_Boleto"));
                b.setPagamento(rs.getString("Data_Pagamento"));
                
                boleto.add(b);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }finally {
            ConnectionCliente.closeConnection(con, stmt, rs);
        }
        return boleto;
    }
    
    public List<Boleto> getBoleto(String codigo){
        List<Boleto> boleto = new ArrayList<>();
        String sql = "SELECT * FROM Boleto WHERE Codigo = '"+ codigo +"';";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Boleto b = new Boleto();
                b.setID_Boleto(rs.getInt("ID_Boleto"));
                b.setID_Cliente(rs.getInt("ID_Cliente"));
                b.setValor(rs.getDouble("Valor"));
                b.setCodigo(rs.getString("Codigo"));
                b.setDataEmissao(rs.getString("Data_Emissao"));
                b.setValidade(rs.getString("Validade"));
                b.setStatus(rs.getString("Status_Boleto"));
                b.setPagamento(rs.getString("Data_Pagamento"));
                
                boleto.add(b);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }finally {
            ConnectionCliente.closeConnection(con, stmt, rs);
        }
        return boleto;
    }
    
    public boolean delete(Boleto b){
        String sql = "CALL pDeletarBoleto(?);";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
            stmt.setInt(1, b.getID_Boleto());
            
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
