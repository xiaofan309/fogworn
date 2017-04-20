/*  1:   */ package com.sys.model;
/*  2:   */ 
/*  3:   */ import com.base.BaseModel;
/*  4:   */ import java.sql.Timestamp;
/*  5:   */ 
/*  6:   */ public class SysRoleModel
/*  7:   */   extends BaseModel
/*  8:   */ {
/*  9:   */   private Integer id;
/* 10:   */   private String roleName;
/* 11:   */   private Timestamp createTime;
/* 12:   */   private Integer createBy;
/* 13:   */   private Timestamp updateTime;
/* 14:   */   private Integer updateBy;
/* 15:   */   private Integer state;
/* 16:   */   private String descr;
/* 17:   */   
/* 18:   */   public Integer getId()
/* 19:   */   {
/* 20:18 */     return this.id;
/* 21:   */   }
/* 22:   */   
/* 23:   */   public void setId(Integer id)
/* 24:   */   {
/* 25:21 */     this.id = id;
/* 26:   */   }
/* 27:   */   
/* 28:   */   public String getRoleName()
/* 29:   */   {
/* 30:25 */     return this.roleName;
/* 31:   */   }
/* 32:   */   
/* 33:   */   public void setRoleName(String roleName)
/* 34:   */   {
/* 35:28 */     this.roleName = roleName;
/* 36:   */   }
/* 37:   */   
/* 38:   */   public Timestamp getCreateTime()
/* 39:   */   {
/* 40:31 */     return this.createTime;
/* 41:   */   }
/* 42:   */   
/* 43:   */   public void setCreateTime(Timestamp createTime)
/* 44:   */   {
/* 45:34 */     this.createTime = createTime;
/* 46:   */   }
/* 47:   */   
/* 48:   */   public Integer getCreateBy()
/* 49:   */   {
/* 50:37 */     return this.createBy;
/* 51:   */   }
/* 52:   */   
/* 53:   */   public void setCreateBy(Integer createBy)
/* 54:   */   {
/* 55:40 */     this.createBy = createBy;
/* 56:   */   }
/* 57:   */   
/* 58:   */   public Timestamp getUpdateTime()
/* 59:   */   {
/* 60:43 */     return this.updateTime;
/* 61:   */   }
/* 62:   */   
/* 63:   */   public void setUpdateTime(Timestamp updateTime)
/* 64:   */   {
/* 65:46 */     this.updateTime = updateTime;
/* 66:   */   }
/* 67:   */   
/* 68:   */   public Integer getUpdateBy()
/* 69:   */   {
/* 70:49 */     return this.updateBy;
/* 71:   */   }
/* 72:   */   
/* 73:   */   public void setUpdateBy(Integer updateBy)
/* 74:   */   {
/* 75:52 */     this.updateBy = updateBy;
/* 76:   */   }
/* 77:   */   
/* 78:   */   public Integer getState()
/* 79:   */   {
/* 80:55 */     return this.state;
/* 81:   */   }
/* 82:   */   
/* 83:   */   public void setState(Integer state)
/* 84:   */   {
/* 85:58 */     this.state = state;
/* 86:   */   }
/* 87:   */   
/* 88:   */   public String getDescr()
/* 89:   */   {
/* 90:61 */     return this.descr;
/* 91:   */   }
/* 92:   */   
/* 93:   */   public void setDescr(String descr)
/* 94:   */   {
/* 95:64 */     this.descr = descr;
/* 96:   */   }
/* 97:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.model.SysRoleModel
 * JD-Core Version:    0.7.0.1
 */