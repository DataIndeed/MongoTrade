package finData;

import FinDAO.MongoDB;
import FinDAO.UpdateFinDB;

import java.net.*;
import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class GetYahooData {
	public static void main(String[] args) {
		try {

            UpdateFinDB update = new UpdateFinDB("Finviz");
			//URL YahooURL = new URL("http://docs.oracle.com/javase/7/docs/api/java/io/InputStreamReader.html");
			//InputStream YahooURLReader = new URL("http://finance.yahoo.com/d/quotes.csv?s=XOM+BBDb.TO+JNJ+MSFT&f=snd1l1yr").openStream();;
			//InputStream Finviz = new URL("http://finviz.com/export.ashx?v=111&&t=QIHU").openStream();;
			//InputStream YahooURLReader = new URL("http://finance.yahoo.com/d/quotes.csv?s=MANS.OB&amp;f=sl1d1t1c1ohgv&amp;e=.csv").openStream();
			//QueryDetail qd = new QueryDetail("finviz", "view","overview");
			//InputStream FinvizURLReader = new FinvizServiceQuery(qd).getURLStream();
			
//			InputStream YahooURLReader = new URL("http://ichart.finance.yahoo.com/table.csv?s=WU&a=01&b=19&c=2010&d=04&e=19&f=2010&g=d").openStream();
//            List<String> FinvizRec = new LinkedList<String>();
//			BufferedReader in = new BufferedReader(
//			        new InputStreamReader(YahooURLReader,"UTF-8"));
//			String inputLine;
//			while ((inputLine = in.readLine()) != null){
//                  FinvizRec.add(inputLine);
//			      System.out.println(inputLine);
//			}
//			in.close();

            //MongoDB DBConn = new MongoDB();
            //DBConn.InsertFinvizData(FinvizRec);
		} catch (IOException e) {
			e.printStackTrace();
		}


	}


}
