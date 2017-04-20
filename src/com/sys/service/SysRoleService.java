package com.sys.service;

import com.base.BaseModel;
import com.sys.bean.SysRole;
import java.util.List;

public abstract interface SysRoleService
{
  public abstract SysRole queryById(Object paramObject)
    throws Exception;
  
  public abstract void add(SysRole paramSysRole)
    throws Exception;
  
  public abstract void update(SysRole paramSysRole)
    throws Exception;
  
  public abstract void delete(Object... paramVarArgs)
    throws Exception;
  
  public abstract List<SysRole> queryByList(BaseModel paramBaseModel)
    throws Exception;
  
  public abstract void addRoleMenuRel(Integer paramInteger, Integer[] paramArrayOfInteger)
    throws Exception;
  
  public abstract void addRoleBtnRel(Integer paramInteger, Integer[] paramArrayOfInteger)
    throws Exception;
  
  public abstract void add(SysRole paramSysRole, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2)
    throws Exception;
  
  public abstract void delete(Integer[] paramArrayOfInteger)
    throws Exception;
  
  public abstract void update(SysRole paramSysRole, Integer[] paramArrayOfInteger1, Integer[] paramArrayOfInteger2)
    throws Exception;
  
  public abstract List<SysRole> queryAllList();
  
  public abstract List<SysRole> queryByUserid(Integer paramInteger);
}


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.service.SysRoleService
 * JD-Core Version:    0.7.0.1
 */