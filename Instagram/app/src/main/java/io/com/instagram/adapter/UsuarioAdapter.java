package io.com.instagram.adapter;import android.content.Context;import android.support.annotation.NonNull;import android.support.annotation.Nullable;import android.view.LayoutInflater;import android.view.View;import android.view.ViewGroup;import android.widget.ArrayAdapter;import android.widget.TextView;import com.parse.ParseUser;import java.util.ArrayList;import io.com.instagram.R;/** * Created by rafaelcarvalho on 25/12/2017. */public class UsuarioAdapter extends ArrayAdapter<ParseUser> {    private Context context;    private ArrayList<ParseUser> usuarios;    public UsuarioAdapter(@NonNull Context c, @NonNull ArrayList<ParseUser> objects) {        super(c, 0, objects);        this.context = c;        this.usuarios = objects;    }    @NonNull    @Override    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {        View view = convertView;        if(view == null ){            LayoutInflater inflater = (LayoutInflater) context.getSystemService( context.LAYOUT_INFLATER_SERVICE );            //monstando a view a partir do xml            view = inflater.inflate(R.layout.lista_usuarios, parent, false );        }        TextView userName = (TextView) view.findViewById(R.id.text_username);        //config para exibir usuários        ParseUser user = usuarios.get( position );        userName.setText( user.getUsername() );        return view;    }}