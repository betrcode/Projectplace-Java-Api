package com.projectplace.api;

import java.util.List;

public interface Cursor<T extends APISerializable> {

	void setLimit(int count);
	List<T> next();
	List<T> prev();
}
