package com.universidade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.universidade.entity.*;

public class ConexaoBanco {
    private String nomeBanco;
	private String senha;
	private String nomeUsuario;
	private Connection conexao;
	
	public ConexaoBanco(String nomeBanco, String senha, String nomeUsuario) {
		this.nomeBanco = nomeBanco;
		this.senha = senha;
		this.nomeUsuario = nomeUsuario;
	}
	
	public ConexaoBanco() {
		
	}

	public String getNomeBanco() {
		return nomeBanco;
	}

	public void setNomeBanco(String nomeBanco) {
		this.nomeBanco = nomeBanco;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNomeUsuario() {
		return nomeUsuario;
	}

	public void setNomeUsuario(String nomeUsuario) {
		this.nomeUsuario = nomeUsuario;
	}
	
	public boolean conectar () {
		this.conexao = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conexao = DriverManager.getConnection("jdbc:mysql://localhost:3306/"+this.nomeBanco,this.nomeUsuario,this.senha);
			return true;
		} catch (ClassNotFoundException ex) {
			System.out.println("Driver do banco de dados não localizado.");
		} catch (SQLException e) {
			System.out.println("Erro ao tentar acessar o banco de dados: " + e.getMessage());
		}
		return false;
	}
	
	public void desconectar () {
		if(this.conexao != null) {
		try {
			this.conexao.close();
		} catch (SQLException e) {System.out.println("Erro ao tentar fechar o banco de dados: " + e.getMessage());}
		this.conexao = null;
		}else{System.out.println("Não há conexão para ser encerrada");}
	}

    // CRUD Pessoa
    public int inserirPessoa(Pessoa pessoa) {
        int resultadoOp = -1;
        String sql = "INSERT INTO Pessoa (cpf, nome, idade, endereco, telefone, estado_moradia, bairro_moradia) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, pessoa.getCpf());
            pst.setString(2, pessoa.getNome());
            if (pessoa.getIdade() != null) pst.setInt(3, pessoa.getIdade()); else pst.setNull(3, java.sql.Types.INTEGER);
            pst.setString(4, pessoa.getEndereco());
            pst.setString(5, pessoa.getTelefone() != null ? new com.google.gson.Gson().toJson(pessoa.getTelefone()) : null);
            pst.setString(6, pessoa.getEstadoMoradia());
            pst.setString(7, pessoa.getBairroMoradia());
            resultadoOp = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return resultadoOp;
    }

    public Pessoa buscarPessoa(String cpf) {
        Pessoa pessoa = null;
        String sql = "SELECT * FROM Pessoa WHERE cpf = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, cpf);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                pessoa = new Pessoa();
                pessoa.setCpf(rs.getString("cpf"));
                pessoa.setNome(rs.getString("nome"));
                pessoa.setIdade(rs.getInt("idade"));
                pessoa.setEndereco(rs.getString("endereco"));
                String telefonesJson = rs.getString("telefone");
                if (telefonesJson != null) {
                    java.lang.reflect.Type type = new com.google.gson.reflect.TypeToken<java.util.List<String>>(){}.getType();
                    pessoa.setTelefone(new com.google.gson.Gson().fromJson(telefonesJson, type));
                }
                pessoa.setEstadoMoradia(rs.getString("estado_moradia"));
                pessoa.setBairroMoradia(rs.getString("bairro_moradia"));
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return pessoa;
    }

    public int atualizarPessoa(Pessoa pessoa) {
        int resultadoOp = -1;
        String sql = "UPDATE Pessoa SET nome=?, idade=?, endereco=?, telefone=?, estado_moradia=?, bairro_moradia=? WHERE cpf=?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, pessoa.getNome());
            if (pessoa.getIdade() != null) pst.setInt(2, pessoa.getIdade()); else pst.setNull(2, java.sql.Types.INTEGER);
            pst.setString(3, pessoa.getEndereco());
            pst.setString(4, pessoa.getTelefone() != null ? new com.google.gson.Gson().toJson(pessoa.getTelefone()) : null);
            pst.setString(5, pessoa.getEstadoMoradia());
            pst.setString(6, pessoa.getBairroMoradia());
            pst.setString(7, pessoa.getCpf());
            resultadoOp = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return resultadoOp;
    }

    public int removerPessoa(String cpf) {
        int resultadoOp = -1;
        String sql = "DELETE FROM Pessoa WHERE cpf = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, cpf);
            resultadoOp = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return resultadoOp;
    }

