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
public class RelatorioFinanceiro{
    private int ID_RelatorioFinanceiro;
    private int ID_Departamento;
    private String Data;
    private String dataFormatada;
    private String Descricao;
    private double Entrada;
    private double entradaFormatada;
    private double Saida;
    private double saidaFormatada;
    private double Saldo;
    private double saldoFormatado;

    public RelatorioFinanceiro() {
    }

    public int getID_RelatorioFinanceiro() {
        return ID_RelatorioFinanceiro;
    }

    public void setID_RelatorioFinanceiro(int ID_RelatorioFinanceiro) {
        this.ID_RelatorioFinanceiro = ID_RelatorioFinanceiro;
    }

    public int getID_Departamento() {
        return ID_Departamento;
    }

    public void setID_Departamento(int ID_Departamento) {
        this.ID_Departamento = ID_Departamento;
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
