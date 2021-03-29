/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAOCliente.BoletoDAO;
import DAOCliente.ContaDAO;
import ModelCliente.Boleto;
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
public class PagamentosController implements Initializable {

    @FXML
    private TextField tfCodigo;
    @FXML
    private Label lbDescricao;
    @FXML
    private Label lbSenha;
    @FXML
    private JFXButton btConfirmar;
    @FXML
    private PasswordField tfSenha;
    
    SessaoCliente sessao = SessaoCliente.getInstancia();
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    @FXML
    private void confirmarPagamento(ActionEvent event) {
        BoletoDAO daoB = new BoletoDAO();
        List<Boleto> b = daoB.getBoleto(tfCodigo.getText());
        
        if(sessao.getSenha().equals(Criptografia.MD5(tfSenha.getText()))){
            ContaDAO daoC = new ContaDAO();
            daoC.pagarBoleto(tfCodigo.getText(), sessao.getID_Cliente());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Banco Digital");
            alert.setHeaderText("Transação completa.");
            alert.setContentText("O pagamento foi realizado com sucesso.");
            alert.showAndWait();
            
            tfCodigo.setText("");
            lbDescricao.setText("");
            tfSenha.setText("");
        }else {
            if(tfSenha.getText().length() == 0){
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Banco Digital");
                alert.setHeaderText("Campo senha vazio.");
                alert.setContentText("Informe sua senha.");
                alert.showAndWait();
            }else {
                tfSenha.setText("");
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Banco Digital");
                alert.setHeaderText("Senha inválida.");
                alert.setContentText("Tente novamente.");
                alert.showAndWait();
            }
        }
    }

    @FXML
    private void limparDescricao(MouseEvent event) {
        if(lbDescricao.getText().equals("Você não pode pagar seu próprio boleto")){
            lbDescricao.setText("");
            tfCodigo.setText("");
            tfCodigo.setEditable(true);
        }
    }

    @FXML
    private void verificandoStatusBoleto(KeyEvent event) {
        ContaDAO daoC = new ContaDAO();
        List<Conta> conta = daoC.getConta(sessao.getID_Cliente());
        
        try {
            BoletoDAO daoB = new BoletoDAO();
            List<Boleto> boleto = daoB.getBoleto(tfCodigo.getText());

            Boleto b = new Boleto();
            b.setValor(boleto.get(0).getValor());
                
            if(boleto.get(0).getID_Cliente()!= sessao.getID_Cliente()){
                if(boleto.get(0).getStatus().equals("Pendente")){
                    lbDescricao.setText("Valor: " + b.getValorFormatado());
                    lbDescricao.setStyle("-fx-text-fill: black;");
                    if(conta.get(0).getSaldo() >= boleto.get(0).getValor()){
                        btConfirmar.setDisable(false);
                    }else {
                        btConfirmar.setDisable(true);
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Banco Digital");
                        alert.setHeaderText("Transação indisponível.");
                        alert.setContentText("Você não possui saldo suficiente.");
                        alert.showAndWait();
                    }
                    
                }else {
                    lbDescricao.setText("Boleto expirado, valor: " + b.getValorFormatado());
                    lbDescricao.setStyle("-fx-text-fill: black;");
                    btConfirmar.setDisable(true);
                }
            }else {
                lbDescricao.setText("Você não pode pagar seu próprio boleto");
                lbDescricao.setStyle("-fx-text-fill: red;");
                tfCodigo.setEditable(false);
                btConfirmar.setDisable(true);
            }
            
            if(boleto.get(0).getStatus().equals("Pago")){
                lbDescricao.setText("Boleto pago, valor: " + b.getValorFormatado());
                btConfirmar.setDisable(true);
            }
        } catch (Exception e) {
        }
        
        if(tfCodigo.getText().length() != 44){
            lbDescricao.setText("");
            btConfirmar.setDisable(true);
        }
    }
}
