package FinDAO;

import Util.NumericalUtil;
import com.mongodb.*;
import finData.QueryDetail;

import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * Created with IntelliJ IDEA.
 * User: Xiliang
 * Date: 23/10/13
 * Time: 10:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class MongoDB {
    private DB database;

    public MongoDB () throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost",27017)) ;
        database = client.getDB("FinData");
    }

    public MongoDB (String DBName) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress("localhost",27017)) ;
        database = client.getDB(DBName);
    }

    public MongoDB (String DBName, String ServerAdr,int port) throws UnknownHostException {
        MongoClient client = new MongoClient(new ServerAddress(ServerAdr,port)) ;
        database = client.getDB(DBName);
    }

    public DB GetDBConn(){
        return database;
    }
    public void InsertYahooData (List<String> YahooReturn, String Ticker){
        List<DBObject> YahooData = new LinkedList<DBObject>();
        YahooData = ConstructYahooObj(YahooReturn, Ticker);
        DBCollection YahooHist = database.getCollection("YahooHistory");
        //separate year/month/day
        for (DBObject DataItem: YahooData){
            //DBObject IDQuery = new BasicDBObject("Ticker",Ticker);
            //YahooHist.update(IDQuery,DataItem,true,false);
            YahooHist.insert(DataItem);
        }
    }
    //perform ETL
    //keep price as List, update using push price to the day's finviz data
    //however if price change substantially, re-download all the data column
    public void InsertFinvizData (List<String> FinvizReturn){
        //construct map
        Date now = new Date();
        DateFormat DatePart = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat TimePart = new SimpleDateFormat("HH:mm:ss");
        String datestr = DatePart.format(now);
        String timestr = TimePart.format(now);

        List<DBObject> FinvizData = new LinkedList<DBObject>() ;
        FinvizData = ConstructFinvizObj(FinvizReturn);


        DBCollection FinvizHist = database.getCollection("FinvizHistory");
        DBCollection FinvizRealtime = database.getCollection("FinvizRealtime");

        for (DBObject DataItem: FinvizData){
            DBObject setVal = (DBObject)DataItem.get("$set");
            DBObject IDQuery = new BasicDBObject("_id",setVal.get("_id"));
            setVal.removeField("_id");
            DataItem.put("$set", setVal);
            FinvizRealtime.update(IDQuery, DataItem, true, false);
        }


        List<DBObject> FinvizHistData = new LinkedList<DBObject>();
        FinvizHistData = ConstructFinvizObj(FinvizReturn);

        //add time tag to id and ticker column for hist table
        //add subdoc of map time:time, price:price

        for (DBObject DataItem: FinvizHistData){
            DBObject ValueSet = (DBObject)DataItem.get("$set");
            DBObject IDQuery = new BasicDBObject("_id",ValueSet.get("_id")+":"+datestr);
            BasicDBObject PriceData = new BasicDBObject("time",timestr).append("Price",ValueSet.get("Price"));
            DataItem.put("$push",new BasicDBObject("Price",PriceData));
            ValueSet.put("Ticker", ValueSet.get("_id"));
            ValueSet.removeField("_id");
            ValueSet.removeField("Price");
            DataItem.put("$set",ValueSet);
            FinvizHist.update(IDQuery,DataItem,true,false);
        }
        //FinvizHist.insert(IDQuery,DataItem,true,false);
        //for (BasicDBObject DataItem : FinvizData) {

        //}
    }

    private List<DBObject> ConstructYahooObj(List<String> finvizReturn, String Ticker) {
        LinkedList<DBObject> MongoObjList = new LinkedList<DBObject>();
        int headerFlag = 0;
        String[] headers = new String[]{""};
        for (String finvizValues : finvizReturn){
            if (headerFlag == 0){
                //extract headers
                headers = finvizValues.split(",");
                headerFlag++;
              }else {
                BasicDBObject YahooEntry = new BasicDBObject();
                String[] YahooRec = finvizValues.split(",");
                int counter = 0;
                for (String header : headers){
                    if(NumericalUtil.isDouble(YahooRec[counter])){
                        YahooEntry.put(header,Double.parseDouble(YahooRec[counter]));
                    }else{
                        YahooEntry.put(header,YahooRec[counter]);
                    }
                    counter ++;
                }
                YahooEntry.put("Ticker",Ticker);
                //MongoObjList.add(new BasicDBObject("$set",YahooEntry));
                MongoObjList.add(YahooEntry);
            }


        }

        return MongoObjList;
    }

    private List<DBObject> ConstructFinvizObj(List<String> finvizReturn) {




        LinkedList<DBObject> MongoObjList = new LinkedList<DBObject>();
        int headerFlag = 0;
        String[] headers = new String[]{""};
        for (String finvizValues : finvizReturn){
            finvizValues = finvizValues.replaceAll("\"","");
            if (headerFlag == 0){
                //extract headers
                headers = finvizValues.split(",");
                headerFlag++;
                headers[1] = "_id";
            }else {
                BasicDBObject FinvizEntry = new BasicDBObject();
                String[] FinvizRec = finvizValues.split(",");
                int counter = 0;
                for (String header : headers){
                    if (counter >= 1){     //ignore finviz table id
                        if(NumericalUtil.isDouble(FinvizRec[counter])){
                            FinvizEntry.put(header,Double.parseDouble(FinvizRec[counter]));
                        }else{
                            FinvizEntry.put(header,FinvizRec[counter]);
                        }

                    }
                    counter ++;
                }
                MongoObjList.add(new BasicDBObject("$set", FinvizEntry));
            }


        }

        return MongoObjList;
    }


    private List<DBObject> ConstructMongoQuery(List<String> finvizReturn) {

        LinkedList<DBObject> MongoQueryList = new LinkedList<DBObject>();
        int headerFlag = 0;
        String[] headers = new String[]{""};
        for (String finvizValues : finvizReturn){

            if (headerFlag == 0){
                //extract headers
                headers = finvizReturn.get(0).split(",");
                headerFlag++;
                headers[0] = "_id";
            }else {
                //use stock ticker as ID
                BasicDBObject FinvizQuery = new BasicDBObject();
                String[] FinvizRec = finvizValues.split(",");
                FinvizQuery.put(headers[1],FinvizRec[1]);
                MongoQueryList.add(FinvizQuery);
            }


        }

        return MongoQueryList;
    }


}
