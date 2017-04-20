package com.sys.service;

import com.base.BaseModel;
import com.sys.bean.SysMenu;
import com.sys.bean.SysMenuBtn;
import java.util.List;

public abstract interface SysMenuService
{
  public abstract SysMenu queryById(Object paramObject)
    throws Exception;
  
  public abstract List<SysMenu> queryByList(BaseModel paramBaseModel)
    throws Exception;
  
  public abstract void add(SysMenu paramSysMenu)
    throws Exception;
  
  public abstract void update(SysMenu paramSysMenu)
    throws Exception;
  
  public abstract void delete(Object... paramVarArgs)
    throws Exception;
  
  public abstract void saveBtns(Integer paramInteger, List<SysMenuBtn> paramList)
    throws Exception;
  
  public abstract List<SysMenu> queryByAll();
  
  public abstract List<SysMenu> getRootMenu(Integer paramInteger);
  
  public abstract List<SysMenu> getChildMenu();
  
  public abstract List<SysMenu> getRootMenuByUser(Integer paramInteger);
  
  public abstract List<SysMenu> getChildMenuByUser(Integer paramInteger);
  
  public abstract List<SysMenu> getMenuByRoleId(Integer paramInteger);
}


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.service.SysMenuService
 * JD-Core Version:    0.7.0.1
 */