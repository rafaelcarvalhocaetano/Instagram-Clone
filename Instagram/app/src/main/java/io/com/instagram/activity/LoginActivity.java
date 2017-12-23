package io.com.instagram.activity;import android.content.Intent;import android.os.Bundle;import android.support.annotation.Nullable;import android.support.v7.app.AppCompatActivity;import android.view.View;import android.widget.Button;import android.widget.EditText;import android.widget.TextView;import android.widget.Toast;import com.parse.LogInCallback;import com.parse.Parse;import com.parse.ParseException;import com.parse.ParseUser;import io.com.instagram.R;import io.com.instagram.util.ParseErro;/** * Created by rafaelcarvalho on 23/12/2017. */public class LoginActivity extends AppCompatActivity {    private TextView text_cadastro;    private EditText email;    private EditText senha;    private Button btn_logar;    private String user;    private String pass;    //deixando a mensagem de erro global    ParseErro parseErro = new ParseErro();    @Override    protected void onCreate(@Nullable Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_login);        text_cadastro = (TextView) findViewById(R.id.text_cadastro);        email = (EditText) findViewById(R.id.edit_logar_nome);        senha = (EditText) findViewById(R.id.edit_logar_senha);        btn_logar = (Button) findViewById(R.id.btn_logar);        //ParseUser.logOut();        //verificando usuário logado        verificandoUserLogado();        btn_logar.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                user = email.getText().toString();                pass = senha.getText().toString();                logar(user, pass);            }        });        text_cadastro.setOnClickListener(new View.OnClickListener() {            @Override            public void onClick(View view) {                Intent i = new Intent(LoginActivity.this, CadastroActivity.class);                startActivity(i);                finish();            }        });    }    private void verificandoUserLogado(){        if(ParseUser.getCurrentUser() != null ){            openWindowsLogin();        }    }    private void logar(String user, String pass){        ParseUser.logInInBackground(user, pass, new LogInCallback() {            @Override            public void done(ParseUser user, ParseException e) {                if(e == null ){                    //sucesso                    Toast.makeText(LoginActivity.this, "Logado com sucesso", Toast.LENGTH_LONG).show();                    openWindowsLogin();                }else{                    String erro = parseErro.getErro( e.getCode() );                    Toast.makeText(LoginActivity.this, erro, Toast.LENGTH_LONG).show();                    e.printStackTrace();                }            }        });    }    private void openWindowsLogin(){        Intent intent = new Intent(LoginActivity.this, MainActivity.class);        startActivity( intent );    }}