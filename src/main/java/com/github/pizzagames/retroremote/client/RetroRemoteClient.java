package com.github.pizzagames.retroremote.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class RetroRemoteClient implements Runnable {
	
	final Logger logger = LoggerFactory.getLogger(RetroRemoteClient.class);

	public RetroRemoteClient(final List args) {
		setupLogger();
		logger.info("Initializing retro remote client.");
	}

	private void setupLogger() {
		//TODO
	}

	public void run() {

	}

	public static void main(String[] args) {
		new RetroRemoteClient(Arrays.asList(args)).run();		
	}
}
