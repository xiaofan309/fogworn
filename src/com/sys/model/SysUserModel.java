/*  1:   */ package com.sys.model;
/*  2:   */ 
/*  3:   */ import com.base.BaseModel;
/*  4:   */ import java.sql.Timestamp;
/*  5:   */ 
/*  6:   */ public class SysUserModel
/*  7:   */   extends BaseModel
/*  8:   */ {
/*  9:   */   private Integer id;
/* 10:   */   private String email;
/* 11:   */   private String pwd;
/* 12:   */   private String nickName;
/* 13:   */   private Integer state;
/* 14:   */   private Integer loginCount;
/* 15:   */   private Timestamp loginTime;
/* 16:   */   private Integer deleted;
/* 17:   */   private Timestamp createTime;
/* 18:   */   private Timestamp updateTime;
/* 19:   */   private Integer createBy;
/* 20:   */   private Integer updateBy;
/* 21:   */   
/* 22:   */   public Integer getId()
/* 23:   */   {
/* 24:22 */     return this.id;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void setId(Integer id)
/* 28:   */   {
/* 29:25 */     this.id = id;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public String getEmail()
/* 33:   */   {
/* 34:28 */     return this.email;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void setEmail(String email)
/* 38:   */   {
/* 39:31 */     this.email = email;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public String getPwd()
/* 43:   */   {
/* 44:34 */     return this.pwd;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void setPwd(String pwd)
/* 48:   */   {
/* 49:37 */     this.pwd = pwd;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public String getNickName()
/* 53:   */   {
/* 54:40 */     return this.nickName;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void setNickName(String nickName)
/* 58:   */   {
/* 59:43 */     this.nickName = nickName;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public Integer getState()
/* 63:   */   {
/* 64:46 */     return this.state;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public void setState(Integer state)
/* 68:   */   {
/* 69:49 */     this.state = state;
/* 70:   */   }
/* 71:   */   
/* 72:   */   public Integer getLoginCount()
/* 73:   */   {
/* 74:52 */     return this.loginCount;
/* 75:   */   }
/* 76:   */   
/* 77:   */   public void setLoginCount(Integer loginCount)
/* 78:   */   {
/* 79:55 */     this.loginCount = loginCount;
/* 80:   */   }
/* 81:   */   
/* 82:   */   public Timestamp getLoginTime()
/* 83:   */   {
/* 84:58 */     return this.loginTime;
/* 85:   */   }
/* 86:   */   
/* 87:   */   public void setLoginTime(Timestamp loginTime)
/* 88:   */   {
/* 89:61 */     this.loginTime = loginTime;
/* 90:   */   }
/* 91:   */   
/* 92:   */   public Integer getDeleted()
/* 93:   */   {
/* 94:64 */     return this.deleted;
/* 95:   */   }
/* 96:   */   
/* 97:   */   public void setDeleted(Integer deleted)
/* 98:   */   {
/* 99:67 */     this.deleted = deleted;
/* :0:   */   }
/* :1:   */   
/* :2:   */   public Timestamp getCreateTime()
/* :3:   */   {
/* :4:70 */     return this.createTime;
/* :5:   */   }
/* :6:   */   
/* :7:   */   public void setCreateTime(Timestamp createTime)
/* :8:   */   {
/* :9:73 */     this.createTime = createTime;
/* ;0:   */   }
/* ;1:   */   
/* ;2:   */   public Timestamp getUpdateTime()
/* ;3:   */   {
/* ;4:76 */     return this.updateTime;
/* ;5:   */   }
/* ;6:   */   
/* ;7:   */   public void setUpdateTime(Timestamp updateTime)
/* ;8:   */   {
/* ;9:79 */     this.updateTime = updateTime;
/* <0:   */   }
/* <1:   */   
/* <2:   */   public Integer getCreateBy()
/* <3:   */   {
/* <4:82 */     return this.createBy;
/* <5:   */   }
/* <6:   */   
/* <7:   */   public void setCreateBy(Integer createBy)
/* <8:   */   {
/* <9:85 */     this.createBy = createBy;
/* =0:   */   }
/* =1:   */   
/* =2:   */   public Integer getUpdateBy()
/* =3:   */   {
/* =4:88 */     return this.updateBy;
/* =5:   */   }
/* =6:   */   
/* =7:   */   public void setUpdateBy(Integer updateBy)
/* =8:   */   {
/* =9:91 */     this.updateBy = updateBy;
/* >0:   */   }
/* >1:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.model.SysUserModel
 * JD-Core Version:    0.7.0.1
 */