package io.com.instagram.activity;import android.content.Context;import android.content.DialogInterface;import android.content.Intent;import android.graphics.Bitmap;import android.net.Uri;import android.provider.MediaStore;import android.support.v4.content.ContextCompat;import android.support.v4.view.ViewPager;import android.support.v7.app.AppCompatActivity;import android.os.Bundle;import android.app.AlertDialog;import android.app.ProgressDialog;import android.support.v7.widget.Toolbar;import android.view.Menu;import android.view.MenuInflater;import android.view.MenuItem;import android.view.View;import android.widget.Button;import android.widget.EditText;import android.widget.Toast;import com.parse.LogInCallback;import com.parse.ParseException;import com.parse.ParseFile;import com.parse.ParseObject;import com.parse.ParseUser;import com.parse.SaveCallback;import com.parse.SignUpCallback;import java.io.ByteArrayInputStream;import java.io.ByteArrayOutputStream;import java.io.IOException;import java.text.SimpleDateFormat;import java.util.Date;import io.com.instagram.R;import io.com.instagram.adapter.TabAdapter;import io.com.instagram.fragment.HomeFragment;import io.com.instagram.sched.SlidingTabLayout;public class MainActivity extends AppCompatActivity {    private Toolbar toolbar;    private SlidingTabLayout slidingTabLayout;    private ViewPager pager;    @Override    protected void onCreate(Bundle savedInstanceState) {        super.onCreate(savedInstanceState);        setContentView(R.layout.activity_main);        toolbar = (Toolbar) findViewById(R.id.toolbar_main);        toolbar.setLogo(R.drawable.instagramlogo);        setSupportActionBar( toolbar );        //configurando as abas        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.slider_tab_main);        pager = (ViewPager) findViewById(R.id.view_page_main);        //configurando o adapter        TabAdapter adapter = new TabAdapter( getSupportFragmentManager(), this );        pager.setAdapter( adapter );        //config navegação das tab        slidingTabLayout.setCustomTabView(R.layout.tab_view, R.id.item_tab);        slidingTabLayout.setDistributeEvenly(true);        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.cinza));        slidingTabLayout.setViewPager( pager );    }    @Override    public boolean onOptionsItemSelected(MenuItem item) {        switch (item.getItemId()){            case R.id.menu_sair:                //deslogando usuário                deslogandoUser();                return true;            case R.id.menu_compartilhar:                compartilharFoto();                return true;            case R.id.menu_config:                return true;            default:                return super.onOptionsItemSelected(item);        }    }    private void deslogandoUser(){        ParseUser.logOut();        Intent intent = new Intent(this, LoginActivity.class);        startActivity(intent);    }    @Override    protected void onActivityResult(int requestCode, int resultCode, Intent data) {        super.onActivityResult(requestCode, resultCode, data);        //testando o processo de retorno dos dados        if( requestCode == 1 && resultCode == RESULT_OK && data != null ){            //recuperando local do recurso            Uri local = data.getData();            //recuperando a imagem do local que foi selecionada            try {                Bitmap imagem = MediaStore.Images.Media.getBitmap( getContentResolver(), local );                //criando outoputstream                ByteArrayOutputStream stream = new ByteArrayOutputStream();                //cumprimir para png                imagem.compress(Bitmap.CompressFormat.PNG, 75, stream);                //criando um array de bytes                byte[] byteArray = stream.toByteArray();                //convertendo data para ficar como nome da imagem                SimpleDateFormat dataFormatada = new SimpleDateFormat("ddMMyyyyhhmmss");                String nomeImagem = dataFormatada.format( new Date() );                //criando um arquivo próprio do parse                ParseFile arquivoImg = new ParseFile(nomeImagem+"imagem.png", byteArray);                //salvando objetos no parse                ParseObject parseObject = new ParseObject("Imagens");                parseObject.put("username", ParseUser.getCurrentUser().getUsername() );                parseObject.put("imagem", arquivoImg);                //salvar os dados                parseObject.saveInBackground(new SaveCallback() {                    @Override                    public void done(ParseException e) {                        if(e == null){                            Toast.makeText(getApplicationContext(), "Imagem postada", Toast.LENGTH_LONG).show();                            //Atualizando a lista de imagens                            TabAdapter tabAdapter = (TabAdapter) pager.getAdapter();                            HomeFragment homeFragment = (HomeFragment) tabAdapter.getFragment( 0 );                            homeFragment.atualizaPostagem();                        }else{                            Toast.makeText(getApplicationContext(), "Erro ao postar a imagem", Toast.LENGTH_LONG).show();                        }                    }                });            } catch (IOException e) {                e.printStackTrace();            }        }    }    @Override    public boolean onCreateOptionsMenu(Menu menu) {        MenuInflater inflater = getMenuInflater();        inflater.inflate(R.menu.menu, menu);        return true;    }    private void compartilharFoto(){        Intent intent = new Intent( Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI );        startActivityForResult( intent, 1 );    }}