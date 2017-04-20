package com.sys.mapper;

import com.base.BaseMapper;
import java.util.List;

public abstract interface SysMenuBtnMapper<T>
  extends BaseMapper<T>
{
  public abstract List<T> queryByMenuid(Integer paramInteger);
  
  public abstract List<T> queryByMenuUrl(String paramString);
  
  public abstract void deleteByMenuid(Integer paramInteger);
  
  public abstract List<T> getMenuBtnByUser(Integer paramInteger);
  
  public abstract List<T> queryByAll();
}


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.mapper.SysMenuBtnMapper
 * JD-Core Version:    0.7.0.1
 */