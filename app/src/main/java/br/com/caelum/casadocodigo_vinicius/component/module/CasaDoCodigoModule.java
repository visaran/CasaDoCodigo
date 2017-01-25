package br.com.caelum.casadocodigo_vinicius.component.module;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo_vinicius.converter.LivroServiceConverterFactory;
import br.com.caelum.casadocodigo_vinicius.modelo.Carrinho;
import br.com.caelum.casadocodigo_vinicius.server.LivroService;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by android6587 on 25/01/17.
 */
@Module
public class CasaDoCodigoModule {
    private static final String SERVER_URL = "http://cdcmob.herokuapp.com/";

    @Provides
    @Singleton
    public Carrinho getCarrinho() {
        return new Carrinho();
    }

    @Provides
    @Singleton
    public LivroService getLivroService() {
        Retrofit client = new Retrofit.Builder()
                .baseUrl(SERVER_URL)
                .addConverterFactory(new LivroServiceConverterFactory())
                .build();
        LivroService service = client.create(LivroService.class);
        return service;
    }
}
