import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Tarefa> tarefa = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int opcao = 0;

        try {
            carregarTarefasDoArquivo(tarefa);
        } catch (IOException e) {
            System.out.println("Nenhuma tarefa carregada (talvez o arquivo ainda não exista).");
        }


        while(opcao != 4) {

            System.out.println("=== GERENCIADOR DE TAREFAS ===");
            System.out.println("1 - Adicionar nova tarefa");
            System.out.println("2 - Listar tarefas");
            System.out.println("3 - Marcar tarefa como concluída");
            System.out.println("4 - Sair");
            System.out.println("Escolha uma opção: ");
            opcao = scanner.nextInt();

            if (opcao == 1) {
                scanner.nextLine();
                System.out.println("Digite o titulo da tarefa: ");
                String titulo = scanner.nextLine();
                System.out.println("Digite a descrição da tarefa:");
                String descricao = scanner.nextLine();

                System.out.println("Escolha o estado: 1 - PENDENTE, 2 - EM_ANDAMENTO, 3 - CONCLUIDA");
                int estadoOpcao = scanner.nextInt();
                EstadoTarefa estado = null;

                if (estadoOpcao == 1) {
                    estado = EstadoTarefa.PENDENTE;
                } else if (estadoOpcao == 2) {
                    estado = EstadoTarefa.EM_ANDAMENTO;
                } else if (estadoOpcao == 3) {
                    estado = EstadoTarefa.CONCLUIDA;
                }

                Tarefa novaTarefa = new Tarefa(titulo, descricao, estado);
                tarefa.add(novaTarefa);
                System.out.println("Tarefa adicionada com sucesso!");

                try {
                    salvarTarefaEmArquivo(novaTarefa);
                } catch (IOException e) {
                    System.out.println("Erro ao salvar no arquivo: " + e.getMessage());
                }
            }
            if (opcao == 2) {

                if (tarefa.isEmpty()) {
                    System.out.println("Nenhuma tarefa encontrada.");
                }
                else {
                    for (int i = 0; i < tarefa.size(); i++) {
                        Tarefa t = tarefa.get(i);
                        System.out.println("Tarefa " + (i+1) + ": ");
                        System.out.println("Titulo: " + t.getTitulo());
                        System.out.println("Descricão: " + t.getDescricao());
                        System.out.println("Estado: " + t.getEstado());
                        System.out.println("-------------------");
                    }
                }

            }
            if (opcao == 3) {
                if (tarefa.isEmpty()) {
                    System.out.println("Nenhuma tarefa encontrada.");
                } else {
                    boolean temTarefaParaConcluir = false;

                    for (int i = 0; i<tarefa.size(); i++) {
                        Tarefa t = tarefa.get(i);
                        if (t.getEstado() != EstadoTarefa.CONCLUIDA) {
                            System.out.println((i+1) + " - " + t.getTitulo() + " (" + t.getEstado() + ")");
                        }
                    }

                    if (temTarefaParaConcluir) {
                        System.out.println("Digite o número da tarefa que deseja concluir:");
                        int numero = scanner.nextInt();

                        Tarefa tSelecionado = tarefa.get(numero-1);
                        tSelecionado.setEstado(EstadoTarefa.CONCLUIDA);
                        System.out.println("Tarefa marcada como concluida!");
                    } else {
                        System.out.println("Nenhuma tarefa pendente encontrada.");
                    }

                }
            }
            if (opcao == 4) {
                System.out.println("Fechando o programa!");
                break;
            }

        }
    }

    public static void salvarTarefaEmArquivo(Tarefa tarefa) throws IOException {
        BufferedWriter bw = new BufferedWriter(new FileWriter("tarefas.txt", true));
        bw.write(tarefa.getTitulo() + "|" + tarefa.getDescricao() + "|" + tarefa.getEstado());
        bw.newLine();
        bw.close();
    }

    public static void carregarTarefasDoArquivo(ArrayList<Tarefa> tarefas) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("tarefas.txt"));
        String linha;

        while ((linha = br.readLine()) != null) {
            String[] partes = linha.split("\\|");
            EstadoTarefa estado = EstadoTarefa.valueOf(partes[2]);
            Tarefa tarefaLida = new Tarefa(partes[0], partes[1], estado);
            tarefas.add(tarefaLida);
        }

        br.close();

    }
}



