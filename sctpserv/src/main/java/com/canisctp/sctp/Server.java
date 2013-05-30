package com.canisctp.sctp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import com.sun.nio.sctp.MessageInfo;
import com.sun.nio.sctp.SctpChannel;
import com.sun.nio.sctp.SctpServerChannel;

public class Server extends Thread {

	private Logger log = Logger.getLogger(Server.class);

//	@Value("#applicationProperties")
//	private int port;
	private int port = 8081;

	private SctpChannel sctpChannel;

	public Server() {
		super("SCTPServer");
	}

	/**
	 * Called on shutdown @See destroy-method
	 */
	public void shutdown() {

	}

	@Override
	public void run() {
		log.debug("SCTP Server startup");
		try 
		{
			SocketAddress serverSocketAddress = new InetSocketAddress(port);
			SctpServerChannel sctpServerChannel = SctpServerChannel.open().bind(serverSocketAddress);

			while ((sctpChannel = sctpServerChannel.accept()) != null) 
			{
//				final Association association = sctpChannel.association();
				
				MessageInfo messageInfo = sctpChannel.receive(ByteBuffer.allocate(64000), null, null);
				log.info(messageInfo);
			}
		} catch (IOException e) {
			log.error("Failed to setup SCTP server socket " + port, e);
		}

	}
	
	public static void main(String[] args) {
		Server s = new Server();
		s.start();
	}
}
