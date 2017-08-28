package fr.husta.test.pojo;

import java.math.BigDecimal;
import java.math.BigInteger;

public class SimpleNumbersPojo {

    private int nbInt;
    private long nbLong;

    private float nbFloat;
    private double nbDouble;

    private BigInteger nbBigInteger;
    private BigDecimal nbBigDecimal;

    public SimpleNumbersPojo() {
    }

    public int getNbInt() {
        return nbInt;
    }

    public void setNbInt(int nbInt) {
        this.nbInt = nbInt;
    }

    public long getNbLong() {
        return nbLong;
    }

    public void setNbLong(long nbLong) {
        this.nbLong = nbLong;
    }

    public float getNbFloat() {
        return nbFloat;
    }

    public void setNbFloat(float nbFloat) {
        this.nbFloat = nbFloat;
    }

    public double getNbDouble() {
        return nbDouble;
    }

    public void setNbDouble(double nbDouble) {
        this.nbDouble = nbDouble;
    }

    public BigInteger getNbBigInteger() {
        return nbBigInteger;
    }

    public void setNbBigInteger(BigInteger nbBigInteger) {
        this.nbBigInteger = nbBigInteger;
    }

    public BigDecimal getNbBigDecimal() {
        return nbBigDecimal;
    }

    public void setNbBigDecimal(BigDecimal nbBigDecimal) {
        this.nbBigDecimal = nbBigDecimal;
    }
}
