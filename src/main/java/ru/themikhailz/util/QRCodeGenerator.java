package ru.themikhailz.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Hashtable;

public class QRCodeGenerator {

    public static byte[] generateQRCodeImage(String data, int size) {

        QRCodeWriter barcodeWriter = new QRCodeWriter();

        Hashtable<EncodeHintType, String> hashtable = new Hashtable<>();
        hashtable.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        try (ByteArrayOutputStream qrImageByteArray = new ByteArrayOutputStream()) {

            BitMatrix bitMatrix = barcodeWriter.encode(data, BarcodeFormat.QR_CODE, size, size, hashtable);

            MatrixToImageWriter.writeToStream(bitMatrix, "jpg", qrImageByteArray);

            return qrImageByteArray.toByteArray();
        } catch (WriterException | IOException e) {
            throw new RuntimeException("Не удалось преобразовать строку в QR-код " + e);
        }
    }
}