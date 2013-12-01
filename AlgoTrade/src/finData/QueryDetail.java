package finData;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class QueryDetail {
	public static Map<String,String> urlstrMap = new HashMap<String,String>();
	public String provider;
	LinkedList<AttrNode> AttrList = new LinkedList<AttrNode>();
	public QueryDetail (){ //default query item
		iniconst();
		provider = "finviz";
		AttrList.add(new AttrNode(urlstrMap.get("view"), urlstrMap.get("overview")));
	}
	public QueryDetail (String prdr, String... query){
		iniconst();
		provider = prdr;
		if (query.length % 2 == 1){
			System.out.println("Please input all pairs");
			System.exit(1);
		}
		String aname = "";
		int count = 1;
		String urlvalue = "";
		for (String str : query){
			urlvalue = urlstrMap.get(str);
			if (urlvalue == null){
				urlvalue = str;
			}
			if (count%2==1){
				aname = urlvalue;
			}else{
				AttrList.add(new AttrNode(aname, urlvalue));
			}
			count ++;
		}
		
	}
	
	public static void iniconst(){
		
		urlstrMap.put("view","v");
		urlstrMap.put("overview","111");
		urlstrMap.put("valuation","121");
		urlstrMap.put("financial","161");
		urlstrMap.put("ownership","131");
		urlstrMap.put("performance","141");
		urlstrMap.put("technical","171");
		
		urlstrMap.put("ticker","t");
		
	}
//	public enum ViewType
//	{
//	    OVERVIEW(111),
//	    VALUATION(121),
//	    FINANCIAL(161),
//	    OWNERSHIP(131),
//	    PERFORMANCE(141),
//	    TECHNICAL(171);
//	    	    
//	    private Integer value;
//	    private ViewType(Integer value) {
//	       this.value = value;
//	    }
//	    public Integer getValue() {
//	       return value;
//	    }
//	}

}
