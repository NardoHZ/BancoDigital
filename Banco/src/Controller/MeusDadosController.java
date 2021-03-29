/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ClassePrincipal.LoginCadastro;
import DAOCliente.ClienteDAO;
import DAOCliente.EmprestimoDAO;
import DAOCliente.EnderecoDAO;
import ModelCliente.ClienteC;
import ModelCliente.Emprestimo;
import ModelCliente.Endereco;
import ModelCliente.SessaoCliente;
import Recursos.Criptografia;
import Recursos.TextFieldFormatter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class MeusDadosController implements Initializable {

    @FXML
    private Label lbMostrarTelefone;
    @FXML
    private Label lbMostrarCPF;
    @FXML
    private Label lbMostrarSobrenome;
    @FXML
    private Label lbmostrarNome;
    @FXML
    private Label lbMostrarRenda;
    @FXML
    private Label lbMostrarProfissao;
    @FXML
    private Label lbMostrarLogradouro;
    @FXML
    private Label lbMostrarBairro;
    @FXML
    private Label lbMostrarCEP;
    @FXML
    private Label lbMostrarUF;
    @FXML
    private JFXComboBox<?> cbRenda;
    @FXML
    private JFXComboBox<?> cbUF;
    @FXML
    private JFXTextField tfProfissao;
    @FXML
    private JFXTextField tfTelefone;
    @FXML
    private JFXTextField tfLogradouro;
    @FXML
    private JFXTextField tfBairro;
    @FXML
    private JFXTextField tfCEP;
    @FXML
    private Label lbSenha;
    @FXML
    private JFXPasswordField tfSenha;
    @FXML
    private JFXButton btEditar;
    @FXML
    private JFXButton btCancelar;
    @FXML
    private JFXButton btSalvar;
    @FXML
    private Label lbMostrarDataNascimento;
    @FXML
    private JFXButton btDeletar;
    @FXML
    private Pane paneConfirmarExclusao;
    @FXML
    private JFXPasswordField tfSenhaExclusao;
    @FXML
    private Label lbStatusSenha;

    SessaoCliente sessao = SessaoCliente.getInstancia();
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       preencherComboBoxRenda();
       preencherComboBoxUF();
       carregarDados();
       verificarEmprestimo();
       
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
           @Override
            public void run(){
                 Platform.runLater(() -> {
                     carregarDados();
                 });
            }
        }, 1000, 1000);
    } 
    
    private void verificarEmprestimo(){
        EmprestimoDAO daoE = new EmprestimoDAO();
        List<Emprestimo> emprestimo = daoE.getEmprestimo(sessao.getID_Cliente());
        
        if(emprestimo.isEmpty() == false){
            if(emprestimo.get(0).getStatus().equals("Aprovado")){
                btDeletar.setDisable(true);
            }else {
                btDeletar.setDisable(false);
            }
        }
    }
    
    private void getDadosAtualizados(){
        lbMostrarRenda.setText(cbRenda.getSelectionModel().getSelectedItem().toString());
        lbMostrarProfissao.setText(tfProfissao.getText());
        lbMostrarTelefone.setText(tfTelefone.getText());
        lbMostrarLogradouro.setText(tfLogradouro.getText());
        lbMostrarBairro.setText(tfBairro.getText());
        lbMostrarCEP.setText(tfCEP.getText());
        lbMostrarUF.setText(cbUF.getSelectionModel().getSelectedItem().toString());
    }
    
    private void updateCliente(){
        ClienteC c = new ClienteC();
        ClienteDAO daoC = new ClienteDAO();
        Endereco e = new Endereco();
        EnderecoDAO daoE = new EnderecoDAO();
                
        c.setRenda(cbRenda.getSelectionModel().getSelectedItem().toString());
        c.setProfissao(tfProfissao.getText().trim());
        c.setTelefone(tfTelefone.getText().trim());
        c.setID_Cliente(sessao.getID_Cliente());
                
        e.setLogradouro(tfLogradouro.getText().trim());
        e.setBairro(tfBairro.getText().trim());
        e.setCEP(tfCEP.getText());
        e.setUF(cbUF.getSelectionModel().getSelectedItem().toString());
        e.setID_Cliente(sessao.getID_Cliente());
                
        daoC.update(c);
        daoE.update(e);
    }
    
    private void carregarDados(){
        
        EnderecoDAO daoE = new EnderecoDAO();
        List<Endereco> endereco = daoE.getEndereco(sessao.getID_Cliente());
        
        ClienteDAO daoC = new ClienteDAO();
        List<ClienteC> c = daoC.getUsuario(sessao.getCPF());
        
        lbMostrarBairro.setText(endereco.get(0).getBairro());
        lbMostrarCEP.setText(endereco.get(0).getCEP());
        lbMostrarLogradouro.setText(endereco.get(0).getLogradouro());
        lbMostrarUF.setText(endereco.get(0).getUF());
        
        lbMostrarCPF.setText(c.get(0).getCPF());
        lbMostrarProfissao.setText(c.get(0).getProfissao());
        lbMostrarRenda.setText(c.get(0).getRenda());
        lbMostrarSobrenome.setText(c.get(0).getSobrenome());
        lbMostrarTelefone.setText(c.get(0).getTelefone());
        lbmostrarNome.setText(c.get(0).getNome());
        lbMostrarDataNascimento.setText(c.get(0).getDataNascimentoFormatada());
    }
    
    //Preencher a combobox UF
    private void preencherComboBoxUF(){
        List uf = new ArrayList();
        uf.add("AC");
        uf.add("AL");
        uf.add("AM");
        uf.add("AP");
        uf.add("BA");
        uf.add("CE");
        uf.add("DF");
        uf.add("ES");
        uf.add("GO");
        uf.add("MA");
        uf.add("MG");
        uf.add("MS");
        uf.add("MT");
        uf.add("PA");
        uf.add("PB");
        uf.add("PE");
        uf.add("PI");
        uf.add("PR");
        uf.add("RJ");
        uf.add("RN");
        uf.add("RO");
        uf.add("RR");
        uf.add("RS");
        uf.add("SC");
        uf.add("SE");
        uf.add("SP");
        uf.add("TO");
        
        cbUF.setItems(FXCollections.observableArrayList(uf));
    }
    
    //Preencher a combobox Renda
    private void preencherComboBoxRenda(){
        List renda = new ArrayList();
        renda.add("Inferior a R$ 998,00");
        renda.add("Entre R$ 998,00 e R$ 1.996,00");
        renda.add("Entre R$ 1.996,00 e R$ 3.992,00");
        renda.add("Superior à R$ R$ 3.992,00");
        
        cbRenda.setItems(FXCollections.observableArrayList(renda));
    }
    
    //Limpar o formulário
    private void limparFormularios(){
        cbRenda.getSelectionModel().clearSelection();
        cbUF.getSelectionModel().clearSelection();
        tfBairro.setText("");
        tfCEP.setText("");
        tfLogradouro.setText("");
        tfProfissao.setText("");
        tfSenha.setText("");
        tfTelefone.setText("");
    }
    
    @FXML
    private void editarMinhaConta(ActionEvent event) {
        btEditar.setVisible(false);
        btDeletar.setVisible(false);
        lbMostrarRenda.setVisible(false);
        lbMostrarProfissao.setVisible(false);
        lbMostrarTelefone.setVisible(false);
        lbMostrarLogradouro.setVisible(false);
        lbMostrarCEP.setVisible(false);
        lbMostrarBairro.setVisible(false);
        lbMostrarUF.setVisible(false);
        btSalvar.setVisible(true);
        btCancelar.setVisible(true);
        lbSenha.setVisible(true);
        cbRenda.setVisible(true);
        cbUF.setVisible(true);
        tfBairro.setVisible(true);
        tfCEP.setVisible(true);
        tfLogradouro.setVisible(true);
        tfProfissao.setVisible(true);
        tfSenha.setVisible(true);
        tfTelefone.setVisible(true);
        
        limparFormularios();
    }

    @FXML
    private void cancelarEdicao(ActionEvent event) {
        btEditar.setVisible(true);
        btDeletar.setVisible(true);
        btSalvar.setVisible(false);
        btCancelar.setVisible(false);
        lbSenha.setVisible(false);
        cbRenda.setVisible(false);
        cbUF.setVisible(false);
        tfBairro.setVisible(false);
        tfCEP.setVisible(false);
        tfLogradouro.setVisible(false);
        tfProfissao.setVisible(false);
        tfSenha.setVisible(false);
        tfTelefone.setVisible(false);
        lbMostrarRenda.setVisible(true);
        lbMostrarProfissao.setVisible(true);
        lbMostrarTelefone.setVisible(true);
        lbMostrarLogradouro.setVisible(true);
        lbMostrarCEP.setVisible(true);
        lbMostrarBairro.setVisible(true);
        lbMostrarUF.setVisible(true);
    }

    @FXML
    private void salvarEdicao(ActionEvent event) {
        if(cbRenda.getSelectionModel().isEmpty() == false &&
                cbUF.getSelectionModel().isEmpty() == false && 
                tfProfissao.getText().length() > 1 && 
                tfTelefone.getText().length() > 1 && 
                tfLogradouro.getText().length() > 1 && 
                tfBairro.getText().length() > 1 && 
                tfCEP.getText().length() > 1){
            if(sessao.getSenha().equals(Criptografia.MD5(tfSenha.getText()))){
                updateCliente();
                getDadosAtualizados();
                cancelarEdicao(event);
            }else {
                if(tfSenha.getText().length() != 0){
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Banco Digital");
                    alert.setHeaderText("Senha inválida.");
                    alert.setContentText("Tente novamente....");
                    alert.showAndWait(); 
                }else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Banco Digital");
                    alert.setHeaderText("Campo senha está vazio.");
                    alert.setContentText("Digite sua senha....");
                    alert.showAndWait();
                } 
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Banco Digital");
            alert.setHeaderText("Campos vazios.");
            alert.setContentText("Preencha todos os campos.");
            alert.showAndWait();
        }
    }

    @FXML
    private void tfFormatTelefone(KeyEvent event) {
        TextFieldFormatter formatar = new TextFieldFormatter();
        formatar.setMask("(##)#####-####");
        formatar.setCaracteresValidos("0123456789");
        formatar.setTf(tfTelefone);
        formatar.formatter();
    }

    @FXML
    private void tfFormatCEP(KeyEvent event) {
        TextFieldFormatter formatar = new TextFieldFormatter();
        formatar.setMask("#####-###");
        formatar.setCaracteresValidos("0123456789");
        formatar.setTf(tfCEP);
        formatar.formatter();
    }

    @FXML
    private void deletarMinhaConta(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Banco Digital");
        alert.setHeaderText("Se você continuar, todos os seus dados serão perdidos.");
        alert.setContentText("Você tem certeza disso?");
        
        ButtonType buttonTypeSim = new ButtonType("Sim");
        ButtonType buttonTypeCancelar = new ButtonType("Cancelar");
         
        alert.getButtonTypes().setAll(buttonTypeSim, buttonTypeCancelar);
        
        Optional<ButtonType> resultado = alert.showAndWait();
        if(resultado.get() == buttonTypeSim){
            paneConfirmarExclusao.setVisible(true);
        }else {
            alert.close();
        }
    }  

    @FXML
    private void cancelarExclusaoConta(ActionEvent event) {
        paneConfirmarExclusao.setVisible(false);
        tfSenhaExclusao.setText("");
    }

    @FXML
    private void confirmarExclusaoConta(ActionEvent event) {
        if(sessao.getSenha().equals(Criptografia.MD5(tfSenhaExclusao.getText()))){
            ClienteC c = new ClienteC();
            ClienteDAO daoC = new ClienteDAO();
            c.setID_Cliente(sessao.getID_Cliente());
            daoC.delete(c);
            
            LoginCadastro lc = new LoginCadastro();
            ClienteController cc = new ClienteController();
            cc.fecharCliente();
            try {
                lc.start(new Stage());;
            } catch (Exception ex) {
                Logger.getLogger(MeusDadosController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }else {
            lbStatusSenha.setText("Senha inválida");
            tfSenhaExclusao.setText("");
            tfSenha.setText("");
        }
    }

    @FXML
    private void removerStatusSenhaInvalida(MouseEvent event) {
        lbStatusSenha.setText("");
    }
}
