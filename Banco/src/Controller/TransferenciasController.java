/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOCliente.ContaDAO;
import DAOCliente.TransferenciaDAO;
import ModelCliente.ClienteC;
import ModelCliente.Conta;
import ModelCliente.SessaoCliente;
import Recursos.Criptografia;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class TransferenciasController implements Initializable {

    @FXML
    private TextField tfAgencia;
    @FXML
    private TextField tfConta;
    @FXML
    private TextField tfDigito;
    @FXML
    private TextField tfValor;
    @FXML
    private PasswordField tfSenha;
    @FXML
    private Label lbFavorecido;

    SessaoCliente sessao = SessaoCliente.getInstancia();
    @FXML
    private JFXButton btConfirmarTransferencia;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    private void confirmarTransferencia(ActionEvent event) {
        TransferenciaDAO daoT = new TransferenciaDAO();
        ContaDAO daoC = new ContaDAO();
        List<Conta> conta = daoC.getConta(sessao.getID_Cliente());
        
        try {
            if(tfValor.getText().length() > 0){
                if(sessao.getSenha().equals(Criptografia.MD5(tfSenha.getText()))){
                    if(conta.get(0).getSaldo() >= Double.parseDouble(tfValor.getText().replace(",", "."))){
                        
                        daoT.transferir(sessao.getID_Cliente(),
                            tfConta.getText(), 
                            tfAgencia.getText(),
                            tfDigito.getText(), 
                            Double.parseDouble(tfValor.getText().replace(",", ".")));
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Banco Digital");
                        alert.setHeaderText("Transação Completa.");
                        alert.setContentText("Dinheiro transferido com sucesso.");
                        alert.showAndWait();

                        tfAgencia.setText("");
                        tfConta.setText("");
                        tfDigito.setText("");
                        tfSenha.setText("");
                        tfValor.setText("");
                        lbFavorecido.setText("");

                        btConfirmarTransferencia.setDisable(true);
                    }else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Banco Digital");
                        alert.setHeaderText("Transação indisponível.");
                        alert.setContentText("Você não possui saldo suficiente.");
                        alert.showAndWait();
                    }
                }else {
                    if(tfSenha.getText().length() > 0){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Banco Digital");
                        alert.setHeaderText("Senha inválida.");
                        alert.setContentText("Tente novamente.");
                        alert.showAndWait();
                        
                        tfSenha.setText("");
                    }else {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Banco Digital");
                        alert.setHeaderText("Campo senha vazio.");
                        alert.setContentText("Informe sua senha.");
                        alert.showAndWait();
                    }
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Banco Digital");
                alert.setHeaderText("Campo valor vazio.");
                alert.setContentText("Informe o valor da transferência.");
                alert.showAndWait();
            }
        } catch (Exception e) {
        }
    }
    
    private void transferir(){
        ContaDAO daoC = new ContaDAO();
        List<Conta> conta = daoC.getConta(sessao.getID_Cliente());
    }

    @FXML
    private void identificarFavorecido(KeyEvent event) {
        if(tfAgencia.getText().length() > 0 && 
                tfConta.getText().length() > 0 && 
                tfDigito.getText().length() == 1){
            try {
                TransferenciaDAO daoT = new TransferenciaDAO();
                List<ClienteC> cliente = daoT.identificaTransmissario(tfConta.getText(), tfDigito.getText());
                
                if(cliente.isEmpty() == false){
                    if(sessao.getID_Cliente() != cliente.get(0).getID_Cliente()){
                        lbFavorecido.setText("Favorecido: " + cliente.get(0).getNome() + " "
                            + "" + cliente.get(0).getSobrenome());
                        lbFavorecido.setStyle("-fx-text-fill: black;");
                        btConfirmarTransferencia.setDisable(false);
                    }else {
                        lbFavorecido.setText("Você é o próprio favorecido.");
                        lbFavorecido.setStyle("-fx-text-fill: red;");
                        btConfirmarTransferencia.setDisable(true);
                        tfConta.setText("");
                        tfDigito.setText("");
                    }
                }
                
                if(tfAgencia.getText().length() < 1){
                    lbFavorecido.setText("");
                }
                
            } catch (Exception e) {
            }
        }else {
            lbFavorecido.setText("");
            btConfirmarTransferencia.setDisable(true);
        }
    }

    @FXML
    private void limparDescricao(MouseEvent event) {
        lbFavorecido.setText("");
    }
}
