package com.desun.mina;

import java.io.IOException;
import java.net.InetSocketAddress;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fogworn.bean.TbDev;
import com.fogworn.service.TbDevService;

@Component
public class MinaServer {
	private static Logger logger = LoggerFactory.getLogger(MinaServer.class);
	private static final int BufferSize = 10240;
	@Autowired
	private TbDevService tbDevService;
	private static int count = 0;
	IoAcceptor acceptor;
	@Autowired
	ServerHandler serverHandler;

	class StartThread extends Thread {
		StartThread() {
		}

		public void run() {
			try {
				Thread.sleep(3000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				MinaServer.this.startup();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	@PostConstruct
	public void init() throws IOException {
		this.acceptor = new NioSocketAcceptor();

		this.acceptor.getFilterChain().addLast("logger", new LoggingFilter());
		KeepAliveFilter keepAliveFileter = new KeepAliveFilter(
				new FogKeepAliveMessageFactory(), IdleStatus.READER_IDLE,
				KeepAliveRequestTimeoutHandler.CLOSE, 70, 70);
		this.acceptor.getFilterChain()
				.addLast(
						"FogCumulativeProtocolCodec",
						new ProtocolCodecFilter(
								new FogCumulativeProtocolCodecFactory()));
		this.acceptor.setHandler(this.serverHandler);
		this.acceptor.getSessionConfig().setReadBufferSize(10240);
		this.acceptor.getSessionConfig().setIdleTime(IdleStatus.READER_IDLE,
				180);

		this.acceptor.setDefaultLocalAddress(new InetSocketAddress("0.0.0.0",
				15002));
		logger.info("-----------------------mina-server init");
		new StartThread().start();
	}

	public void startup() throws IOException {
		TbDev updateDev = new TbDev();
		updateDev.setDevip("");
		updateDev.setOfflinetime("");
		updateDev.setOnlinetime("");
		updateDev.setCurstate("");
		try {
			this.tbDevService.updateSelectiveByAll(updateDev);
		} catch (Exception e) {
			logger.error("tbDevService.updateSelectiveByAll", e);
		}
		this.acceptor.bind();
		logger.info("-----------------------mina server lazy start");
	}

	@PreDestroy
	public void shutdown() {
		logger.info("-----------------------mina-server shutdown");
		this.acceptor.unbind();
		this.acceptor.dispose();
	}
}
