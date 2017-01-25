package br.com.caelum.casadocodigo_vinicius.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo_vinicius.adapter.LivroAdapter;
import br.com.caelum.casadocodigo_vinicius.application.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo_vinicius.component.CasaDoCodigoComponent;
import br.com.caelum.casadocodigo_vinicius.endless_list.EndlessListListener;
import br.com.caelum.casadocodigo_vinicius.infra.Infra;
import br.com.caelum.casadocodigo_vinicius.modelo.Livro;
import br.com.caelum.casadocodigo_vinicius.server.WebClient;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by android6587 on 23/01/17.
 */

public class ListaLivrosFragment extends Fragment {
    private ArrayList<Livro> livros = new ArrayList<>();

    private WebClient webClient;

    @Inject
    public void setWebClient(WebClient webClient) {
        this.webClient = webClient;
    }

    @BindView(R.id.lista_livros) RecyclerView recyclerView;

    @BindView(R.id.include_carregando)
    View include_carregando;

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("lista", livros);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CasaDoCodigoApplication app = (CasaDoCodigoApplication) getActivity().getApplication();
        CasaDoCodigoComponent component = app.getComponent();
        component.inject(this);

        if(savedInstanceState != null && savedInstanceState.getSerializable("lista") != null) {
            livros = (ArrayList<Livro>) savedInstanceState.getSerializable("lista");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup group, Bundle savedIntasnceState) {
        View view = inflater.inflate(R.layout.fragment_lista_livros, group, false);

        ButterKnife.bind(this, view);


        recyclerView.setAdapter(new LivroAdapter(livros));
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()) );

        recyclerView.addOnScrollListener(new EndlessListListener() {
            @Override
            public void carregaMaisItens() {
//                new WebClient().getLivros(livros.size(), 10);
                webClient.getLivros(livros.size(), 10);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Infra.retiraBotaoBack((AppCompatActivity) getActivity());
        removeInclude();
    }

    private void removeInclude() {
        if(!livros.isEmpty()) {
            include_carregando.setVisibility(View.GONE);
        }
    }

    public void populaListaCom(List<Livro> livros) {
        this.livros.addAll(livros);
        recyclerView.getAdapter().notifyDataSetChanged();
        removeInclude();
    }
}
