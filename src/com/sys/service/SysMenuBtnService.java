package com.sys.service;

import com.base.BaseModel;
import com.sys.bean.SysMenuBtn;
import java.util.List;

public abstract interface SysMenuBtnService
{
  public abstract SysMenuBtn queryById(Object paramObject)
    throws Exception;
  
  public abstract void add(SysMenuBtn paramSysMenuBtn)
    throws Exception;
  
  public abstract void update(SysMenuBtn paramSysMenuBtn)
    throws Exception;
  
  public abstract void delete(Object... paramVarArgs)
    throws Exception;
  
  public abstract List<SysMenuBtn> queryByList(BaseModel paramBaseModel)
    throws Exception;
  
  public abstract List<SysMenuBtn> queryByAll();
  
  public abstract List<SysMenuBtn> queryByMenuid(Integer paramInteger);
  
  public abstract List<SysMenuBtn> queryByMenuUrl(String paramString);
  
  public abstract void deleteByMenuid(Integer paramInteger);
  
  public abstract List<SysMenuBtn> getMenuBtnByUser(Integer paramInteger);
}


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.service.SysMenuBtnService
 * JD-Core Version:    0.7.0.1
 */