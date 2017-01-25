package br.com.caelum.casadocodigo_vinicius.server;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import br.com.caelum.casadocodigo_vinicius.event.LivroEvent;
import br.com.caelum.casadocodigo_vinicius.modelo.Livro;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by android6587 on 24/01/17.
 */

public class WebClient {
    private static final String SERVER_URL = "http://cdcmob.herokuapp.com/";
    private LivroService livroService;

    @Inject
    WebClient(LivroService livroService) {
        this.livroService = livroService;
    }

    public void getLivros(int indicePrimeiroLivro, int qtdLivros) {
//        Retrofit client = new Retrofit.Builder()
//                .baseUrl(SERVER_URL)
//                .addConverterFactory(new LivroServiceConverterFactory())
//                .build();
//
//        LivroService service = client.create(LivroService.class);

        Call<List<Livro>> call = livroService.listaLivros(indicePrimeiroLivro, qtdLivros);
        call.enqueue(new Callback<List<Livro>>() {
            @Override
            public void onResponse(Call<List<Livro>> call, Response<List<Livro>> response) {
                EventBus.getDefault().post(new LivroEvent(response.body()));
            }

            @Override
            public void onFailure(Call<List<Livro>> call, Throwable t) {
                EventBus.getDefault().post(t);
            }
        });
    }
}
