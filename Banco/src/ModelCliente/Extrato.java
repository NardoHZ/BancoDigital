/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelCliente;

import Recursos.FormatarData;
import Recursos.FormatarDinheiro;
import static Recursos.FormatarDinheiro.formatarDinheiro;

/**
 *
 * @author gugaa
 */
public class Extrato{
    private int ID_Extrato;
    private int ID_Conta;
    private String Data;
    private String dataFormatada;
    private String Descricao;
    private double Entrada;
    private double entradaFormatada;
    private double Saida;
    private double saidaFormatada;
    private double Saldo;
    private double saldoFormatado;

    public Extrato() {
    }

    public int getID_Extrato() {
        return ID_Extrato;
    }

    public void setID_Extrato(int ID_Extrato) {
        this.ID_Extrato = ID_Extrato;
    }

    public int getID_Conta() {
        return ID_Conta;
    }

    public void setID_Conta(int ID_Conta) {
        this.ID_Conta = ID_Conta;
    }

    public String getData() {
        return Data;
    }

    public String getDataFormatada() {
        return FormatarData.formatarData(Data, dataFormatada);
    }

    public void setData(String Data) {
        this.Data = Data;
    }

    public String getDescricao() {
        return Descricao;
    }

    public void setDescricao(String Descricao) {
        this.Descricao = Descricao;
    }

    public double getEntrada() {
        return Entrada;
    }

    public void setEntrada(double Entrada) {
        this.Entrada = Entrada;
    }

    public String getEntradaFormatada() {
        return FormatarDinheiro.formatarDinheiro(Entrada);
    }
    
    public double getSaida() {
        return Saida;
    }

    public void setSaida(double Saida) {
        this.Saida = Saida;
    }

    public String getSaidaFormatada() {
        return formatarDinheiro(Saida);
    }

    public double getSaldo() {
        return Saldo;
    }

    public void setSaldo(double Saldo) {
        this.Saldo = Saldo;
    }

    public String getSaldoFormatado() {
        return formatarDinheiro(Saldo);
    }
}
