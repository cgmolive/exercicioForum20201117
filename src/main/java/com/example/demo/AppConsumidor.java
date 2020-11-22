package com.example.demo;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;


public class AppConsumidor extends JDialog{
	private JPanel panel = new JPanel();
	private List<Job> jobPendente = new ArrayList();
	private List<Worker> workers = new ArrayList();
	
	protected void criarNovoJob(int tamanho) {
		Job novoJob = new Job(tamanho);
		JobProgressPanel jobProgressPanel = new JobProgressPanel(novoJob);
	}
	
	private static class JobProgressPanel extends JPanel {
		private Job job;
		private int workDone = 0;
		private JProgressBar progressBar;
		
		public JobProgressPanel(Job job) {
			this.progressBar = new JProgressBar(job.getTamanho());
			this.job = job;
			BoxLayout boxLayout = new BoxLayout(this, BoxLayout.LINE_AXIS);
			this.setLayout(boxLayout);
			this.add(progressBar);
		}
	}
	
	private class ControlPanel extends JPanel {
		private JTextField jobSize = new JTextField(20);
		private JButton btnAddJob = new JButton("Adicionar Job");
		private JLabel contadorDeTrabalhadores = new JLabel("0");
		private JButton btnAddWorker = new JButton("Adicionar Trabalhador");
		
		public ControlPanel() {
			btnAddJob.addActionListener(e -> {
				int jobSizeAsInt = Integer.parseInt(jobSize.getText());
				System.out.println(jobSizeAsInt);
				criarNovoJob(jobSizeAsInt);
			});
			btnAddWorker.addActionListener(e -> {
				System.out.println("Novo trabalhador criado!");
				Worker novoWorker = new Worker();
				workers.add(novoWorker);
				novoWorker.start();
			});
			BoxLayout boxLayout = new BoxLayout(this, BoxLayout.LINE_AXIS);
			this.setLayout(boxLayout);
			this.add(jobSize);
			this.add(btnAddJob);
			this.add(contadorDeTrabalhadores);
			this.add(btnAddWorker);
		}
	}
	
	public AppConsumidor() {
		super();
		panel.setBackground(Color.DARK_GRAY);
		BoxLayout boxLayout = new BoxLayout(panel, BoxLayout.Y_AXIS);
		panel.setLayout(boxLayout);
		panel.add(new ControlPanel());
		this.add(panel);
		this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		this.setSize(600, 400);
	}
}
