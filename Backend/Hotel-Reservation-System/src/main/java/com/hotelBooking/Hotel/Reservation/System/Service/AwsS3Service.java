package com.hotelBooking.Hotel.Reservation.System.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.springframework.beans.factory.annotation.Value;
import com.hotelBooking.Hotel.Reservation.System.Exception.OurException;

import java.io.InputStream;

@Service
public class AwsS3Service {

    private final String bucketName = "hote-booking-system-images";


    @Value("${aws.s3.access.key}")
    private String awsS3AccessKey;

    @Value("${aws.s3.secret.key}")
    private String awsS3SecretKey;

    public String saveImageToS3(MultipartFile photo){
        String s3LocationImage = null;

        try(InputStream inputStream = photo.getInputStream()){
            String s3FileName = photo.getOriginalFilename();

            BasicAWSCredentials awsCredentials = new BasicAWSCredentials(awsS3AccessKey, awsS3SecretKey);

            AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
            .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
            .withRegion(Regions.EU_NORTH_1)
            .build();


            ObjectMetadata metadata = new ObjectMetadata();

            metadata.setContentType(photo.getContentType());

            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, s3FileName, inputStream, metadata);
            s3Client.putObject(putObjectRequest);

            return "http://" + bucketName + ".s3.amazonaws.com/" + s3FileName;

        } catch (Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }


}
