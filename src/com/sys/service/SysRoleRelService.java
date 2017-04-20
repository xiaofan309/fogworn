package com.sys.service;

import com.base.BaseModel;
import com.sys.bean.SysRoleRel;
import java.util.List;

public abstract interface SysRoleRelService
{
  public abstract SysRoleRel queryById(Object paramObject)
    throws Exception;
  
  public abstract List<SysRoleRel> queryByList(BaseModel paramBaseModel)
    throws Exception;
  
  public abstract void add(SysRoleRel paramSysRoleRel)
    throws Exception;
  
  public abstract void update(SysRoleRel paramSysRoleRel)
    throws Exception;
  
  public abstract void delete(Object... paramVarArgs)
    throws Exception;
  
  public abstract List<SysRoleRel> queryByRoleId(Integer paramInteger1, Integer paramInteger2);
  
  public abstract List<SysRoleRel> queryByObjId(Integer paramInteger1, Integer paramInteger2);
  
  public abstract void deleteByObjId(Integer paramInteger1, Integer paramInteger2);
  
  public abstract void deleteByRoleId(Integer paramInteger);
  
  public abstract void deleteByRoleId(Integer paramInteger1, Integer paramInteger2);
}


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.service.SysRoleRelService
 * JD-Core Version:    0.7.0.1
 */