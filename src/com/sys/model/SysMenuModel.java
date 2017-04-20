/*  1:   */ package com.sys.model;
/*  2:   */ 
/*  3:   */ import com.base.BaseModel;
/*  4:   */ import java.sql.Timestamp;
/*  5:   */ 
/*  6:   */ public class SysMenuModel
/*  7:   */   extends BaseModel
/*  8:   */ {
/*  9:   */   private Integer id;
/* 10:   */   private String name;
/* 11:   */   private String url;
/* 12:   */   private Integer parentId;
/* 13:   */   private Integer deleted;
/* 14:   */   private Timestamp createTime;
/* 15:   */   private Timestamp updateTime;
/* 16:   */   private Integer rank;
/* 17:   */   private String actions;
/* 18:   */   
/* 19:   */   public String getActions()
/* 20:   */   {
/* 21:19 */     return this.actions;
/* 22:   */   }
/* 23:   */   
/* 24:   */   public void setActions(String actions)
/* 25:   */   {
/* 26:22 */     this.actions = actions;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public Integer getId()
/* 30:   */   {
/* 31:25 */     return this.id;
/* 32:   */   }
/* 33:   */   
/* 34:   */   public void setId(Integer id)
/* 35:   */   {
/* 36:28 */     this.id = id;
/* 37:   */   }
/* 38:   */   
/* 39:   */   public String getName()
/* 40:   */   {
/* 41:31 */     return this.name;
/* 42:   */   }
/* 43:   */   
/* 44:   */   public void setName(String name)
/* 45:   */   {
/* 46:34 */     this.name = name;
/* 47:   */   }
/* 48:   */   
/* 49:   */   public String getUrl()
/* 50:   */   {
/* 51:37 */     return this.url;
/* 52:   */   }
/* 53:   */   
/* 54:   */   public void setUrl(String url)
/* 55:   */   {
/* 56:40 */     this.url = url;
/* 57:   */   }
/* 58:   */   
/* 59:   */   public Integer getParentId()
/* 60:   */   {
/* 61:43 */     return this.parentId;
/* 62:   */   }
/* 63:   */   
/* 64:   */   public void setParentId(Integer parentId)
/* 65:   */   {
/* 66:46 */     this.parentId = parentId;
/* 67:   */   }
/* 68:   */   
/* 69:   */   public Integer getDeleted()
/* 70:   */   {
/* 71:49 */     return this.deleted;
/* 72:   */   }
/* 73:   */   
/* 74:   */   public void setDeleted(Integer deleted)
/* 75:   */   {
/* 76:52 */     this.deleted = deleted;
/* 77:   */   }
/* 78:   */   
/* 79:   */   public Timestamp getCreateTime()
/* 80:   */   {
/* 81:55 */     return this.createTime;
/* 82:   */   }
/* 83:   */   
/* 84:   */   public void setCreateTime(Timestamp createTime)
/* 85:   */   {
/* 86:58 */     this.createTime = createTime;
/* 87:   */   }
/* 88:   */   
/* 89:   */   public Timestamp getUpdateTime()
/* 90:   */   {
/* 91:61 */     return this.updateTime;
/* 92:   */   }
/* 93:   */   
/* 94:   */   public void setUpdateTime(Timestamp updateTime)
/* 95:   */   {
/* 96:64 */     this.updateTime = updateTime;
/* 97:   */   }
/* 98:   */   
/* 99:   */   public Integer getRank()
/* :0:   */   {
/* :1:67 */     return this.rank;
/* :2:   */   }
/* :3:   */   
/* :4:   */   public void setRank(Integer rank)
/* :5:   */   {
/* :6:70 */     this.rank = rank;
/* :7:   */   }
/* :8:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.model.SysMenuModel
 * JD-Core Version:    0.7.0.1
 */