package iGestion;

import java.time.LocalDate;

import Models.FichaDiaria;

public interface iGestionFichas {
    public boolean save();
    public FichaDiaria get(LocalDate date);
}
