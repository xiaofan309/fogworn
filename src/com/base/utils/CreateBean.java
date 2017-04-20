/*   1:    */ package com.base.utils;
/*   2:    */ 
/*   3:    */ import java.io.File;
/*   4:    */ import java.io.FileWriter;
/*   5:    */ import java.io.IOException;
/*   6:    */ import java.io.PrintStream;
/*   7:    */ import java.sql.Connection;
/*   8:    */ import java.sql.DriverManager;
/*   9:    */ import java.sql.PreparedStatement;
/*  10:    */ import java.sql.ResultSet;
/*  11:    */ import java.sql.SQLException;
/*  12:    */ import java.text.SimpleDateFormat;
/*  13:    */ import java.util.ArrayList;
/*  14:    */ import java.util.Date;
/*  15:    */ import java.util.HashMap;
/*  16:    */ import java.util.List;
/*  17:    */ import java.util.Map;
/*  18:    */ 
/*  19:    */ public class CreateBean
/*  20:    */ {
/*  21: 27 */   private Connection connection = null;
/*  22:    */   static String url;
/*  23:    */   static String username;
/*  24:    */   static String password;
/*  25: 31 */   static String rt = "\r\t";
/*  26: 32 */   String SQLTables = "show tables";
/*  27:    */   private String method;
/*  28:    */   private String argv;
/*  29:    */   
/*  30:    */   static
/*  31:    */   {
/*  32:    */     try
/*  33:    */     {
/*  34: 35 */       Class.forName("com.mysql.jdbc.Driver");
/*  35:    */     }
/*  36:    */     catch (Exception e)
/*  37:    */     {
/*  38: 37 */       e.printStackTrace();
/*  39:    */     }
/*  40:    */   }
/*  41:    */   
/*  42:    */   public void setMysqlInfo(String url, String username, String password)
/*  43:    */   {
/*  44: 42 */     url = url;
/*  45: 43 */     username = username;
/*  46: 44 */     password = password;
/*  47:    */   }
/*  48:    */   
/*  49:    */   public void setConnection(Connection connection)
/*  50:    */   {
/*  51: 47 */     this.connection = connection;
/*  52:    */   }
/*  53:    */   
/*  54:    */   public Connection getConnection()
/*  55:    */     throws SQLException
/*  56:    */   {
/*  57: 50 */     return DriverManager.getConnection(url, username, password);
/*  58:    */   }
/*  59:    */   
/*  60:    */   public List<String> getTables()
/*  61:    */     throws SQLException
/*  62:    */   {
/*  63: 53 */     Connection con = getConnection();
/*  64: 54 */     PreparedStatement ps = con.prepareStatement(this.SQLTables);
/*  65: 55 */     ResultSet rs = ps.executeQuery();
/*  66: 56 */     List<String> list = new ArrayList();
/*  67: 57 */     while (rs.next())
/*  68:    */     {
/*  69: 58 */       String tableName = rs.getString(1);
/*  70: 59 */       list.add(tableName);
/*  71:    */     }
/*  72: 61 */     rs.close();
/*  73: 62 */     ps.close();
/*  74: 63 */     con.close();
/*  75: 64 */     return list;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public List<ColumnData> getColumnDatas(String tableName)
/*  79:    */     throws SQLException
/*  80:    */   {
/*  81: 74 */     String SQLColumns = "SELECT distinct COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM information_schema.columns WHERE table_name =  '" + tableName + "' ";
/*  82: 75 */     Connection con = getConnection();
/*  83: 76 */     PreparedStatement ps = con.prepareStatement(SQLColumns);
/*  84: 77 */     List<ColumnData> columnList = new ArrayList();
/*  85: 78 */     ResultSet rs = ps.executeQuery();
/*  86: 79 */     StringBuffer str = new StringBuffer();
/*  87: 80 */     StringBuffer getset = new StringBuffer();
/*  88: 81 */     while (rs.next())
/*  89:    */     {
/*  90: 82 */       String name = rs.getString(1);
/*  91: 83 */       String type = rs.getString(2);
/*  92: 84 */       String comment = rs.getString(3);
/*  93: 85 */       type = getType(type);
/*  94: 86 */       String propertyName = getPropertyName(name);
/*  95:    */       
/*  96: 88 */       ColumnData cd = new ColumnData();
/*  97: 89 */       cd.setColumnName(name);
/*  98: 90 */       cd.setPropertyName(propertyName);
/*  99: 91 */       cd.setDataType(type);
/* 100: 92 */       cd.setColumnComment(comment);
/* 101: 93 */       columnList.add(cd);
/* 102:    */     }
/* 103: 95 */     this.argv = str.toString();
/* 104: 96 */     this.method = getset.toString();
/* 105: 97 */     rs.close();
/* 106: 98 */     ps.close();
/* 107: 99 */     con.close();
/* 108:100 */     return columnList;
/* 109:    */   }
/* 110:    */   
/* 111:    */   public String getBeanFeilds(String tableName)
/* 112:    */     throws SQLException
/* 113:    */   {
/* 114:113 */     List<ColumnData> dataList = getColumnDatas(tableName);
/* 115:114 */     StringBuffer str = new StringBuffer();
/* 116:115 */     StringBuffer getset = new StringBuffer();
/* 117:116 */     for (ColumnData d : dataList)
/* 118:    */     {
/* 119:117 */       String name = d.getPropertyName();
/* 120:118 */       String type = d.getDataType();
/* 121:119 */       String comment = d.getColumnComment();
/* 122:    */       
/* 123:121 */       String maxChar = name.substring(0, 1).toUpperCase();
/* 124:122 */       str.append("\r\t").append("private ").append(type + " ").append(name).append(";//   ").append(comment);
/* 125:123 */       String method = maxChar + name.substring(1, name.length());
/* 126:124 */       getset.append("\r\t").append("public ").append(type + " ").append("get" + method + "() {\r\t");
/* 127:125 */       getset.append("    return this.").append(name).append(";\r\t}");
/* 128:126 */       getset.append("\r\t").append("public void ").append("set" + method + "(" + type + " " + name + ") {\r\t");
/* 129:127 */       getset.append("    this." + name + "=").append(name).append(";\r\t}");
/* 130:    */     }
/* 131:129 */     this.argv = str.toString();
/* 132:130 */     this.method = getset.toString();
/* 133:131 */     return this.argv + this.method;
/* 134:    */   }
/* 135:    */   
/* 136:    */   public String getType(String type)
/* 137:    */   {
/* 138:171 */     type = type.toLowerCase();
/* 139:172 */     if (("char".equals(type)) || ("varchar".equals(type))) {
/* 140:173 */       return "String";
/* 141:    */     }
/* 142:174 */     if ("int".equals(type)) {
/* 143:175 */       return "Integer";
/* 144:    */     }
/* 145:176 */     if ("bigint".equals(type)) {
/* 146:177 */       return "Long";
/* 147:    */     }
/* 148:178 */     if (("timestamp".equals(type)) || ("date".equals(type)) || ("datetime".equals(type))) {
/* 149:179 */       return "java.sql.Timestamp";
/* 150:    */     }
/* 151:181 */     return null;
/* 152:    */   }
/* 153:    */   
/* 154:    */   public String getPropertyName(String columnName)
/* 155:    */   {
/* 156:184 */     String[] split = columnName.split("_");
/* 157:185 */     if (split.length > 1)
/* 158:    */     {
/* 159:186 */       StringBuffer sb = new StringBuffer();
/* 160:187 */       for (int i = 0; i < split.length; i++) {
/* 161:188 */         if (i == 0)
/* 162:    */         {
/* 163:189 */           String tempColumnName = split[i].substring(0, 1).toLowerCase() + split[i].substring(1, split[i].length());
/* 164:190 */           sb.append(tempColumnName);
/* 165:    */         }
/* 166:    */         else
/* 167:    */         {
/* 168:192 */           String tempColumnName = split[i].substring(0, 1).toUpperCase() + split[i].substring(1, split[i].length());
/* 169:193 */           sb.append(tempColumnName);
/* 170:    */         }
/* 171:    */       }
/* 172:196 */       return sb.toString();
/* 173:    */     }
/* 174:198 */     String tempColumn = split[0].substring(0, 1).toLowerCase() + split[0].substring(1, split[0].length());
/* 175:199 */     return tempColumn;
/* 176:    */   }
/* 177:    */   
/* 178:    */   public void getPackage(int type, String createPath, String content, String packageName, String className, String extendsClassName, String... importName)
/* 179:    */     throws Exception
/* 180:    */   {
/* 181:204 */     if (packageName == null) {
/* 182:205 */       packageName = "";
/* 183:    */     }
/* 184:207 */     StringBuffer sb = new StringBuffer();
/* 185:208 */     sb.append("package ").append(packageName).append(";\r");
/* 186:209 */     sb.append("\r");
/* 187:210 */     for (int i = 0; i < importName.length; i++) {
/* 188:211 */       sb.append("import ").append(importName[i]).append(";\r");
/* 189:    */     }
/* 190:213 */     sb.append("\r");
/* 191:214 */     sb.append("/**\r *  entity. @author wolf Date:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()) + "\r */");
/* 192:215 */     sb.append("\r");
/* 193:216 */     sb.append("\rpublic class ").append(className);
/* 194:217 */     if (extendsClassName != null) {
/* 195:218 */       sb.append(" extends ").append(extendsClassName);
/* 196:    */     }
/* 197:220 */     if (type == 1) {
/* 198:221 */       sb.append(" ").append("implements java.io.Serializable {\r");
/* 199:    */     } else {
/* 200:223 */       sb.append(" {\r");
/* 201:    */     }
/* 202:225 */     sb.append("\r\t");
/* 203:226 */     sb.append("private static final long serialVersionUID = 1L;\r\t");
/* 204:227 */     String temp = className.substring(0, 1).toLowerCase();
/* 205:228 */     temp = temp + className.substring(1, className.length());
/* 206:229 */     if (type == 1) {
/* 207:230 */       sb.append("private " + className + " " + temp + "; // entity ");
/* 208:    */     }
/* 209:232 */     sb.append(content);
/* 210:233 */     sb.append("\r}");
/* 211:234 */     System.out.println(sb.toString());
/* 212:235 */     createFile(createPath, "", sb.toString());
/* 213:    */   }
/* 214:    */   
/* 215:    */   public String getTablesNameToClassName(String tableName)
/* 216:    */   {
/* 217:248 */     String[] split = tableName.split("_");
/* 218:249 */     if (split.length > 1)
/* 219:    */     {
/* 220:250 */       StringBuffer sb = new StringBuffer();
/* 221:251 */       for (int i = 0; i < split.length; i++)
/* 222:    */       {
/* 223:252 */         String tempTableName = split[i].substring(0, 1).toUpperCase() + split[i].substring(1, split[i].length());
/* 224:253 */         sb.append(tempTableName);
/* 225:    */       }
/* 226:255 */       System.out.println(sb.toString());
/* 227:256 */       return sb.toString();
/* 228:    */     }
/* 229:258 */     String tempTables = split[0].substring(0, 1).toUpperCase() + split[0].substring(1, split[0].length());
/* 230:259 */     return tempTables;
/* 231:    */   }
/* 232:    */   
/* 233:    */   public void createFile(String path, String fileName, String str)
/* 234:    */     throws IOException
/* 235:    */   {
/* 236:274 */     FileWriter writer = new FileWriter(new File(path + fileName));
/* 237:275 */     writer.write(new String(str.getBytes("utf-8")));
/* 238:276 */     writer.flush();
/* 239:277 */     writer.close();
/* 240:    */   }
/* 241:    */   
/* 242:287 */   static String selectStr = "select ";
/* 243:288 */   static String from = " from ";
/* 244:    */   
/* 245:    */   public Map<String, Object> getAutoCreateSql(String tableName)
/* 246:    */     throws Exception
/* 247:    */   {
/* 248:291 */     Map<String, Object> sqlMap = new HashMap();
/* 249:292 */     List<ColumnData> columnDatas = getColumnDatas(tableName);
/* 250:293 */     String columns = getColumnSplit(columnDatas);
/* 251:294 */     String columnsWithAlias = getColumnSplitWithAlias(columnDatas);
/* 252:295 */     String properties = getPropertiesSplit(columnDatas);
/* 253:296 */     String[] columnList = getColumnList(columns);
/* 254:297 */     String[] propertyList = getPropertyList(properties);
/* 255:298 */     String insert = "insert into " + tableName + "(" + columns.replaceAll("\\|", ",") + ")\n values(#{" + properties.replaceAll("\\|", "},#{") + "})";
/* 256:299 */     String update = getUpdateSql(tableName, columnList, propertyList);
/* 257:300 */     String updateSelective = getUpdateSelectiveSql(tableName, columnDatas);
/* 258:301 */     String selectById = getSelectByIdSql(tableName, columnList, propertyList);
/* 259:302 */     String delete = getDeleteSql(tableName, columnList, propertyList);
/* 260:303 */     sqlMap.put("columnList", columnList);
/* 261:304 */     sqlMap.put("propertyList", propertyList);
/* 262:305 */     sqlMap.put("columnFields", getColumnFields(columnsWithAlias));
/* 263:306 */     sqlMap.put("insert", insert.replace("#{createTime}", "now()").replace("#{updateTime}", "now()"));
/* 264:307 */     sqlMap.put("update", update.replace("#{createTime}", "now()").replace("#{updateTime}", "now()"));
/* 265:308 */     sqlMap.put("delete", delete);
/* 266:309 */     sqlMap.put("updateSelective", updateSelective);
/* 267:310 */     sqlMap.put("selectById", selectById);
/* 268:311 */     return sqlMap;
/* 269:    */   }
/* 270:    */   
/* 271:    */   public String getDeleteSql(String tableName, String[] columnsList, String[] propertiesList)
/* 272:    */     throws SQLException
/* 273:    */   {
/* 274:322 */     StringBuffer sb = new StringBuffer();
/* 275:323 */     sb.append("delete ");
/* 276:324 */     sb.append("\t from ").append(tableName).append(" where ");
/* 277:325 */     sb.append(columnsList[0]).append(" = #{").append(propertiesList[0]).append("}");
/* 278:326 */     return sb.toString();
/* 279:    */   }
/* 280:    */   
/* 281:    */   public String getSelectByIdSql(String tableName, String[] columnsList, String[] propertiesList)
/* 282:    */     throws SQLException
/* 283:    */   {
/* 284:337 */     StringBuffer sb = new StringBuffer();
/* 285:338 */     sb.append("select <include refid=\"Base_Column_List\" /> \n");
/* 286:339 */     sb.append("\t from ").append(tableName).append(" t where ");
/* 287:340 */     sb.append(columnsList[0]).append(" = #{").append(propertiesList[0]).append("}");
/* 288:341 */     return sb.toString();
/* 289:    */   }
/* 290:    */   
/* 291:    */   public String getColumnFields(String columns)
/* 292:    */     throws SQLException
/* 293:    */   {
/* 294:351 */     String fields = columns;
/* 295:352 */     if ((fields != null) && (!"".equals(fields))) {
/* 296:353 */       fields = fields.replaceAll("[|]", ",");
/* 297:    */     }
/* 298:355 */     return fields;
/* 299:    */   }
/* 300:    */   
/* 301:    */   public String[] getColumnList(String columns)
/* 302:    */     throws SQLException
/* 303:    */   {
/* 304:365 */     String[] columnList = columns.split("[|]");
/* 305:366 */     return columnList;
/* 306:    */   }
/* 307:    */   
/* 308:    */   public String[] getPropertyList(String properties)
/* 309:    */     throws SQLException
/* 310:    */   {
/* 311:369 */     String[] propertyList = properties.split("[|]");
/* 312:370 */     return propertyList;
/* 313:    */   }
/* 314:    */   
/* 315:    */   public String getUpdateSql(String tableName, String[] columnsList, String[] propertyList)
/* 316:    */     throws SQLException
/* 317:    */   {
/* 318:381 */     StringBuffer sb = new StringBuffer();
/* 319:383 */     for (int i = 1; i < columnsList.length; i++)
/* 320:    */     {
/* 321:384 */       String column = columnsList[i];
/* 322:385 */       String property = propertyList[i];
/* 323:386 */       if (!"CREATETIME".equals(column.toUpperCase()))
/* 324:    */       {
/* 325:389 */         if ("UPDATETIME".equals(column.toUpperCase())) {
/* 326:390 */           sb.append(column + "=now()");
/* 327:    */         } else {
/* 328:392 */           sb.append(column + "=#{" + property + "}");
/* 329:    */         }
/* 330:394 */         if (i + 1 < columnsList.length) {
/* 331:395 */           sb.append(",");
/* 332:    */         }
/* 333:    */       }
/* 334:    */     }
/* 335:398 */     String update = "update " + tableName + " set " + sb.toString() + " where " + columnsList[0] + "=#{" + propertyList[0] + "}";
/* 336:399 */     return update;
/* 337:    */   }
/* 338:    */   
/* 339:    */   public String getUpdateSelectiveSql(String tableName, List<ColumnData> columnList)
/* 340:    */     throws SQLException
/* 341:    */   {
/* 342:403 */     StringBuffer sb = new StringBuffer();
/* 343:404 */     ColumnData cd = (ColumnData)columnList.get(0);
/* 344:405 */     sb.append("\t<trim  suffixOverrides=\",\" >\n");
/* 345:406 */     for (int i = 1; i < columnList.size(); i++)
/* 346:    */     {
/* 347:407 */       ColumnData data = (ColumnData)columnList.get(i);
/* 348:408 */       String columnName = data.getColumnName();
/* 349:409 */       String propertyName = data.getPropertyName();
/* 350:410 */       sb.append("\t<if test=\"").append(propertyName).append(" != null ");
/* 351:412 */       if ("String" == data.getDataType()) {
/* 352:413 */         sb.append(" and ").append(propertyName).append(" != ''");
/* 353:    */       }
/* 354:415 */       sb.append(" \">\n\t\t");
/* 355:416 */       sb.append(columnName + "=#{" + propertyName + "},\n");
/* 356:417 */       sb.append("\t</if>\n");
/* 357:    */     }
/* 358:419 */     sb.append("\t</trim>");
/* 359:420 */     String update = "update " + tableName + " set \n" + sb.toString() + " where " + cd.getColumnName() + "=#{" + cd.getPropertyName() + "}";
/* 360:421 */     return update;
/* 361:    */   }
/* 362:    */   
/* 363:    */   public String getColumnSplit(List<ColumnData> columnList)
/* 364:    */     throws SQLException
/* 365:    */   {
/* 366:435 */     StringBuffer commonColumns = new StringBuffer();
/* 367:436 */     for (ColumnData data : columnList) {
/* 368:437 */       commonColumns.append(data.getColumnName() + "|");
/* 369:    */     }
/* 370:439 */     return commonColumns.delete(commonColumns.length() - 1, commonColumns.length()).toString();
/* 371:    */   }
/* 372:    */   
/* 373:    */   public String getColumnSplitWithAlias(List<ColumnData> columnList)
/* 374:    */     throws SQLException
/* 375:    */   {
/* 376:442 */     StringBuffer commonColumns = new StringBuffer();
/* 377:443 */     for (ColumnData data : columnList) {
/* 378:444 */       commonColumns.append("t." + data.getColumnName() + "|");
/* 379:    */     }
/* 380:446 */     return commonColumns.delete(commonColumns.length() - 1, commonColumns.length()).toString();
/* 381:    */   }
/* 382:    */   
/* 383:    */   public String getPropertiesSplit(List<ColumnData> columnList)
/* 384:    */     throws SQLException
/* 385:    */   {
/* 386:450 */     StringBuffer commonColumns = new StringBuffer();
/* 387:451 */     for (ColumnData data : columnList) {
/* 388:452 */       commonColumns.append(data.getPropertyName() + "|");
/* 389:    */     }
/* 390:454 */     return commonColumns.delete(commonColumns.length() - 1, commonColumns.length()).toString();
/* 391:    */   }
/* 392:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.base.utils.CreateBean
 * JD-Core Version:    0.7.0.1
 */