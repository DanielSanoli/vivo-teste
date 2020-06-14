package br.com.daniel.projeto.service;

import java.util.ArrayList;
import java.util.List;

import br.com.daniel.projeto.model.Job;

public class JobService {

public List<List<Job>> ordenaJobs(List<Job> jobs) {
	
	jobs.sort(new Comparator<Job>());
		
	}
}
