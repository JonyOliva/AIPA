package iService;

import java.util.ArrayList;

import Models.FichaDiaria;

public interface iFichasService {
	public ArrayList<FichaDiaria> getAllFromUser(String email);
	public Boolean deleteAll(String email);
	public Boolean save(FichaDiaria fd, String email);
	public Boolean update(FichaDiaria fd);
	public FichaDiaria getAnterior();
}
