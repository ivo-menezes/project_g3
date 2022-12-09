package org.switch2022.project.model;

import java.util.Objects;

public class NumeroDecimal extends Numero{
    int numero;

    public NumeroDecimal(String valor) {
        this.numero = Integer.parseInt(valor);
    }

    public NumeroDecimal(int valor) {
        this.numero = valor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof NumeroDecimal)) return false;
        NumeroDecimal that = (NumeroDecimal) o;
        return numero == that.numero;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero);
    }

    @Override
    public int getValueDec() {
        return numero;
    }

    @Override
    public Numero duplica() {
        return new NumeroDecimal(this.numero);
    }
}
