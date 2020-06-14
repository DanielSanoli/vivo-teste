package br.com.daniel.projeto.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.com.daniel.projeto.model.Job;

public class JobService {

	public List<List<Job>> ordenaJobs(List<Job> jobs) {

		List<List<Job>> listaFinal = new ArrayList<List<Job>>();
		List<Job> janelaOitoHoras = new ArrayList<Job>();

		// Deixa os jobs em ordem, de acordo com a data máxima
		jobs.sort(new Comparator<Job>() {
			@Override
			public int compare(Job primeiroJob, Job segundoJob) {
				if (primeiroJob.getDataMaxima() == segundoJob.getDataMaxima()) {
					return 0;
				}
				return primeiroJob.getDataMaxima().compareTo(segundoJob.getDataMaxima());
			}
		});

		// percorre a lista de jobs para ver quais podem ser executados juntos
		for (int i = 0; i < jobs.size(); i++) {

			int tempoJanelaAtual = retornaTempoJanela(janelaOitoHoras);

			if (jobs.get(i).getHoras() + tempoJanelaAtual <= 8) {
				janelaOitoHoras.add(jobs.get(i));

				if (i == jobs.size() - 1) {
					listaFinal.add(janelaOitoHoras);
				}
			} else {
				// Adiciona na janela final por não ter mais espaço
				listaFinal.add(janelaOitoHoras);
				// Cria uma nova janela para adicionar novos jobs
				janelaOitoHoras = new ArrayList<Job>();
				janelaOitoHoras.add(jobs.get(i));

				if (i == jobs.size() - 1) {
					listaFinal.add(janelaOitoHoras);
				}
			}

		}

		return listaFinal;

	}

	private int retornaTempoJanela(List<Job> jobs) {
		int tempoExecucao = 0;

		for (Job job : jobs) {
			tempoExecucao = tempoExecucao + job.getHoras();
		}
		return tempoExecucao;
	}

}
