/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ClassePrincipal.Cliente;
import ClassePrincipal.Gerente;
import ClassePrincipal.LoginCadastro;
import DAOFuncionario.AgenciaDAO;
import DAOFuncionario.FuncionarioDAO;
import ModelFuncionario.Agencia;
import ModelCliente.ClienteC;
import ModelFuncionario.Funcionario;
import ModelFuncionario.SessaoFuncionario;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class GerenteController extends ClienteC implements Initializable {
    
    Float data = 0f;
    int operacao = -1;
    
    @FXML
    private Button um;
    @FXML
    private Button sete;
    @FXML
    private Button menos;
    @FXML
    private Button seis;
    @FXML
    private Button cinco;
    @FXML
    private Button quatro;
    @FXML
    private Button mais;
    @FXML
    private Button tres;
    @FXML
    private Button dois;
    @FXML
    private Button dividir;
    @FXML
    private Button igual;
    @FXML
    private Button limpar;
    @FXML
    private Button zero;
    @FXML
    private Button vezes;
    @FXML
    private Button nove;
    @FXML
    private Button oito;
    @FXML
    private TextField display;
    @FXML
    private BorderPane borderPaneGerente;
    @FXML
    private Label lbNomeFuncionario;
    @FXML
    private Label lbDepartamento;
    @FXML
    private Label lbSaldoCaixa;
    
    
    SessaoFuncionario sessao = SessaoFuncionario.getInstancia();
    @FXML
    private Label lbHora;
    @FXML
    private Label lbData;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTelas("/View/HomeFuncionario");
        carregarDadosAgencia();
        relogioDigital();
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    carregarDadosAgencia();
                    relogioDigital();
                });
            }
        }, 1000, 1000);
    }
    
    //Ações no clique da calculadora
    @FXML
    private void acaoCliqueCalculadora(ActionEvent event) {
        
        if(event.getSource() == um){
            display.setText(display.getText() + "1");
        }else if (event.getSource() == dois){
            display.setText(display.getText() + "2");
        }else if (event.getSource() == tres){
            display.setText(display.getText() + "3");
        }else if (event.getSource() == quatro){
            display.setText(display.getText() + "4");
        }else if (event.getSource() == cinco){
            display.setText(display.getText() + "5");
        }else if (event.getSource() == seis){
            display.setText(display.getText() + "6");
        }else if (event.getSource() == sete){
            display.setText(display.getText() + "7");
        }else if (event.getSource() == oito){
            display.setText(display.getText() + "8");
        }else if (event.getSource() == nove){
            display.setText(display.getText() + "9");
        }else if (event.getSource() == zero){
            display.setText(display.getText() + "0");
        }else if (event.getSource() == limpar){
            display.setText("");
        }else if(event.getSource() == mais) {
            data = Float.parseFloat(display.getText());
            operacao = 1; //Adição
            display.setText("");
        }else if(event.getSource() == menos) {
            data = Float.parseFloat(display.getText());
            operacao = 2; //Subtração
            display.setText("");
        }else if(event.getSource() == vezes) {
            data = Float.parseFloat(display.getText());
            operacao = 3; //Multiplicação
            display.setText("");
        }else if(event.getSource() == dividir) {
            data = Float.parseFloat(display.getText());
            operacao = 4; //Divisão
            display.setText("");
        }else if (event.getSource() == igual){
            Float segundaOperacao  = Float.parseFloat(display.getText());
            switch(operacao)
            {
                case 1: //Adição
                    Float ans = data + segundaOperacao;
                    display.setText(String.valueOf(ans));
                    break;
                case 2: //Subtração
                    ans = data - segundaOperacao;
                    display.setText(String.valueOf(ans));
                    break;
                case 3: //Multiplicação
                    ans = data * segundaOperacao;
                    display.setText(String.valueOf(ans));
                    break;
                case 4: //Divisão
                    ans = 0f;
                    try {
                        ans = data / segundaOperacao;
                    } catch (Exception e) {
                        display.setText("Erro");
                    }
                    display.setText(String.valueOf(ans));
                    break;
            }
        }
    }
    
    private void relogioDigital(){
        SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm:ss a");
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
        Date hora = new Date();
        Date data = new Date();
        lbHora.setText(formatHora.format(hora));
        lbData.setText(formatData.format(data));
    }
    
    private void carregarDadosAgencia(){
        AgenciaDAO daoC = new AgenciaDAO();
        List<Agencia> agencia = daoC.getSaldoAgencia();
        lbSaldoCaixa.setText(agencia.get(0).getSaldoFormatado());
        
        FuncionarioDAO daoF = new FuncionarioDAO();
        List<Funcionario> f = daoF.getFuncionario(sessao.getCPF());
        
        lbDepartamento.setText(f.get(0).getDepartamento());
        lbNomeFuncionario.setText(f.get(0).getNome() + " " + f.get(0).getSobrenome());
    }
    
    private void loadTelas(String tela){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(tela + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(GerenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPaneGerente.setCenter(root);
    }

    private void sairCliente(ActionEvent event) {
        try {
            LoginCadastro lc = new LoginCadastro();
            lc.start(new Stage());
            Cliente.getStage().close();
        } catch (Exception e) {
        }
    }

    @FXML
    private void fecharJanela(ActionEvent event) {
        fecharGerente();
    }
    
    public void fecharGerente(){
        System.exit(0);
    }

    @FXML
    private void minimizarJanela(ActionEvent event) {
        Gerente.getStage().setIconified(true);
    }

    @FXML
    private void atualizarDados(ActionEvent event) {
        carregarDadosAgencia();
    }

    @FXML
    private void detalhesEmprestimo(ActionEvent event) {
        loadTelas("/View/DetalhesEmprestimos");
    }

    @FXML
    private void relatorioFinanceiro(ActionEvent event) {
        loadTelas("/View/RelatorioFinanceiro");
    }

    @FXML
    private void listaClientes(ActionEvent event) {
        loadTelas("/View/ListaClientes");
    }

    @FXML
    private void homeFuncionario(ActionEvent event) {
        loadTelas("/View/HomeFuncionario");
    }

    @FXML
    private void abrirMeusDados(ActionEvent event) {
        loadTelas("/View/MeusDadosFuncionario");
    }

    @FXML
    private void sairGerente(ActionEvent event) {
        Gerente.getStage().close();
        LoginCadastro l = new LoginCadastro();
        try {
            l.start(new Stage());
        } catch (Exception ex) {
            Logger.getLogger(GerenteController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
