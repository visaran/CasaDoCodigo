package br.com.caelum.casadocodigo_vinicius.component;

import javax.inject.Singleton;

import br.com.caelum.casadocodigo_vinicius.activity.CarrinhoActivity;
import br.com.caelum.casadocodigo_vinicius.activity.MainActivity;
import br.com.caelum.casadocodigo_vinicius.fragment.DetalhesLivroFragment;
import br.com.caelum.casadocodigo_vinicius.component.module.CasaDoCodigoModule;
import br.com.caelum.casadocodigo_vinicius.fragment.ListaLivrosFragment;
import dagger.Component;

/**
 * Created by android6587 on 25/01/17.
 */

@Singleton
@Component(modules = CasaDoCodigoModule.class)
public interface CasaDoCodigoComponent {
    public void inject(ListaLivrosFragment fragment);

    public void inject(CarrinhoActivity activity);

    public void inject(DetalhesLivroFragment fragment);

    void inject(MainActivity mainActivity);
}
