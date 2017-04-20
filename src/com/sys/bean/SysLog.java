/*  1:   */ package com.sys.bean;
/*  2:   */ 
/*  3:   */ import com.base.BaseBean;
/*  4:   */ 
/*  5:   */ public class SysLog
/*  6:   */   extends BaseBean
/*  7:   */ {
/*  8:   */   private Integer id;
/*  9:   */   private String table_name;
/* 10:   */   private Integer primary_id;
/* 11:   */   private Integer do_user_id;
/* 12:   */   private String do_user_name;
/* 13:   */   private String do_type;
/* 14:   */   private String description;
/* 15:   */   private String do_time;
/* 16:   */   
/* 17:   */   public Integer getId()
/* 18:   */   {
/* 19:17 */     return this.id;
/* 20:   */   }
/* 21:   */   
/* 22:   */   public void setId(Integer id)
/* 23:   */   {
/* 24:20 */     this.id = id;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public String getTable_name()
/* 28:   */   {
/* 29:23 */     return this.table_name;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public void setTable_name(String table_name)
/* 33:   */   {
/* 34:26 */     this.table_name = table_name;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public Integer getPrimary_id()
/* 38:   */   {
/* 39:29 */     return this.primary_id;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void setPrimary_id(Integer primary_id)
/* 43:   */   {
/* 44:32 */     this.primary_id = primary_id;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public Integer getDo_user_id()
/* 48:   */   {
/* 49:35 */     return this.do_user_id;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void setDo_user_id(Integer do_user_id)
/* 53:   */   {
/* 54:38 */     this.do_user_id = do_user_id;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public String getDo_user_name()
/* 58:   */   {
/* 59:41 */     return this.do_user_name;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void setDo_user_name(String do_user_name)
/* 63:   */   {
/* 64:44 */     this.do_user_name = do_user_name;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public String getDo_type()
/* 68:   */   {
/* 69:47 */     return this.do_type;
/* 70:   */   }
/* 71:   */   
/* 72:   */   public void setDo_type(String do_type)
/* 73:   */   {
/* 74:50 */     this.do_type = do_type;
/* 75:   */   }
/* 76:   */   
/* 77:   */   public String getDescription()
/* 78:   */   {
/* 79:53 */     return this.description;
/* 80:   */   }
/* 81:   */   
/* 82:   */   public void setDescription(String description)
/* 83:   */   {
/* 84:56 */     this.description = description;
/* 85:   */   }
/* 86:   */   
/* 87:   */   public String getDo_time()
/* 88:   */   {
/* 89:59 */     return this.do_time;
/* 90:   */   }
/* 91:   */   
/* 92:   */   public void setDo_time(String do_time)
/* 93:   */   {
/* 94:62 */     this.do_time = do_time;
/* 95:   */   }
/* 96:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.bean.SysLog
 * JD-Core Version:    0.7.0.1
 */