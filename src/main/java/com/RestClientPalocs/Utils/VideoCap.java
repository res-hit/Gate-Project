package com.RestClientPalocs.Utils;

import org.bytedeco.javacv.*;

import org.bytedeco.opencv.opencv_core.*;
import org.bytedeco.opencv.opencv_videoio.*;
//import org.opencv.core.Core;
//import org.opencv.videoio.VideoCapture;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.concurrent.*;

public class VideoCap {

//	static {
//		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//	}

	VideoCapture cap;
	Mat2Image mat2Img = new Mat2Image();
	private Semaphore mutex = new Semaphore(1);
	
	public VideoCap() {
		cap = new VideoCapture();
		cap.open(0);
	}

	public BufferedImage getOneFrame() {
		try {
			  mutex.acquire();
			  try {
				  cap.read(mat2Img.mat);
			    // do something
			  } finally {
			    mutex.release();
			  }
			} catch(InterruptedException ie) {
			  // ...
			}
		return mat2Img.getImage(mat2Img.mat);
	}
	
	public void close() {
		cap.close();
		return;
	} 
}
