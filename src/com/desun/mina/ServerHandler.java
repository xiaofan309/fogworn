package com.desun.mina;

import java.net.InetSocketAddress;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desun.protocal.BitConverter;
import com.desun.protocal.DesunProtocal;
import com.fogworn.bean.TbDev;
import com.fogworn.bean.TbDevlog;
import com.fogworn.bean.TbDevparams;
import com.fogworn.bean.TbVisibHistory;
import com.fogworn.service.TbDevService;
import com.fogworn.service.TbDevlogService;
import com.fogworn.service.TbDevparamsService;
import com.fogworn.service.TbVisibHistoryService;

@Component
public class ServerHandler extends IoHandlerAdapter {
	private static Logger logger = LoggerFactory.getLogger(ServerHandler.class);
	private static final String DEV_OBJECT = "dev_object";
	@Autowired
	private TbDevService tbDevService;
	@Autowired
	private TbDevlogService tbDevlogService;
	@Autowired
	private TbDevparamsService tbDevparamsService;
	@Autowired
	private TbVisibHistoryService tbVisibHistoryService;
	@Autowired
	private DesunProtocal desunProtocal;
	public static final String RECEIVE_DATA = "data_received";
	private static ConcurrentHashMap<String, IoSession> sessionMap = new ConcurrentHashMap();

	public static IoSession getSession(String devcd) {
		return (IoSession) sessionMap.get(devcd);
	}

	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		logger.error("session exceptionCaught", cause);
		session.close(true);
	}

	public void sessionOpened(IoSession session) throws Exception {
		logger.warn("connected: " + session.getRemoteAddress().toString());
	}

	public void sessionClosed(IoSession session) throws Exception {
		logger.warn("sessionClosed ");
		if (session == null) {
			logger.error("session closed: session is empty!!!");
			return;
		}
		TbDev dev = (TbDev) session.getAttribute("dev_object");
		if ((dev == null) || (dev.getId() == null)) {
			return;
		}
		String devCd = dev.getDevno();
		IoSession tmpSession = (IoSession) sessionMap.get(devCd);
		if ((tmpSession != null) && (tmpSession.equals(session))) {
			sessionMap.remove(devCd);
			TbDev updateDev = new TbDev();
			updateDev.setDevno(devCd);
			String ip = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
			updateDev.setDevip(ip);
			updateDev.setOfflinetime(BitConverter.getCurrentTime());
			updateDev.setCurstate("离线");
			try {
				this.tbDevService.updateSelectiveByDevno(updateDev);
			} catch (Exception e) {
				logger.error("tbDevService.queryByDevno", e);
			}
		}
	}

	public void messageReceived(IoSession session, Object message) throws Exception {
		if ((message instanceof IoBuffer)) {
			IoBuffer buffer = (IoBuffer) message;
			byte[] data = new byte[buffer.limit()];
			buffer.get(data);
			if ((data[0] == -91) && (data[1] == -18)) {
				Map<String, String> visibilityInf = this.desunProtocal.parseHeaderVisibility(data);
				String devCd = (String) visibilityInf.get("RESULT_MASTERADDR");
				logger.warn("receive CommAddr=" + devCd + " visib=" + (String) visibilityInf.get("RESULT_VISIBILITY"));
				if ((devCd == null) || ("".equals(devCd))) {
					session.close(true);
					return;
				}
				TbDev tbDev = null;
				try {
					tbDev = this.tbDevService.queryByDevno(devCd);
				} catch (Exception e) {
					logger.error("tbDevService.queryByDevno", e);
					session.close(true);
				}
				if ((tbDev != null) && (tbDev.getId() != null)) {
					if (sessionMap.containsKey(devCd)) {
						IoSession oldSession = (IoSession) sessionMap.remove(devCd);
						if ((oldSession != null) && (session != oldSession)
								&& (!session.containsAttribute("dev_object"))) {
							oldSession.removeAttribute("dev_object");
							oldSession.close(true);
						}
					}
					session.setAttributeIfAbsent("dev_object", tbDev);
					sessionMap.putIfAbsent(devCd, session);

					TbDev updateDev = new TbDev();
					updateDev.setId(tbDev.getId());
					String ip = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
					updateDev.setDevip(ip);
					updateDev.setOnlinetime(BitConverter.getCurrentTime());
					updateDev.setCurstate("在线");
					try {
						this.tbDevService.updateBySelective(updateDev);
					} catch (Exception e) {
						logger.error("updateBySelective", e);
					}
					TbDevparams tbDevparams = new TbDevparams();
					tbDevparams.setDevid(tbDev.getId());
					tbDevparams.setVisibility(Integer.valueOf(Integer.parseInt((String) visibilityInf
							.get("RESULT_VISIBILITY"))));
					try {
						this.tbDevparamsService.updateSelectiveByDevId(tbDevparams);
					} catch (Exception e) {
						logger.error("tbDevparamsService.update(tbDevparams)", e);
					}
					TbVisibHistory tbVisibHistory = new TbVisibHistory();
					tbVisibHistory.setDevid(tbDev.getId());
					tbVisibHistory.setVisibility(tbDevparams.getVisibility());
					tbVisibHistory.setDt(new Timestamp(new Date().getTime()));
					try {
						this.tbVisibHistoryService.add(tbVisibHistory);
					} catch (Exception e) {
						logger.error("tbVisibHistoryService.add(tbVisibHistory)", e);
					}
				}
			} else if (data[0] == 90) {
				Map<String, String> result = this.desunProtocal.parseHeaderFault(data);
				TbDev tbDev = (TbDev) session.getAttribute("dev_object");
				if (tbDev != null) {
					TbDevlog devLog = new TbDevlog();
					devLog.setCommtime(BitConverter.getCurrentTime());
					devLog.setDevid(tbDev.getId());
					devLog.setDevnm(tbDev.getDevnm());
					devLog.setDevno(tbDev.getDevno());
					devLog.setDevonlinetime(tbDev.getOnlinetime());
					devLog.setNotes((String) result.get("RESULT_ERRORCODE"));
					devLog.setPointid(Integer.valueOf(Integer.parseInt((String) result.get("RESULT_ERRORCODE"))));
					this.tbDevlogService.add(devLog);
				}
			} else {
				Object obj = session.getAttribute("data_received");
				if ((obj != null) && ((obj instanceof BlockingQueue))) {
					BlockingQueue<byte[]> blockingQeue = (BlockingQueue) obj;
					blockingQeue.add(data);
				}
			}
		}
	}

	public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
		if ((status == IdleStatus.READER_IDLE) && (session != null)) {
			logger.warn("session idle to close " + session.getId());
			session.close(true);
		}
	}
}