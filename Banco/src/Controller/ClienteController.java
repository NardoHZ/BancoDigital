/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import ClassePrincipal.Cliente;
import ClassePrincipal.LoginCadastro;
import DAOCliente.ClienteDAO;
import DAOCliente.ContaDAO;
import DAOCliente.EnderecoDAO;
import ModelCliente.ClienteC;
import ModelCliente.Conta;
import ModelCliente.Endereco;
import ModelCliente.SessaoCliente;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Anderson
 */
public class ClienteController extends ClienteC implements Initializable {
    
    Float data = 0f;
    int operacao = -1;
    
    private JFXButton btMeusCartoes;
    private AnchorPane anchorMeusCartoes;
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
    private JFXButton btExtratos;
    private AnchorPane anchorExtratos;
    private AnchorPane anchorDepositos;
    @FXML
    private JFXButton btMinhaConta;
    @FXML
    private JFXButton btHome;
    @FXML
    private JFXButton btTransferencias;
    @FXML
    private JFXButton btDepositos;
    @FXML
    private JFXButton btPagamentos;
    @FXML
    private JFXButton btEmprestimos;
    @FXML
    private BorderPane borderPaneCliente;
    @FXML
    private Label lbNomeCliente;
    @FXML
    private Label lbSaldoCliente;
    @FXML
    private Label lbLimiteEmprestimo;
    
    SessaoCliente sessao = SessaoCliente.getInstancia();
    @FXML
    private Label lbHora;
    @FXML
    private Label lbData;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadTelas("/View/Home");
        carregarDadosCliente();
        relogioDigital();
        
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            @Override
            public void run(){
                Platform.runLater(() -> {
                    carregarDadosCliente();
                    relogioDigital();
                });
            }
        },1000,1000);
    }
    
    private void relogioDigital(){
        SimpleDateFormat formatHora = new SimpleDateFormat("hh:mm:ss a");
        SimpleDateFormat formatData = new SimpleDateFormat("dd/MM/yyyy");
        Date hora = new Date();
        Date data = new Date();
        lbHora.setText(formatHora.format(hora));
        lbData.setText(formatData.format(data));
    }
    
    private void carregarDadosCliente(){
        Locale l = new Locale("pt", "BR");
        NumberFormat nf = NumberFormat.getCurrencyInstance(l);
        
        EnderecoDAO daoE = new EnderecoDAO();
        List<Endereco> endereco = daoE.getEndereco(sessao.getID_Cliente());
        
        ContaDAO daoC = new ContaDAO();
        List<Conta> conta = daoC.getConta(sessao.getID_Cliente());
        
        ClienteDAO daoCl = new ClienteDAO();
        List<ClienteC> cliente = daoCl.getUsuario(sessao.getCPF());
        
        
        lbNomeCliente.setText(cliente.get(0).getNome() + " " + cliente.get(0).getSobrenome() + "!");
        lbSaldoCliente.setText(nf.format(conta.get(0).getSaldo()));
        
        String renda = cliente.get(0).getRenda();
        
        switch(renda){
            case "Inferior a R$ 998,00":
                lbLimiteEmprestimo.setText(nf.format(2994));
                break;
                
            case "Entre R$ 998,00 e R$ 1.996,00":
                lbLimiteEmprestimo.setText(nf.format(5998));
                break;
                
            case "Entre R$ 1.996,00 e R$ 3.992,00":
                lbLimiteEmprestimo.setText(nf.format(11976));
                break;
                
            case "Superior à R$ R$ 3.992,00":
                lbLimiteEmprestimo.setText(nf.format(39920));
                break;
        }
        
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

    @FXML
    private void abrirMeusDados(ActionEvent event) {
        loadTelas("/View/MeusDados");
    }
    
    @FXML
    private void abrirMinhaConta(ActionEvent event) {
        loadTelas("/View/MinhaConta");
    }
        
    @FXML
    private void abrirHome(ActionEvent event) {
        loadTelas("/View/Home");
    }

    @FXML
    private void abrirExtratos(ActionEvent event) {
        loadTelas("/View/Extratos");
    }

    @FXML
    private void abrirTransferencias(ActionEvent event) {
        loadTelas("/View/Transferencias");
    }

    @FXML
    private void abrirDepositos(ActionEvent event) {
        loadTelas("/View/Depositos");
    }

    @FXML
    private void abrirPagamentos(ActionEvent event) {
        loadTelas("/View/Pagamentos");
    }

    @FXML
    private void abrirEmprestimos(ActionEvent event) {
        loadTelas("/View/Emprestimos");
    }
    
     private void loadTelas(String tela){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(tela + ".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(ClienteController.class.getName()).log(Level.SEVERE, null, ex);
        }
        borderPaneCliente.setCenter(root);
    }

    @FXML
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
        fecharCliente();
    }
    
    public void fecharCliente(){
        System.exit(0);
    }

    @FXML
    private void minimizarJanela(ActionEvent event) {
        Cliente.getStage().setIconified(true);
    }

    @FXML
    private void atualizarDados(ActionEvent event) {
        carregarDadosCliente();
    }
}
