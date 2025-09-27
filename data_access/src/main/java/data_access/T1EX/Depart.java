package data_access.T1EX;

import java.io.Serializable;

public class Depart implements Serializable {
    private String nombreDepart;
    private int numeroDepart;

    public Depart(String nombreDepart, int numeroDepart) {
        this.nombreDepart = nombreDepart;
        this.numeroDepart = numeroDepart;
    }

    public void setNombreDepart(String nombreDepart) {
        this.nombreDepart = nombreDepart;
    }

    public void setNumeroDepart(int numeroDepart) {
        this.numeroDepart = numeroDepart;
    }

    public String getNombreDepart() {
        return nombreDepart;
    }

    public int getNumeroDepart() {
        return numeroDepart;
    }

    @Override
    public String toString() {
        return "nombreDepart: '" + nombreDepart + "', numeroDepart: " + numeroDepart + "}";
    }
}
