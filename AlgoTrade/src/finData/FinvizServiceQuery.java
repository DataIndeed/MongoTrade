package finData;

import java.util.ListIterator;

public class FinvizServiceQuery extends GenericServiceQuery {

	public FinvizServiceQuery(QueryDetail query) {
		super(query);
		
	}
	public String connstrConstructor(QueryDetail query) {
		String cstr = new String();
		cstr = "http://finviz.com/export.ashx?";
		ListIterator<AttrNode> itr = query.AttrList.listIterator();
		while (itr.hasNext()){
			AttrNode current = itr.next();
			cstr+= current.attrName + "=" + current.attrValue + "&";
		}
		cstr = cstr.substring(0,cstr.length()-1);
		return cstr;
	}

}
