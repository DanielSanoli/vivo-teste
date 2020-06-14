package br.com.daniel.projeto.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.daniel.projeto.model.JanelaExecucao;
import br.com.daniel.projeto.model.Job;
import br.com.daniel.projeto.service.JobService;

@RestController
@RequestMapping("/jobs")
public class JobController {

		@PostMapping
		public List<List<Job>> recebeJobs(@RequestBody JanelaExecucao janelaExecucao) throws Exception {
			JobService service = new JobService();
			return service.ordenaJobs(janelaExecucao);
		}
	}

