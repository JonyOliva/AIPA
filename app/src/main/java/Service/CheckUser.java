package Service;

import android.content.Context;
import android.widget.Toast;

import iService.iUsuariosService;

public class CheckUser implements  Runnable {

    String email;
    Context context;
    iUsuariosService us;

    public CheckUser(String email, Context context) {
        this.email = email;
        this.context = context;
        us = new UsuariosService();
    }

    @Override
    public void run() {
        if(us.getUser(email, "") != null) {
            Toast.makeText(context, "Ese email ya está registrado.", Toast.LENGTH_SHORT).show();
        }
    }
}
