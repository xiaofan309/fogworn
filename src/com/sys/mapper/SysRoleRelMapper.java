package com.sys.mapper;

import com.base.BaseMapper;
import com.sys.bean.SysRoleRel;
import java.util.List;
import java.util.Map;

public abstract interface SysRoleRelMapper<T>
  extends BaseMapper<T>
{
  public abstract void deleteByRoleId(Map<String, Object> paramMap);
  
  public abstract void deleteByObjId(Map<String, Object> paramMap);
  
  public abstract List<SysRoleRel> queryByRoleId(Map<String, Object> paramMap);
  
  public abstract List<SysRoleRel> queryByObjId(Map<String, Object> paramMap);
}


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.mapper.SysRoleRelMapper
 * JD-Core Version:    0.7.0.1
 */