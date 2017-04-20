/*  1:   */ package com.base.utils.json;
/*  2:   */ 
/*  3:   */ import org.json.JSONString;
/*  4:   */ 
/*  5:   */ public class JSONStringObject
/*  6:   */   implements JSONString
/*  7:   */ {
/*  8: 7 */   private String jsonString = null;
/*  9:   */   
/* 10:   */   public JSONStringObject(String jsonString)
/* 11:   */   {
/* 12:10 */     this.jsonString = jsonString;
/* 13:   */   }
/* 14:   */   
/* 15:   */   public String toString()
/* 16:   */   {
/* 17:15 */     return this.jsonString;
/* 18:   */   }
/* 19:   */   
/* 20:   */   public String toJSONString()
/* 21:   */   {
/* 22:19 */     return this.jsonString;
/* 23:   */   }
/* 24:   */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.json.JSONStringObject
 * JD-Core Version:    0.7.0.1
 */