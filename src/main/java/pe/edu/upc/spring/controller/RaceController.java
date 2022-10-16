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

import pe.edu.upc.spring.model.Race;
import pe.edu.upc.spring.service.IRaceService;

@Controller
@RequestMapping("/race")
public class RaceController {
	
	@Autowired
	private IRaceService rService;
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}

	@RequestMapping("/")
	public String irPaginaListadoRazas(Map<String, Object> model) {
		model.put("listaRazas", rService.listar());
		return "listRace";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("race", new Race());
		return "race";

	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Race objRace, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
			return "race";
		else {			
			boolean flag = rService.registrar(objRace);
			
			if (flag)
				return "redirect:/race/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/race/irRegistrar";
				
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Race> objRace = rService.listarId(id);
		if (objRace == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/race/listar";
		}
		else {
			model.addAttribute("race", objRace);
			return "race";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaRazas", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaRazas", rService.listar());
		}
		return "listRace";

	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaRazas", rService.listar());
		return "listRace";
	}		
	
	@RequestMapping("/irBuscar")
	public String irBuscar(Model model) 
	{
		model.addAttribute("race", new Race());
		return "buscar2";
	}	
	
	@RequestMapping("/buscar2")
	public String buscar(Map<String, Object> model, @ModelAttribute Race race)
			throws ParseException
	{
		List<Race> listaRazas;
		race.setNameRace(race.getNameRace());
		listaRazas = rService.buscarNombre(race.getNameRace());
		if (listaRazas.isEmpty()) {
			listaRazas = rService.buscarNombre(race.getNameRace());		
		}
		if (listaRazas.isEmpty()) {
			model.put("mensaje", "No existen coincidencias");
		}
		model.put("listaRazas", listaRazas);		
		return "buscar2";
	}		

}