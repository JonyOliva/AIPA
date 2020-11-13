package iGestion;

import java.util.ArrayList;

import Models.Sintoma;

public interface iSintomasGestion {
    public Boolean save(Sintoma sintoma);
    public Boolean deleteAll();
    public ArrayList<Sintoma> getAll();
}
