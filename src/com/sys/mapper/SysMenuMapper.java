package com.sys.mapper;

import com.base.BaseMapper;
import java.util.List;
import java.util.Map;

public abstract interface SysMenuMapper<T>
  extends BaseMapper<T>
{
  public abstract List<T> queryByAll();
  
  public abstract List<T> getRootMenu(Map paramMap);
  
  public abstract List<T> getChildMenu();
  
  public abstract List<T> getMenuByRoleId(Integer paramInteger);
  
  public abstract List<T> getRootMenuByUser(Integer paramInteger);
  
  public abstract List<T> getChildMenuByUser(Integer paramInteger);
}


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.mapper.SysMenuMapper
 * JD-Core Version:    0.7.0.1
 */