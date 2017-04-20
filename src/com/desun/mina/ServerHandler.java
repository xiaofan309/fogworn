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
	/* 37: */@Autowired
	/* 38: */private TbDevlogService tbDevlogService;
	/* 39: */@Autowired
	/* 40: */private TbDevparamsService tbDevparamsService;
	/* 41: */@Autowired
	/* 42: */private TbVisibHistoryService tbVisibHistoryService;
	/* 43: */@Autowired
	/* 44: */private DesunProtocal desunProtocal;
	/* 45: */public static final String RECEIVE_DATA = "data_received";
	/* 46: 53 */private static ConcurrentHashMap<String, IoSession> sessionMap = new ConcurrentHashMap();

	/* 47: */
	/* 48: */public static IoSession getSession(String devcd)
	/* 49: */{
		/* 50: 59 */return (IoSession) sessionMap.get(devcd);
		/* 51: */}

	/* 52: */
	/* 53: */public void exceptionCaught(IoSession session, Throwable cause)
	/* 54: */throws Exception
	/* 55: */{
		/* 56: 65 */logger.error("session exceptionCaught", cause);
		/* 57: 66 */session.close(true);
		/* 58: */}

	/* 59: */
	/* 60: */public void sessionOpened(IoSession session)
	/* 61: */throws Exception
	/* 62: */{
		/* 63: 71 */logger.warn("connected: "
				+ session.getRemoteAddress().toString());
		/* 64: */}

	/* 65: */
	/* 66: */public void sessionClosed(IoSession session)
	/* 67: */throws Exception
	/* 68: */{
		/* 69: 89 */logger.warn("sessionClosed ");
		/* 70: 90 */if (session == null)
		/* 71: */{
			/* 72: 91 */logger.error("session closed: session is empty!!!");
			/* 73: 92 */return;
			/* 74: */}
		/* 75: 94 */TbDev dev = (TbDev) session.getAttribute("dev_object");
		/* 76: 95 */if ((dev == null) || (dev.getId() == null)) {
			/* 77: 96 */return;
			/* 78: */}
		/* 79: 98 */String devCd = dev.getDevno();
		/* 80: 99 */IoSession tmpSession = (IoSession) sessionMap.get(devCd);
		/* 81:101 */if ((tmpSession != null) && (tmpSession.equals(session)))
		/* 82: */{
			/* 83:102 */sessionMap.remove(devCd);
			/* 84:103 */TbDev updateDev = new TbDev();
			/* 85:104 */updateDev.setDevno(devCd);
			/* 86:105 */String ip = ((InetSocketAddress) session
					.getRemoteAddress()).getAddress().getHostAddress();
			/* 87:106 */updateDev.setDevip(ip);
			/* 88:107 */updateDev.setOfflinetime(BitConverter.getCurrentTime());
			/* 89:108 */updateDev.setCurstate("离线");
			/* 90: */try
			/* 91: */{
				/* 92:110 */this.tbDevService.updateSelectiveByDevno(updateDev);
				/* 93: */}
			/* 94: */catch (Exception e)
			/* 95: */{
				/* 96:112 */logger.error("tbDevService.queryByDevno", e);
				/* 97: */}
			/* 98: */}
		/* 99: */}

	/* 100: */
	/* 101: */public void messageReceived(IoSession session, Object message)
	/* 102: */throws Exception
	/* 103: */{
		/* 104:237 */if ((message instanceof IoBuffer))
		/* 105: */{
			/* 106:238 */IoBuffer buffer = (IoBuffer) message;
			/* 107:239 */byte[] data = new byte[buffer.limit()];
			/* 108:240 */buffer.get(data);
			/* 109:241 */if ((data[0] == -91) && (data[1] == -18))
			/* 110: */{
				/* 111:242 */Map<String, String> visibilityInf = this.desunProtocal
						.parseHeaderVisibility(data);
				/* 112:243 */String devCd = (String) visibilityInf
						.get("RESULT_MASTERADDR");
				/* 113:244 */logger.warn("receive CommAddr=" + devCd
						+ " visib="
						+ (String) visibilityInf.get("RESULT_VISIBILITY"));
				/* 114:245 */if ((devCd == null) || ("".equals(devCd)))
				/* 115: */{
					/* 116:246 */session.close(true);
					/* 117:247 */return;
					/* 118: */}
				/* 119:251 */TbDev tbDev = null;
				/* 120: */try
				/* 121: */{
					/* 122:253 */tbDev = this.tbDevService.queryByDevno(devCd);
					/* 123: */}
				/* 124: */catch (Exception e)
				/* 125: */{
					logger.error("tbDevService.queryByDevno", e);
					/* 127:256 */session.close(true);
					/* 128: */}
				/* 129:258 */if ((tbDev != null) && (tbDev.getId() != null))
				/* 130: */{
					/* 131:259 */if (sessionMap.containsKey(devCd))
					/* 132: */{
						/* 133:260 */IoSession oldSession = (IoSession) sessionMap
								.remove(devCd);
						/* 134:261 */if ((oldSession != null)
								&& (session != oldSession)
								&& (!session.containsAttribute("dev_object")))
						/* 135: */{
							/* 136:262 */oldSession
									.removeAttribute("dev_object");
							/* 137:263 */oldSession.close(true);
							/* 138: */}
						/* 139: */}
					/* 140:266 */session.setAttributeIfAbsent("dev_object",
							tbDev);
					/* 141:267 */sessionMap.putIfAbsent(devCd, session);
					/* 142: */
					/* 143:269 */TbDev updateDev = new TbDev();
					/* 144:270 */updateDev.setId(tbDev.getId());
					/* 145:271 */String ip = ((InetSocketAddress) session
							.getRemoteAddress()).getAddress().getHostAddress();
					/* 146:272 */updateDev.setDevip(ip);
					/* 147:273 */updateDev.setOnlinetime(BitConverter
							.getCurrentTime());
					/* 148:274 */updateDev.setCurstate("在线");
					/* 149: */try
					/* 150: */{
						/* 151:276 */this.tbDevService
								.updateBySelective(updateDev);
						/* 152: */}
					/* 153: */catch (Exception e)
					/* 154: */{
						/* 155:278 */logger.error("updateBySelective", e);
						/* 156: */}
					/* 157:281 */TbDevparams tbDevparams = new TbDevparams();
					/* 158:282 */tbDevparams.setDevid(tbDev.getId());
					/* 159:283 */tbDevparams.setVisibility(Integer
							.valueOf(Integer.parseInt((String) visibilityInf
									.get("RESULT_VISIBILITY"))));
					/* 160: */try
					/* 161: */{
						this.tbDevparamsService
								.updateSelectiveByDevId(tbDevparams);
						/* 163: */}
					/* 164: */catch (Exception e)
					/* 165: */{
						/* 166:287 */logger.error(
								"tbDevparamsService.update(tbDevparams)", e);
						/* 167: */}
					/* 168:290 */TbVisibHistory tbVisibHistory = new TbVisibHistory();
					/* 169:291 */tbVisibHistory.setDevid(tbDev.getId());
					/* 170:292 */tbVisibHistory.setVisibility(tbDevparams
							.getVisibility());
					/* 171:293 */tbVisibHistory.setDt(new Timestamp(new Date()
							.getTime()));
					/* 172: */try
					/* 173: */{
						/* 174:295 */this.tbVisibHistoryService
								.add(tbVisibHistory);
						/* 175: */}
					/* 176: */catch (Exception e)
					/* 177: */{
						/* 178:297 */logger.error(
								"tbVisibHistoryService.add(tbVisibHistory)", e);
						/* 179: */}
					/* 180: */}
				/* 181: */}
			/* 182:300 */else if (data[0] == 90)
			/* 183: */{
				/* 184:301 */Map<String, String> result = this.desunProtocal
						.parseHeaderFault(data);
				/* 185:302 */TbDev tbDev = (TbDev) session
						.getAttribute("dev_object");
				/* 186:303 */if (tbDev != null)
				/* 187: */{
					/* 188:304 */TbDevlog devLog = new TbDevlog();
					devLog.setCommtime(BitConverter.getCurrentTime());
					/* 190:306 */devLog.setDevid(tbDev.getId());
					/* 191:307 */devLog.setDevnm(tbDev.getDevnm());
					/* 192:308 */devLog.setDevno(tbDev.getDevno());
					/* 193:309 */devLog.setDevonlinetime(tbDev.getOnlinetime());
					/* 194:310 */devLog.setNotes((String) result
							.get("RESULT_ERRORCODE"));
					/* 195:311 */devLog
							.setPointid(Integer.valueOf(Integer
									.parseInt((String) result
											.get("RESULT_ERRORCODE"))));
					/* 196:312 */this.tbDevlogService.add(devLog);
					/* 197: */}
				/* 198: */}
			/* 199: */else
			/* 200: */{
				/* 201:315 */Object obj = session.getAttribute("data_received");
				/* 202:316 */if ((obj != null)
						&& ((obj instanceof BlockingQueue)))
				/* 203: */{
					/* 204:317 */BlockingQueue<byte[]> blockingQeue = (BlockingQueue) obj;
					/* 205:318 */blockingQeue.add(data);
					/* 206: */}
				/* 207: */}
			/* 208: */}
		/* 209: */}

	/* 210: */
	/* 211: */public void sessionIdle(IoSession session, IdleStatus status)
	/* 212: */throws Exception
	/* 213: */{
		/* 214:328 */if ((status == IdleStatus.READER_IDLE) &&
		/* 215:329 */(session != null))
		/* 216: */{
			/* 217:330 */logger
					.warn("session idle to close " + session.getId());
			session.close(true);
		}
	}
}