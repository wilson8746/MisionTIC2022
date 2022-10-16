package pe.edu.upc.spring.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sun.el.parser.ParseException;

import pe.edu.upc.spring.model.Pet;
import pe.edu.upc.spring.model.Race;
import pe.edu.upc.spring.model.Dueno;

import pe.edu.upc.spring.service.IPetService;
import pe.edu.upc.spring.service.IRaceService;
import pe.edu.upc.spring.service.IDuenoService;

@Controller
@RequestMapping("/pet")
public class PetController {
	@Autowired
	private IRaceService rService;
	
	@Autowired
	private IDuenoService dService;
	
	@Autowired
	private IPetService pService;	
	
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}
		
	@RequestMapping("/")
	public String irPaginaListadoMascotas(Map<String, Object> model) {
		model.put("listaMascotas", pService.listar());
		return "listPet";
	}
	
	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		
		model.addAttribute("race", new Race());
		model.addAttribute("dueno", new Dueno());
		model.addAttribute("pet", new Pet());
		
		model.addAttribute("listaRazas", rService.listar());
		model.addAttribute("listaDuenos", dService.listar());		
		
		return "pet";
	}
	
	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Pet objPet, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors()) 
			{
				model.addAttribute("listaRazas", rService.listar());
				model.addAttribute("listaDuenos", dService.listar());			
				return "pet";
			}
		else {
			boolean flag = pService.registrar(objPet);
			if (flag)
				return "redirect:/pet/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/pet/irRegistrar";
			}
		}
	}
	
	
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Pet> objPet = pService.buscarId(id);
		if (objPet == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/pet/listar";
		}
		else {
			model.addAttribute("listaRazas", rService.listar());
			model.addAttribute("listaDuenos", dService.listar());			
					
			if (objPet.isPresent())
				objPet.ifPresent(o -> model.addAttribute("pet", o));
			
			return "pet";
		}
	}
	
	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				pService.eliminar(id);
				model.put("listaMascotas", pService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaMascotas", pService.listar());
			
		}
		return "listPet";
	}
	
	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaMascotas", pService.listar());
		return "listPet";
	}		
	
	@RequestMapping("/listarId")
	public String listarId(Map<String, Object> model, @ModelAttribute Pet pet) 
	throws ParseException
	{
		pService.listarId(pet.getIdPet());
		return "listPet";
	}	
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("pet", new Pet());
		return "buscar";
	}	
	
	@RequestMapping("/buscar")
	public String buscar(Map<String, Object> model, @ModelAttribute Pet pet)
			throws ParseException
	{
		List<Pet> listaMascotas;
		pet.setNamePet(pet.getNamePet());
		listaMascotas = pService.buscarNombre(pet.getNamePet());
		if (listaMascotas.isEmpty()) {
			listaMascotas = pService.buscarPropietario(pet.getNamePet());
		}
		if (listaMascotas.isEmpty()) {
			listaMascotas = pService.buscarRaza(pet.getNamePet());
		}
		if (listaMascotas.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaMascotas", listaMascotas);		
		return "buscar";
	}		
}
