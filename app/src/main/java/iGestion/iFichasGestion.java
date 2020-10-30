package iGestion;

import java.time.LocalDate;

import Models.FichaDiaria;

public interface iFichasGestion {
    public boolean save();
    public FichaDiaria get(LocalDate date);
}
