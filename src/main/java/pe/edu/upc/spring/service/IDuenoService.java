package pe.edu.upc.spring.service;

import java.util.List;
import java.util.Optional;

import pe.edu.upc.spring.model.Dueno;

public interface IDuenoService {
	public boolean registrar(Dueno dueno);
	public void eliminar(int idDueno);
	public Optional<Dueno> listarId(int idDueno);
	List<Dueno> listar();
	
}
