package com.desun.protocal;

import java.nio.ByteBuffer;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.desun.util.CodecUtil;

@Component
//@Scope("prototype")
public class FogWarnProtocal {
	private final static Logger logger = Logger.getLogger(FogWarnProtocal.class);
	
	private static class ProtocalStruct{
		public final static int BroadCastAddr = 0xFFFF;
		private final static Logger logger = Logger.getLogger(ProtocalStruct.class);
		private final static byte[] header = new byte[]{(byte) 0xFE,(byte) 0xEF}; 
		private byte[] addr = new byte[]{0,0}; 
		private byte cmd = 0; 
		private byte data=0; 
		private byte[] crc = new byte[]{0,0}; 
		
		private byte[] frame = new byte[8]; 
		private ByteBuffer byteBuffer = ByteBuffer.allocate(8);
		
		public ProtocalStruct(){}
		public ProtocalStruct(int addr,int cmd,int data){
			setAddr(addr);
			setCmd(cmd);
			setData(data);
		}
		
		
		
		public void setAddr(int addr){
			this.addr=BitConverter.intToBytes(addr, 2);
		}
		
		public int getAddr() throws Exception{
			return BitConverter.bytes2Integer(this.addr, 0, 2);
		}
		
		public void setCmd(int cmd){
			this.cmd = (byte)cmd;
		}
		public int getCmd(){
			return cmd&0xFF;
		}
		
		public void setData(int data){
			this.data=(byte) data;
		}
		
		public int getData(){
			return data&0xFF;
		}
		
		
		private byte[] getRawFrame(){
			byteBuffer.clear();
			byteBuffer.put(header);
			byteBuffer.put(addr);
			byteBuffer.put(cmd);
			byteBuffer.put(data);
			byteBuffer.put(crc);
			byteBuffer.flip();
			byteBuffer.get(frame);
			return frame;
		}
		
		
		public byte[] getCrcValidFrame(){
			byte[] tempFrame=getRawFrame(); 
			this.crc = CodecUtil.crc16Bytes(Arrays.copyOfRange(tempFrame, 2, tempFrame.length-2));
//			BitConverter.reverseBytes(this.crc);
//			int tmpcrc =BitConverter.crc16Check(tempFrame, 2, tempFrame.length-2);
//			this.crc=BitConverter.intToBytes(tmpcrc, 2);
			return getRawFrame(); 
		}
		
		
		public boolean checkFrame(byte[] receiveData){
			
			if((null==receiveData)||(receiveData.length!=8)){
				return false;
			}
			try {
				
				if(!Arrays.equals(this.header, Arrays.copyOfRange(receiveData, 0, 2))){
					return false;
				}
				
				
				byte[] expectCrc=CodecUtil.crc16Bytes(Arrays.copyOfRange(receiveData, 2, receiveData.length-2));
	//			int expectCrc=BitConverter.crc16Check(receiveData, 2, receiveData.length-2);
				byte[] curCrc;
			
				curCrc = Arrays.copyOfRange(receiveData, 6, receiveData.length);
				
				if(!Arrays.equals(expectCrc, curCrc)){
					return false;
				}
			} catch (Exception e) {
				logger.error("curCrc = BitConverter.bytes2Integer(receiveData, 6, 2);", e);
				return false;
			}
			
			setFrame(receiveData);
			return true;
		}
		
		
		private void setFrame(byte[] receiveData){
			this.frame = receiveData;
			int pos=0;
			System.arraycopy(frame, pos, header, 0, header.length);
			pos=pos+header.length;
			System.arraycopy(frame, pos, addr, 0, addr.length);
			pos+=addr.length;
			cmd=frame[pos++];
			data=frame[pos++];
			System.arraycopy(frame, pos, crc, 0, crc.length);
			pos+=crc.length;
			logger.warn("setFrame length="+pos);
		}
	}
	
	
	 
	public byte[] encodeStartSystem(){
		ProtocalStruct protocal = new ProtocalStruct(ProtocalStruct.BroadCastAddr,0x01,0x00);
		return protocal.getCrcValidFrame();
	}
	
	
	public byte[] encodeStopSystem(){
		ProtocalStruct protocal = new ProtocalStruct(ProtocalStruct.BroadCastAddr,0x02,0x00);
		return protocal.getCrcValidFrame();
	}
	
	
	
	public byte[] encodeSetFlickerfrequency(int freq){
		if((freq>3)||(freq<1)){
			return null;
		}
		ProtocalStruct protocal = new ProtocalStruct(ProtocalStruct.BroadCastAddr,0x03,freq);
		return protocal.getCrcValidFrame();
	}
	
	
	
