/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAOFuncionario;

import ConnectionFactory.ConnectionFuncionario;
import ModelFuncionario.Agencia;
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
public class AgenciaDAO {
    private Connection con;
    private PreparedStatement stmt;
    private ResultSet rs;
    
    public AgenciaDAO(){
        this.con = new ConnectionFuncionario().getConnectionFuncionario();
    }
    
    public List<Agencia> getSaldoAgencia(){
        List<Agencia> agencia = new ArrayList<>();
        String sql = "SELECT * FROM Agencia WHERE ID_Agencia = 1;";
        
        try {
            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                Agencia c = new Agencia();
                c.setSaldo(rs.getDouble("Saldo"));
                
                agencia.add(c);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }finally {
            ConnectionFuncionario.closeConnection(con, stmt, rs);
        }
        return agencia;
    }
}
