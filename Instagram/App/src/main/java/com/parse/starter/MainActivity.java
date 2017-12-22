/*
 * Copyright (c) 2015-present, Parse, LLC.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */
package com.parse.starter;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;

import java.util.List;


public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

        /*
      ParseObject pontuacao = new ParseObject("Teste");
      pontuacao.put("nome", "Rose");
      pontuacao.put("pontos", 150 );
      pontuacao.saveInBackground(new SaveCallback() {
          @Override
          public void done(ParseException e) {
              if( e == null ){//não temos erros, pois o objeto está nulo
                  Log.i("salvarPontos", "Dados salvos com sucesso");
              }else {
                  Log.i("salvarPontos", "Erro ao salvar os dados");
              }
          }
      });


        //update de dados
      ParseQuery<ParseObject> p = ParseQuery.getQuery("Teste");
      p.getInBackground("cfl5DycEQb", new GetCallback<ParseObject>() {
          @Override
          public void done(ParseObject object, ParseException e) {
              if( e == null ){

                  object.put("pontos", 500);
                  object.saveInBackground();

              }else {
                  Log.i("t", "Erro");
              }
          }
      });
      */
        ParseQuery<ParseObject> filtro = ParseQuery.getQuery("Teste");

        filtro.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> objects, ParseException e) {
                if( e == null ){
                    //Log.i("listando dados", "SUCESSO: "+objects.size());

                    for (ParseObject p : objects ){
                        Log.i("listando dados", "NOME: "+p.get("nome")+" PONTOS: "+p.get("pontos"));
                    }
                }else {
                    Log.i("listando dados", "Erro");
                }
            }
        });

  }

}
