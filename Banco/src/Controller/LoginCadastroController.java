/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ClassePrincipal.LoginCadastro;
import ClassePrincipal.Cliente;
import ClassePrincipal.Gerente;
import ConnectionFactory.ConnectionCliente;
import DAOCliente.ClienteDAO;
import ModelFuncionario.Funcionario;
import DAOFuncionario.FuncionarioDAO;
import ModelCliente.ClienteC;
import ModelCliente.Conta;
import ModelCliente.Endereco;
import ModelCliente.SessaoCliente;
import ModelFuncionario.SessaoFuncionario;
import Recursos.Criptografia;
import Recursos.TextFieldFormatter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class LoginCadastroController implements Initializable {

    @FXML
    private Rectangle cartao;
    @FXML
    private JFXTextField tfCPFLogin;
    @FXML
    private JFXPasswordField tfSenhaLogin;
    @FXML
    private JFXTextField tfNome;
    @FXML
    private JFXTextField tfSobrenome;
    @FXML
    private JFXTextField tfCPFCadastro;
    @FXML
    private JFXDatePicker dpNascimento;
    @FXML
    private JFXComboBox<?> cbSexo;
    @FXML
    private JFXTextField tfProfissao;
    @FXML
    private JFXTextField tfTelefone;
    @FXML
    private JFXTextField tfLogradouro;
    @FXML
    private JFXTextField tfCEP;
    @FXML
    private JFXTextField tfBairro;
    @FXML
    private JFXComboBox<?> cbUF;
    @FXML
    private JFXPasswordField tfSenhaCadastro;
    @FXML
    private JFXPasswordField tfConfirmarSenhaCadastro;
    @FXML
    private AnchorPane anchorLogin;
    @FXML
    private AnchorPane anchorCadastro;
    @FXML
    private JFXComboBox<?> cbRenda;
    @FXML
    private JFXTextField tfCPFLoginFuncionario;
    @FXML
    private JFXPasswordField tfSenhaLoginFuncionario;
    @FXML
    private AnchorPane anchorLoginFuncionario;
    
    
    private Connection con;
    
    SessaoCliente sessao;
    SessaoFuncionario sessaoF;
    @FXML
    private JFXButton btConfirmarCadastro;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        propriedadesCartao();
        preencherComboBoxRenda();
        preencherComboBoxSexo();
        preencherComboBoxUF();
        testeConexao();
        
    }
    
    //Inserir dados de cadastro no banco de dados
    //Gerar conta bancâria para o banco de dados
    private void insertDadosCliente(){
        Random gerar = new Random();
        String conta = "100";
        String digito = "";
        
        for(int i = 1; i <= 4; i++){
           conta += gerar.nextInt(10);
        }
        
        digito += gerar.nextInt(10);
        
        ClienteC cl = new ClienteC();
        Endereco e = new Endereco();
        Conta co = new Conta();
        ClienteDAO daoC = new ClienteDAO();
        
        e.setBairro(tfBairro.getText().trim());
        e.setCEP(tfCEP.getText().trim());
        e.setLogradouro(tfLogradouro.getText().trim());
        e.setUF(cbUF.getSelectionModel().getSelectedItem().toString());
        cl.setCPF(tfCPFCadastro.getText().trim());
        cl.setDataNascimento(dpNascimento.getValue().toString());
        cl.setNome(tfNome.getText().trim());
        cl.setProfissao(tfProfissao.getText().trim());
        cl.setRenda(cbRenda.getSelectionModel().getSelectedItem().toString());
        cl.setSenha(Criptografia.MD5(tfSenhaCadastro.getText()));
        cl.setSexo(cbSexo.getSelectionModel().getSelectedItem().toString());
        cl.setSobrenome(tfSobrenome.getText().trim());
        cl.setTelefone(tfTelefone.getText().trim());
        co.setConta(conta);
        co.setDigito(digito);
        
        daoC.cadastrar(cl, e, co);
    }
    
    //Teste de conexão com o banco de dados
    private void testeConexao(){
        this.con = new ConnectionCliente().getConnectionCliente();
        if (con != null) {
            sessao = SessaoCliente.getInstancia();
            sessaoF = SessaoFuncionario.getInstancia();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Banco Digital");
            alert.setHeaderText("Erro na conexão.");
            alert.setContentText("O servidor está offline.");
            alert.showAndWait();
        }
    }
    
    //Arredondar os cantos de um retangulo
    private void propriedadesCartao(){
        cartao.setArcHeight(20.0);
        cartao.setArcWidth(30.0); 
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
    
    //Preencher a combobox Sexo
    private void preencherComboBoxSexo(){
        List sexo = new ArrayList();
        sexo.add("Masculino");
        sexo.add("Feminino");
        
        cbSexo.setItems(FXCollections.observableArrayList(sexo));
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
    
    private void limparFormularios(){
        cbRenda.getSelectionModel().clearSelection();
        cbSexo.getSelectionModel().clearSelection();
        cbUF.getSelectionModel().clearSelection();
        tfBairro.setText("");
        tfCEP.setText("");
        tfCPFCadastro.setText("");
        tfCPFLogin.setText("");
        tfConfirmarSenhaCadastro.setText("");
        tfLogradouro.setText("");
        tfNome.setText("");
        tfProfissao.setText("");
        tfSenhaCadastro.setText("");
        tfSenhaLogin.setText("");
        tfSobrenome.setText("");
        tfTelefone.setText("");
    }
    
    //Verifica se o CPF digitado já se encontra cadastrado
    
    private void verificarCPFExistente(){
        ClienteDAO daoC = new ClienteDAO();
        List<ClienteC> cliente = daoC.getUsuario(tfCPFCadastro.getText());
        
        if(cliente.isEmpty() == false){
            btConfirmarCadastro.setDisable(true);
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Banco Digital");
            alert.setHeaderText("CPF indisponível para cadastro.");
            alert.setContentText("O CPF informado já se encontra cadastrado.");
            alert.showAndWait();
        }else {
            btConfirmarCadastro.setDisable(false);
        }
    }
    
    //Métodos criado pelo FXML
    @FXML
    private void abrirCadastro(ActionEvent event) {
        anchorCadastro.setVisible(true);
        anchorLogin.setVisible(false);
        limparFormularios();
    }


    @FXML
    private void abrirCliente(ActionEvent event) {
        ClienteDAO daoC = new ClienteDAO();
        List<ClienteC> cliente = daoC.getUsuario(tfCPFLogin.getText());
        
        if(tfCPFLogin.getText().length() > 0 && tfSenhaLogin.getText().length() > 0){
            if(cliente.isEmpty() == false){
                if(cliente.get(0).getSenha().equals(Criptografia.MD5(tfSenhaLogin.getText()))){
                    
                    sessao.setCPF(cliente.get(0).getCPF());
                    sessao.setDataNascimento(cliente.get(0).getDataNascimento());
                    sessao.setID_Cliente(cliente.get(0).getID_Cliente());
                    sessao.setNome(cliente.get(0).getNome());
                    sessao.setProfissao(cliente.get(0).getProfissao());
                    sessao.setRenda(cliente.get(0).getRenda());
                    sessao.setSenha(cliente.get(0).getSenha());
                    sessao.setSexo(cliente.get(0).getSexo());
                    sessao.setSobrenome(cliente.get(0).getSobrenome());
                    sessao.setTelefone(cliente.get(0).getTelefone());

                    try {
                        Cliente c = new Cliente();
                        LoginCadastro.getStage().close();
                        c.start(new Stage());
                    } catch (Exception e) {
                        System.out.println("Erro Login");
                    }
                }else {
                    if(tfSenhaLogin.getText().length() != 0){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Banco Digital");
                        alert.setHeaderText("Senha inválida.");
                        alert.setContentText("Tente novamente....");
                        alert.showAndWait();
                        tfSenhaLogin.setText("");
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
                alert.setHeaderText("CPF inválido.");
                alert.setContentText("Tente novamente....");
                alert.showAndWait();
                tfCPFLogin.setText("");
                tfSenhaLogin.setText("");
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Banco Digital");
            alert.setHeaderText("Campo vazio.");
            alert.setContentText("Preencha todos os campos.");
            alert.showAndWait(); 
        }
    }

    @FXML
    private void tfCPFLoginFormat(KeyEvent event) {
        TextFieldFormatter formatar = new TextFieldFormatter();
        formatar.setMask("###.###.###-##");
        formatar.setCaracteresValidos("0123456789");
        formatar.setTf(tfCPFLogin);
        formatar.formatter();
    }
    
    @FXML
    private void tfCPFLoginFuncionarioFormat(KeyEvent event) {
        TextFieldFormatter formatar = new TextFieldFormatter();
        formatar.setMask("###.###.###-##");
        formatar.setCaracteresValidos("0123456789");
        formatar.setTf(tfCPFLoginFuncionario);
        formatar.formatter();
    }
    
    @FXML
    private void tfCPFCadastroFormat(KeyEvent event) {
        verificarCPFExistente();
        
        TextFieldFormatter formatar = new TextFieldFormatter();
        formatar.setMask("###.###.###-##");
        formatar.setCaracteresValidos("0123456789");
        formatar.setTf(tfCPFCadastro);
        formatar.formatter();
    }

    @FXML
    private void tfTelefoneFormat(KeyEvent event) {
        TextFieldFormatter formatar = new TextFieldFormatter();
        formatar.setMask("(##)#####-####");
        formatar.setCaracteresValidos("0123456789");
        formatar.setTf(tfTelefone);
        formatar.formatter();
    }

    @FXML
    private void tfCEPFormat(KeyEvent event) {
        TextFieldFormatter formatar = new TextFieldFormatter();
        formatar.setMask("#####-###");
        formatar.setCaracteresValidos("0123456789");
        formatar.setTf(tfCEP);
        formatar.formatter();
    }

    @FXML
    private void confirmarCadastro(ActionEvent event) {
        if(tfSenhaCadastro.getText().length() >= 4){
            if(tfSenhaCadastro.getText().trim().equals(tfConfirmarSenhaCadastro.getText().trim())){
                insertDadosCliente();
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Banco Digital");
                alert.setHeaderText("Cadastro de cliente.");
                alert.setContentText("O cadadastro foi realizado com sucesso.");
                alert.showAndWait();
                
                limparFormularios();
                anchorLogin.setVisible(true);
                anchorCadastro.setVisible(false);
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Banco Digital");
                alert.setHeaderText("As senhas não coincidem.");
                alert.setContentText("Tente novamente....");
                alert.showAndWait();
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Banco Digital");
            alert.setHeaderText("Senha curta.");
            alert.setContentText("Sua senha deve conter no minimo 4 dígitos.");
            alert.showAndWait();
        }
    }

    @FXML
    private void voltarLogin(ActionEvent event) {
        anchorLogin.setVisible(true);
        anchorCadastro.setVisible(false);
        limparFormularios();
    }

    @FXML
    private void fecharJanela(ActionEvent event) {
        LoginCadastro.getStage().close();
    }

    @FXML
    private void minimizarJanela(ActionEvent event) {
        LoginCadastro.getStage().setIconified(true);
    }

    @FXML
    private void abrirFuncionario(ActionEvent event) {
        FuncionarioDAO daoF = new FuncionarioDAO();
        List<Funcionario> funcionario = daoF.getFuncionario(tfCPFLoginFuncionario.getText());
        
        if(tfCPFLoginFuncionario.getText().length() > 0 && tfSenhaLoginFuncionario.getText().length() > 0){
            if(funcionario.isEmpty() == false){
                if(funcionario.get(0).getSenha().equals(Criptografia.MD5(tfSenhaLoginFuncionario.getText()))){
                    
                    sessaoF.setCPF(funcionario.get(0).getCPF());
                    sessaoF.setDataNascimento(funcionario.get(0).getDataNascimento());
                    sessaoF.setID_Departamento(funcionario.get(0).getID_Departamento());
                    sessaoF.setID_Funcionario(funcionario.get(0).getID_Funcionario());
                    sessaoF.setNome(funcionario.get(0).getNome());
                    sessaoF.setProfissao(funcionario.get(0).getProfissao());
                    sessaoF.setSalario(funcionario.get(0).getSalario());
                    sessaoF.setSenha(funcionario.get(0).getSenha());
                    sessaoF.setSexo(funcionario.get(0).getSexo());
                    sessaoF.setSobrenome(funcionario.get(0).getSobrenome());
                    sessaoF.setTelefone(funcionario.get(0).getTelefone());
                    sessaoF.setDepartamento(funcionario.get(0).getDepartamento());

                    try {
                        Gerente g = new Gerente();
                        LoginCadastro.getStage().close();
                        g.start(new Stage());
                    } catch (Exception e) {
                    }
                }else {
                    if(tfSenhaLoginFuncionario.getText().length() != 0){
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Banco Digital");
                        alert.setHeaderText("Senha inválida.");
                        alert.setContentText("Tente novamente....");
                        alert.showAndWait();
                        tfSenhaLoginFuncionario.setText("");
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
                alert.setHeaderText("CPF inválido.");
                alert.setContentText("Tente novamente....");
                alert.showAndWait();
                tfCPFLoginFuncionario.setText("");
                tfSenhaLoginFuncionario.setText("");
            }
        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Banco Digital");
            alert.setHeaderText("Campo vazio.");
            alert.setContentText("Preencha todos os campos.");
            alert.showAndWait(); 
        }
    }

    @FXML
    private void verificarCPF(MouseEvent event) {
        verificarCPFExistente();
    }
}
