package com.base;

import java.util.List;

public abstract interface BaseMapper<T> {
	public abstract void add(T paramT);

	public abstract void update(T paramT);

	public abstract void updateBySelective(T paramT);

	public abstract void delete(Object paramObject);

	public abstract int queryByCount(BaseModel paramBaseModel);

	public abstract List<T> queryByList(BaseModel paramBaseModel);

	public abstract T queryById(Object paramObject);
}