    // CRUD Vacina
    public int inserirVacina(Vacina vacina) {
        int resultadoOp = -1;
        String sql = "INSERT INTO Vacina (cod_vacina, nome, lote, doses_recomendadas, intervalo_doses, fabricante, registro_anvisa, tipo, observacoes, validade) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, vacina.getCodVacina());
            pst.setString(2, vacina.getNome());
            pst.setString(3, vacina.getLote());
            pst.setInt(4, vacina.getDosesRecomendadas());
            pst.setInt(5, vacina.getIntervaloDoses());
            pst.setString(6, vacina.getFabricante());
            pst.setString(7, vacina.getRegistroAnvisa());
            pst.setString(8, vacina.getTipo());
            pst.setString(9, vacina.getObservacoes());
            pst.setDate(10, new java.sql.Date(vacina.getValidade().getTime()));
            resultadoOp = pst.executeUpdate();
            pst.close();
        } catch (NullPointerException e) {
            System.out.println("Erro: data nula ou em formato inválido.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return resultadoOp;
    }

    public Vacina buscarVacina(String codVacina) {
        Vacina vacina = null;
        String sql = "SELECT * FROM Vacina WHERE cod_vacina = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, codVacina);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                vacina = new Vacina();
                vacina.setCodVacina(rs.getString("cod_vacina"));
                vacina.setNome(rs.getString("nome"));
                vacina.setLote(rs.getString("lote"));
                vacina.setDosesRecomendadas(rs.getInt("doses_recomendadas"));
                vacina.setIntervaloDoses(rs.getInt("intervalo_doses"));
                vacina.setFabricante(rs.getString("fabricante"));
                vacina.setRegistroAnvisa(rs.getString("registro_anvisa"));
                vacina.setTipo(rs.getString("tipo"));
                vacina.setObservacoes(rs.getString("observacoes"));
                vacina.setValidade(rs.getDate("validade"));
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return vacina;
    }

    public int atualizarVacina(Vacina vacina) {
        int resultadoOp = -1;
        String sql = "UPDATE Vacina SET nome=?, lote=?, doses_recomendadas=?, intervalo_doses=?, fabricante=?, registro_anvisa=?, tipo=?, observacoes=?, validade=? WHERE cod_vacina=?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, vacina.getNome());
            pst.setString(2, vacina.getLote());
            pst.setInt(3, vacina.getDosesRecomendadas());
            pst.setInt(4, vacina.getIntervaloDoses());
            pst.setString(5, vacina.getFabricante());
            pst.setString(6, vacina.getRegistroAnvisa());
            pst.setString(7, vacina.getTipo());
            pst.setString(8, vacina.getObservacoes());
            pst.setDate(9, new java.sql.Date(vacina.getValidade().getTime()));
            pst.setString(10, vacina.getCodVacina());
            resultadoOp = pst.executeUpdate();
            pst.close();
        } catch (NullPointerException e) {
            System.out.println("Erro: data de validade nula.");
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return resultadoOp;
    }

    public int removerVacina(String codVacina) {
        int resultadoOp = -1;
        String sql = "DELETE FROM Vacina WHERE cod_vacina = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, codVacina);
            resultadoOp = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return resultadoOp;
    }

    // CRUD Pessoa_tomou_Vacina
    public int inserirPessoaTomouVacina(PessoaTomouVacina ptv) {
        int resultadoOp = -1;
        String sql = "INSERT INTO Pessoa_tomou_Vacina (Pessoa_cpf, Vacina_cod_vacina, dose, data, estado_vacina, bairro_vacina, unidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, ptv.getPessoaCpf());
            pst.setString(2, ptv.getVacinaCodVacina());
            pst.setInt(3, ptv.getDose());
            pst.setDate(4, new java.sql.Date(ptv.getData().getTime()));
            pst.setString(5, ptv.getEstadoVacina());
            pst.setString(6, ptv.getBairroVacina());
            pst.setString(7, ptv.getUnidade());
            resultadoOp = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro : " + e.getMessage());
        }
        return resultadoOp;
    }

    public PessoaTomouVacina buscarPessoaTomouVacina(String cpf, String codVacina) {
        PessoaTomouVacina ptv = null;
        String sql = "SELECT * FROM Pessoa_tomou_Vacina WHERE Pessoa_cpf = ? AND Vacina_cod_vacina = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.setString(2, codVacina);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                ptv = new PessoaTomouVacina();
                ptv.setPessoaCpf(rs.getString("Pessoa_cpf"));
                ptv.setVacinaCodVacina(rs.getString("Vacina_cod_vacina"));
                ptv.setDose(rs.getInt("dose"));
                ptv.setData(rs.getDate("data"));
                ptv.setEstadoVacina(rs.getString("estado_vacina"));
                ptv.setBairroVacina(rs.getString("bairro_vacina"));
                ptv.setUnidade(rs.getString("unidade"));
            }
            rs.close();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return ptv;
    }

    public int atualizarPessoaTomouVacina(PessoaTomouVacina ptv) {
        int resultadoOp = -1;
        String sql = "UPDATE Pessoa_tomou_Vacina SET dose=?, data=?, estado_vacina=?, bairro_vacina=?, unidade=? WHERE Pessoa_cpf=? AND Vacina_cod_vacina=?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setInt(1, ptv.getDose());
            pst.setDate(2, new java.sql.Date(ptv.getData().getTime()));
            pst.setString(3, ptv.getEstadoVacina());
            pst.setString(4, ptv.getBairroVacina());
            pst.setString(5, ptv.getUnidade());
            pst.setString(6, ptv.getPessoaCpf());
            pst.setString(7, ptv.getVacinaCodVacina());
            resultadoOp = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return resultadoOp;
    }

    public int removerPessoaTomouVacina(String cpf, String codVacina) {
        int resultadoOp = -1;
        String sql = "DELETE FROM Pessoa_tomou_Vacina WHERE Pessoa_cpf = ? AND Vacina_cod_vacina = ?";
        try {
            PreparedStatement pst = conexao.prepareStatement(sql);
            pst.setString(1, cpf);
            pst.setString(2, codVacina);
            resultadoOp = pst.executeUpdate();
            pst.close();
        } catch (SQLException e) {
            System.out.println("Erro: " + e.getMessage());
        }
        return resultadoOp;
    }

}
