/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelCliente;

import Recursos.FormatarDinheiro;

/**
 *
 * @author gugaa
 */
public class Emprestimo {
    private int ID_Emprestimo;
    private int ID_Cliente;
    private int parcelasTotal;
    private int parcelasParcial;
    private double Juros;
    private double valorEmprestimo;
    private double valorEmprestimoFormatado;
    private double dividaTotal;
    private double dividaTotalFormatada;
    private double dividaParcial;
    private double dividaParcialFormatada;
    private double valorParcelas;
    private double valorParcelasFormatado;
    private String Status;

    public Emprestimo() {
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getValorEmprestimoFormatado() {
        return FormatarDinheiro.formatarDinheiro(valorEmprestimo);
    }

    public String getDividaTotalFormatada() {
        return FormatarDinheiro.formatarDinheiro(dividaTotal);
    }

    public String getDividaParcialFormatada() {
        return FormatarDinheiro.formatarDinheiro(dividaParcial);
    }

    public String getValorParcelasFormatado() {
        return FormatarDinheiro.formatarDinheiro(valorParcelas);
    }

    public int getID_Emprestimo() {
        return ID_Emprestimo;
    }

    public void setID_Emprestimo(int ID_Emprestimo) {
        this.ID_Emprestimo = ID_Emprestimo;
    }

    public int getID_Cliente() {
        return ID_Cliente;
    }

    public void setID_Cliente(int ID_Cliente) {
        this.ID_Cliente = ID_Cliente;
    }

    public int getParcelasTotal() {
        return parcelasTotal;
    }

    public void setParcelasTotal(int parcelasTotal) {
        this.parcelasTotal = parcelasTotal;
    }

    public int getParcelasParcial() {
        return parcelasParcial;
    }

    public void setParcelasParcial(int parcelasParcial) {
        this.parcelasParcial = parcelasParcial;
    }

    public double getJuros() {
        return Juros;
    }

    public void setJuros(double Juros) {
        this.Juros = Juros;
    }

    public double getValorEmprestimo() {
        return valorEmprestimo;
    }

    public void setValorEmprestimo(double valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }

    public double getDividaTotal() {
        return dividaTotal;
    }

    public void setDividaTotal(double dividaTotal) {
        this.dividaTotal = dividaTotal;
    }

    public double getDividaParcial() {
        return dividaParcial;
    }

    public void setDividaParcial(double dividaParcial) {
        this.dividaParcial = dividaParcial;
    }

    public double getValorParcelas() {
        return valorParcelas;
    }

    public void setValorParcelas(double valorParcelas) {
        this.valorParcelas = valorParcelas;
    }
}
