package com.ef.extract;

import java.util.HashMap;

public interface ContentParser<KEY,VALUE> {
	
	public HashMap<KEY, VALUE> getData();

}
