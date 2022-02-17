package com.AppRH.AppRH.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.models.Empresa;
import com.AppRH.AppRH.repository.EmpresaRepository;

@Controller
public class EmpresaController {

	@Autowired
	private EmpresaRepository er;


	// CADASTRAR EMPRESA
	@RequestMapping("/cadastrarEmpresa")
	public String form() {
		return "empresa/form-empresa";
	}


	@RequestMapping(value = "/cadastrarEmpresa", method = RequestMethod.POST)
	public String form(@Valid Empresa empresa, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarEmpresa";
		}

		er.save(empresa);
		attributes.addFlashAttribute("mensagem", "Empresa cadastrado com sucesso!");
		return "redirect:/cadastrarEmpresa";
	}	

	
	@RequestMapping("/editar-empresa")
	public ModelAndView editarEmpresa(long id) {
		Empresa empresa = er.findById(id);	
		ModelAndView mv = new ModelAndView("empresa/update-empresa");
		mv.addObject("empresa",empresa);
		return mv;
	}

	// ATUALIZA O CADASTRO
	@RequestMapping(value = "/editar-empresa", method = RequestMethod.POST)
	public String updateVaga(@Valid Empresa empresa, BindingResult result, RedirectAttributes attributes) {
		er.save(empresa);
		attributes.addFlashAttribute("success", "Empresa alterada com sucesso!");

		return "redirect:/empresas";
	}

	//	LISTA AS EMPRESAS
	@RequestMapping("/empresas")
	public ModelAndView listaEmpresa() {

		ModelAndView mv = new ModelAndView("empresa/lista-empresa");

		Iterable<Empresa> empresas = er.findAll();

		mv.addObject("empresas",empresas);

		return mv;
	}

	// DELETA A EMPRESA ADICIONADA

	@RequestMapping("/deletarEmpresa")
	public String deletarEmpresa(long id) {
		Empresa empresa = er.findById(id);
		er.delete(empresa);
		return "redirect:/empresas";
	}

}
