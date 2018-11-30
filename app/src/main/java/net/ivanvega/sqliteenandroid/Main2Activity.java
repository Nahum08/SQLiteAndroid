package net.ivanvega.sqliteenandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import net.ivanvega.sqliteenandroid.db.DAOUsuarios;
import net.ivanvega.sqliteenandroid.db.MiAdaptadorUsuariosConexion;

public class Main2Activity extends AppCompatActivity {
    EditText txteliminar,txtBuscar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txteliminar = findViewById(R.id.txtEliminar);
        txtBuscar = findViewById(R.id.txtBuscar);
    }


    public void btnE_click (View view){
        DAOUsuarios daoUsuarios =  new DAOUsuarios(this);
        MiAdaptadorUsuariosConexion db = new MiAdaptadorUsuariosConexion(getApplicationContext());
        String nombre = txteliminar.getText().toString();
        String mensaje = daoUsuarios.delete(nombre);
        Toast.makeText(this,mensaje,Toast.LENGTH_SHORT).show();
    }
    public void btnB_click(View view){
        DAOUsuarios daoUsuarios = new DAOUsuarios(this);
        MiAdaptadorUsuariosConexion db = new MiAdaptadorUsuariosConexion(getApplicationContext());
        String buscar = txtBuscar.getText().toString();
        String [] datos;
        datos = daoUsuarios.buscar(buscar);
        Toast.makeText(this,datos[2],Toast.LENGTH_SHORT).show();
    }
}
