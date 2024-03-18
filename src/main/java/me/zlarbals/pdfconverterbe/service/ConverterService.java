package me.zlarbals.pdfconverterbe.service;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.graphics.image.LosslessFactory;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ConverterService {

    public ByteArrayOutputStream convertPdf(MultipartFile file) throws IOException {
        //JPG 파일을 BufferedImage로 변환
        BufferedImage bufferedImage = ImageIO.read(file.getInputStream());

        //PDF 문서 생성
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // BufferedImage를 PDImageXObject로 변환
        PDImageXObject pdImage = LosslessFactory.createFromImage(document, bufferedImage);

        //PDF에 이미지 추가
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        contentStream.drawImage(pdImage,0,0,pdImage.getWidth(), pdImage.getHeight());
        contentStream.close();

        // PDF를 바이트 배열로 변환
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();

        return  byteArrayOutputStream;
    }
}
