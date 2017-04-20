/*  1:   */ package com.sys.bean;
/*  2:   */ 
/*  3:   */ import com.base.BaseBean;
/*  4:   */ 
/*  5:   */ public class SysMenuBtn
/*  6:   */   extends BaseBean
/*  7:   */ {
/*  8:   */   private Integer id;
/*  9:   */   private Integer menuid;
/* 10:   */   private String btnName;
/* 11:   */   private String btnType;
/* 12:   */   private String actionUrls;
/* 13:   */   private String deleteFlag;
/* 14:   */   
/* 15:   */   public String getDeleteFlag()
/* 16:   */   {
/* 17:18 */     return this.deleteFlag;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public void setDeleteFlag(String deleteFlag)
/* 21:   */   {
/* 22:21 */     this.deleteFlag = deleteFlag;
/* 23:   */   }
/* 24:   */   
/* 25:   */   public Integer getId()
/* 26:   */   {
/* 27:24 */     return this.id;
/* 28:   */   }
/* 29:   */   
/* 30:   */   public void setId(Integer id)
/* 31:   */   {
/* 32:27 */     this.id = id;
/* 33:   */   }
/* 34:   */   
/* 35:   */   public Integer getMenuid()
/* 36:   */   {
/* 37:30 */     return this.menuid;
/* 38:   */   }
/* 39:   */   
/* 40:   */   public void setMenuid(Integer menuid)
/* 41:   */   {
/* 42:33 */     this.menuid = menuid;
/* 43:   */   }
/* 44:   */   
/* 45:   */   public String getBtnName()
/* 46:   */   {
/* 47:36 */     return this.btnName;
/* 48:   */   }
/* 49:   */   
/* 50:   */   public void setBtnName(String btnName)
/* 51:   */   {
/* 52:39 */     this.btnName = btnName;
/* 53:   */   }
/* 54:   */   
/* 55:   */   public String getBtnType()
/* 56:   */   {
/* 57:42 */     return this.btnType;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public void setBtnType(String btnType)
/* 61:   */   {
/* 62:45 */     this.btnType = btnType;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public String getActionUrls()
/* 66:   */   {
/* 67:48 */     return this.actionUrls;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public void setActionUrls(String actionUrls)
/* 71:   */   {
/* 72:51 */     this.actionUrls = actionUrls;
/* 73:   */   }
/* 74:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.bean.SysMenuBtn
 * JD-Core Version:    0.7.0.1
 */