/*  1:   */ package com.sys.bean;
/*  2:   */ 
/*  3:   */ import com.base.BaseBean;
/*  4:   */ 
/*  5:   */ public class SiteScore
/*  6:   */   extends BaseBean
/*  7:   */ {
/*  8:   */   private Integer siteId;
/*  9:   */   private Integer viewNum;
/* 10:   */   private Integer likeNum;
/* 11:   */   private Integer shareNum;
/* 12:   */   private Integer clickNum;
/* 13:   */   private Integer collectNum;
/* 14:   */   private Integer commentNum;
/* 15:   */   
/* 16:   */   public SiteScore() {}
/* 17:   */   
/* 18:   */   public SiteScore(Integer siteId, Integer viewNum, Integer likeNum, Integer shareNum, Integer clickNum, Integer collectNum, Integer commentNum)
/* 19:   */   {
/* 20:15 */     this.siteId = siteId;
/* 21:16 */     this.viewNum = viewNum;
/* 22:17 */     this.likeNum = likeNum;
/* 23:18 */     this.shareNum = shareNum;
/* 24:19 */     this.clickNum = clickNum;
/* 25:20 */     this.collectNum = collectNum;
/* 26:21 */     this.commentNum = commentNum;
/* 27:   */   }
/* 28:   */   
/* 29:   */   public static enum ScoreType
/* 30:   */   {
/* 31:34 */     VIEW(1, "viewNum"),  LIKE(2, "likeNum"),  SHARE(1, "shareNum"),  CLICK(2, 
/* 32:35 */       "clickNum"),  COLLECT(1, "collectNum"),  COMMENT(2, "commentNum");
/* 33:   */     
/* 34:   */     public int key;
/* 35:   */     public String value;
/* 36:   */     
/* 37:   */     private ScoreType(int key, String value)
/* 38:   */     {
/* 39:40 */       this.key = key;
/* 40:41 */       this.value = value;
/* 41:   */     }
/* 42:   */     
/* 43:   */     public static ScoreType get(int key)
/* 44:   */     {
/* 45:45 */       ScoreType[] values = values();
/* 46:46 */       for (ScoreType object : values) {
/* 47:47 */         if (object.key == key) {
/* 48:48 */           return object;
/* 49:   */         }
/* 50:   */       }
/* 51:51 */       return null;
/* 52:   */     }
/* 53:   */   }
/* 54:   */   
/* 55:   */   public Integer getSiteId()
/* 56:   */   {
/* 57:57 */     return this.siteId;
/* 58:   */   }
/* 59:   */   
/* 60:   */   public void setSiteId(Integer siteId)
/* 61:   */   {
/* 62:60 */     this.siteId = siteId;
/* 63:   */   }
/* 64:   */   
/* 65:   */   public Integer getViewNum()
/* 66:   */   {
/* 67:63 */     return this.viewNum;
/* 68:   */   }
/* 69:   */   
/* 70:   */   public void setViewNum(Integer viewNum)
/* 71:   */   {
/* 72:66 */     this.viewNum = viewNum;
/* 73:   */   }
/* 74:   */   
/* 75:   */   public Integer getLikeNum()
/* 76:   */   {
/* 77:69 */     return this.likeNum;
/* 78:   */   }
/* 79:   */   
/* 80:   */   public void setLikeNum(Integer likeNum)
/* 81:   */   {
/* 82:72 */     this.likeNum = likeNum;
/* 83:   */   }
/* 84:   */   
/* 85:   */   public Integer getShareNum()
/* 86:   */   {
/* 87:75 */     return this.shareNum;
/* 88:   */   }
/* 89:   */   
/* 90:   */   public void setShareNum(Integer shareNum)
/* 91:   */   {
/* 92:78 */     this.shareNum = shareNum;
/* 93:   */   }
/* 94:   */   
/* 95:   */   public Integer getClickNum()
/* 96:   */   {
/* 97:81 */     return this.clickNum;
/* 98:   */   }
/* 99:   */   
/* :0:   */   public void setClickNum(Integer clickNum)
/* :1:   */   {
/* :2:84 */     this.clickNum = clickNum;
/* :3:   */   }
/* :4:   */   
/* :5:   */   public Integer getCollectNum()
/* :6:   */   {
/* :7:87 */     return this.collectNum;
/* :8:   */   }
/* :9:   */   
/* ;0:   */   public void setCollectNum(Integer collectNum)
/* ;1:   */   {
/* ;2:90 */     this.collectNum = collectNum;
/* ;3:   */   }
/* ;4:   */   
/* ;5:   */   public Integer getCommentNum()
/* ;6:   */   {
/* ;7:93 */     return this.commentNum;
/* ;8:   */   }
/* ;9:   */   
/* <0:   */   public void setCommentNum(Integer commentNum)
/* <1:   */   {
/* <2:96 */     this.commentNum = commentNum;
/* <3:   */   }
/* <4:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.sys.bean.SiteScore
 * JD-Core Version:    0.7.0.1
 */