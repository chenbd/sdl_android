package com.smartdevicelink.protocol;

import android.util.Log;

import com.smartdevicelink.protocol.enums.FrameDataControlFrameType;
import com.smartdevicelink.protocol.enums.SessionType;

public class SdlPacketFactory {

	/* 
	 * public SdlPacket(int version, boolean compression, int frameType,
			int serviceType, int frameInfo, int sessionId,
			int dataSize, int messageId, byte[] payload) {
	 */
	public static SdlPacket createStartSession(SessionType serviceType, int messageID, byte version, byte sessionID) {
		Log.d("Joey", "Creating start session: " + version);
		SdlPacket packet =  new SdlPacket(version,false,SdlPacket.FRAME_TYPE_CONTROL,
				serviceType.getValue(),SdlPacket.FRAME_INFO_START_SERVICE,sessionID,
				0,messageID,null);
		Log.d("Joey", "Creating start session packet : " + packet.toString());

		return packet;
	}

    public static SdlPacket createHeartbeat(SessionType serviceType, byte sessionID, byte version) {
    
    	return new SdlPacket(version,false,SdlPacket.FRAME_TYPE_CONTROL,
				serviceType.getValue(),FrameDataControlFrameType.Heartbeat.value(),sessionID,
				0,0,null);		//TODO check that 0 works for message Id. I can't find where it gets set otherwise

    }

	public static SdlPacket createHeartbeatACK(SessionType serviceType, byte sessionID, byte version) {
		return new SdlPacket(version,false,SdlPacket.FRAME_TYPE_CONTROL,
				serviceType.getValue(),FrameDataControlFrameType.HeartbeatACK.value(),sessionID,
				0,0,null);		//TODO check that 0 works for message Id. I can't find where it gets set otherwise
		}
	
	public static SdlPacket createStartSessionACK(SessionType serviceType, byte sessionID, int messageID, byte version) {
		
		return new SdlPacket(version,false,SdlPacket.FRAME_TYPE_CONTROL,
				serviceType.getValue(),FrameDataControlFrameType.StartSessionACK.value(),sessionID,
				0,messageID,null);

	}

	public static SdlPacket createStartSessionNACK(SessionType serviceType, byte sessionID, int messageID, byte version) {
		
		return new SdlPacket(version,false,SdlPacket.FRAME_TYPE_CONTROL,
				serviceType.getValue(),SdlPacket.FRAME_INFO_START_SERVICE_NAK,sessionID,
				0,messageID,null);
	}

	public static SdlPacket createEndSession(SessionType serviceType, byte sessionID, int messageID, byte version) {
		return new SdlPacket(version,false,SdlPacket.FRAME_TYPE_CONTROL,
				serviceType.getValue(),SdlPacket.FRAME_INFO_END_SERVICE,sessionID,
				0,messageID,null);
	}

	public static SdlPacket createSingleSendData(SessionType serviceType, byte sessionID,
			int dataLength, int messageID, byte version, byte[] payload) {
		
		return new SdlPacket(version,false,SdlPacket.FRAME_TYPE_SINGLE,
				serviceType.getValue(),0,sessionID,
				payload.length,messageID,payload);
	}

	public static SdlPacket createMultiSendDataFirst(SessionType serviceType, byte sessionID, 
			int messageID, byte version, byte[] payload) {
		
		return new SdlPacket(version,false,SdlPacket.FRAME_TYPE_FIRST,
				serviceType.getValue(),0,sessionID,
				8,messageID,payload);

	}

	public static SdlPacket createMultiSendDataRest(SessionType serviceType, byte sessionID,
			int dataLength, byte frameSequenceNumber, int messageID, byte version, byte[] payload,int offset,int length) {
		
		return new SdlPacket(version,false,SdlPacket.FRAME_TYPE_CONSECUTIVE,
				serviceType.getValue(),frameSequenceNumber,sessionID,
				length,messageID,payload,offset,length);
	}
	
	
	public static BinaryFrameHeader createBinaryFrameHeader(byte rpcType, int functionID, int corrID, int jsonSize) {
		BinaryFrameHeader msg = new BinaryFrameHeader();
		msg.setRPCType(rpcType);
		msg.setFunctionID(functionID);
		msg.setCorrID(corrID);
		msg.setJsonSize(jsonSize);
		
		return msg;
	}
	
}