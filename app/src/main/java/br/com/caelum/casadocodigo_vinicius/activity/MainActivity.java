package br.com.caelum.casadocodigo_vinicius.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo_vinicius.application.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo_vinicius.delegate.LivrosDelegate;
import br.com.caelum.casadocodigo_vinicius.event.LivroEvent;
import br.com.caelum.casadocodigo_vinicius.fragment.DetalhesLivroFragment;
import br.com.caelum.casadocodigo_vinicius.fragment.ListaLivrosFragment;
import br.com.caelum.casadocodigo_vinicius.modelo.Livro;
import br.com.caelum.casadocodigo_vinicius.server.WebClient;

public class MainActivity extends AppCompatActivity implements LivrosDelegate {

    private ListaLivrosFragment listaLivrosFragment;
    @Inject
    WebClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CasaDoCodigoApplication application = (CasaDoCodigoApplication) getApplication();
        application.getComponent().inject(this);

        if(savedInstanceState == null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            listaLivrosFragment = new ListaLivrosFragment();
            transaction.replace(R.id.frame_principal, listaLivrosFragment);
            transaction.commit();

            client.getLivros(0, 10);

        }

        EventBus.getDefault().register(this);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
            case R.id.vai_para_carinho:
                Intent vaiParaCarrinho = new Intent(this, CarrinhoActivity.class);
                startActivity(vaiParaCarrinho);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void lidaComLivroSelecionado(Livro livro) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        DetalhesLivroFragment detalhesLivroFragment = criaDetalhesCom(livro);
        transaction.replace(R.id.frame_principal, detalhesLivroFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Subscribe
    public void lidaComSucesso(LivroEvent livroEvent) {
        listaLivrosFragment.populaListaCom(livroEvent.getLivros());
    }

    @Subscribe
    public void lidaComErro(Throwable t) {
        Toast.makeText(this, "Não foi possível carregar os livros", Toast.LENGTH_SHORT).show();
    }

    private DetalhesLivroFragment criaDetalhesCom(Livro livro) {
        Bundle bundle = new Bundle();
        bundle.putSerializable("livro", livro);

        DetalhesLivroFragment detalhesLivroFragment = new DetalhesLivroFragment();
        detalhesLivroFragment.setArguments(bundle);
        return detalhesLivroFragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
}