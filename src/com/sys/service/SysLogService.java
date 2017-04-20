package com.sys.service;

import com.base.BaseModel;
import com.sys.bean.SysLog;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public abstract interface SysLogService
{
  public abstract SysLog queryById(Object paramObject)
    throws Exception;
  
  public abstract void add(SysLog paramSysLog)
    throws Exception;
  
  public abstract void update(SysLog paramSysLog)
    throws Exception;
  
  public abstract void delete(Object... paramVarArgs)
    throws Exception;
  
  public abstract List<SysLog> queryByList(BaseModel paramBaseModel)
    throws Exception;
  
  public abstract void addLog(HttpServletRequest paramHttpServletRequest, String paramString1, Integer paramInteger, String paramString2, String paramString3)
    throws Exception;
  
  public abstract void addLog(Integer paramInteger1, String paramString1, String paramString2, Integer paramInteger2, String paramString3, String paramString4)
    throws Exception;
}


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.service.SysLogService
 * JD-Core Version:    0.7.0.1
 */