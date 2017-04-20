/*   1:    */ package com.sys.bean;
/*   2:    */ 
/*   3:    */ import com.base.BaseBean;
/*   4:    */ import java.sql.Timestamp;
/*   5:    */ 
/*   6:    */ public class SysUser
/*   7:    */   extends BaseBean
/*   8:    */ {
/*   9:    */   private Integer id;
/*  10:    */   private String email;
/*  11:    */   private String pwd;
/*  12:    */   private String nickName;
/*  13:    */   private Integer state;
/*  14:    */   private Integer loginCount;
/*  15:    */   private Timestamp loginTime;
/*  16:    */   private Integer deleted;
/*  17:    */   private Timestamp createTime;
/*  18:    */   private Timestamp updateTime;
/*  19:    */   private Integer createBy;
/*  20:    */   private Integer updateBy;
/*  21:    */   private Integer superAdmin;
/*  22:    */   private String roleStr;
/*  23:    */   
/*  24:    */   public String getRoleStr()
/*  25:    */   {
/*  26: 29 */     return this.roleStr;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public void setRoleStr(String roleStr)
/*  30:    */   {
/*  31: 32 */     this.roleStr = roleStr;
/*  32:    */   }
/*  33:    */   
/*  34:    */   public Integer getId()
/*  35:    */   {
/*  36: 35 */     return this.id;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void setId(Integer id)
/*  40:    */   {
/*  41: 38 */     this.id = id;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public String getEmail()
/*  45:    */   {
/*  46: 41 */     return this.email;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void setEmail(String email)
/*  50:    */   {
/*  51: 44 */     this.email = email;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public String getPwd()
/*  55:    */   {
/*  56: 47 */     return this.pwd;
/*  57:    */   }
/*  58:    */   
/*  59:    */   public void setPwd(String pwd)
/*  60:    */   {
/*  61: 50 */     this.pwd = pwd;
/*  62:    */   }
/*  63:    */   
/*  64:    */   public String getNickName()
/*  65:    */   {
/*  66: 53 */     return this.nickName;
/*  67:    */   }
/*  68:    */   
/*  69:    */   public void setNickName(String nickName)
/*  70:    */   {
/*  71: 56 */     this.nickName = nickName;
/*  72:    */   }
/*  73:    */   
/*  74:    */   public Integer getState()
/*  75:    */   {
/*  76: 59 */     return this.state;
/*  77:    */   }
/*  78:    */   
/*  79:    */   public void setState(Integer state)
/*  80:    */   {
/*  81: 62 */     this.state = state;
/*  82:    */   }
/*  83:    */   
/*  84:    */   public Integer getLoginCount()
/*  85:    */   {
/*  86: 65 */     return this.loginCount;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void setLoginCount(Integer loginCount)
/*  90:    */   {
/*  91: 68 */     this.loginCount = loginCount;
/*  92:    */   }
/*  93:    */   
/*  94:    */   public Timestamp getLoginTime()
/*  95:    */   {
/*  96: 71 */     return this.loginTime;
/*  97:    */   }
/*  98:    */   
/*  99:    */   public void setLoginTime(Timestamp loginTime)
/* 100:    */   {
/* 101: 74 */     this.loginTime = loginTime;
/* 102:    */   }
/* 103:    */   
/* 104:    */   public Integer getDeleted()
/* 105:    */   {
/* 106: 77 */     return this.deleted;
/* 107:    */   }
/* 108:    */   
/* 109:    */   public void setDeleted(Integer deleted)
/* 110:    */   {
/* 111: 80 */     this.deleted = deleted;
/* 112:    */   }
/* 113:    */   
/* 114:    */   public Timestamp getCreateTime()
/* 115:    */   {
/* 116: 83 */     return this.createTime;
/* 117:    */   }
/* 118:    */   
/* 119:    */   public void setCreateTime(Timestamp createTime)
/* 120:    */   {
/* 121: 86 */     this.createTime = createTime;
/* 122:    */   }
/* 123:    */   
/* 124:    */   public Timestamp getUpdateTime()
/* 125:    */   {
/* 126: 89 */     return this.updateTime;
/* 127:    */   }
/* 128:    */   
/* 129:    */   public void setUpdateTime(Timestamp updateTime)
/* 130:    */   {
/* 131: 92 */     this.updateTime = updateTime;
/* 132:    */   }
/* 133:    */   
/* 134:    */   public Integer getCreateBy()
/* 135:    */   {
/* 136: 95 */     return this.createBy;
/* 137:    */   }
/* 138:    */   
/* 139:    */   public void setCreateBy(Integer createBy)
/* 140:    */   {
/* 141: 98 */     this.createBy = createBy;
/* 142:    */   }
/* 143:    */   
/* 144:    */   public Integer getUpdateBy()
/* 145:    */   {
/* 146:101 */     return this.updateBy;
/* 147:    */   }
/* 148:    */   
/* 149:    */   public void setUpdateBy(Integer updateBy)
/* 150:    */   {
/* 151:104 */     this.updateBy = updateBy;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public Integer getSuperAdmin()
/* 155:    */   {
/* 156:107 */     return this.superAdmin;
/* 157:    */   }
/* 158:    */   
/* 159:    */   public void setSuperAdmin(Integer superAdmin)
/* 160:    */   {
/* 161:110 */     this.superAdmin = superAdmin;
/* 162:    */   }
/* 163:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.bean.SysUser
 * JD-Core Version:    0.7.0.1
 */