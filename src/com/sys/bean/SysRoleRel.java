/*  1:   */ package com.sys.bean;
/*  2:   */ 
/*  3:   */ import com.base.BaseBean;
/*  4:   */ 
/*  5:   */ public class SysRoleRel
/*  6:   */   extends BaseBean
/*  7:   */ {
/*  8:   */   private Integer roleId;
/*  9:   */   private Integer objId;
/* 10:   */   private Integer relType;
/* 11:   */   
/* 12:   */   public static enum RelType
/* 13:   */   {
/* 14:18 */     MENU(0, "菜单"),  USER(1, "用户"),  BTN(2, "按钮");
/* 15:   */     
/* 16:   */     public int key;
/* 17:   */     public String value;
/* 18:   */     
/* 19:   */     private RelType(int key, String value)
/* 20:   */     {
/* 21:22 */       this.key = key;
/* 22:23 */       this.value = value;
/* 23:   */     }
/* 24:   */     
/* 25:   */     public static RelType get(int key)
/* 26:   */     {
/* 27:26 */       RelType[] values = values();
/* 28:27 */       for (RelType object : values) {
/* 29:28 */         if (object.key == key) {
/* 30:29 */           return object;
/* 31:   */         }
/* 32:   */       }
/* 33:32 */       return null;
/* 34:   */     }
/* 35:   */   }
/* 36:   */   
/* 37:   */   public Integer getRoleId()
/* 38:   */   {
/* 39:38 */     return this.roleId;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public void setRoleId(Integer roleId)
/* 43:   */   {
/* 44:41 */     this.roleId = roleId;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public Integer getObjId()
/* 48:   */   {
/* 49:44 */     return this.objId;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public void setObjId(Integer objId)
/* 53:   */   {
/* 54:47 */     this.objId = objId;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public Integer getRelType()
/* 58:   */   {
/* 59:50 */     return this.relType;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public void setRelType(Integer relType)
/* 63:   */   {
/* 64:53 */     this.relType = relType;
/* 65:   */   }
/* 66:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.bean.SysRoleRel
 * JD-Core Version:    0.7.0.1
 */