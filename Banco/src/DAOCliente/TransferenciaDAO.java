/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOCliente;

import ConnectionFactory.ConnectionCliente;
import ModelCliente.ClienteC;
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
public class TransferenciaDAO {
    private Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    
    public TransferenciaDAO(){
        this.con = new ConnectionCliente().getConnectionCliente();
    }
    
     public boolean transferir(int idD, String conta,
             String agencia, String digito, double valor){
        String sql = "CALL ptTransacao(" + idD + ", " + conta + ", " 
                + agencia + ", " + digito + ", " + valor + ");";
        
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
     
         
    public List<ClienteC> identificaTransmissario(String co, String d){
        List<ClienteC> cliente = new ArrayList<>();
        String sql = "CALL pIdentificarFavorecido(" + co + ", " + d + ");" ;
        
        try {
            java.sql.PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                ClienteC c = new ClienteC();
                c.setNome(rs.getString("Nome"));
                c.setSobrenome(rs.getString("Sobrenome"));
                c.setID_Cliente(rs.getInt("ID_Cliente"));
                
                cliente.add(c);
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace();   
            return null;
        }finally {
            ConnectionCliente.closeConnection(con, stmt, rs);
        }
        return cliente;
    }
}
