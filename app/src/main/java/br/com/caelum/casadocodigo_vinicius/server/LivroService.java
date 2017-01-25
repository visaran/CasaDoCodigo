package br.com.caelum.casadocodigo_vinicius.server;

import java.util.List;

import br.com.caelum.casadocodigo_vinicius.modelo.Livro;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by android6587 on 24/01/17.
 */

public interface LivroService {
    @GET("listarLivros")
    Call<List<Livro>> listaLivros(@Query("indicePrimeiroLivro") int indicePrimeiroLivro,
                                  @Query("qtdLivros") int qtdLivros);
}
