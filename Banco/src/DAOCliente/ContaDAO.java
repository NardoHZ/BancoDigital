/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOCliente;

import ConnectionFactory.ConnectionCliente;
import ModelCliente.Conta;
import java.sql.PreparedStatement;
import java.sql.Connection;
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
public class ContaDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public ContaDAO(){
        this.con = new ConnectionCliente().getConnectionCliente();
    }
    
    public List<Conta> getConta(int ID_Cliente){
        List<Conta> conta = new ArrayList<>();
        String sql = "SELECT * FROM Conta WHERE ID_Cliente = "+ ID_Cliente +";";
        
        try {
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Conta c = new Conta();
                c.setAgencia(rs.getString("Agencia"));
                c.setConta(rs.getString("Conta"));
                c.setDigito(rs.getString("Digito"));
                c.setID_Conta(rs.getInt("ID_Conta"));
                c.setID_Cliente(rs.getInt("ID_Cliente"));
                c.setSaldo(rs.getDouble("Saldo"));
                
                conta.add(c);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();   
            return null;
        }finally {
            ConnectionCliente.closeConnection(con, stmt, rs);
        }
        return conta;
    }
    
    public boolean pagarBoleto(String codigo, int idCliente){
        String sql = "CALL ptPagamento(" + codigo + ", " + idCliente +");";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            
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
