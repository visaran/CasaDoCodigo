package br.com.caelum.casadocodigo_vinicius.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo_vinicius.adapter.ItensAdapter;
import br.com.caelum.casadocodigo_vinicius.application.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo_vinicius.modelo.Carrinho;
import br.com.caelum.casadocodigo_vinicius.modelo.Item;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by android6587 on 25/01/17.
 */

public class CarrinhoActivity extends AppCompatActivity {
    @BindView(R.id.lista_itens_carrinho)
    RecyclerView listaItens;

    @BindView(R.id.valor_carrinho)
    TextView valorCarrinho;

    @Inject
    Carrinho carrinho;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrinho);

        ButterKnife.bind(this);

        CasaDoCodigoApplication app = (CasaDoCodigoApplication) getApplication();
        app.getComponent().inject(this);
    }

    public void carregaLista() {
        listaItens.setAdapter(new ItensAdapter(carrinho.getItens(), this));
        listaItens.setLayoutManager(new LinearLayoutManager(this));


        double total = 0;
        for(Item item: carrinho.getItens()) {
            total += item.getValor();
        }
        valorCarrinho.setText("R$ " + total);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregaLista();
    }
}
