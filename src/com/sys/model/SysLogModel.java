/*  1:   */ package com.sys.model;
/*  2:   */ 
/*  3:   */ import com.base.BaseModel;
/*  4:   */ 
/*  5:   */ public class SysLogModel
/*  6:   */   extends BaseModel
/*  7:   */ {
/*  8:   */   private Integer id;
/*  9:   */   private String table_name;
/* 10:   */   private Integer primary_id;
/* 11:   */   private Integer do_user_id;
/* 12:   */   private String do_user_name;
/* 13:   */   private String do_type;
/* 14:   */   private String description;
/* 15:   */   private String do_time;
/* 16:   */   private String do_time_b;
/* 17:   */   private String do_time_e;
/* 18:   */   
/* 19:   */   public Integer getId()
/* 20:   */   {
/* 21:22 */     return this.id;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void setId(Integer id)
/* 25:   */   {
/* 26:25 */     this.id = id;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public String getTable_name()
/* 30:   */   {
/* 31:28 */     return this.table_name;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void setTable_name(String table_name)
/* 35:   */   {
/* 36:31 */     this.table_name = table_name;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public Integer getPrimary_id()
/* 40:   */   {
/* 41:34 */     return this.primary_id;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void setPrimary_id(Integer primary_id)
/* 45:   */   {
/* 46:37 */     this.primary_id = primary_id;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public Integer getDo_user_id()
/* 50:   */   {
/* 51:40 */     return this.do_user_id;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public void setDo_user_id(Integer do_user_id)
/* 55:   */   {
/* 56:43 */     this.do_user_id = do_user_id;
/* 57:   */   }
/* 58:   */   
/* 59:   */   public String getDo_user_name()
/* 60:   */   {
/* 61:46 */     return this.do_user_name;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public void setDo_user_name(String do_user_name)
/* 65:   */   {
/* 66:49 */     this.do_user_name = do_user_name;
/* 67:   */   }
/* 68:   */   
/* 69:   */   public String getDo_type()
/* 70:   */   {
/* 71:52 */     return this.do_type;
/* 72:   */   }
/* 73:   */   
/* 74:   */   public void setDo_type(String do_type)
/* 75:   */   {
/* 76:55 */     this.do_type = do_type;
/* 77:   */   }
/* 78:   */   
/* 79:   */   public String getDescription()
/* 80:   */   {
/* 81:58 */     return this.description;
/* 82:   */   }
/* 83:   */   
/* 84:   */   public void setDescription(String description)
/* 85:   */   {
/* 86:61 */     this.description = description;
/* 87:   */   }
/* 88:   */   
/* 89:   */   public String getDo_time()
/* 90:   */   {
/* 91:64 */     return this.do_time;
/* 92:   */   }
/* 93:   */   
/* 94:   */   public void setDo_time(String do_time)
/* 95:   */   {
/* 96:67 */     this.do_time = do_time;
/* 97:   */   }
/* 98:   */   
/* 99:   */   public String getDo_time_b()
/* :0:   */   {
/* :1:70 */     return this.do_time_b;
/* :2:   */   }
/* :3:   */   
/* :4:   */   public void setDo_time_b(String doTimeB)
/* :5:   */   {
/* :6:73 */     this.do_time_b = doTimeB;
/* :7:   */   }
/* :8:   */   
/* :9:   */   public String getDo_time_e()
/* ;0:   */   {
/* ;1:76 */     return this.do_time_e;
/* ;2:   */   }
/* ;3:   */   
/* ;4:   */   public void setDo_time_e(String doTimeE)
/* ;5:   */   {
/* ;6:79 */     this.do_time_e = doTimeE;
/* ;7:   */   }
/* ;8:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.model.SysLogModel
 * JD-Core Version:    0.7.0.1
 */