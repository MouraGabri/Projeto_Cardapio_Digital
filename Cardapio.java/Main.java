import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        int escolha;
        String[][] cadastroUsuario = new String[1][4]; // Matriz para cadastrar os usuários
        String[][] restaurantes = {
                { "Paladar", "7895446", "Marmitas", "R$20,00" },
                { "Primavera", "4547812", "Pizzas", "R$70,00" }
        };
        do {
            System.out.println("\n-----Bem vindo-----\n" + "Escolha uma das opções que deseja");
            System.out.print("Opção 1 --> Realize seu cadastro\n" +
                    "Opção 2 --> Fazer pedido\n" +
                    "Opção 3 --> Efetuar pagamento\n" +
                    "Opção 4 --> Acompanhar pedido\n" +
                    "Opção 5 --> Digite 0 para sair do programa\n" + "-->");
            escolha = ler.nextInt();
            switch (escolha) {

                case 1:
                    realizarCadastro(cadastroUsuario, ler);
                    break;
                case 2:
                    listarRestaurantes(restaurantes, ler);
                    break;
                case 3:
                    fazerPagamento(restaurantes, ler, null, null);
                    break;
                case 4:
                    acompanharPedido(null, null, null, null);
                    break;
                default:
                    break;
            }

        } while (escolha != 0);

        System.out.println("Programa Encerrado");

    }

    public static void realizarCadastro(String[][] cadastroUsuario, Scanner ler) {
        System.out.println("-----------------------------------------------------");
        System.out.println("===Realize seu cadastro===");

        Random random = new Random();
        int senhaAleatoria = random.nextInt(10000);

        // Consumir a quebra de linha deixada após o número lido
        ler.nextLine();

        System.out.print("Nome:");
        cadastroUsuario[0][0] = ler.nextLine();

        System.out.print("Idade:");
        cadastroUsuario[0][1] = ler.nextLine();

        System.out.print("CPF:");
        cadastroUsuario[0][2] = ler.nextLine();

        System.out.println("Sua senha é:" + senhaAleatoria);
        cadastroUsuario[0][3] = String.valueOf(senhaAleatoria);

        loginUsuario(cadastroUsuario, ler);
    }

    public static void loginUsuario(String[][] cadastroUsuario, Scanner ler) {
        System.out.println("-----------------------------------------------------");
        System.out.println("===Faça seu login===");
        int maxTentativa = 2;
        int tentativa = 0;
        boolean loginSucesso = false;

        while (!loginSucesso && tentativa < maxTentativa) {
            System.out.print("Informe seu nome:");
            String nome = ler.nextLine();

            System.out.print("Informe sua senha:");
            String senha = ler.nextLine();

            for (int i = 0; i < cadastroUsuario.length; i++) {
                if (nome.equalsIgnoreCase(cadastroUsuario[i][0]) && senha.equalsIgnoreCase(cadastroUsuario[i][3])) {
                    System.out.println("==Seja bem vindo== " + nome);
                    loginSucesso = true; // Defina como true para sair do loop
                    break; // Saia do loop de verificação
                }
            }

            if (!loginSucesso) {
                System.out.println("-----------------------------------------------------");
                System.out.println("xxxxUsuário não encontradoxxxx\n" + ("Informe seus dados"));
                tentativa++; // Incrementa o contador de tentativas

                if (tentativa < maxTentativa) {
                    System.out.println("Tentativa " + tentativa + " de " + maxTentativa);
                } else {
                    System.out.println("Número máximo de tentativas excedido. Refaça seu cadastro.");
                }
            }
        }

        if (loginSucesso) {

        }
    }

    public static String listarRestaurantes(String[][] Restaurantes, Scanner ler) {
        String valorTotalRestaurante = null; // Inicialize aqui
        String[][] pratosRestaurantes = new String[][] {
                { "Arroz", "Feijão", "Ovo", "Fritas", "Salada" },
                { "Calabresa", "Coração", "4 Queijos", "Strogonof", "Camarão" },
        };

        System.out.println("-----------------------------------------------------");
        System.out.println("\n                 ===Restaurantes===\n"
                + "    Nome         CNPJ          Pratos        Valores\n");

        for (int i = 0; i < Restaurantes.length; i++) {
            for (int j = 0; j < Restaurantes[i].length; j++) {
                String formattedValue = String.format("| %-12s", Restaurantes[i][j]);
                System.out.print(formattedValue);
            }
            System.out.println("|");
        }

        int tentativa = 0;
        int tentativaMax = 2;
        boolean restauranteEncontrado = false;

        while (!restauranteEncontrado && tentativa < tentativaMax) {
            System.out.print("\nEscolha o nome do restaurante:\n" + ("-->"));
            String nomeRestaurante = ler.next();
            ler.nextLine();

            for (int i = 0; i < Restaurantes.length; i++) {
                if (nomeRestaurante.equalsIgnoreCase(Restaurantes[i][0])) {
                    System.out.println("-->Restaurante: " + Restaurantes[i][0]);

                    System.out.println(" Cardápio:");
                    for (String prato : pratosRestaurantes[i]) {
                        System.out.println("- " + prato);
                    }
                    System.out.println("Valor total: " + Restaurantes[i][3]);
                    nomeRestaurante = Restaurantes[i][0];
                    valorTotalRestaurante = Restaurantes[i][3];

                    restauranteEncontrado = true;
                    System.out.println("     ===Realize o pagamento para  prosseguir seu atendimento===");
                    System.out.println("-----------------------------------------------------");
                    fazerPagamento(Restaurantes, ler, valorTotalRestaurante, nomeRestaurante);

                    break;

                }

            }

            if (!restauranteEncontrado) {
                tentativa++;
                if (tentativa < tentativaMax) {
                    System.out.println("Restaurante não encontrado.");
                    System.out.println("Tentativa " + tentativa + " de " + tentativaMax);
                } else {
                    System.out.println("===Número máximo de tentativas excedido. Saindo do menu de escolha===");
                }
            }
        }

        return " ";
    }

    public static void fazerPagamento(String[][] Restaurantes, Scanner ler, String valorTotalRestaurante,
            String nomeRestauranteEscolhido) {
        int escolhaUsuario;

        System.out.println("          Preencha os campos para realizar seu Pedido");
        System.out.print("--> Bairro:");
        String endereco = ler.nextLine();
        System.out.print("--> Rua/N \u00BA:");
        String rua = ler.nextLine();
        System.out.println("-----------------------------------------------------");

        System.out.print("    ===Escolha a forma de pagamento===\n" + "[1]--> Débito\n" + "[2]--> Crédito\n" + ("-->"));

        escolhaUsuario = ler.nextInt();

        if (valorTotalRestaurante != null) {
            if (escolhaUsuario == 1 || escolhaUsuario == 2) {
                // Lógica para pagamento com débito ou crédito
                System.out.println("Sua compra é: R$" + valorTotalRestaurante);
                System.out.print("Informe sua senha:");
                int senha = ler.nextInt();
                System.out.println("Verificando Pagamento....");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Compra aprovada!\n" + ("         ===Emitindo sua nota fiscal==="));
                System.out.println("-----------------------------------------------------");

            }
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            acompanharPedido(nomeRestauranteEscolhido, valorTotalRestaurante, endereco, rua);

        }

    }

    public static void acompanharPedido(String valorTotalRestaurante, String nomeRestaurante, String endereco,
            String rua) {
        System.out.println("===== Nota Fiscal =====");
        System.out.println("Valor total: " + nomeRestaurante);
        System.out.println("Restaurante: " + valorTotalRestaurante);
        System.out.println("Bairro: " + endereco);
        System.out.println("Rua/Nº: " + rua);
        System.out.println("Tempo estimado:" + ("40 min"));
        System.out.println("=======================");
    }
}