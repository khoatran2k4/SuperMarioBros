package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Window;
import java.awt.image.BufferStrategy;

import com.game.gfx.Windows;
import com.game.object.Block;
import com.game.object.Player;
import com.game.object.util.Handler;
import com.game.object.util.KeyInput;

public class Game extends Canvas implements Runnable {
	
	// Game CONSTANTS
	private static final int MILLIS_PER_SEC = 1000;
	private static final int NANOS_PER_SEC = 1000000000;
	private static final double NUM_TICKS = 60.0;
	private static final String NAME = "Super Mario Bros";
	
	private static final int WINDOW_WIDTH = 960;
	private static final int WINDOW_HEIGHT = 720;
	
	
	// Game VARIABLES
	private boolean running;
	
	// Game COMPONENTS
	private Thread thread;
	private Handler handler;
	
	public Game() {
		initialize();
	}
	
	public static void main(String args[]) {
		new Game();
	}
	
	private void initialize() {
		
		handler = new Handler();
		this.addKeyListener(new KeyInput(handler));
		
		// temporary code
		handler.setPlayer(new Player(32, 32, 1, handler));
		System.out.println(handler.getGameObjs());
		for(int i=0; i<20; i++) {
			handler.addObj(new Block(i*32, 32*10, 32, 32, 1));
		}
		for(int i=0; i<30; i++) {
			handler.addObj(new Block(i*32, 32*15, 32, 32, 1));
		}
		
	
		
		new Windows(WINDOW_WIDTH, WINDOW_HEIGHT, NAME, this);
		
		start();
	}
	
	private synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
		
	}
	
	private synchronized void stop(){
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime(); // Start time
		double amountOfTicks = NUM_TICKS;
		double ns = NANOS_PER_SEC / amountOfTicks; // nanos per tick
		double delta = 0; // compare with time for updating a tick
		long timer = System.currentTimeMillis(); //
		int frames = 0; // Number of frames per second
		int updates = 0; // Number of ticks per second
		double count = 1;
		
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns; // nanoSecond
			
//			System.out.println("delta: " + delta);
			
			while (delta >= 1) {
				tick();
				updates++;
				delta = delta - count * 15;
//				delta = delta - 1;
				count = count + 0.3;
			}
			
			if (running) {
				render();
				frames++;
			}
			
			if( System.currentTimeMillis() - timer > MILLIS_PER_SEC) {
				timer += MILLIS_PER_SEC;
				System.out.println("FPS: " + frames + " TPS: " + updates);
				frames = 0;
				updates = 0;
				
			}
		}
		stop();
		
	}
	
	public void tick() {
		handler.tick();
		
	}
	
	public void render() {
		BufferStrategy buf = this.getBufferStrategy();
		if (buf == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		// draw graphics
		Graphics g = buf.getDrawGraphics();
		
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
		
		handler.render(g);
		
		// clean for next frame
		g.dispose();
		buf.show();
		
		
	}
}
