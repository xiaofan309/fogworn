/*   1:    */ package com.desun.mina;
/*   2:    */ 
/*   3:    */ import com.fogworn.bean.TbDev;
/*   4:    */ import com.fogworn.service.TbDevService;
/*   5:    */ import java.io.IOException;
/*   6:    */ import java.net.InetSocketAddress;
/*   7:    */ import javax.annotation.PostConstruct;
/*   8:    */ import javax.annotation.PreDestroy;
/*   9:    */ import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
/*  10:    */ import org.apache.mina.core.service.IoAcceptor;
/*  11:    */ import org.apache.mina.core.session.IdleStatus;
/*  12:    */ import org.apache.mina.core.session.IoSessionConfig;
/*  13:    */ import org.apache.mina.filter.codec.ProtocolCodecFilter;
/*  14:    */ import org.apache.mina.filter.keepalive.KeepAliveFilter;
/*  15:    */ import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
/*  16:    */ import org.apache.mina.filter.logging.LoggingFilter;
/*  17:    */ import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
/*  18:    */ import org.slf4j.Logger;
/*  19:    */ import org.slf4j.LoggerFactory;
/*  20:    */ import org.springframework.beans.factory.annotation.Autowired;
/*  21:    */ import org.springframework.stereotype.Component;
/*  22:    */ 
/*  23:    */ @Component
/*  24:    */ public class MinaServer
/*  25:    */ {
/*  26: 33 */   private static Logger logger = LoggerFactory.getLogger(MinaServer.class);
/*  27:    */   private static final int BufferSize = 10240;
/*  28:    */   @Autowired
/*  29:    */   private TbDevService tbDevService;
/*  30: 37 */   private static int count = 0;
/*  31:    */   IoAcceptor acceptor;
/*  32:    */   @Autowired
/*  33:    */   ServerHandler serverHandler;
/*  34:    */   
/*  35:    */   class StartThread
/*  36:    */     extends Thread
/*  37:    */   {
/*  38:    */     StartThread() {}
/*  39:    */     
/*  40:    */     public void run()
/*  41:    */     {
/*  42:    */       try
/*  43:    */       {
/*  44: 53 */         Thread.sleep(3000L);
/*  45:    */       }
/*  46:    */       catch (InterruptedException e)
/*  47:    */       {
/*  48: 55 */         e.printStackTrace();
/*  49:    */       }
/*  50:    */       try
/*  51:    */       {
/*  52: 59 */         MinaServer.this.startup();
/*  53:    */       }
/*  54:    */       catch (IOException e)
/*  55:    */       {
/*  56: 61 */         e.printStackTrace();
/*  57:    */       }
/*  58:    */     }
/*  59:    */   }
/*  60:    */   
/*  61:    */   @PostConstruct
/*  62:    */   public void init()
/*  63:    */     throws IOException
/*  64:    */   {
/*  65: 70 */     this.acceptor = new NioSocketAcceptor();
/*  66:    */     
/*  67:    */ 
/*  68:    */ 
/*  69: 74 */     this.acceptor.getFilterChain().addLast("logger", new LoggingFilter());
/*  70: 75 */     KeepAliveFilter keepAliveFileter = new KeepAliveFilter(new FogKeepAliveMessageFactory(), IdleStatus.READER_IDLE, KeepAliveRequestTimeoutHandler.CLOSE, 70, 70);
/*  71:    */     
/*  72:    */ 
/*  73: 78 */     this.acceptor.getFilterChain().addLast("FogCumulativeProtocolCodec", new ProtocolCodecFilter(new FogCumulativeProtocolCodecFactory()));
/*  74:    */     
/*  75:    */ 
/*  76:    */ 
/*  77: 82 */     this.acceptor.setHandler(this.serverHandler);
/*  78:    */     
/*  79:    */ 
/*  80: 85 */     this.acceptor.getSessionConfig().setReadBufferSize(10240);
/*  81: 86 */     this.acceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE, 180);
/*  82:    */     
/*  83:    */ 
/*  84:    */ 
/*  85:    */ 
/*  86:    */ 
/*  87: 92 */     this.acceptor.setDefaultLocalAddress(new InetSocketAddress("0.0.0.0", 15002));
/*  88: 93 */     logger.info("-----------------------mina-server init");
/*  89:    */     
/*  90: 95 */     new StartThread().start();
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void startup()
/*  94:    */     throws IOException
/*  95:    */   {
/*  96:103 */     TbDev updateDev = new TbDev();
/*  97:104 */     updateDev.setDevip("");
/*  98:105 */     updateDev.setOfflinetime("");
/*  99:106 */     updateDev.setOnlinetime("");
/* 100:107 */     updateDev.setCurstate("");
/* 101:    */     try
/* 102:    */     {
/* 103:109 */       this.tbDevService.updateSelectiveByAll(updateDev);
/* 104:    */     }
/* 105:    */     catch (Exception e)
/* 106:    */     {
/* 107:111 */       logger.error("tbDevService.updateSelectiveByAll", e);
/* 108:    */     }
/* 109:113 */     this.acceptor.bind();
/* 110:114 */     logger.info("-----------------------mina server lazy start");
/* 111:    */   }
/* 112:    */   
/* 113:    */   @PreDestroy
/* 114:    */   public void shutdown()
/* 115:    */   {
/* 116:119 */     logger.info("-----------------------mina-server shutdown");
/* 117:120 */     this.acceptor.unbind();
/* 118:121 */     this.acceptor.dispose();
/* 119:    */   }
/* 120:    */ }


/* Location:           C:\Users\congxiaofan\Desktop\
 * Qualified Name:     com.desun.mina.MinaServer
 * JD-Core Version:    0.7.0.1
 */