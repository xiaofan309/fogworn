package com.desun.protocal;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class DesunProtocal {
	
	public final static String RESPONSE_SUCESS="应答信息";
	public final static String RESPONSE_NOACTION="应答无法执行指令";
	public final static String RESPONSE_ERROR="错误数据";
	public final static String RESPONSE_FAIL="失败";

	public final static int ProtocalLen=8; 
	public final static int DataBeginIndex=2; 
	public final static int DataLen=4; 
	
	public final static byte HeaderSend = (byte) 0xAA; 
	public final static byte HeaderReceive = (byte) 0xA5; 
	public final static byte HeaderFault = 0x5A; 
	public final static byte HeaderError = 0x55; 
//	public final static byte HeaderVisibility = (byte) 0x0A5; 
	
	
	public final static String RESULT_MASTERADDR="RESULT_MASTERADDR"; 
	public final static String RESULT_VISIBILITY="RESULT_VISIBILITY"; 
	public final static String RESULT_ERRORCODE="RESULT_ERRORCODE"; 
	public final static String RESULT_MASTER_ID="RESULT_ERRORCODE"; 
	public final static String RESULT_MASTER_CHAN="RESULT_ERRORCODE";
	public final static String RESULT_SLAVE_ID="RESULT_ERRORCODE";
	
	public final static byte Funcode_CoverOn=0x01; 
	public final static byte Funcode_CoverOff=0x02;

	
	
	public final static byte Funcode_PassOn=0x11; 
	public final static byte Funcode_PassOff=0x12; 
	public final static byte Funcode_FogParam=0x13; 
	public final static byte Funcode_NightParam=0x14; 
	
	

	public final static byte Funcode_NightAutoOn=0x09; 
	public final static byte Funcode_VisibilityDeal=0x0B;
	public final static byte Funcode_DataTransType=0x07; 
	

	
	
	private byte[] assemble(byte funcode,byte addr,byte[] payloaddata){
		byte[] ret = new byte[ProtocalLen];
		ret[0]= HeaderSend;
		ret[1]= funcode;
		System.arraycopy(payloaddata, 0, ret, DataBeginIndex, payloaddata.length);
		return ret;
	}
	

	
	
	

	public byte[] packCoverOn(byte addr,byte flickerfrequency,byte luminance,byte guidlights,boolean tailenable,int taildelay){
		byte[] ret = new byte[DataLen];
		int i=0;
		ret[i++]=flickerfrequency;
		ret[i++]=luminance;
		ret[i++]=guidlights;
		ret[i++]=(byte)(tailenable?((0x01<<7)|taildelay&0xFF):taildelay) ;
		return assemble(Funcode_CoverOn,addr,ret);
	}
	
	
	public byte[] packCoverOff(byte addr){
		byte[] ret = new byte[DataLen];
		Arrays.fill(ret, (byte)0);
		return assemble(Funcode_CoverOff,addr,ret);
	}
	


	public byte[] packPassOn(byte addr,byte flickerfrequency,byte luminance,byte guidlights,boolean tailenable,int taildelay){
		byte[] ret = new byte[DataLen];
		int i=0;
		ret[i++]=flickerfrequency;
		ret[i++]=luminance;
		ret[i++]=guidlights;
		ret[i++]=(byte)(tailenable?((0x01<<7)|taildelay&0xFF):taildelay) ;
		return assemble(Funcode_PassOn,addr,ret);
	}
	

	public byte[] packPassOff(byte addr){
		byte[] ret = new byte[DataLen];
		Arrays.fill(ret, (byte)0);
		return assemble(Funcode_PassOff,addr,ret);
	}
	
	
	
	public byte[] packFogParam(byte addr,byte flickerfrequency,byte luminance,byte guidlights,boolean tailenable,int taildelay){
		byte[] ret = new byte[DataLen];
		Arrays.fill(ret, (byte)0);
		int i=0;
		ret[i++]=flickerfrequency;
		ret[i++]=luminance;
		ret[i++]=guidlights;
		ret[i++]=(byte)(tailenable?((0x01<<7)|taildelay&0xFF):taildelay) ;
		return assemble(Funcode_FogParam,addr,ret);
	}
	
	
	
	public byte[] packNightParam(byte addr,byte flickerfrequency,byte luminance,byte guidlights,boolean tailenable,int taildelay){
		byte[] ret = new byte[DataLen];
		Arrays.fill(ret, (byte)0);
		int i=0;
		ret[i++]=flickerfrequency;
		ret[i++]=luminance;
		ret[i++]=guidlights;
		ret[i++]=(byte)(tailenable?((0x01<<7)|taildelay&0xFF):taildelay) ;
		return assemble(Funcode_NightParam,addr,ret);
	}
	
	
	
	


	public byte[] packNightAutoOn(byte addr,boolean order){
		byte[] ret = new byte[DataLen];
		Arrays.fill(ret, (byte)0);
		ret[0]= order?((byte)0x01):((byte)0x00);
		return assemble(Funcode_NightAutoOn,addr,ret);
	}
	

	public byte[] packVisibilityDeal(byte addr,int order){
		byte[] ret = new byte[DataLen];
		Arrays.fill(ret, (byte)0);
		ret[0]= (byte) order;
		return assemble(Funcode_VisibilityDeal,addr,ret);
	}
	

	public byte[] packDataTransType(byte addr,int order){
		byte[] ret = new byte[DataLen];
		Arrays.fill(ret, (byte)0);
		ret[0]= (byte) order;
		return assemble(Funcode_DataTransType,addr,ret);
	}
	

	public Map<String,String> parseHeaderVisibility(byte[] frame){
		Map<String,String> ret = new HashMap<String,String>();
		ret.put(RESULT_MASTERADDR, String.valueOf(frame[frame.length-1]&0xFF));
		ret.put(RESULT_VISIBILITY, ""+(( (frame[DataBeginIndex]<<8) & 0xFF00)|(frame[DataBeginIndex+1])&0xFF));
		return ret;
	}
	
	
	public Map<String,String> parseHeaderFault(byte[] frame){
		Map<String,String> ret = new HashMap<String,String>();
		ret.put(RESULT_ERRORCODE, frame[frame.length-1]+"");
		int i=DataBeginIndex;
		ret.put(RESULT_MASTER_ID, String.valueOf(((frame[i++]<<8)&0xFF00)|(frame[i++]&0xFF)));
		ret.put(RESULT_MASTER_CHAN, String.valueOf(frame[i++]&0xFF));
		ret.put(RESULT_SLAVE_ID, String.valueOf(frame[i++]&0xFF));
		return ret;
	}
	
	
	public String parseResponse(byte[] frame,byte[] sampledata){
		String ret = RESPONSE_FAIL;
		byte[] data = Arrays.copyOfRange(frame, DataBeginIndex, frame.length-2); 
		
		final byte[] switchFuncodes = new byte[]{0x01,0x11,0x02,0x12}; 
		Arrays.sort(switchFuncodes); 
		
		switch(frame[0]){ 
			case HeaderReceive: {
					if((data[0]&0xFF)==0xEF){  
						byte[] data2=Arrays.copyOfRange(data, 1, data.length);
						byte[] sample = new byte[data2.length];
						Arrays.fill(sample, (byte)0);
						if(Arrays.equals(data, sample)){
							ret=RESPONSE_NOACTION;
						}
					}else if(Arrays.binarySearch(switchFuncodes, sampledata[1])>=0){ 
						if(frame[1]==sampledata[1]){ 
							ret=RESPONSE_SUCESS;
						}else if(data[0]==0xF1){ 
							
						}else if(data[0]==0xF2){  
							
						}
					}else{
						byte[] sample = Arrays.copyOfRange(sampledata, DataBeginIndex, sampledata.length-2); 
						if(Arrays.equals(data, sample)){
							ret=RESPONSE_SUCESS;
						}
					}
				};break;
			case HeaderError: {
					byte[] sample = Arrays.copyOfRange(sampledata, 3, sampledata.length);
					if(Arrays.equals(data, sample)){
						ret=RESPONSE_ERROR;
					}
				};break;
		}
		return ret;
	}
	
}
