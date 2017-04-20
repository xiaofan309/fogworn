/*  1:   */ package com.sys.bean;
/*  2:   */ 
/*  3:   */ import com.base.BaseBean;
/*  4:   */ import java.sql.Timestamp;
/*  5:   */ import java.util.List;
/*  6:   */ 
/*  7:   */ public class SysMenu
/*  8:   */   extends BaseBean
/*  9:   */ {
/* 10:   */   private Integer id;
/* 11:   */   private String name;
/* 12:   */   private String url;
/* 13:   */   private Integer parentId;
/* 14:   */   private Integer deleted;
/* 15:   */   private Timestamp createTime;
/* 16:   */   private Timestamp updateTime;
/* 17:   */   private Integer rank;
/* 18:   */   private String actions;
/* 19:   */   private int subCount;
/* 20:   */   private List<SysMenuBtn> btns;
/* 21:   */   
/* 22:   */   public Integer getId()
/* 23:   */   {
/* 24:28 */     return this.id;
/* 25:   */   }
/* 26:   */   
/* 27:   */   public void setId(Integer id)
/* 28:   */   {
/* 29:31 */     this.id = id;
/* 30:   */   }
/* 31:   */   
/* 32:   */   public String getName()
/* 33:   */   {
/* 34:34 */     return this.name;
/* 35:   */   }
/* 36:   */   
/* 37:   */   public void setName(String name)
/* 38:   */   {
/* 39:37 */     this.name = name;
/* 40:   */   }
/* 41:   */   
/* 42:   */   public String getUrl()
/* 43:   */   {
/* 44:40 */     return this.url;
/* 45:   */   }
/* 46:   */   
/* 47:   */   public void setUrl(String url)
/* 48:   */   {
/* 49:43 */     this.url = url;
/* 50:   */   }
/* 51:   */   
/* 52:   */   public Integer getParentId()
/* 53:   */   {
/* 54:46 */     return this.parentId;
/* 55:   */   }
/* 56:   */   
/* 57:   */   public void setParentId(Integer parentId)
/* 58:   */   {
/* 59:49 */     this.parentId = parentId;
/* 60:   */   }
/* 61:   */   
/* 62:   */   public Integer getDeleted()
/* 63:   */   {
/* 64:52 */     return this.deleted;
/* 65:   */   }
/* 66:   */   
/* 67:   */   public void setDeleted(Integer deleted)
/* 68:   */   {
/* 69:55 */     this.deleted = deleted;
/* 70:   */   }
/* 71:   */   
/* 72:   */   public Timestamp getCreateTime()
/* 73:   */   {
/* 74:58 */     return this.createTime;
/* 75:   */   }
/* 76:   */   
/* 77:   */   public void setCreateTime(Timestamp createTime)
/* 78:   */   {
/* 79:61 */     this.createTime = createTime;
/* 80:   */   }
/* 81:   */   
/* 82:   */   public Timestamp getUpdateTime()
/* 83:   */   {
/* 84:64 */     return this.updateTime;
/* 85:   */   }
/* 86:   */   
/* 87:   */   public void setUpdateTime(Timestamp updateTime)
/* 88:   */   {
/* 89:67 */     this.updateTime = updateTime;
/* 90:   */   }
/* 91:   */   
/* 92:   */   public Integer getRank()
/* 93:   */   {
/* 94:70 */     return this.rank;
/* 95:   */   }
/* 96:   */   
/* 97:   */   public void setRank(Integer rank)
/* 98:   */   {
/* 99:73 */     this.rank = rank;
/* :0:   */   }
/* :1:   */   
/* :2:   */   public List<SysMenuBtn> getBtns()
/* :3:   */   {
/* :4:76 */     return this.btns;
/* :5:   */   }
/* :6:   */   
/* :7:   */   public void setBtns(List<SysMenuBtn> btns)
/* :8:   */   {
/* :9:79 */     this.btns = btns;
/* ;0:   */   }
/* ;1:   */   
/* ;2:   */   public String getActions()
/* ;3:   */   {
/* ;4:82 */     return this.actions;
/* ;5:   */   }
/* ;6:   */   
/* ;7:   */   public void setActions(String actions)
/* ;8:   */   {
/* ;9:85 */     this.actions = actions;
/* <0:   */   }
/* <1:   */   
/* <2:   */   public int getSubCount()
/* <3:   */   {
/* <4:88 */     return this.subCount;
/* <5:   */   }
/* <6:   */   
/* <7:   */   public void setSubCount(int subCount)
/* <8:   */   {
/* <9:91 */     this.subCount = subCount;
/* =0:   */   }
/* =1:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.bean.SysMenu
 * JD-Core Version:    0.7.0.1
 */