package com.universidade;

import java.util.Arrays;
import java.util.Scanner;
import com.universidade.entity.Pessoa;
import com.universidade.entity.PessoaTomouVacina;
import com.universidade.entity.Vacina;

public class App 
{
    public static void main( String[] args )
    {       
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome do usuário do banco de dados:");
        String usuario = sc.nextLine();
        System.out.print("Digite a senha do banco de dados:");
        String senha = sc.nextLine();
        System.out.print("Digite o nome da base de dados:");
        String nomeBanco = sc.nextLine();
        ConexaoBanco con = new ConexaoBanco(nomeBanco, senha, usuario);
        if(con.conectar()){    

        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n===== MENU PRINCIPAL =====");
            System.out.println("1 - Inserir Pessoa");
            System.out.println("2 - Buscar Pessoa");
            System.out.println("3 - Atualizar Pessoa");
            System.out.println("4 - Remover Pessoa");
            System.out.println("5 - Inserir Vacina");
            System.out.println("6 - Buscar Vacina");
            System.out.println("7 - Atualizar Vacina");
            System.out.println("8 - Remover Vacina");
            System.out.println("9 - Inserir Registro de Vacinação");
            System.out.println("10 - Buscar Registro de Vacinação");
            System.out.println("11 - Atualizar Registro de Vacinação");
            System.out.println("12 - Remover Registro de Vacinação");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // Limpa buffer

            switch (opcao) {
                case 1:
                    inserirPessoa(con, sc);
                    break;
                case 2:
                    buscarPessoa(con, sc);
                    break;
                case 3:
                    atualizarPessoa(con, sc);
                    break;
                case 4:
                    removerPessoa(con, sc);
                    break;
                case 5:
                    inserirVacina(con, sc);
                    break;
                case 6:
                    buscarVacina(con, sc);
                    break;
                case 7: 
                    atualizarVacina(con, sc);
                    break;
                case 8: 
                    removerVacina(con, sc);
                    break;
                case 9:
                    inserirRegistroVacinacao(con, sc);
                    break;
                case 10:
                    buscarRegistroVacinacao(con, sc);
                    break;
                case 11: 
                    atualizarRegistroVacinacao(con, sc);
                    break;
                case 12: 
                    removerRegistroVacinacao(con, sc);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        System.out.println("Fechando conexão com o banco de dados...");
        con.desconectar();
        sc.close();
        System.out.println("Conexão fechada. Programa encerrado.");
    }
        
    }
    public static void inserirPessoa(ConexaoBanco con, Scanner sc) {
    Pessoa p = new Pessoa();
    System.out.print("CPF: ");
    p.setCpf(sc.nextLine());
    System.out.print("Nome: ");
    p.setNome(sc.nextLine());
    System.out.print("Idade: ");
    String idadeStr = sc.nextLine();
    try {
        p.setIdade(idadeStr.isEmpty() ? null : Integer.parseInt(idadeStr));
    } catch (NumberFormatException e) {
        System.out.println("Idade inválida! Deixe em branco ou digite apenas números.");
        p.setIdade(null);
    }
    System.out.print("Endereço: ");
    p.setEndereco(sc.nextLine());
    System.out.print("Telefones (separados por vírgula): ");
    String tels = sc.nextLine();
    p.setTelefone(Arrays.asList(tels.split(",")));
    System.out.print("Estado de moradia: ");
    p.setEstadoMoradia(sc.nextLine());
    System.out.print("Bairro de moradia: ");
    p.setBairroMoradia(sc.nextLine());
    int res = con.inserirPessoa(p);
    System.out.println(res > 0 ? "Pessoa inserida!" : "Erro ao inserir.");
    System.out.println("Digite 1 para continuar");
    sc.nextLine();
}

public static void buscarPessoa(ConexaoBanco con, Scanner sc) {
    System.out.print("CPF da pessoa: ");
    Pessoa buscada = con.buscarPessoa(sc.nextLine());
    if (buscada != null) {
        System.out.println("Nome: " + buscada.getNome());
        System.out.println("Idade: " + buscada.getIdade());
        System.out.println("Endereço: " + buscada.getEndereco());
        System.out.println("Telefones: " + buscada.getTelefone());
        System.out.println("Estado: " + buscada.getEstadoMoradia());
        System.out.println("Bairro: " + buscada.getBairroMoradia());
    } else {
        System.out.println("Pessoa não encontrada.");
    }
    System.out.println("Digite 1 para continuar");
    sc.nextLine();
}

public static void atualizarPessoa(ConexaoBanco con, Scanner sc) {
    System.out.print("CPF da pessoa a atualizar: ");
    String cpfAtualizar = sc.nextLine();
    Pessoa pAtualizar = con.buscarPessoa(cpfAtualizar);
    if (pAtualizar != null) {
        System.out.print("Novo nome (" + pAtualizar.getNome() + "): ");
        String novoNome = sc.nextLine();
        if (!novoNome.isEmpty()) pAtualizar.setNome(novoNome);
        System.out.print("Nova idade (" + pAtualizar.getIdade() + "): ");
        String novaIdade = sc.nextLine();
        if (!novaIdade.isEmpty()) {
            try {
                pAtualizar.setIdade(Integer.parseInt(novaIdade));
            } catch (NumberFormatException e) {
                System.out.println("Idade inválida! Mantendo valor anterior.");
            }
        }
        System.out.print("Novo endereço (" + pAtualizar.getEndereco() + "): ");
        String novoEnd = sc.nextLine();
        if (!novoEnd.isEmpty()) pAtualizar.setEndereco(novoEnd);
        System.out.print("Novos telefones (separados por vírgula) (" + pAtualizar.getTelefone() + "): ");
        String novosTels = sc.nextLine();
        if (!novosTels.isEmpty()) pAtualizar.setTelefone(Arrays.asList(novosTels.split(",")));
        System.out.print("Novo estado (" + pAtualizar.getEstadoMoradia() + "): ");
        String novoEst = sc.nextLine();
        if (!novoEst.isEmpty()) pAtualizar.setEstadoMoradia(novoEst);
        System.out.print("Novo bairro (" + pAtualizar.getBairroMoradia() + "): ");
        String novoBairro = sc.nextLine();
        if (!novoBairro.isEmpty()) pAtualizar.setBairroMoradia(novoBairro);
        int resAt = con.atualizarPessoa(pAtualizar);
        System.out.println(resAt > 0 ? "Pessoa atualizada!" : "Erro ao atualizar.");
    } else {
        System.out.println("Pessoa não encontrada.");
    }
    System.out.println("Digite 1 para continuar");
    sc.nextLine();
}

public static void removerPessoa(ConexaoBanco con, Scanner sc) {
    System.out.print("CPF da pessoa a remover: ");
    int resRem = con.removerPessoa(sc.nextLine());
    System.out.println(resRem > 0 ? "Pessoa removida!" : "Erro ao remover.");
    System.out.println("Digite 1 para continuar");
    sc.nextLine();
}

public static void inserirVacina(ConexaoBanco con, Scanner sc) {
    Vacina v = new Vacina();
    System.out.print("Código da vacina: ");
    v.setCodVacina(sc.nextLine());
    System.out.print("Nome: ");
    v.setNome(sc.nextLine());
    System.out.print("Lote: ");
    v.setLote(sc.nextLine());
    System.out.print("Doses recomendadas: ");
    try {
        v.setDosesRecomendadas(Integer.parseInt(sc.nextLine()));
    } catch (NumberFormatException e) {
        System.out.println("Número de doses inválido! Digite apenas números.");
        v.setDosesRecomendadas(1); // Caso não seja informada a dose recomendada, o padrão será 1
    }
    System.out.print("Intervalo entre doses (dias): ");
    try {
        v.setIntervaloDoses(Integer.parseInt(sc.nextLine()));
    } catch (NumberFormatException e) {
        System.out.println("Número de dias inválido! Digite apenas números.");
        v.setIntervaloDoses(0); // Caso não seja informado o intervalo entre doses, o padrão será 0
    }
    System.out.print("Fabricante: ");
    v.setFabricante(sc.nextLine());
    System.out.print("Registro Anvisa: ");
    v.setRegistroAnvisa(sc.nextLine());
    System.out.print("Tipo: ");
    v.setTipo(sc.nextLine());
    System.out.print("Observações: ");
    v.setObservacoes(sc.nextLine());
    System.out.print("Validade (yyyy-mm-dd): ");
    try {
        v.setValidade(java.sql.Date.valueOf(sc.nextLine()));
    } catch (IllegalArgumentException e) {
        System.out.println("Data inválida! Formato correto: yyyy-mm-dd");
        v.setValidade(null);
    }
    int resVac = con.inserirVacina(v);
    System.out.println(resVac > 0 ? "Vacina inserida!" : "Erro ao inserir vacina.");
    System.out.println("Digite 1 para continuar");
    sc.nextLine();
}

public static void buscarVacina(ConexaoBanco con, Scanner sc) {
    System.out.print("Código da vacina: ");
    Vacina vbusca = con.buscarVacina(sc.nextLine());
    if (vbusca != null) {
        System.out.println("Nome: " + vbusca.getNome());
        System.out.println("Lote: " + vbusca.getLote());
        System.out.println("Doses recomendadas: " + vbusca.getDosesRecomendadas());
        System.out.println("Intervalo: " + vbusca.getIntervaloDoses());
        System.out.println("Fabricante: " + vbusca.getFabricante());
        System.out.println("Registro Anvisa: " + vbusca.getRegistroAnvisa());
        System.out.println("Tipo: " + vbusca.getTipo());
        System.out.println("Observações: " + vbusca.getObservacoes());
        System.out.println("Validade: " + vbusca.getValidade());
    } else {
        System.out.println("Vacina não encontrada.");
    }
    System.out.println("Digite 1 para continuar");
    sc.nextLine();
}

public static void atualizarVacina(ConexaoBanco con, Scanner sc) {
    System.out.print("Código da vacina a atualizar: ");
    String codVacinaAt = sc.nextLine();
    Vacina vacinaAt = con.buscarVacina(codVacinaAt);
    System.out.println("O sistema irá solicitar os novos dados, deixe em branco para manter o valor atual.");
    if (vacinaAt != null) {
        System.out.print("Novo nome (" + vacinaAt.getNome() + "): ");
        String novoNome = sc.nextLine();
        if (!novoNome.isEmpty()) vacinaAt.setNome(novoNome);
        System.out.print("Novo lote (" + vacinaAt.getLote() + "): ");
        String novoLote = sc.nextLine();
        if (!novoLote.isEmpty()) vacinaAt.setLote(novoLote);
        System.out.print("Novas doses recomendadas (" + vacinaAt.getDosesRecomendadas() + "): ");
        String novasDoses = sc.nextLine();
        if (!novasDoses.isEmpty()) {
            try {
                vacinaAt.setDosesRecomendadas(Integer.parseInt(novasDoses));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Mantendo valor anterior.");
            }
        }
        System.out.print("Novo intervalo (" + vacinaAt.getIntervaloDoses() + "): ");
        String novoInt = sc.nextLine();
        if (!novoInt.isEmpty()) {
            try {
                vacinaAt.setIntervaloDoses(Integer.parseInt(novoInt));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Mantendo valor anterior.");
            }
        }
        System.out.print("Novo fabricante (" + vacinaAt.getFabricante() + "): ");
        String novoFab = sc.nextLine();
        if (!novoFab.isEmpty()) vacinaAt.setFabricante(novoFab);
        System.out.print("Novo registro Anvisa (" + vacinaAt.getRegistroAnvisa() + "): ");
        String novoReg = sc.nextLine();
        if (!novoReg.isEmpty()) vacinaAt.setRegistroAnvisa(novoReg);
        System.out.print("Novo tipo (" + vacinaAt.getTipo() + "): ");
        String novoTipo = sc.nextLine();
        if (!novoTipo.isEmpty()) vacinaAt.setTipo(novoTipo);
        System.out.print("Novas observações (" + vacinaAt.getObservacoes() + "): ");
        String novasObs = sc.nextLine();
        if (!novasObs.isEmpty()) vacinaAt.setObservacoes(novasObs);
        System.out.print("Nova validade (yyyy-mm-dd) (" + vacinaAt.getValidade() + "): ");
        String novaVal = sc.nextLine();
        if (!novaVal.isEmpty()) {
            try {
                vacinaAt.setValidade(java.sql.Date.valueOf(novaVal));
            } catch (IllegalArgumentException e) {
                System.out.println("Data inválida! Mantendo valor anterior.");
            }
        }
        int resAtVac = con.atualizarVacina(vacinaAt);
        System.out.println(resAtVac > 0 ? "Vacina atualizada!" : "Erro ao atualizar.");
    } else {
        System.out.println("Vacina não encontrada.");
    }
    System.out.println("Digite 1 para continuar");
    sc.nextLine();
}

public static void removerVacina(ConexaoBanco con, Scanner sc) {
    System.out.print("Código da vacina a remover: ");
    int resRemVac = con.removerVacina(sc.nextLine());
    System.out.println(resRemVac > 0 ? "Vacina removida!" : "Erro ao remover.");
    System.out.println("Digite 1 para continuar");
    sc.nextLine();
}

public static void inserirRegistroVacinacao(ConexaoBanco con, Scanner sc) {
    PessoaTomouVacina ptv = new PessoaTomouVacina();
    System.out.print("CPF da pessoa: ");
    ptv.setPessoaCpf(sc.nextLine());
    System.out.print("Código da vacina: ");
    ptv.setVacinaCodVacina(sc.nextLine());
    System.out.print("Dose: ");
    try {
        ptv.setDose(Integer.parseInt(sc.nextLine()));
    } catch (NumberFormatException e) {
        System.out.println("Dose inválida! Digite apenas números.");
        ptv.setDose(0);
    }
    System.out.print("Data (yyyy-mm-dd): ");
    try {
        ptv.setData(java.sql.Date.valueOf(sc.nextLine()));
    } catch (IllegalArgumentException e) {
        System.out.println("Data inválida! Formato correto: yyyy-mm-dd");
        ptv.setData(null);
    }
    System.out.print("Estado vacinação: ");
    ptv.setEstadoVacina(sc.nextLine());
    System.out.print("Bairro vacinação: ");
    ptv.setBairroVacina(sc.nextLine());
    System.out.print("Unidade: ");
    ptv.setUnidade(sc.nextLine());
    int resPTV = con.inserirPessoaTomouVacina(ptv);
    System.out.println(resPTV > 0 ? "Registro inserido!" : "Erro ao inserir registro.");
    System.out.println("Digite 1 para continuar");
    sc.nextLine();
}

public static void buscarRegistroVacinacao(ConexaoBanco con, Scanner sc) {
    System.out.print("CPF da pessoa: ");
    String cpfBusca = sc.nextLine();
    System.out.print("Código da vacina: ");
    String codVacinaBusca = sc.nextLine();
    PessoaTomouVacina ptvBusca = con.buscarPessoaTomouVacina(cpfBusca, codVacinaBusca);
    if (ptvBusca != null) {
        System.out.println("Dose: " + ptvBusca.getDose());
        System.out.println("Data: " + ptvBusca.getData());
        System.out.println("Estado: " + ptvBusca.getEstadoVacina());
        System.out.println("Bairro: " + ptvBusca.getBairroVacina());
        System.out.println("Unidade: " + ptvBusca.getUnidade());
    } else {
        System.out.println("Registro não encontrado.");
    }
    System.out.println("Digite 1 para continuar");
    sc.nextLine();
}

public static void atualizarRegistroVacinacao(ConexaoBanco con, Scanner sc) {
    System.out.print("CPF da pessoa: ");
    String cpfAt = sc.nextLine();
    System.out.print("Código da vacina: ");
    String codVacAt = sc.nextLine();
    PessoaTomouVacina ptvAt = con.buscarPessoaTomouVacina(cpfAt, codVacAt);
    if (ptvAt != null) {
        System.out.print("Nova dose (" + ptvAt.getDose() + "): ");
        String novaDose = sc.nextLine();
        if (!novaDose.isEmpty()) {
            try {
                ptvAt.setDose(Integer.parseInt(novaDose));
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Mantendo valor anterior.");
            }
        }
        System.out.print("Nova data (yyyy-mm-dd) (" + ptvAt.getData() + "): ");
        String novaData = sc.nextLine();
        if (!novaData.isEmpty()) {
            try {
                ptvAt.setData(java.sql.Date.valueOf(novaData));
            } catch (IllegalArgumentException e) {
                System.out.println("Data inválida! Mantendo valor anterior.");
            }
        }
        System.out.print("Novo estado (" + ptvAt.getEstadoVacina() + "): ");
        String novoEst = sc.nextLine();
        if (!novoEst.isEmpty()) ptvAt.setEstadoVacina(novoEst);
        System.out.print("Novo bairro (" + ptvAt.getBairroVacina() + "): ");
        String novoBairro = sc.nextLine();
        if (!novoBairro.isEmpty()) ptvAt.setBairroVacina(novoBairro);
        System.out.print("Nova unidade (" + ptvAt.getUnidade() + "): ");
        String novaUnidade = sc.nextLine();
        if (!novaUnidade.isEmpty()) ptvAt.setUnidade(novaUnidade);
        int resAtPTV = con.atualizarPessoaTomouVacina(ptvAt);
        System.out.println(resAtPTV > 0 ? "Registro atualizado!" : "Erro ao atualizar.");
    } else {
        System.out.println("Registro não encontrado.");
    }
    System.out.println("Digite 1 para continuar");
    sc.nextLine();
}

public static void removerRegistroVacinacao(ConexaoBanco con, Scanner sc) {
    System.out.print("CPF da pessoa: ");
    String cpfRem = sc.nextLine();
    System.out.print("Código da vacina: ");
    String codVacRem = sc.nextLine();
    int resRemPTV = con.removerPessoaTomouVacina(cpfRem, codVacRem);
    System.out.println(resRemPTV > 0 ? "Registro removido!" : "Erro ao remover.");
    System.out.println("Digite 1 para continuar");
    sc.nextLine();
}

}
