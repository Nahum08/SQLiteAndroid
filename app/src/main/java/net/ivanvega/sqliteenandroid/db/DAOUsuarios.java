package net.ivanvega.sqliteenandroid.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DAOUsuarios {

    private SQLiteDatabase _ad ;

    public DAOUsuarios(Context ctx) {
          _ad =
                  new MiAdaptadorUsuariosConexion(ctx).getWritableDatabase();
    }

    public long add(Usuario u)
    {

        ContentValues cv = new ContentValues();

        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[1], u.getNombre() );
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[2], u.getTelefono() );
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[3], u.getEmail());
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[4], u.getRed_social() );

        return _ad.insert(
                MiAdaptadorUsuariosConexion.TABLES_DB[0],
                null,
                cv);

    }

    public long   update (Usuario u){
        ContentValues cv = new ContentValues();
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[1], u.getNombre() );
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[2], u.getTelefono() );
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[3], u.getEmail());
        cv.put(MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS[4], u.getRed_social() );

        _ad.update(MiAdaptadorUsuariosConexion.TABLES_DB[0],
                cv,
                "_id=?",
                new String[]{String.valueOf( u.getId())}
                );

        return 0;
    }

    public String[] buscar (String nombre) {
        String[] datos = new String[3];
        String q = "select * from usuarios where nombre = '" + nombre + "'";
        Cursor registros = _ad.rawQuery(q,null);
        if (registros.moveToFirst()){
            for (int i = 0;i<2;i++){
                datos[i]= registros.getString(i);
            }
            datos[2] = "encontrado";
        }else {
            datos[2] = "no se encontro"+nombre;
        }
        return datos;
    }

    public String delete(String nombre){
        String mensaje;
        int cantidad = _ad.delete("usuarios","nombre = '"+nombre+"'",null);
        if (cantidad != 0){
            mensaje = "eliminado";
        }else {
            mensaje = "No existe";
        }
        return mensaje;
    }



    public List<Usuario > getAll(){
        List <Usuario> lst = new ArrayList<Usuario>();



         Cursor c = _ad.query(
                MiAdaptadorUsuariosConexion.TABLES_DB[0],
                MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS,
                null, null,null,null,null
        );

         while(c.moveToNext()){

             lst.add(
               new Usuario(c.getInt(0), c.getString(1),
                       c.getString(2), c.getString(3),
                       c.getString(4) )
             );

         }


        return lst;
    }

    public Cursor getAllC(){




        Cursor c = _ad.query(
                MiAdaptadorUsuariosConexion.TABLES_DB[0],
                MiAdaptadorUsuariosConexion.COLUMNS_USUARIOS,
                null, null,null,null,null
        );



        return c;
    }

}
