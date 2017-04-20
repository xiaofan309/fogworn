package com.sys.mapper;

import com.base.BaseMapper;
import java.util.List;

public abstract interface SysRoleMapper<T>
  extends BaseMapper<T>
{
  public abstract List<T> queryAllList();
  
  public abstract List<T> queryByUserid(Integer paramInteger);
}


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.mapper.SysRoleMapper
 * JD-Core Version:    0.7.0.1
 */