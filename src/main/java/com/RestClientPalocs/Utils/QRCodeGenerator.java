package com.RestClientPalocs.Utils;

//import com.github.sarxos.webcam.Webcam;
import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
//import com.google.zxing.client.j2se.MatrixToImageWriter;
//import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
//import com.google.zxing.qrcode.QRCodeWriter;
import com.RestClientPalocs.Utils.Security.Encrypter;

//import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
//import java.io.ByteArrayOutputStream;
//import java.io.File;
//import java.io.IOException;
//import java.nio.file.FileSystems;
//import java.nio.file.Path;
import java.util.concurrent.Exchanger;

public class QRCodeGenerator {
//	private static final String QR_CODE_IMAGE_PATH = "/home/rez/MyQRCode.png";
	public static final String QR_ENCRYPTION_SECRET = "palocsKeyPalocsKey!!";
	private static Exchanger<String> exchanger = new Exchanger<>();
//	private static BitMatrix bitMatrix;

/*	private static void generateQRCodeImage(String text, int width, int height, String filePath) throws Exception {
		QRCodeWriter qrWriter = new QRCodeWriter();
		String msg = Encrypter.encrypt(text, QR_ENCRYPTION_SECRET);

		if (msg != null) {
			bitMatrix = qrWriter.encode(msg, BarcodeFormat.QR_CODE, width, height);
			System.out.println("msg criptato : " + msg);
		} else {
			throw new Exception("errore todo");
		}

		Path path = FileSystems.getDefault().getPath(filePath);

		MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
	}*/

	/**
	 * This method takes the text to be encoded, the width and height of the QR
	 * Code, and returns the QR Code in the form of a byte array. The byteArray can
	 * be returned as body of an http response and can be converted to a BitMap and
	 * then shown in an ImageView (if working with android).
	 */
/*	private byte[] getQRCodeByteArray(String text, int width, int height) throws WriterException, IOException {
		QRCodeWriter qrCodeWriter = new QRCodeWriter();
		BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

		ByteArrayOutputStream pngOutStream = new ByteArrayOutputStream();
		MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutStream);
		byte[] pngData = pngOutStream.toByteArray();
		return pngData;
	}*/

	public static String decodeQRCode(BufferedImage bI) throws Exception {
		LuminanceSource source = new BufferedImageLuminanceSource(bI);
		BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
		try {
			Result result = new MultiFormatReader().decode(bitmap);
			String msg = Encrypter.decrypt(result.getText(), QR_ENCRYPTION_SECRET);
		//	System.out.print(msg);
			if (msg == null)
				throw new Exception("errore todo...");
			return msg;
		} catch (NotFoundException e) {
			e.printStackTrace();
			System.err.println("There is no QR code in the image");
			return null;
		}
	}

/*	private static BinaryBitmap toBinaryBitmap(BufferedImage image) {
		return new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(image)));
	}*/

	/*
	 * private static void getCamCapture(){ Webcam cam = Webcam.getDefault();
	 * cam.open(); Result result = null; BufferedImage image = null;
	 * 
	 * if(cam.isOpen()){ if((image = cam.getImage()) == null) return; try{ result =
	 * new MultiFormatReader().decode(toBinaryBitmap(image)); } catch
	 * (NotFoundException e) { e.printStackTrace(); return; } // if(result != null
	 * ){ // try{ // exchanger.exchange(result.getText()); // } catch
	 * (InterruptedException e) { // e.printStackTrace(); // return; // } // } } }
	 */

	public static String getResult() throws InterruptedException {
		return exchanger.exchange(null);
	}

	/*
	 * public static void main(String[] args){ try{
	 * generateQRCodeImage("Monet_1 1",350,350,QR_CODE_IMAGE_PATH); //
	 * getCamCapture(); System.out.println(getResult()); // Webcam cam =
	 * Webcam.getDefault(); // File f = new File("prova.png"); // cam.open(); //
	 * BufferedImage image = cam.getImage(); // try{ImageIO.write(image,"png",
	 * f);}catch(Exception e) {System.out.print("error\n");} //Path path =
	 * FileSystems.getDefault().getPath(QR_CODE_IMAGE_PATH);
	 * //MatrixToImageWriter.writeToPath(bitMatrix,"PNG",path);
	 * 
	 * // File file = new File(QR_CODE_IMAGE_PATH); // String decodedText =
	 * decodeQRCode(file); // if(decodedText==null){ //
	 * System.out.println("NO QR CODE FOUND IN THE IMAGE"); // }else //
	 * System.out.println("Decoded text : "+decodedText); }catch (WriterException
	 * we){ we.printStackTrace();
	 * System.out.println("Could not generate QR Code, WriterException :: " +
	 * we.getMessage()); }catch (IOException ioe){ ioe.printStackTrace();
	 * System.out.println("Could not generate QR Code, IOException :: " +
	 * ioe.getMessage()); } catch (Exception e) { e.printStackTrace(); }
	 * System.out.print("main teminato"); return;}
	 */
}
