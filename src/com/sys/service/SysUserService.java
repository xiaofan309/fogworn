package com.sys.service;

import com.base.BaseModel;
import com.sys.bean.SysRoleRel;
import com.sys.bean.SysUser;
import java.util.List;

public abstract interface SysUserService
{
  public abstract SysUser queryById(Object paramObject)
    throws Exception;
  
  public abstract void add(SysUser paramSysUser)
    throws Exception;
  
  public abstract void update(SysUser paramSysUser)
    throws Exception;
  
  public abstract void updateBySelective(SysUser paramSysUser)
    throws Exception;
  
  public abstract void delete(Object... paramVarArgs)
    throws Exception;
  
  public abstract List<SysUser> queryByList(BaseModel paramBaseModel)
    throws Exception;
  
  public abstract SysUser queryLogin(String paramString1, String paramString2);
  
  public abstract int getUserCountByEmail(String paramString);
  
  public abstract List<SysRoleRel> getUserRole(Integer paramInteger);
  
  public abstract void addUserRole(Integer paramInteger, Integer[] paramArrayOfInteger)
    throws Exception;
}


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.service.SysUserService
 * JD-Core Version:    0.7.0.1
 */