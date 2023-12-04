package com.persistJPHM.sistemapersistencia.DAO;

import java.util.List;
import java.util.Optional;

import com.persistJPHM.sistemapersistencia.entity.Usuario;

public interface UsuarioGeneric {

  public int contaUsuarios();

  public List<Usuario> procureCpfsComTresPrimeirosNumeros(String tresPrimeirosNumeros);

  public List<Usuario> consultaPorLetra(String prefix);

  public List<Usuario> consultaPorNomeEspecifico(String nome);

  public Usuario findFirstByCpf(String cpf);

    public void save(Usuario usu);

    public void deleteByCpf(String cpf);

    public Optional<Usuario> findById(String id);

    public List<Usuario> findAll();
}
