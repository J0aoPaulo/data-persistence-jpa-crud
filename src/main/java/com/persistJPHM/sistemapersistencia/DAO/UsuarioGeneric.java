package com.persistJPHM.sistemapersistencia.DAO;

import java.util.List;

import com.persistJPHM.sistemapersistencia.entity.Usuario;

public interface UsuarioGeneric {

  public int contaUsuarios();

  public List<Usuario> procureCpfsComTresPrimeirosNumeros(String tresPrimeirosNumeros);

  public List<Usuario> consultaPorLetra(String prefix);

  public List<Usuario> consultarUsuarioEntreId(String idInicial, String idFinal);

  public List<Usuario> consultaPorNomeEspecifico(String nome);

  public Usuario findFirstByCpf(String cpf);

    public void save(Usuario usu);

    public void deleteById(String id);

    public Usuario findById(String id);

    public List<Usuario> findAll();
}
