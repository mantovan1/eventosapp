package com.eventosapp.controllers;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.eventosapp.models.Evento;
import com.eventosapp.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;

	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.GET)
	public String nome(Model model) {
		
		LocalDate now = LocalDate.now();
		
		model.addAttribute("now", now);
		
		return "evento/formEvento";
	}
	
	@RequestMapping(value = "/cadastrarEvento", method = RequestMethod.POST)
	public String nome(Evento evento) {
		
		er.save(evento);
		
		return "redirect:/";
	}
	
	@RequestMapping("/eventos")
	public ModelAndView listaEventos() {
		ModelAndView mv = new ModelAndView("index");
		
		Iterable<Evento> eventos = er.findAll();
		
		mv.addObject("eventos", eventos);
		
		return mv;
	}
	
	@RequestMapping("/{codigo}")
	public ModelAndView detalhesEvento(@PathVariable("codigo") long codigo) {
		Evento evento = er.findByCodigo(codigo);
		ModelAndView mv = new ModelAndView("evento/detalhesEvento");
		
		mv.addObject("evento", evento);
		
		return mv;
	}
	
	@RequestMapping(value = "/deletarEvento/{codigo}", method = RequestMethod.GET)
	public String deletarEvento(@PathVariable("codigo") long codigo) {
		
		Evento evento = er.findByCodigo(codigo);
		er.delete(evento);
		
		return "redirect:/";
	}
	
}
