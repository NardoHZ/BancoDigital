/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOCliente.ContaDAO;
import ModelCliente.Conta;
import ModelCliente.SessaoCliente;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class MinhaContaController implements Initializable {

    @FXML
    private Label lbMostrarDigito;
    @FXML
    private Label lbMostrarConta;
    @FXML
    private Label lbMostrarAgencia;
    
    SessaoCliente sessao = SessaoCliente.getInstancia();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        carregarDados();
    }
    
    private void carregarDados(){
        ContaDAO daoC = new ContaDAO();
        List<Conta> conta = daoC.getConta(sessao.getID_Cliente());
        
        lbMostrarAgencia.setText(conta.get(0).getAgencia());
        lbMostrarConta.setText(conta.get(0).getConta());
        lbMostrarDigito.setText(conta.get(0).getDigito());
    }
}
