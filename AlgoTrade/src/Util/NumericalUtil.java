package Util;

/**
 * Created with IntelliJ IDEA.
 * User: Xiliang
 * Date: 25/10/13
 * Time: 6:37 PM
 * To change this template use File | Settings | File Templates.
 */
public class NumericalUtil {
    public static Boolean isDouble(String str){
          try{
                Double.parseDouble( str );
                return true;
          }
           catch( Exception e ){
                return false;
           }

    }
}
