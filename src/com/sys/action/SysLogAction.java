/*   1:    */ package com.sys.action;
/*   2:    */ 
/*   3:    */ import com.base.BaseAction;
/*   4:    */ import com.base.utils.HtmlUtil;
/*   5:    */ import com.base.utils.Pager;
/*   6:    */ import com.sys.bean.SysLog;
/*   7:    */ import com.sys.model.SysLogModel;
/*   8:    */ import com.sys.service.SysLogService;
/*   9:    */ import java.util.HashMap;
/*  10:    */ import java.util.List;
/*  11:    */ import java.util.Map;
/*  12:    */ import javax.servlet.http.HttpServletRequest;
/*  13:    */ import javax.servlet.http.HttpServletResponse;
/*  14:    */ import org.apache.log4j.Logger;
/*  15:    */ import org.springframework.beans.factory.annotation.Autowired;
/*  16:    */ import org.springframework.stereotype.Controller;
/*  17:    */ import org.springframework.web.bind.annotation.RequestMapping;
/*  18:    */ import org.springframework.web.servlet.ModelAndView;
/*  19:    */ 
/*  20:    */ @Controller
/*  21:    */ @RequestMapping({"/sysLog"})
/*  22:    */ public class SysLogAction
/*  23:    */   extends BaseAction
/*  24:    */ {
/*  25: 26 */   private static final Logger log = Logger.getLogger(SysLogAction.class);
/*  26:    */   @Autowired(required=false)
/*  27:    */   private SysLogService sysLogService;
/*  28:    */   
/*  29:    */   @RequestMapping({"/list"})
/*  30:    */   public ModelAndView list(SysLogModel model, HttpServletRequest request)
/*  31:    */     throws Exception
/*  32:    */   {
/*  33: 40 */     Map<String, Object> context = new HashMap();
/*  34: 41 */     return forword("sys/sysLog", context);
/*  35:    */   }
/*  36:    */   
/*  37:    */   @RequestMapping({"/dataList"})
/*  38:    */   public void datalist(SysLogModel model, HttpServletResponse response)
/*  39:    */     throws Exception
/*  40:    */   {
/*  41: 54 */     model.setSort("do_time");
/*  42: 55 */     model.setOrder("desc");
/*  43: 56 */     List<SysLog> dataList = this.sysLogService.queryByList(model);
/*  44:    */     
/*  45: 58 */     Map<String, Object> jsonMap = new HashMap();
/*  46: 59 */     jsonMap.put("total", Integer.valueOf(model.getPager().getRowCount()));
/*  47: 60 */     jsonMap.put("rows", dataList);
/*  48: 61 */     HtmlUtil.writerJson(response, jsonMap);
/*  49:    */   }
/*  50:    */   
/*  51:    */   @RequestMapping({"/save"})
/*  52:    */   public void save(SysLog bean, Integer[] typeIds, HttpServletResponse response)
/*  53:    */     throws Exception
/*  54:    */   {
/*  55: 74 */     if (bean.getId() == null) {
/*  56: 75 */       this.sysLogService.add(bean);
/*  57:    */     } else {
/*  58: 77 */       this.sysLogService.update(bean);
/*  59:    */     }
/*  60: 79 */     sendSuccessMessage(response, "保存成功~");
/*  61:    */   }
/*  62:    */   
/*  63:    */   @RequestMapping({"/getId"})
/*  64:    */   public void getId(Integer id, HttpServletResponse response)
/*  65:    */     throws Exception
/*  66:    */   {
/*  67: 85 */     Map<String, Object> context = new HashMap();
/*  68: 86 */     SysLog bean = this.sysLogService.queryById(id);
/*  69: 87 */     if (bean == null)
/*  70:    */     {
/*  71: 88 */       sendFailureMessage(response, "没有找到对应的记录!");
/*  72: 89 */       return;
/*  73:    */     }
/*  74: 91 */     context.put("success", Boolean.valueOf(true));
/*  75: 92 */     context.put("data", bean);
/*  76: 93 */     HtmlUtil.writerJson(response, context);
/*  77:    */   }
/*  78:    */   
/*  79:    */   @RequestMapping({"/delete"})
/*  80:    */   public void delete(Integer id, HttpServletResponse response)
/*  81:    */     throws Exception
/*  82:    */   {
/*  83:100 */     this.sysLogService.delete(new Object[] { id });
/*  84:101 */     sendSuccessMessage(response, "删除成功");
/*  85:    */   }
/*  86:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.action.SysLogAction
 * JD-Core Version:    0.7.0.1
 */