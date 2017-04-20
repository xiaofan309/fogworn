package com.desun.mina;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

public class FogCumulativeProtocolEncoder
  extends ProtocolEncoderAdapter
{
  public void encode(IoSession session, Object message, ProtocolEncoderOutput out)
    throws Exception
  {}
}