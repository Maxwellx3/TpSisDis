/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.mycompany.tpsisdis1;

/**
 *
 * @author PC15 LIB
 */
import java.net.InetAddress;
import java.net.DatagramPacket;
public class TpSisDis1 {

    public static void main(String[] args) {
        //System.out.println("Hello World!");
        try {
            DatagramPacket paqueteUDP; //declaracion del paquete UDP
            String host = new String("127.0.0.1"); // Dir. Internet host remoto
            InetAddress dir_remota = InetAddress.getByName( host );
            int port = 1000; //puerto donde se envia el datagrama
            //Datos a enviar en el datagrama
            String datos = new String("Hola, estos son los datos !!");
            byte[] buffer = datos.getBytes(); // retorna los bytes del string
            //crear el paquete UDP
            paqueteUDP = new DatagramPacket( buffer, buffer.length, dir_remota, port);
            //mostrar los datos del paquete
            System.out.println("Paquete UDP :");
            System.out.println("Direccion Internet:" + paqueteUDP.getAddress());
            System.out.println("Datos en bytes[]:" + paqueteUDP.getData());
            System.out.println("Datos en string:" + new String (paqueteUDP.getData()));
            System.out.println("Longitud :" + paqueteUDP.getLength());
            System.out.println("Offset :" + paqueteUDP.getOffset());
            System.out.println("Puerto :" + paqueteUDP.getPort());
            } //cierra el try
        catch(Exception e) {
        System.out.println(e);
        }
        
    }
}
* Protocolo Simple de mensajes Cortos
*/
public class MSSP {
// atributos de la clase
byte id_msn;
byte sec_msn;
String msn;
/** Constructor de la clase
* id_mensaje : identificador del mensaje
* secuencia : numero de secuencia del mensaje
* menasje : mensaje a enviar
*/
MSSP(byte id_mensaje, byte secuencia, String mensaje){
id_msn = id_mensaje;
sec_msn = secuencia;
msn = mensaje;
}
/** Constructor para armar un MSSP a partir de un array de bytes
*/
MSSP(byte[] datos_UDP){
Byte msg_aux[]; // variable auxiliar para armar el mensaje en un String
Id_msn = datos_UDP[0]; // copia el identificador
Sec_msn = datos_UDP[1]; // copia el numero de secuencia
//dimensiona el array de byte para el mensaje String
msg_aux = new byte[datos_UDP.length-2];
//copia los bytes del string
for(int i=2;i<datos_UDP.length;i++) msg_aux[i-2] = datos_UDP[i];
//transform el array de byte en un mansaje String
msn = new String(msg_aux);
}
/**retorna el MSSP como un array de bytes, listo para generar
* un paquete UDP*/
public byte[] getArrayBytes(){
byte proto[] = new byte[52]; //array de bytes para todo el protocolo
byte msn_by[] = msn.getBytes(); //transforma el msn String a byte[]
proto[0] = id_msn; //asigan el primer byte
proto[1] = sec_msn; //asigna el segundo byte
//asigna el mensaje al array, byte a byte
for(int i=0;i<msn_by.length;i++) {
proto[i+2] = msn_by[i];
}
return proto; //retorna el MSSP como byte[]
}
}