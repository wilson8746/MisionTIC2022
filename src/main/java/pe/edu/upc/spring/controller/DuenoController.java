package pe.edu.upc.spring.controller;

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

import pe.edu.upc.spring.model.Dueno;
import pe.edu.upc.spring.service.IDuenoService;

@Controller
@RequestMapping("/dueno")
public class DuenoController {
	
	@Autowired
	private IDuenoService rService;
	@RequestMapping("/bienvenido")
	public String irPaginaBienvenida() {
		return "bienvenido";
	}

	@RequestMapping("/")
	public String irPaginaListadoRazas(Map<String, Object> model) {
		model.put("listaDuenos", rService.listar());
		return "listDueno";
	}

	@RequestMapping("/irRegistrar")
	public String irPaginaRegistrar(Model model) {
		model.addAttribute("dueno", new Dueno());
		return "dueno";

	}

	@RequestMapping("/registrar")
	public String registrar(@ModelAttribute Dueno objDueno, BindingResult binRes, Model model)
			throws ParseException
	{
		if (binRes.hasErrors())
			return "dueno";
		else {			
			boolean flag = rService.registrar(objDueno);
			
			if (flag)
				return "redirect:/dueno/listar";
			else {
				model.addAttribute("mensaje", "Ocurrio un error");
				return "redirect:/dueno/irRegistrar";
				
			}
		}
	}

	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir)
		throws ParseException 
	{
		Optional<Dueno> objDueno = rService.listarId(id);
		if (objDueno == null) {
			objRedir.addFlashAttribute("mensaje", "Ocurrio un error");
			return "redirect:/dueno/listar";
		}
		else {
			model.addAttribute("dueno", objDueno);
			return "dueno";
		}
	}

	@RequestMapping("/eliminar")
	public String eliminar(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!=null && id>0) {
				rService.eliminar(id);
				model.put("listaDuenos", rService.listar());
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje","Ocurrio un roche");
			model.put("listaDuenos", rService.listar());
		}
		return "listDueno";

	}

	@RequestMapping("/listar")
	public String listar(Map<String, Object> model) {
		model.put("listaDuenos", rService.listar());
		return "listDueno";
	}		

}