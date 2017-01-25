package br.com.caelum.casadocodigo_vinicius.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import br.com.caelum.casadocodigo.R;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by android6587 on 25/01/17.
 */

public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_email)
    TextView email;

    @BindView(R.id.login_senha)
    TextView senha;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_logar)
    public void login() {

    }

    @OnClick(R.id.login_novo)
    public void criarUsuario() {

    }
}
