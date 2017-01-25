package br.com.caelum.casadocodigo_vinicius.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import br.com.caelum.casadocodigo.R;
import br.com.caelum.casadocodigo_vinicius.application.CasaDoCodigoApplication;
import br.com.caelum.casadocodigo_vinicius.infra.Infra;
import br.com.caelum.casadocodigo_vinicius.modelo.Autor;
import br.com.caelum.casadocodigo_vinicius.modelo.Carrinho;
import br.com.caelum.casadocodigo_vinicius.modelo.Item;
import br.com.caelum.casadocodigo_vinicius.modelo.Livro;
import br.com.caelum.casadocodigo_vinicius.modelo.TipoDeCompra;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android6587 on 23/01/17.
 */

public class DetalhesLivroFragment extends Fragment {
    private Livro livro;

    @BindView(R.id.detalhes_livro_foto)
    ImageView foto;

    @BindView(R.id.detalhes_livro_nome)
    TextView nome;

    @BindView(R.id.detalhes_livro_autores)
    TextView autores;

    @BindView(R.id.detalhes_livro_descricao)
    TextView descricao;

    @BindView(R.id.detalhes_livro_num_paginas)
    TextView numPaginas;

    @BindView(R.id.detalhes_livro_isbn)
    TextView isbn;

    @BindView(R.id.detalhes_livro_data_publicacao)
    TextView dataPublicacao;

    @BindView(R.id.detalhes_livro_comprar_fisico)
    Button comprarFisico;

    @BindView(R.id.detalhes_livro_comprar_ambos)
    Button comprarAmbos;

    @BindView(R.id.detalhes_livro_comprar_ebook)
    Button comprarEbook;

    @Inject
    Carrinho carrinho;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalhes_livro, container, false);
        ButterKnife.bind(this, view);

        Infra.colocaBotaoBack((AppCompatActivity) getActivity());

        Bundle arguments = getArguments();
        livro = (Livro) arguments.getSerializable("livro");
        populaCamposCom(livro);

        CasaDoCodigoApplication app = (CasaDoCodigoApplication) getActivity().getApplication();
        app.getComponent().inject(this);

        return view;
    }

    private void populaCamposCom(Livro livro) {
        nome.setText(livro.getNome());

        String listaDeAutores = "";
        for (Autor autor : livro.getAutores()) {
            if(!listaDeAutores.isEmpty()) {
                listaDeAutores += ", ";
            }
            listaDeAutores += autor.getNome();
        }
        autores.setText(listaDeAutores);
        descricao.setText(livro.getDescricao());
        numPaginas.setText(String.valueOf(livro.getNumPaginas()));
        isbn.setText(livro.getISBN());
        dataPublicacao.setText(livro.getDataPublicacao());

        String textoComprarFisico = String.format("Comprar livro físico - R$ %.2f", livro.getValorFisico());
        comprarFisico.setText(textoComprarFisico);
        String textoComprarEbook = String.format("Comprar EBook - R$ %.2f", livro.getValorVirtual());
        comprarEbook.setText(textoComprarEbook);
        String textoComprarAmbos = String.format("Comprar ambos - R$ %.2f", livro.getValorDoisJuntos());
        comprarAmbos.setText(textoComprarAmbos);

        Picasso.with(getContext()).load(livro.getUrlFoto())
                .placeholder(R.drawable.livro).into(foto);
    }

    @OnClick(R.id.detalhes_livro_comprar_fisico)
    public void comprarFisico() {
        Toast.makeText(getActivity(), "Livro adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
        carrinho.adiciona(new Item(livro, TipoDeCompra.FISICO));
    }

    @OnClick(R.id.detalhes_livro_comprar_ebook)
    public void comprarEBook() {
        Toast.makeText(getActivity(), "Livro adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
        carrinho.adiciona(new Item(livro, TipoDeCompra.VIRTUAL));
    }

    @OnClick(R.id.detalhes_livro_comprar_ambos)
    public void comprarAmbos() {
        Toast.makeText(getActivity(), "Livro adicionado ao carrinho!", Toast.LENGTH_SHORT).show();
        carrinho.adiciona(new Item(livro, TipoDeCompra.JUNTOS));
    }

}
