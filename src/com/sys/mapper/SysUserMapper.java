package com.sys.mapper;

import com.base.BaseMapper;
import com.sys.model.SysUserModel;

public abstract interface SysUserMapper<T>
  extends BaseMapper<T>
{
  public abstract T queryLogin(SysUserModel paramSysUserModel);
  
  public abstract int getUserCountByEmail(String paramString);
}


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.mapper.SysUserMapper
 * JD-Core Version:    0.7.0.1
 */