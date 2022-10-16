package pe.edu.upc.spring.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import pe.edu.upc.spring.model.Pet;
import pe.edu.upc.spring.repository.IPetRepository;
import pe.edu.upc.spring.service.IPetService;

@Service
public class PetServiceImpl implements IPetService {

	@Autowired
	private IPetRepository dPet;
	
	@Override
	@Transactional
	public boolean registrar(Pet pet) {
		Pet objPet = dPet.save(pet);
		if(objPet == null)
			return false;
		else
			return true;
	}

	@Override
	@Transactional
	public void eliminar(int idPet) {
		dPet.deleteById(idPet);

	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Pet> listarId(int idPet) {
		return dPet.findById(idPet);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Optional<Pet> buscarId(int idPet) {
		return dPet.findById(idPet);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Pet> listar() {
		return dPet.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Pet> buscarNombre(String namePet) {
		return dPet.buscarNombre(namePet);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Pet> buscarRaza(String nameRace) {
		return dPet.buscarRaza(nameRace);
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<Pet> buscarPropietario(String nameDueno) {
		return dPet.buscarPropietario(nameDueno);
	}
}
