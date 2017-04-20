/*   1:    */ package com.base.utils;
/*   2:    */ 
/*   3:    */ public class Pager
/*   4:    */ {
/*   5: 11 */   private int pageId = 1;
/*   6: 12 */   private int rowCount = 0;
/*   7: 13 */   private int pageSize = 20;
/*   8: 14 */   private int pageCount = 0;
/*   9: 15 */   private int pageOffset = 0;
/*  10: 16 */   private int pageTail = 0;
/*  11:    */   private String orderField;
/*  12:    */   private boolean orderDirection;
/*  13: 19 */   private String orderCondition = "";
/*  14: 22 */   private int length = 6;
/*  15: 24 */   private int startIndex = 0;
/*  16: 26 */   private int endIndex = 0;
/*  17:    */   private int[] indexs;
/*  18:    */   
/*  19:    */   public int getLength()
/*  20:    */   {
/*  21: 41 */     return this.length;
/*  22:    */   }
/*  23:    */   
/*  24:    */   public void setLength(int length)
/*  25:    */   {
/*  26: 45 */     this.length = length;
/*  27:    */   }
/*  28:    */   
/*  29:    */   public int[] getIndexs()
/*  30:    */   {
/*  31: 49 */     int len = getEndIndex() - getStartIndex() + 1;
/*  32: 50 */     this.indexs = new int[len];
/*  33: 52 */     for (int i = 0; i < len; i++) {
/*  34: 53 */       this.indexs[i] = (getStartIndex() + i);
/*  35:    */     }
/*  36: 55 */     return this.indexs;
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void setIndexs(int[] indexs)
/*  40:    */   {
/*  41: 59 */     this.indexs = indexs;
/*  42:    */   }
/*  43:    */   
/*  44:    */   public int getStartIndex()
/*  45:    */   {
/*  46: 63 */     this.startIndex = (this.pageId - this.length / 2);
/*  47: 64 */     if (this.startIndex < 1) {
/*  48: 65 */       this.startIndex = 1;
/*  49:    */     }
/*  50: 67 */     return this.startIndex;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public void setStartIndex(int startIndex)
/*  54:    */   {
/*  55: 71 */     this.startIndex = startIndex;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public int getEndIndex()
/*  59:    */   {
/*  60: 75 */     if (getStartIndex() < 1) {
/*  61: 76 */       setStartIndex(1);
/*  62:    */     }
/*  63: 78 */     this.endIndex = (getStartIndex() + this.length <= getPageCount() ? getStartIndex() + this.length : 
/*  64: 79 */       getPageCount());
/*  65: 80 */     return this.endIndex;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void setEndIndex(int endIndex)
/*  69:    */   {
/*  70: 84 */     this.endIndex = endIndex;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public Pager()
/*  74:    */   {
/*  75: 88 */     this.orderDirection = true;
/*  76:    */   }
/*  77:    */   
/*  78:    */   protected void doPage()
/*  79:    */   {
/*  80: 92 */     this.pageCount = (this.rowCount / this.pageSize + 1);
/*  81: 94 */     if ((this.rowCount % this.pageSize == 0) && (this.pageCount > 1)) {
/*  82: 95 */       this.pageCount -= 1;
/*  83:    */     }
/*  84:105 */     this.pageOffset = ((this.pageId - 1) * this.pageSize);
/*  85:106 */     this.pageTail = (this.pageOffset + this.pageSize);
/*  86:107 */     if (this.pageOffset + this.pageSize > this.rowCount) {
/*  87:108 */       this.pageTail = this.rowCount;
/*  88:    */     }
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void setOrderCondition(String orderConditions)
/*  92:    */   {
/*  93:113 */     this.orderCondition = orderConditions;
/*  94:    */   }
/*  95:    */   
/*  96:    */   public String getOrderCondition()
/*  97:    */   {
/*  98:117 */     if ((StringUtil.isEmpty(this.orderCondition)) && 
/*  99:118 */       (this.orderField != null) && (this.orderField.length() != 0)) {
/* 100:119 */       this.orderCondition = 
/* 101:120 */         (" order by " + this.orderField + (this.orderDirection ? " " : " desc "));
/* 102:    */     }
/* 103:123 */     return this.orderCondition;
/* 104:    */   }
/* 105:    */   
/* 106:    */   public String getMysqlQueryCondition()
/* 107:    */   {
/* 108:127 */     String condition = "";
/* 109:128 */     condition = " limit " + this.pageOffset + "," + this.pageSize;
/* 110:129 */     return condition;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public void setOrderDirection(boolean orderDirection)
/* 114:    */   {
/* 115:133 */     this.orderDirection = orderDirection;
/* 116:    */   }
/* 117:    */   
/* 118:    */   public boolean isOrderDirection()
/* 119:    */   {
/* 120:137 */     return this.orderDirection;
/* 121:    */   }
/* 122:    */   
/* 123:    */   public void setOrderField(String orderField)
/* 124:    */   {
/* 125:141 */     this.orderField = orderField;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public String getOrderField()
/* 129:    */   {
/* 130:145 */     return this.orderField;
/* 131:    */   }
/* 132:    */   
/* 133:    */   public void setPageCount(int pageCount)
/* 134:    */   {
/* 135:149 */     this.pageCount = pageCount;
/* 136:    */   }
/* 137:    */   
/* 138:    */   public int getPageCount()
/* 139:    */   {
/* 140:153 */     return this.pageCount;
/* 141:    */   }
/* 142:    */   
/* 143:    */   public void setPageId(int pageId)
/* 144:    */   {
/* 145:157 */     this.pageId = pageId;
/* 146:    */   }
/* 147:    */   
/* 148:    */   public int getPageId()
/* 149:    */   {
/* 150:161 */     return this.pageId;
/* 151:    */   }
/* 152:    */   
/* 153:    */   public void setPageOffset(int pageOffset)
/* 154:    */   {
/* 155:165 */     this.pageOffset = pageOffset;
/* 156:    */   }
/* 157:    */   
/* 158:    */   public int getPageOffset()
/* 159:    */   {
/* 160:169 */     return this.pageOffset;
/* 161:    */   }
/* 162:    */   
/* 163:    */   public void setPageSize(int pageSize)
/* 164:    */   {
/* 165:173 */     this.pageSize = pageSize;
/* 166:    */   }
/* 167:    */   
/* 168:    */   public int getPageSize()
/* 169:    */   {
/* 170:177 */     return this.pageSize;
/* 171:    */   }
/* 172:    */   
/* 173:    */   public void setPageTail(int pageTail)
/* 174:    */   {
/* 175:181 */     this.pageTail = pageTail;
/* 176:    */   }
/* 177:    */   
/* 178:    */   public int getPageTail()
/* 179:    */   {
/* 180:185 */     return this.pageTail;
/* 181:    */   }
/* 182:    */   
/* 183:    */   public void setRowCount(int rowCount)
/* 184:    */   {
/* 185:189 */     this.rowCount = rowCount;
/* 186:190 */     doPage();
/* 187:    */   }
/* 188:    */   
/* 189:    */   public int getRowCount()
/* 190:    */   {
/* 191:194 */     return this.rowCount;
/* 192:    */   }
/* 193:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.Pager
 * JD-Core Version:    0.7.0.1
 */