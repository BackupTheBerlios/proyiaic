//Source file: C:\\GENERADO\\controlador\\exceptions\\ExistenceException.java

package controlador.exceptions;


/**
 * Excepcion que se produce al manipular la base de datos por problemas de 
 * existencia o inexistencia de los objetos a tratar.
 */
public class ExistenceException extends Exception 
{
   
   /**
    * Indica el tipo de elemento que ha producido la excepcion de existencia ( 
    * Documento, Usuario, Proyecto,....).
    */
   private int tipo;
   
   /**
    * @roseuid 47C8A7100119
    */
   public ExistenceException() 
   {
    
   }
}
