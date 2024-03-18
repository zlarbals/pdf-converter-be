package me.zlarbals.pdfconverterbe.controller;

import lombok.RequiredArgsConstructor;
import me.zlarbals.pdfconverterbe.service.ConverterService;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
@RequestMapping("/convert")
public class ConverterController {

    private final ConverterService converterService;

    @PostMapping("/convertToPdf")
    public ResponseEntity<byte[]> convertPdf(@RequestParam MultipartFile file) throws IOException {

        ByteArrayOutputStream byteArrayOutputStream = converterService.convertPdf(file);

        // HTTP 응답으로 PDF 파일 전송
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(byteArrayOutputStream.toByteArray());
    }

}
