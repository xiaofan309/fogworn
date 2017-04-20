package com.desun.protocal;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.desun.mina.ServerHandler;
import com.fogworn.bean.TbDev;

@Component
public class FogWarnExinterface {
	
	private final static Logger logger = Logger.getLogger(FogWarnExinterface.class);
	private final static long TIME_OUT=8000; 
	private final static String CommTimeOut="通讯超时";
	@Autowired
	private DesunProtocal desunProtocal;
	
	private byte[] dataInterchange(TbDev dev,byte[] sendData){
		if(null==sendData){
			return null;
		}
		BlockingQueue<byte[]> blockingQueue = new LinkedBlockingQueue<byte[]>();
//		IoSession session=ServerHandler.getSession(dev.getDevno());
		IoSession session=ServerHandler.getSession(dev.getDevno());
		if(null==session){
			return null;
		}
		session.setAttribute(ServerHandler.RECEIVE_DATA, blockingQueue);
		logger.warn("sendData="+BitConverter.bytes2HexStr(sendData));
		session.write(IoBuffer.wrap(sendData));
		byte[] receiveData=null;
		try {
			receiveData = blockingQueue.poll(TIME_OUT, TimeUnit.MILLISECONDS);
			logger.warn("receiveData="+BitConverter.bytes2HexStr(receiveData));
		} catch (InterruptedException e) {
			receiveData=null;;
		}
		session.removeAttribute(ServerHandler.RECEIVE_DATA);
		return receiveData;
	}
	
	
	public String passDataTransType(TbDev dev,int order){
		byte[] sendData = desunProtocal.packDataTransType((byte)Long.parseLong(dev.getDevno()),order);
		byte[] receiveData= dataInterchange(dev,sendData);
		if(null==receiveData){
			return CommTimeOut;
		}
		return desunProtocal.parseResponse(receiveData, sendData) ;
	}
	
	
	public String nightAutoOn(TbDev dev,boolean order){
		byte[] sendData = desunProtocal.packNightAutoOn((byte)Long.parseLong(dev.getDevno()),order);
		byte[] receiveData= dataInterchange(dev,sendData);
		if(null==receiveData){
			return CommTimeOut;
		}
		return desunProtocal.parseResponse(receiveData, sendData) ;
	}
	
	
	public String visibilityDeal(TbDev dev,int order){
		byte[] sendData = desunProtocal.packVisibilityDeal((byte)Long.parseLong(dev.getDevno()),order);
		byte[] receiveData= dataInterchange(dev,sendData);
		if(null==receiveData){
			return CommTimeOut;
		}
		return desunProtocal.parseResponse(receiveData, sendData) ;
	}
	

	public String coverOn(TbDev dev,int flickerfrequency,int luminance,int guidlights,boolean tailenable,int taildelay){
		byte[] sendData = desunProtocal.packCoverOn((byte)Long.parseLong(dev.getDevno()),(byte)flickerfrequency,(byte)luminance,(byte)guidlights,tailenable,taildelay);
		byte[] receiveData= dataInterchange(dev,sendData);
		if(null==receiveData){
			return CommTimeOut;
		}
		return desunProtocal.parseResponse(receiveData, sendData) ;
	}
	
	
	public String coverOff(TbDev dev){
		byte[] sendData = desunProtocal.packCoverOff((byte)Long.parseLong(dev.getDevno()));
		byte[] receiveData= dataInterchange(dev,sendData);
		if(null==receiveData){
			return CommTimeOut;
		}
		return desunProtocal.parseResponse(receiveData, sendData) ;
	}
	
	
	public String passOn(TbDev dev,int flickerfrequency,int luminance,int guidlights,boolean tailenable,int taildelay){
		byte[] sendData = desunProtocal.packPassOn((byte)Long.parseLong(dev.getDevno()),(byte)flickerfrequency,(byte)luminance,(byte)guidlights,tailenable,taildelay);
		byte[] receiveData= dataInterchange(dev,sendData);
		if(null==receiveData){
			return CommTimeOut;
		}
		return desunProtocal.parseResponse(receiveData, sendData) ;
	}
	
	
	public String passOff(TbDev dev){
		byte[] sendData = desunProtocal.packPassOff((byte)Long.parseLong(dev.getDevno()));
		byte[] receiveData= dataInterchange(dev,sendData);
		if(null==receiveData){
			return CommTimeOut;
		}
		return desunProtocal.parseResponse(receiveData, sendData) ;
	}
	
	

	public String fogParam(TbDev dev,int flickerfrequency,int luminance,int guidlights,boolean tailenable,int taildelay){
		byte[] sendData = desunProtocal.packFogParam((byte)Long.parseLong(dev.getDevno()),(byte)flickerfrequency,(byte)luminance,(byte)guidlights,tailenable,taildelay);
		byte[] receiveData= dataInterchange(dev,sendData);
		if(null==receiveData){
			return CommTimeOut;
		}
		return desunProtocal.parseResponse(receiveData, sendData) ;
	}
	
	
	
	
	public String nightParam(TbDev dev,int flickerfrequency,int luminance,int guidlights,boolean tailenable,int taildelay){
		byte[] sendData = desunProtocal.packNightParam((byte)Long.parseLong(dev.getDevno()),(byte)flickerfrequency,(byte)luminance,(byte)guidlights,tailenable,taildelay);
		byte[] receiveData= dataInterchange(dev,sendData);
		if(null==receiveData){
			return CommTimeOut;
		}
		return desunProtocal.parseResponse(receiveData, sendData) ;
	}
	
	
	
	
	

	

}
