package Projeto_int;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.util.Scanner;

public class ProjetoIntegrado1_Boletim {
	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);

		System.out.print("Nome da Turma: ");
		String turma = scn.nextLine();

		System.out.print("Unidade curricular: ");
		String unidade = scn.nextLine();

		System.out.print("Professor: ");
		String professor = scn.nextLine();

		System.out.print("Quantos alunos? ");
		int numAlunos = scn.nextInt();
		scn.nextLine(); // Limpeza do buffer

		String[] nomes = new String[numAlunos]; // Array para armazenar os nomes dos alunos
		double[] medias = new double[numAlunos]; // Array para armazenar as médias dos alunos

		// Loop para pegar os dados de cada aluno
		for (int j = 0; j < numAlunos; j++) {
			System.out.print("\nDigite o nome do aluno: ");
			nomes[j] = scn.nextLine(); // Armazena o nome do aluno

			int[] notas = new int[4]; // Array para armazenar as notas dos 4 bimestres

			// Loop para pedir as notas de cada bimestre
			for (int i = 0; i < 4; i++) {
				while (true) {
					System.out.print("Digite a nota do " + (i + 1) + "º Bimestre (0 a 100): ");
					notas[i] = scn.nextInt();
					if (notas[i] >= 0 && notas[i] <= 100) {
						break; // Se a nota for válida, sai do loop
					} else {
						System.out.println("Nota inválida! ");
					}
				}
			}

			// Cálculo da média das notas
			double media = (notas[0] + notas[1] + notas[2] + notas[3]) / 4.0;
			medias[j] = media; // Armazena a média do aluno na lista de médias
			System.out.printf("Média das notas: %.2f%n", media);

			int faltas;
			while (true) {
				System.out.print("Digite o número de faltas (0 a 10): ");
				faltas = scn.nextInt();
				if (faltas >= 0 && faltas <= 10) {
					break;
				} else {
					System.out.println("Número de faltas inválido! Deve ser entre 0 e 10.");
				}
			}

			// Verificando a situação do aluno
			if (media >= 70 && faltas <= 2) {
				System.out.println("Aluno aprovado! ");
			} else if (media >= 50 && media < 70 && faltas <= 5) {
				System.out.println("Aluno de recuperação! ");
			} else {
				System.out.println("Aluno reprovado! ");
			}

			scn.nextLine(); // Limpeza do buffer
		}

		// Cálculo da média geral da turma e estatísticas
		double somaMedia = 0;
		int acimaMedia = 0;
		int abaixoMedia = 0;

		double maiorMedia = medias[0];
		double menorMedia = medias[0];
		String melhorAluno = nomes[0];

		for (int x = 0; x < medias.length; x++) {
			somaMedia += medias[x];

			if (medias[x] > maiorMedia) {
				maiorMedia = medias[x];
				melhorAluno = nomes[x];
			}

			if (medias[x] < menorMedia) {
				menorMedia = medias[x];
			}
		}

		double mediaGeral = somaMedia / numAlunos;
		for (double media : medias) {
			if (media > mediaGeral) {
				acimaMedia++;
			} else if (media < mediaGeral) {
				abaixoMedia++;
			}
		}

		// Informações finais
		System.out.printf("\nMédia geral da turma: %.2f%n", mediaGeral);
		System.out.println("Alunos acima da média geral: " + acimaMedia);
		System.out.println("Alunos abaixo da média geral: " + abaixoMedia);
		System.out.printf("Maior média final: %.2f (%s)%n", maiorMedia, melhorAluno);
		System.out.printf("Menor média final: %.2f%n", menorMedia);

		// Salva os dados em um arquivo de texto
		try (PrintWriter writer = new PrintWriter(new FileWriter("boletim.txt"))) {
			writer.printf("BOLETIM ESCOLAR - %s\n", turma);
			writer.printf("Unidade curricular: %s\n", unidade);
			writer.printf("Professor: %s\n", professor);

			for (int i = 0; i < numAlunos; i++) {
				writer.printf("Nome: %s\nMédia: %.2f\n", nomes[i], medias[i]);
			}

			System.out.println("Boletim escolar salvo com sucesso! ");
		} catch (IOException e) {
			System.out.println("Problemas ao salvar o boletim: " + e.getMessage());
		
		}

		scn.close();

	}

}