	public byte[] encodeSetGuidlights(int count){
		if((count>5)||(count<3)){
			return null;
		}
		ProtocalStruct protocal = new ProtocalStruct(ProtocalStruct.BroadCastAddr,0x04,count);
		return protocal.getCrcValidFrame();
	}
	
	
	public byte[] encodeSetLuminance(int grade){
		if((grade>9)||(grade<1)){
			return null;
		}
		int luminance=0;
		switch(grade){
		case 1: luminance=0x01;break;
		case 2: luminance=0x10;break;
		case 3: luminance=0x14;break;
		case 4: luminance=0x1E;break;
		case 5: luminance=0x28;break;
		case 6: luminance=0x32;break;
		case 7: luminance=0x3C;break;
		case 8: luminance=0x46;break;
		case 9: luminance=0x50;break;
		}
		ProtocalStruct protocal = new ProtocalStruct(ProtocalStruct.BroadCastAddr,0x05,luminance);
		return protocal.getCrcValidFrame();
	}
	
	
	
	public byte[] encodeLuminancethreshold(int luminancethreshold){
		if((luminancethreshold>3)||(luminancethreshold<1)){
			return null;
		}
		ProtocalStruct protocal = new ProtocalStruct(ProtocalStruct.BroadCastAddr,0x06,luminancethreshold);
		return protocal.getCrcValidFrame();
	}
	
	
	
	public byte[] encodeNightEnable(boolean nightEnable){
		int value;
		if(nightEnable){
			value=1;
		}else{
			value=2;
		}
		
		ProtocalStruct protocal = new ProtocalStruct(ProtocalStruct.BroadCastAddr,0x08,value);
		return protocal.getCrcValidFrame();
	}
	
	
	
	private boolean translateResult(int expectCmd,byte[] receiveData){
		boolean result=false;
		ProtocalStruct protocal = new ProtocalStruct();
		
		if((protocal.checkFrame(receiveData))&&(expectCmd==protocal.getCmd())&&(0x01==protocal.getData())){
			result =true;
		}
		return result;
	}
	
	
	public boolean decodeStartSystem(byte[] receiveData){
		return translateResult(0x01,receiveData);
	}
	
	
	public boolean decodeStopSystem(byte[] receiveData){
		return translateResult(0x02,receiveData);
	}
	
	
	public boolean decodeSetFlickerfrequency(byte[] receiveData){
		return translateResult(0x03,receiveData);
	}
	
	
	public boolean decodeSetGuidlights(byte[] receiveData){
		return translateResult(0x04,receiveData);
	}
	
	
	
	public boolean decodeSetLuminance(byte[] receiveData){
		return translateResult(0x05,receiveData);
	}
	
	
	
	public boolean decodeLuminancethreshold(byte[] receiveData){
		return translateResult(0x06,receiveData);
	}
	
	
	
	public boolean decodeNightEnable(byte[] receiveData){
		return translateResult(0x08,receiveData);
	}
	
	public static class ActiveMessage{
		
		public final static String VISIBITY = "能见度"; 
		public final static String DEV_ERROR = "故障"; 
		public final static String ROAD_ERROR = "路障"; 
		public final static String WORK_POINT = "产品正常工作数量"; 
		
		private String cmdType; 
		private int value;
		private int commAddr; 
		
		public ActiveMessage(){}
		public ActiveMessage(String cmdType,int value){
			this.cmdType=cmdType;
			this.value=value;
		}
		
		public String getCmdType() {
			return cmdType;
		}
		public void setCmdType(String cmdType) {
			this.cmdType = cmdType;
		}
		public int getValue() {
			return value;
		}
		public void setValue(int value) {
			this.value = value;
		}
		public int getCommAddr() {
			return commAddr;
		}
		public void setCommAddr(int commAddr) {
			this.commAddr = commAddr;
		}
		
		
	}
	
	/**
	 * 
	 */
	public ActiveMessage decodeActive(byte[] receiveData){
		ActiveMessage result = null;
		try{
			ProtocalStruct protocal = new ProtocalStruct();
			if(protocal.checkFrame(receiveData)){
				int value = ((protocal.getCmd()<<8)&0xFF00)|(0xFF&protocal.getData());
				if((protocal.getAddr()>=0xFFF0)&&(protocal.getAddr()<=0xFFFE)){ // 当前能见度显示值
					result=new ActiveMessage(ActiveMessage.VISIBITY,value);
					result.setCommAddr(protocal.getAddr());
				}/*else{
					switch(value){
					case 0x0700: result=new ActiveMessage(ActiveMessage.DEV_ERROR,protocal.getAddr());break;
					case 0x0800: result=new ActiveMessage(ActiveMessage.ROAD_ERROR,protocal.getAddr());break;
					case 0x0900: result=new ActiveMessage(ActiveMessage.WORK_POINT,protocal.getAddr());break;
					}
				}*/
			}
			return result;
		}catch(Exception e){
			logger.error("decodeActive", e);
			return null;
		}
	}
	
	/**
	 * 
	 * @param receiveData
	 * @return
	 */
	public boolean checkFrame(byte[] receiveData){
		try{
			ProtocalStruct protocal = new ProtocalStruct();
			if(protocal.checkFrame(receiveData)){
				return true;
			}else{
				return false;
			}
		}catch(Exception e){
			logger.error("checkFrame", e);
			return false;
		}
	}
	
}
