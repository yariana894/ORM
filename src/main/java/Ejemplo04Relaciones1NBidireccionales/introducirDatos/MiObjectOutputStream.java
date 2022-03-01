package Ejemplo04Relaciones1NBidireccionales.introducirDatos;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

public class MiObjectOutputStream extends ObjectOutputStream {

    public MiObjectOutputStream(OutputStream out) throws IOException {
        super(out);
    }

    public MiObjectOutputStream() throws IOException, SecurityException {
        super();
    }

    //Sobre escribimos el metodo que crea una cabecera al escribir en un archivo para que no haga nada
    public void writeStreamHeader() {

    }
}
