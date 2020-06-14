package br.com.daniel.projeto.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import br.com.daniel.projeto.model.JanelaExecucao;
import br.com.daniel.projeto.model.Job;

public class JobService {

	public List<List<Job>> ordenaJobs(JanelaExecucao janelaExecucao) throws Exception {

		List<List<Job>> listaFinal = new ArrayList<List<Job>>();

		// Valida se o tempo dos jobs somados cabe na janela de execução.
		if (this.validaTempoDosJobs(janelaExecucao)) {
			listaFinal.addAll(this.trataListaDeJobs(janelaExecucao));
		} else {
			throw new Exception("Janela curta demais para executar");
		}

		return listaFinal;
	}

	private List<List<Job>> trataListaDeJobs(JanelaExecucao janelaExecucao) {

		List<List<Job>> listaFinal = new ArrayList<List<Job>>();
		List<Job> janelaDeOitoHoras = new ArrayList<Job>();

		// Deixa osjobs em ordem, de acordo com a data máxima
		janelaExecucao.getJobs().sort(new Comparator<Job>() {
			@Override
			public int compare(Job primeiroJob, Job segundoJob) {
				if (primeiroJob.getDataMaxima() == segundoJob.getDataMaxima()) {
					return 0;
				}
				return primeiroJob.getDataMaxima().compareTo(segundoJob.getDataMaxima());
			}
		});

		// percorre a lista de jobs para ver quais podem ser executados juntos
		for (int i = 0; i < janelaExecucao.getJobs().size(); i++) {

			// precisa calcular o tempo de execução dos jobs de um determinado array
			int tempoJanelaAtual = retornaTempoTotalDaJanela(janelaDeOitoHoras);
			if (janelaExecucao.getJobs().get(i).getHoras() + tempoJanelaAtual <= 8) {
				janelaDeOitoHoras.add(janelaExecucao.getJobs().get(i));

				if (i == janelaExecucao.getJobs().size() - 1) {
					listaFinal.add(janelaDeOitoHoras);
				}

			} else {
				// Adiciono janela na lista final, pq não tem mais espaço de tempo
				listaFinal.add(janelaDeOitoHoras);
				// Ínicio uma nova janelade execução
				janelaDeOitoHoras = new ArrayList<Job>();
				janelaDeOitoHoras.add(janelaExecucao.getJobs().get(i));

				if (i == janelaExecucao.getJobs().size() - 1) {
					listaFinal.add(janelaDeOitoHoras);
				}
			}
		}

		return listaFinal;

	}

	private int retornaTempoTotalDaJanela(List<Job> jobs) {
		int tempoExecucao = 0;
		for (Job job : jobs) {
			tempoExecucao = tempoExecucao + job.getHoras();
		}
		return tempoExecucao;

	}

	private boolean validaTempoDosJobs(JanelaExecucao janelaExecucao) {
		long horasTotais = 0;
		for (Job job : janelaExecucao.getJobs()) {
			horasTotais = horasTotais + job.getHoras();
		}

		if (janelaExecucao.getDataInicio().plusHours(horasTotais).isAfter(janelaExecucao.getDataFim())) {
			return false;
		}

		return true;

	}

}
